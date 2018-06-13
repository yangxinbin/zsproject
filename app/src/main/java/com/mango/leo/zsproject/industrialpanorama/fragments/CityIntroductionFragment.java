package com.mango.leo.zsproject.industrialpanorama.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.adapter.CityAdapter;
import com.mango.leo.zsproject.industrialpanorama.bean.CityBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.GlideImageLoader;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.SwipeItemLayout;
import com.mango.leo.zsproject.utils.Urls;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by admin on 2018/5/30.
 */

public class CityIntroductionFragment extends Fragment {
/*    @Bind(R.id.recycle_city)
    RecyclerView recycleCity;
    @Bind(R.id.refresh_city)
    SwipeRefreshLayout refreshCity;
    private LinearLayoutManager mLayoutManager;
    private CityAdapter adapter;
    private RelativeLayout h;
    private Banner banner;
    private ConstraintLayout h1;
    private CityBean cityBean;*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.city_introduction, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    /*private void loadCityMes() {
                    HttpUtils.doGet(Urls.HOST_CITY + "?city=" + "深圳", new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            mHandler.sendEmptyMessage(0);
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                CityBean bean = ProjectsJsonUtils.readJsonCityBeans(response.body().string());//data是json字段获得data的值即对象数组
                                mHandler.sendEmptyMessage(1);
                                cityBean = bean;
                            } catch (Exception e) {
                                mHandler.sendEmptyMessage(0);
//                    Log.e("eeeee", response.body().string()+"Exception = " + e);
                            }
                        }
                    });
                }

                private void initRecycle() {
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
                        AppUtils.showToast(getActivity(), "获取城市信息成功");
                        initHeader();
                        recycleCity.setAdapter(adapter);
                        *//*List<IntroductionBean> list = new ArrayList<>();
                        for (int i=0;i<cityBean.getResponseObject().getIntroduction().size();i++){
                            list.add(cityBean.getResponseObject().getIntroduction().get(i));
                        }*//*
                        adapter.setmDate(cityBean.getResponseObject().getIntroduction());
                    default:
                        break;
                }
            }
        }
    }*/
}
