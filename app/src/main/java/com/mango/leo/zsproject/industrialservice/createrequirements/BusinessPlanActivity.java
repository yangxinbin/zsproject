package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.adapter.RecycleAdapter4;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardEigththItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFifthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFourthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardNinthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardSecondItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardSeventhItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardSixthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardThirdItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardNinthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.DateUtil;
import com.mango.leo.zsproject.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessPlanActivity extends BaseActivity implements View.OnClickListener, RecycleAdapter4.OnCard4ClickListener, OnGetGeoCoderResultListener {

    @Bind(R.id.imageViewback)
    ImageView imageViewback;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.carfirst_content)
    ConstraintLayout carfirstContent;
    @Bind(R.id.carfirst)
    CardView carfirst;
    @Bind(R.id.carsecond_content)
    ConstraintLayout carsecondContent;
    @Bind(R.id.carsecond)
    CardView carsecond;
    @Bind(R.id.carthird_content)
    ConstraintLayout carthirdContent;
    @Bind(R.id.carthird)
    CardView carthird;
    @Bind(R.id.carfourth_content)
    ConstraintLayout carfourthContent;
    @Bind(R.id.carfourth)
    CardView carfourth;
    /*@Bind(R.id.carfifth_content)
    ConstraintLayout carfifthContent;
    @Bind(R.id.carfifth)
    CardView carfifth;
    @Bind(R.id.carsixth_content)
    ConstraintLayout carsixthContent;
    @Bind(R.id.carsixth)
    CardView carsixth;
    @Bind(R.id.carseventh_content)
    ConstraintLayout carseventhContent;
    @Bind(R.id.carseventh)
    CardView carseventh;
    @Bind(R.id.careighth_content)
    ConstraintLayout careighthContent;
    @Bind(R.id.careighth)
    CardView careighth;*/
    @Bind(R.id.carninth_content)
    ConstraintLayout carninthContent;
    @Bind(R.id.carninth)
    CardView carninth;
    @Bind(R.id.send)
    Button send;
    private TextView title, what, time, content, p1, p2, tv9_1, tv9_2, tv9_3, tv9_4, tv9_5;
    CardFirstItemBean bean1;
    CardThirdItemBean bean3;
    private ImageView im_1, im_3, im_9;
    // 声明存储首选项 对象
    private LinearLayoutManager mLayoutManager;
    private List<CardFourthItemBean> bean4;
    private String projectId;
    private MapView mMapView;
    GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    BaiduMap mBaiduMap = null;
    private static final int REQUEST_CODE = 1;
    private CardNinthItemBean bean9;
    private StringBuffer stringBuffer1, stringBuffer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_plan);
        ButterKnife.bind(this);
        bean1 = new CardFirstItemBean();
        bean9 = new CardNinthItemBean();
        //initFirstItem();
        stringBuffer1 = new StringBuffer();
        stringBuffer2 = new StringBuffer();
        EventBus.getDefault().register(this);//放最后

    }

    private void initLocation() {
        mBaiduMap = mMapView.getMap();
        if (Build.VERSION.SDK_INT >= 23) {
            needPermission();
        } else {
            initLocationMode();
        }
    }

    public void needPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            AppUtils.showToast(this, "没有权限,请手动开启定位权限");
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(BusinessPlanActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        } else {
            initLocationMode();
        }
    }

    private void initLocationMode() {
        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        // 删除百度地图LoGo
        mMapView.removeViewAt(1);
        //mSearch.setOnGetGeoCodeResultListener(this);
        // Geo搜索
        mSearch.setOnGetGeoCodeResultListener(this);
        mSearch.geocode(new GeoCodeOption().city("").address(bean3.getProvince()+bean3.getCity()+bean3.getDistrict() + bean3.getAddress()));

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CardFirstItemBean bean) {
        this.bean1 = bean;
        carfirstContent.setVisibility(View.GONE);
        carfirst.setEnabled(false);
        //渲染card1布局
        View item1 = LayoutInflater.from(this).inflate(R.layout.carditem1, null);
        carfirst.addView(item1);
        title = (TextView) item1.findViewById(R.id.textView_card1Name);
        what = (TextView) item1.findViewById(R.id.textView_what);
        time = (TextView) item1.findViewById(R.id.textView_time);
        content = (TextView) item1.findViewById(R.id.textView_card1Content);
        im_1 = (ImageView) item1.findViewById(R.id.imageView_1);

        title.setText(bean.getItemName());
        what.setText(bean.getDepartmentName());
        time.setText(bean.getTime());
        content.setText(bean.getItemContent());
        im_1.setOnClickListener(this);
/*        if (bean.getItemImagePath().size() != 0) {
            Log.v("yyyyy", "__________");
            slider.setVisibility(View.VISIBLE);
            List<String> pathsImage = new ArrayList<>();
            List<String> pathsTitle = new ArrayList<>();
            for (int i = 0; i < bean.getItemImagePath().size(); i++) {
                pathsImage.add(bean.getItemImagePath().get(i).getPath());
                pathsTitle.add("");
            }
            //图片与标题个数要对应
            slider.setImages(pathsImage)
                    .setBannerTitles(pathsTitle)
                    .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                    .setImageLoader(new GlideImageLoader())
                    //.setIndicatorGravity(BannerConfig.CENTER)
                    .isAutoPlay(false)
                    .start();
        } else {
            slider.setVisibility(View.GONE);
        }*/
        //EventBus.getDefault().removeStickyEvent(bean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card3EventBus(CardThirdItemBean bean) {
        this.bean3 = bean;
        Log.v("33333", "___________" + bean3.getAddress());
        carthirdContent.setVisibility(View.GONE);
        carthird.setEnabled(false);
        //渲染card1布局
        View item1 = LayoutInflater.from(this).inflate(R.layout.carditem3, null);
        carthird.addView(item1);
        mMapView = item1.findViewById(R.id.bmapView3);
        p1 = (TextView) item1.findViewById(R.id.textView_p1);
        p2 = (TextView) item1.findViewById(R.id.textView_p2);
        im_3 = (ImageView) item1.findViewById(R.id.imageView_3);
        p1.setText(bean.getProvince()+bean.getCity()+bean.getDistrict());
        p2.setText(bean.getAddress());
        im_3.setOnClickListener(this);
        initLocation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card4EventBus(List<CardFourthItemBean> bean) {
        /*if (bean4 != null){
            bean4.clear();
        }*/
        bean4 = bean;
        Log.v("4444444", "___________" + bean4.size());
        //渲染card4布局
        View item4 = LayoutInflater.from(this).inflate(R.layout.carditem4, null);
        if (bean.size() == 0) {
            carfourthContent.setVisibility(View.VISIBLE);
            item4.setVisibility(View.GONE);
            carfourth.setEnabled(true);
        } else {
            carfourthContent.setVisibility(View.GONE);
            item4.setVisibility(View.VISIBLE);
            carfourth.setEnabled(false);
        }
        carfourth.addView(item4);
        ImageView imageView = item4.findViewById(R.id.img_add);
        RecyclerView recyclerView = item4.findViewById(R.id.recycle_4);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);//禁止滑动
        RecycleAdapter4 adapter4 = new RecycleAdapter4(this, bean);
        recyclerView.setAdapter(adapter4);
        imageView.setOnClickListener(this);
        adapter4.setOnItemnewsClickListener(this);
        //EventBus.getDefault().removeStickyEvent(bean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card9EventBus(CardNinthItemBean bean) {
        Log.v("99999", "___bus_" + bean.getMoshi());
        this.bean9 = bean;
        //渲染card1布局
        View item9 = LayoutInflater.from(this).inflate(R.layout.carditem9, null);
        if (bean != null) {
            item9.setVisibility(View.VISIBLE);
            carninthContent.setVisibility(View.GONE);
            carninth.setEnabled(false);
        } else {
            item9.setVisibility(View.GONE);
            carninthContent.setVisibility(View.VISIBLE);
            carninth.setEnabled(true);
        }
        carninth.addView(item9);
        tv9_1 = item9.findViewById(R.id.textView_91);
        tv9_2 = item9.findViewById(R.id.textView_92);
        tv9_3 = item9.findViewById(R.id.textView_93);
        tv9_4 = item9.findViewById(R.id.textView_94);
        tv9_5 = item9.findViewById(R.id.textView_95);
        im_9 = item9.findViewById(R.id.imageView_9);
        im_9.setOnClickListener(this);
        for (int i = 0; i < bean.getWhy().size(); i++) {
            stringBuffer1.append(bean.getWhy().get(i) + " ");
        }
        for (int i = 0; i < bean.getType().size(); i++) {
            stringBuffer2.append(bean.getType().get(i) + " ");
        }
        tv9_1.setText(bean.getMoshi());
        tv9_2.setText(bean.getMoney());
        tv9_3.setText(stringBuffer1);
        tv9_4.setText(stringBuffer2);
        tv9_5.setText(bean.getQita());

    }

    @OnClick({R.id.imageViewback, R.id.save, R.id.carfirst, R.id.carsecond, R.id.carthird, R.id.carfourth, /*R.id.carfifth, R.id.carsixth, R.id.carseventh, R.id.careighth,*/ R.id.carninth, R.id.send})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageViewback:
                intent = new Intent(this, AllAndCreatedPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.save:
                break;
            case R.id.carfirst:
                intent = new Intent(this, CardFirstItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.carsecond:
                intent = new Intent(this, CardSecondItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.carthird:
                intent = new Intent(this, CardThirdItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.carfourth:
                intent = new Intent(this, CardFourthItemActivity.class);
                startActivity(intent);
                finish();
                break;
            /*case R.id.carfifth:
                intent = new Intent(this, CardFifthItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.carsixth:
                intent = new Intent(this, CardSixthItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.carseventh:
                intent = new Intent(this, CardSeventhItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.careighth:
                intent = new Intent(this, CardEigththItemActivity.class);
                startActivity(intent);
                finish();
                break;*/
            case R.id.carninth:
                intent = new Intent(this, CardNinthItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.send:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        //EventBus.getDefault().removeAllStickyEvents();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_1:
                EventBus.getDefault().postSticky(bean1);
                intent = new Intent(this, CardFirstItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_3:
                EventBus.getDefault().postSticky(bean3);
                intent = new Intent(this, CardThirdItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.imageView_9:
                EventBus.getDefault().postSticky(bean9);
                intent = new Intent(this, CardNinthItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.img_add:
                EventBus.getDefault().postSticky(bean4);
                intent = new Intent(this, CardFourthItemActivity.class);
                intent.putExtra("position", bean4.size());
                startActivity(intent);
                finish();
                break;
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        EventBus.getDefault().postSticky(bean4);
        Intent intent = new Intent(this, CardFourthItemActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(BusinessPlanActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                    .show();
            return;
        }
        double latitude = geoCodeResult.getLocation().latitude;
        double longitude = geoCodeResult.getLocation().longitude;
        Log.v("uuuuuuu", "onGetGeoCodeResult");
        mBaiduMap.clear();
        mBaiduMap.addOverlay(new MarkerOptions().position(geoCodeResult.getLocation())
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.icon_gcoding)));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(geoCodeResult
                .getLocation()));
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

    }
}
