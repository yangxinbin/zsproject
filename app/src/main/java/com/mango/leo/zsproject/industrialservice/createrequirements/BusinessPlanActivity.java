package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.luck.picture.lib.tools.ScreenUtils;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.adapter.RecycleAdapter2;
import com.mango.leo.zsproject.industrialservice.createrequirements.adapter.RecycleAdapter4;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFourthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardNinthItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardSecondItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardThirdItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFourthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardNinthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusinessPlanActivity extends BaseActivity implements View.OnClickListener, RecycleAdapter4.OnCard4ClickListener, OnGetGeoCoderResultListener, RecycleAdapter2.OnCard2ClickListener {

    @Bind(R.id.imageViewback)
    ImageView imageViewback;
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
    @Bind(R.id.textView5)
    TextView textView5;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.zhaoshang)
    LinearLayout zhaoshang;
    @Bind(R.id.co)
    TextView co;
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
    private UserMessageBean userBean;
    private SharedPreferences sharedPreferences;
    private String xiugai;
    private int type;
    private TextView textView;
    private ConstraintLayout card1;
    private RelativeLayout card3;
    private RelativeLayout card9;
    private List<CardSecondItemBean> bean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_plan);
        ButterKnife.bind(this);
        xiugai = getIntent().getStringExtra("xiugai");
        type = getIntent().getIntExtra("type", -1);
        whereFrom();
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        bean1 = new CardFirstItemBean();
        bean9 = new CardNinthItemBean();
        //initFirstItem();
        EventBus.getDefault().register(this);//放最后
    }

    private void whereFrom() {
        Log.v("xxxxxxxxxx", "___" + getIntent().getStringExtra("xiugai"));
        if ("xiugai".equals(xiugai)) {
            Log.v("xxxxxxxxx", "_xxxxx_");
            save.setVisibility(View.INVISIBLE);
            textView5.setText("修改招商计划");
        }
        if (type > 0) {
            textView = new TextView(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dip2px(this, 50));
            //lp.gravity = Gravity.CENTER_HORIZONTAL;
            textView.setGravity(Gravity.CENTER);
            switch (type) {
                case 1:
                    save.setVisibility(View.INVISIBLE);
                    textView5.setText("招商信息");
                    textView.setText("审核中，请耐心等待！");
                    break;
                case 2:
                    save.setText("申请修改");
                    textView5.setText("招商信息");
                    textView.setText("已完成审核");
                    break;
            }
            textView.setTextSize(18);
            textView.setTextColor(getResources().getColor(R.color.red));
            zhaoshang.addView(textView, 1, lp);
            send.setVisibility(View.GONE);
            co.setVisibility(View.GONE);

        }
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
            AppUtils.showToast(this, "没有权限,请手动开启定位权限", "正在加载数据，请稍后...");
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
        mSearch.geocode(new GeoCodeOption().city("").address(bean3.getProvince() + bean3.getCity() + bean3.getDistrict() + bean3.getAddress()));

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
        card1 = (ConstraintLayout)item1.findViewById(R.id.card1);
        if (type == 1 || type == 2) {
            im_1.setVisibility(View.INVISIBLE);
        }
        title.setText(bean.getItemName());
        what.setText(bean.getDepartmentName());
        time.setText(bean.getTime());
        content.setText(bean.getItemContent());
        //im_1.setOnClickListener(this);
        card1.setOnClickListener(this);
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
        card3 = (RelativeLayout) item1.findViewById(R.id.card3);

        if (type == 1 || type == 2) {
            im_3.setVisibility(View.INVISIBLE);
        }
        p1.setText(sharedPreferences.getString("where", "广东省深圳市南山区"));
        Log.v("33333333",""+sharedPreferences.getString("where","广东省深圳市南山区"));
        p2.setText(bean.getAddress());
        //im_3.setOnClickListener(this);
        card3.setOnClickListener(this);
        initLocation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card2EventBus(List<CardSecondItemBean> bean) {
 /*       if(bean == null){
            return;
        }*/
        this.bean2 = bean;
        carsecondContent.setVisibility(View.GONE);
        carsecond.setEnabled(false);
        //渲染card1布局
        View item2 = LayoutInflater.from(this).inflate(R.layout.carditem2, null);
        if (bean.size() == 0) {
            carsecondContent.setVisibility(View.VISIBLE);
            item2.setVisibility(View.GONE);
            carsecond.setEnabled(true);
        } else {
            carsecondContent.setVisibility(View.GONE);
            item2.setVisibility(View.VISIBLE);
            carsecond.setEnabled(false);
        }
        carsecond.addView(item2);
        ImageView imageView2 = item2.findViewById(R.id.img_2add);
        RecyclerView recyclerView2 = item2.findViewById(R.id.recycle_2);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(mLayoutManager);
        recyclerView2.setNestedScrollingEnabled(false);//禁止滑动
        RecycleAdapter2 adapter2 = new RecycleAdapter2(this, bean, type);
        recyclerView2.setAdapter(adapter2);
        imageView2.setOnClickListener(this);
        if (type == 1 || type == 2) {
            imageView2.setVisibility(View.INVISIBLE);
        } else {
            adapter2.setOnItemnewsClickListener(this);
        }
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
        RecycleAdapter4 adapter4 = new RecycleAdapter4(this, bean, type);
        recyclerView.setAdapter(adapter4);
        imageView.setOnClickListener(this);
        if (type == 1 || type == 2) {
            imageView.setVisibility(View.INVISIBLE);
        } else {
            adapter4.setOnItemnewsClickListener(this);
        }
        //EventBus.getDefault().removeStickyEvent(bean);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card9EventBus(CardNinthItemBean bean) {
        this.bean9 = bean;
        //渲染card1布局
        stringBuffer1 = new StringBuffer();
        stringBuffer2 = new StringBuffer();
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
        card9 = item9.findViewById(R.id.card9);
        tv9_1.setText(bean.getMoshi());
        tv9_2.setText(bean.getMoney());
        if (type == 1 || type == 2) {
            im_9.setVisibility(View.INVISIBLE);
        }
        //im_9.setOnClickListener(this);
        card9.setOnClickListener(this);
        for (int i = 0; i < bean.getWhy().size(); i++) {
            stringBuffer1.append(bean.getWhy().get(i) + " ");
            Log.v("77777", bean.getWhy().get(i)+"___bus_");
        }
        tv9_3.setText(stringBuffer1);
        for (int j = 0; j < bean.getType().size(); j++) {
            stringBuffer2.append(bean.getType().get(j) + " ");
            Log.v("6666", bean.getType().get(j)+"___bus_");
        }
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
                if (type == 2) {
                    intent = new Intent(this, BusinessPlanActivity.class);
                    intent.putExtra("xiugai", "xiugai");
                    startActivity(intent);
                    finish();
                }
                if (type == 0) {//草稿箱才能存草稿
                    showDailog("一键招商", "恭喜你项目创建成功！");
                }
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
                // EventBus.getDefault().postSticky(userBean);
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
                publishProject();
                break;
        }
    }

    private void publishProject() {
        Log.v("hhhhh","----"+Urls.HOST_PUBLISH+"?token="+sharedPreferences.getString("token","")+"&projectId="+sharedPreferences.getString("projectId", ""));
        HashMap<String, String> mapParams = new HashMap<String, String>();
        if (mapParams != null){
            mapParams.clear();
        }
        mapParams.put("token", sharedPreferences.getString("token",""));
        mapParams.put("projectId", sharedPreferences.getString("projectId", ""));
        HttpUtils.doPost(Urls.HOST_PUBLISH,mapParams,new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showDailog("审核","提交审核失败！");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showDailog("审核","恭喜你提交审核成功！");
                        }
                    });
                }else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showDailog("审核","提交审核失败！");
                        }
                    });
                }
            }
        });
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
            case R.id.card1:
                EventBus.getDefault().postSticky(bean1);
                intent = new Intent(this, CardFirstItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.card3:
                EventBus.getDefault().postSticky(bean3);
                intent = new Intent(this, CardThirdItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.card9:
                EventBus.getDefault().postSticky(bean9);
                intent = new Intent(this, CardNinthItemActivity.class);
                intent.putExtra("flag", true);
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
            case R.id.img_2add:
                EventBus.getDefault().postSticky(bean2);
                intent = new Intent(this, CardSecondItemActivity.class);
                intent.putExtra("position", bean2.size());
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
            //  Toast.makeText(BusinessPlanActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
            //          .show();
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

    private void showDailog(String s1, String s2) {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle(s1)//设置对话框的标题
                .setMessage(s2)//设置对话框的内容
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
                        finish();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }

    @Override
    public void onItem2Click(View view, int position) {
        EventBus.getDefault().postSticky(bean2);
        Intent intent = new Intent(this, CardSecondItemActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
    }
}
