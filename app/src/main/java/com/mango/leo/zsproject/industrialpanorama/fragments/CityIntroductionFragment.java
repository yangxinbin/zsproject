package com.mango.leo.zsproject.industrialpanorama.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.adapter.CityAdapter;
import com.mango.leo.zsproject.industrialpanorama.bean.CityBean;
import com.mango.leo.zsproject.industrialpanorama.bean.CityS;
import com.mango.leo.zsproject.utils.NetUtil;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/30.
 */

public class CityIntroductionFragment extends Fragment {
    @Bind(R.id.w)
    WebView webview;
    @Bind(R.id.imageView_noNet)
    ImageView imageViewNoNet;
    /*@Bind(R.id.recycle_city)
        RecyclerView recycleCity;
        @Bind(R.id.refresh_city)
        SwipeRefreshLayout refreshCity;*/
    private LinearLayoutManager mLayoutManager;
    private CityAdapter adapter;
    private RelativeLayout h;
    private Banner banner;
    private ConstraintLayout h1;
    private CityBean cityBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.city_introduction, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
/*        loadCityMes();
        initSwipeRefreshLayout();
        initRecycle();*/
        TextView c = getActivity().findViewById(R.id.city);
        Log.v("yyyyyyyyyy", "----------" + c.getText());
        if (NetUtil.isNetConnect(getActivity())) {
            initW("深圳");
            webview.setVisibility(View.VISIBLE);
            imageViewNoNet.setVisibility(View.GONE);
        } else {
            imageViewNoNet.setVisibility(View.VISIBLE);
            webview.setVisibility(View.GONE);
        }
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CityS bean) {
        Log.v("yyyyyyyyyy", "------szD----" + bean.getCity());
        initW(bean.getCity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    private void initW(final String sp) {
        webview.setVisibility(View.VISIBLE);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        webview.loadUrl("http://47.106.184.121/jetc/#/iosCityIntroduction/:" + sp);
    }

    private void loadCityMes() {
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet(Urls.HOST_CITY + "?city=" + "深圳", new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(0);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            CityBean bean = ProjectsJsonUtils.readJsonCityBeans(response.body().string());//data是json字段获得data的值即对象数组
                            // mHandler.sendEmptyMessage(1);
                            Message message = mHandler.obtainMessage();
                            message.obj = bean;
                            message.what = 1;
                            mHandler.sendMessage(message);
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(0);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                        }
                    }
                });
            }
        }).start();*/
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

   /* private void initRecycle() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleCity.setLayoutManager(mLayoutManager);
        recycleCity.setItemAnimator(new DefaultItemAnimator());//设置默认动画
        recycleCity.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(getContext()));
        adapter = new CityAdapter(getActivity().getApplicationContext());
        //recycleCity.setAdapter(adapter);
        recycleCity.removeAllViews();
    }

    private void initHeader() {
        //渲染header布局
        View header = LayoutInflater.from(getActivity()).inflate(R.layout.city_header, null);
        h1 = (ConstraintLayout) header.findViewById(R.id.header_city);
        banner = (Banner) header.findViewById(R.id.imageVieb_city);
        List<String> pathsImage = new ArrayList<>();
        List<String> pathsTitle = new ArrayList<>();
        if (cityBean.getResponseObject().getBanner() != null) {
            pathsImage.add(Urls.HOST + "/user-service/user/get/file?fileId=" + cityBean.getResponseObject().getBanner().getId());
        }
        //pathsImage.add(getResourcesUri(R.drawable.wechat2));
        //pathsTitle.add("");
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
        h1.setLayoutParams(layoutParam);
        //设置headerview
        //h.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));//除top隐藏headerview
        //ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, Arrays.asList(arrays));
        //arrayAdapter1.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        //mSpinner.setAdapter(arrayAdapter1);
        adapter.setHeaderView(h1);
    }

    public void initSwipeRefreshLayout() {
        refreshCity.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshCity.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshCity.setRefreshing(false);
                        initRecycle();
                        //     mNewsPresenter.visitProjects(getActivity(), mType, page);//请求刷新
                    }
                }, 2000);
            }
        });
        refreshCity.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private final CityIntroductionFragment.MyHandler mHandler = new CityIntroductionFragment.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<CityIntroductionFragment> mActivity;

        public MyHandler(CityIntroductionFragment activity) {
            mActivity = new WeakReference<CityIntroductionFragment>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CityIntroductionFragment activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(getActivity(), "获取城市信息失败");
                        break;
                    case 1:
                        CityBean bean = (CityBean) msg.obj;
                        cityBean = bean;
                        AppUtils.showToast(getActivity(), "获取城市信息成功");
                        initHeader();
                        recycleCity.setAdapter(adapter);
                        *//*List<IntroductionBean> list = new ArrayList<>();
                        for (int i=0;i<cityBean.getResponseObject().getIntroduction().size();i++){
                            list.add(cityBean.getResponseObject().getIntroduction().get(i));
                        }*//*
                        adapter.setmDate(cityBean.getResponseObject().getIntroduction());
                        Log.v("dddddddd",""+cityBean.getResponseObject().getIntroduction().size());
                    default:
                        break;
                }
            }
        }
    }*/
}
