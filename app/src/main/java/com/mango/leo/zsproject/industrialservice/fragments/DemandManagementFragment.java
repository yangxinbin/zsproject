package com.mango.leo.zsproject.industrialservice.fragments;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.show.ZhaoShanDetailActivity;
import com.mango.leo.zsproject.industrialservice.adapte.DemandManagementAdapter;
import com.mango.leo.zsproject.industrialservice.bean.DemandManagementBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.AllAndCreatedPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.industrialservice.show.MatchActivity;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.GlideImageLoader;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;
import com.mango.leo.zsproject.utils.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class DemandManagementFragment extends Fragment {//
    @Bind(R.id.recycle_view11)
    RecyclerView recycleView11;
    @Bind(R.id.refresh11)
    SmartRefreshLayout refresh11;
    private LinearLayoutManager mLayoutManager;
    private DemandManagementAdapter adapter;
    private ConstraintLayout h;
    private LinearLayout allPlanLayout, addPlanLayout;
    private Banner banner, banner2;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private ArrayList<DemandManagementBean> mData, mDataAll;
    private String nowCity, nowDistrict;
    private int page = 0;
    private boolean isFirstEnter = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.demandmanagement, container, false);
        ButterKnife.bind(this, view);
        sharedPreferences = getActivity().getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.v("sssss", "-sharedPreferences.getString(\"type\",\"\")--" + sharedPreferences.getString("type", ""));
        if (sharedPreferences.getString("type", "").equals("no")) {
            Log.v("sssss", "-sharedPreferences.getSt");
            recycleView11.setVisibility(View.GONE);
            View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.header2, container, false);
            ImageView imageVie = view1.findViewById(R.id.imageVie);
            ImageView imageVie1 = view1.findViewById(R.id.imageVie9);
            TextView phCall = view1.findViewById(R.id.textView_phone);
            phCall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callPhone();
                }
            });
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);//饱和度 0灰色 100过度彩色，50正常
            ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
            imageVie.setColorFilter(filter);
            imageVie1.setColorFilter(filter);
            banner2 = (Banner) view1.findViewById(R.id.imageVieb);
            List<String> pathsImage = new ArrayList<>();
            List<String> pathsTitle = new ArrayList<>();
            pathsImage.add(getResourcesUri(R.drawable.wechat));
            pathsImage.add(getResourcesUri(R.drawable.wechat2));
            pathsTitle.add("");
            pathsTitle.add("");
            banner2.setImages(pathsImage)
                    .setBannerTitles(pathsTitle)
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                    .setImageLoader(new GlideImageLoader())
                    // .setOnBannerClickListener(this)
                    .start();
            return view1;
        }
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleView11.setLayoutManager(mLayoutManager);
        recycleView11.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recycleView11.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter = new DemandManagementAdapter(getActivity().getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleView11.removeAllViews();
        loadTenantMes(page);
        EventBus.getDefault().register(this);//在initHeader()之前
        initHeader();
        initAllPlanButton();
        recycleView11.setAdapter(adapter);
        refreshAndLoadMore();
        return view;
    }
    private void refreshAndLoadMore() {
        refresh11.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mDataAll != null) {
                            mDataAll.clear();
                        }
                        if (mData != null) {
                            mData.clear();
                        }
                        page = 0;
                        Log.v("zzzzzzzzz", "-------onRefresh-------" + page);
                        loadTenantMes(page);
                        refreshLayout.finishRefresh();
                    }
                }, 500);
            }
        });
        refresh11.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        Log.v("zzzzzzzzz", "-------onLoadMore-------" + page);
                        loadTenantMes(page);
                        refreshLayout.finishLoadMore();

                    }
                }, 500);
            }
        });
        refresh11.setRefreshHeader(new ClassicsHeader(getActivity()));
        refresh11.setHeaderHeight(50);

        //触发自动刷新
        if (isFirstEnter) {
            isFirstEnter = false;
            //refresh.autoRefresh();
        } else {
            //mAdapter.refresh(initData());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        //nowProvince = String.valueOf(bean.getResponseObject().getLocation().getProvince());
        nowCity = String.valueOf(bean.getResponseObject().getLocation().getCity());
        nowDistrict = String.valueOf(bean.getResponseObject().getLocation().getDistrict());
    }

    private void loadTenantMes(final int page) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.v("zzzzzzzzz", "----url-------" + Urls.HOST + "/business-service/tool/list/matching/indexes" + "?token=" + sharedPreferences.getString("token", "") + "&page=" + page);
                HttpUtils.doGet(Urls.HOST + "/business-service/tool/list/matching/indexes" + "?token=" + sharedPreferences.getString("token", "") + "&page=" + page, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            final List<DemandManagementBean> beanList = ProjectsJsonUtils.readJsonTenantListBeans(response.body().string(), "content");//data是json字段获得data的值即对象数组
                            Log.v("zzzzzzzzz", "-----1--------" + beanList.size());
                            if (beanList.size() == 0) {
                                mHandler.sendEmptyMessage(2);
                            } else {
                                mHandler.sendEmptyMessage(1);
                            }
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    addTenant(beanList, page);
                                }
                            });
                    /*ResponseListBeanList.clear();
                    ResponseListBeanList.addAll(beanList);*/
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(0);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                        }
                    }
                });
            }
        }).start();
    }

    private void addTenant(final List<DemandManagementBean> beanList, final int page) {
        Log.v("zzzzzzzzz", page + "-------3------" + beanList.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (beanList == null || beanList.size() == 0) {
                    AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));

                }
                if (mData == null && mDataAll == null) {
                    mData = new ArrayList<DemandManagementBean>();
                    mDataAll = new ArrayList<DemandManagementBean>();
                }
                if (mDataAll != null) {
                    mDataAll.clear();
                }
                mDataAll.addAll(beanList);
                if (page == 0) {
                    for (int i = 0; i < mDataAll.size(); i++) {//
                        mData.add(mDataAll.get(i)); //一次显示page= ? 20条数据
                    }
                    Log.v("zzzzzzzzz", "----4---------" + mData.size());
                    if (mData != null) {
                        adapter.setmDate(mData);
                    }
                } else {
                    if (mDataAll != null) {
                        //加载更多
                        int i;
                        for (i = 0; i < mDataAll.size(); i++) {
                            if (mDataAll == null) {
                                return;//一开始断网报空指针的情况
                            }
                            adapter.addItem(mDataAll.get(i));//addItem里面记得要notifyDataSetChanged 否则第一次加载不会显示数据
                        }
                    }
                }
            }
        });
    }

    private final DemandManagementFragment.MyHandler mHandler = new DemandManagementFragment.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<DemandManagementFragment> mActivity;

        public MyHandler(DemandManagementFragment activity) {
            mActivity = new WeakReference<DemandManagementFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DemandManagementFragment activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(getActivity(), getResources().getString(R.string.load_error));
                        break;
                    case 1:
                        AppUtils.showToast(getActivity(), "获取匹配信息成功");
                        /*List<IntroductionBean> list = new ArrayList<>();
                        for (int i=0;i<cityBean.getResponseObject().getIntroduction().size();i++){
                            list.add(cityBean.getResponseObject().getIntroduction().get(i));
                        }*/
                        //addZhaoShang();
                        break;
                    case 2:
                        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
                    default:
                        break;
                }
            }
        }
    }

    private void initAllPlanButton() {
        /*mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
                TextView tv = (TextView)view;
                tv.setGravity(android.view.Gravity.CENTER);   //设置居中
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent){}
        });*/
        allPlanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AllAndCreatedPlanActivity.class);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                allPlanLayout, getString(R.string.transition_news_img));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                //getActivity().finish();
            }
        });
        addPlanLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CardFirstItemActivity.class);
                editor.putString("projectId", "").commit();
                intent.putExtra("DemandManagementFragment", true);
                EventBus.getDefault().removeAllStickyEvents();
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                allPlanLayout, getString(R.string.transition_news_img));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                //getActivity().finish();
            }
        });
    }

    private void initHeader() {
        //渲染header布局
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header, null);
        h = (ConstraintLayout) header.findViewById(R.id.header);
        addPlanLayout = (LinearLayout) header.findViewById(R.id.r1);
        allPlanLayout = (LinearLayout) header.findViewById(R.id.r2);
        banner = (Banner) header.findViewById(R.id.imageView);
        TextView t_mes = (TextView) header.findViewById(R.id.textView_city_zhaoshang);
        t_mes.setText(nowCity + nowDistrict + "招商信息");
        List<String> pathsImage = new ArrayList<>();
        List<String> pathsTitle = new ArrayList<>();
        pathsImage.add(getResourcesUri(R.drawable.wechat));
        pathsImage.add(getResourcesUri(R.drawable.wechat2));
        pathsTitle.add("");
        pathsTitle.add("");
        banner.setImages(pathsImage)
                .setBannerTitles(pathsTitle)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setImageLoader(new GlideImageLoader())
                // .setOnBannerClickListener(this)
                .start();

        //mSpinner = (Spinner) header.findViewById(R.id.spinnerState);
        //String[] arrays = new String[]{"全部招商计划", "项目"};
        //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(),
        //        android.R.layout.simple_spinner_dropdown_item, arrays);
        //mSpinner.setAdapter(arrayAdapter);
        //createButton = (Button) header.findViewById(R.id.create_button);
        //设置banner的高度为手机屏幕的四分之一
        //mBanner.setLayoutParams(new Banner.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 800));
        ConstraintLayout.LayoutParams layoutParam = new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParam.setMargins(0, 0, 0, 20);
        h.setLayoutParams(layoutParam);

        //设置headerview
        //h.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));//除top隐藏headerview
        //ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Arrays.asList(arrays));
        //arrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        //mSpinner.setAdapter(arrayAdapter1);
        adapter.setHeaderView(h);
    }

    private String getResourcesUri(@DrawableRes int id) {
        Resources resources = getResources();
        String uriPath = ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                resources.getResourcePackageName(id) + "/" +
                resources.getResourceTypeName(id) + "/" +
                resources.getResourceEntryName(id);
        return uriPath;
    }

    private DemandManagementAdapter.OnItemnewsClickListener mOnItemClickListener = new DemandManagementAdapter.OnItemnewsClickListener() {

        @Override
        public void onItemClick(View view, int position) {
            position = position - 1; //配对headerView
            Log.v("oooooooooo", adapter.getItem(position).getContent().get(position % 20).getId() + "****onItemClick1***点击第" + position);
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), ZhaoShanDetailActivity.class);
            intent.putExtra("FavouriteId", adapter.getItem(position).getContent().get(position % 20).getProject().getId());
            startActivity(intent);
        }

        @Override
        public void onItemClick1(View view, int position) {
            Log.v("oooooooooo", "****onItemClick2***点击第" + position);
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), MatchActivity.class);
            editor.putString("match_id", adapter.getItem(position).getContent().get(position % 20).getId()).commit();
            intent.putExtra("match_name", adapter.getItem(position).getContent().get(position % 20).getProject().getName());
            intent.putExtra("which", 0);
            startActivity(intent);
        }

        @Override
        public void onItemClick2(View view, int position) {
            Log.v("oooooooooo", "****onItemClick2***点击第" + position);
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), MatchActivity.class);
            editor.putString("match_id", adapter.getItem(position).getContent().get(position % 20).getId()).commit();
            intent.putExtra("match_name", adapter.getItem(position).getContent().get(position % 20).getProject().getName());
            intent.putExtra("which", 1);
            startActivity(intent);
        }

        @Override
        public void onItemClick3(View view, int position) {
            Log.v("oooooooooo", "****onItemClick2***点击第" + position);
            position = position - 1; //配对headerView
            if (mData.size() <= 0) {
                return;
            }
            Intent intent = new Intent(getActivity(), MatchActivity.class);
            editor.putString("match_id", adapter.getItem(position).getContent().get(position % 20).getId()).commit();
            intent.putExtra("match_name", adapter.getItem(position).getContent().get(position % 20).getProject().getName());
            intent.putExtra("which", 2);
            startActivity(intent);
        }

        @Override
        public void onCancelingMatchClick(View view, int position) {
            Log.v("aaaaa", "clock__1");
            Log.v("oooooooooo", "****onCancelingMatchClick***点击第" + position);
        }

       /* @Override
        public void onDeleteClick(View view, int position) {
            Log.v("oooooooooo", "****onDeleteClick***点击第" + position);
        }*/
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    private void callPhone() {
        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("客服")//设置对话框的标题
                .setMessage("拨打客服电话：88888888")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callPhone("88888888");
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    public void noMoreMsg() {
        AppUtils.showToast(getActivity(), getResources().getString(R.string.no_more));
    }

    @Override
    public void onResume() {
        super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button
                    // 处理fragment的返回事件
                    Log.v("iiiiiiiiiii", "-----------1");
                    exitDialog(true);
                    return true;
                }
                return false;
            }
        });
    }

    private void exitDialog(boolean b) {
        // 创建退出对话框
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //builder.setIcon(R.drawable.exit);
        // 设置对话框标题
        builder.setTitle("提示");
        // 设置对话框消息
        builder.setMessage("确定要退出吗?");
        //监听下方button点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finish();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        // 显示对话框
        builder.show();
    }
}
