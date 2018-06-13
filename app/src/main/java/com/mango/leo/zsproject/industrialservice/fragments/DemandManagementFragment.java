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
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.adapte.DemandManagementAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.AllAndCreatedPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.CreatedStyleActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.utils.GlideImageLoader;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class DemandManagementFragment extends Fragment {//
    @Bind(R.id.recycle_view11)
    RecyclerView recycleView11;
    @Bind(R.id.refresh11)
    SwipeRefreshLayout refresh11;
    private LinearLayoutManager mLayoutManager;
    private DemandManagementAdapter adapter;
    private Spinner mSpinner;
    //private Button createButton;
    private ConstraintLayout h;
    private LinearLayout allPlanLayout, addPlanLayout;
    private Banner banner,banner2;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.demandmanagement, container, false);
        ButterKnife.bind(this, view);
        sharedPreferences = getActivity().getSharedPreferences("CIFIT",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        Log.v("sssss","-sharedPreferences.getString(\"type\",\"\")--"+sharedPreferences.getString("type",""));
        if (sharedPreferences.getString("type","").equals("no")){
            Log.v("sssss","-sharedPreferences.getSt");
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
        initSwipeRefreshLayout();
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleView11.setLayoutManager(mLayoutManager);
        recycleView11.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recycleView11.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter = new DemandManagementAdapter(getActivity().getApplicationContext());
        adapter.setOnItemnewsClickListener(mOnItemClickListener);
        recycleView11.removeAllViews();
        initHeader();
        initAllPlanButton();
        recycleView11.setAdapter(adapter);
        return view;
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
                editor.putString("projectId","").commit();
                intent.putExtra("DemandManagementFragment",true);
                EventBus.getDefault().removeAllStickyEvents();
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                                allPlanLayout, getString(R.string.transition_news_img));
                ActivityCompat.startActivity(getActivity(), intent, options.toBundle());
                //getActivity().finish();
            }
        });
    }

    public void initSwipeRefreshLayout() {
        refresh11.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh11.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh11.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        refresh11.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    private void initHeader() {
        //渲染header布局
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.header, null);
        h = (ConstraintLayout) header.findViewById(R.id.header);
        addPlanLayout = (LinearLayout) header.findViewById(R.id.r1);
        allPlanLayout = (LinearLayout) header.findViewById(R.id.r2);
        banner = (Banner) header.findViewById(R.id.imageView);
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
        public void onItemClick1(View view, int position) {
            Log.v("oooooooooo", "****onItemClick1***点击第" + position);
/*
            String newsurl = adapter.getItem(opsition).getResult().getData().get(position).getUrl();
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("newsurl", newsurl);//传输内容
            View transitionView = view.findViewById(R.id.item_news_img);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),
                            transitionView, getString(R.string.transition_news_img));
            ActivityCompat.startActivity(getActivity(), intent, options.toBundle());*/
        }

        @Override
        public void onItemClick2(View view, int position) {
            Log.v("oooooooooo", "****onItemClick2***点击第" + position);

        }

        @Override
        public void onCancelingMatchClick(View view, int position) {
            Log.v("aaaaa","clock__1");
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
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
