package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardThirdItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardThirdItemActivity extends BaseCardActivity /*implements SensorEventListener*/ implements OnGetGeoCoderResultListener, TextWatcher,UpdateItemView {
    public static final int TYPE3 = 3;
    @Bind(R.id.imageView3_back)
    ImageView imageView3Back;
    @Bind(R.id.button3_save)
    Button buttonSave;
    @Bind(R.id.bmapView)
    MapView mMapView;
    // 定位相关
    /*LocationClient mLocClient;
    public MyLocationListenner myListener = new MyLocationListenner();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    BitmapDescriptor mCurrentMarker;
    private static final int accuracyCircleFillColor = 0xAAFFFF88;
    private static final int accuracyCircleStrokeColor = 0xAA00FF00;
    private SensorManager mSensorManager;
    private Double lastX = 0.0;
    private int mCurrentDirection = 0;
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    BaiduMap mBaiduMap;
    boolean isFirstLoc = true; // 是否首次定位
    private MyLocationData locData;
    private float direction;*/
    private static final int REQUEST_CODE = 1;
    GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
    BaiduMap mBaiduMap = null;
    @Bind(R.id.editText_where)
    EditText editTextWhere;
    @Bind(R.id.textView_place)
    TextView textViewPlace;
    private String touchType;
    /**
     * 当前地点击点
     */
    private LatLng currentPt;
    BitmapDescriptor bdA = BitmapDescriptorFactory
            .fromResource(R.drawable.icon_gcoding);
    private CardThirdItemBean cardThirdItemBean;
    private UpdateItemPresenter updateItemPresenter;
    private String nowProvince,nowCity,nowDistrict;
    private SharedPreferences sharedPreferences;
    private boolean flag;

    /**
     * 此demo用来展示如何进行地理编码搜索（用地址检索坐标）
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_third_item);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        //mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        // 地图初始化
        textViewPlace.setText(sharedPreferences.getString("where","广东省深圳市南山区"));
        Log.v("33333333",""+sharedPreferences.getString("where","广东省深圳市南山区"));
        //textViewPlace.setText("广东省深圳市南山区");
        mBaiduMap = mMapView.getMap();
        editTextWhere.addTextChangedListener(this);
        cardThirdItemBean = new CardThirdItemBean();
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        if (Build.VERSION.SDK_INT >= 23) {
            needPermission();
        } else {
            initLocationMode();
        }
        initListener();
    }
    private void initDate() {
        cardThirdItemBean.setAddress(editTextWhere.getText().toString());//确保数据完整性
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card3EventBus(CardThirdItemBean bean) {
        Log.v("33333", "_____card3EventBus______" + bean.getAddress());
        //textViewPlace.setText(bean.getProvince()+bean.getCity()+bean.getDistrict());
        editTextWhere.setText(bean.getAddress());
        needPermission();
    }

    /**
     * 对地图事件的消息响应
     */
    private void initListener() {
        mBaiduMap.setOnMapTouchListener(new BaiduMap.OnMapTouchListener() {

            @Override
            public void onTouch(MotionEvent event) {

            }
        });


/*        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            *//**
         * 单击地图
         *//*
            public void onMapClick(LatLng point) {
                touchType = "单击地图";
                currentPt = point;
                updateMapState();
            }

            *//**
         * 单击地图中的POI点
         *//*
            public boolean onMapPoiClick(MapPoi poi) {
                touchType = "单击POI点";
                currentPt = poi.getPosition();
                updateMapState();
                return false;
            }
        });*/
       /* mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            *//**
         * 长按地图
         *//*
            public void onMapLongClick(LatLng point) {
                touchType = "长按";
                currentPt = point;
                updateMapState();
            }
        });
        mBaiduMap.setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            *//**
         * 双击地图
         *//*
            public void onMapDoubleClick(LatLng point) {
                touchType = "双击";
                currentPt = point;
                updateMapState();
            }
        });*/
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
            ActivityCompat.requestPermissions(CardThirdItemActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        } else {
            initLocationMode();
        }
    }

    private void initLocationMode() {
        // 初始化搜索模块，注册事件监听
        // 初始化MKSearch
        mSearch = GeoCoder.newInstance();
        mSearch.setOnGetGeoCodeResultListener(this);
        // 删除百度地图LoGo
        mMapView.removeViewAt(1);
        // Geo搜索
        mSearch.geocode(new GeoCodeOption().city("").address(textViewPlace.getText().toString()+editTextWhere.getText().toString()));
        /*mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setMyLocationConfiguration(new MyLocationConfiguration(
                mCurrentMode, true, mCurrentMarker));
        MapStatus.Builder builder1 = new MapStatus.Builder();
        builder1.overlook(0);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder1.build()));
        // 定位初始化
        mLocClient = new LocationClient(this);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();*/
    }

    //Android6.0申请权限的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // requestCode即所声明的权限获取码，在checkSelfPermission时传入
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获取到权限，作相应处理（调用定位SDK应当确保相关权限均被授权，否则可能引起定位失败）
                    initLocationMode();
                } else {
                    // 没有获取到权限，做特殊处理
                    AppUtils.showToast(this, "没有权限,请手动开启定位权限");
                }
                break;
            default:
                break;
        }
    }

/*    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        double x = sensorEvent.values[SensorManager.DATA_X];
        if (Math.abs(x - lastX) > 1.0) {
            mCurrentDirection = (int) x;
            locData = new MyLocationData.Builder()
                    .accuracy(mCurrentAccracy)
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(mCurrentLat)
                    .longitude(mCurrentLon).build();
            mBaiduMap.setMyLocationData(locData);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }*/

    /**
     * 定位SDK监听函数
     */
    /*public class MyLocationListenner extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            Log.v("ppppppp", mCurrentLat + "**" + province + "*********" + city + "*****" + district + "----" + street);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }*/
    @OnClick({R.id.imageView3_back, R.id.button3_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView3_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button3_save:
                initDate();
                if (!TextUtils.isEmpty(editTextWhere.getText().toString()) && cardThirdItemBean != null) {
                    Log.v("doPutWithJson", "^^^^cardThirdItemBean^^^^^^"+cardThirdItemBean.toString());
                    if(flag){
                        updateItemPresenter.visitUpdateItem(this, TYPE3,cardThirdItemBean);//更新后台数据
                        EventBus.getDefault().postSticky(cardThirdItemBean);
                        intent = new Intent(this, BusinessPlanActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(CardThirdItemActivity.this, "请重新输入地址！", Toast.LENGTH_LONG)
                                .show();
                    }
                } else {
                    AppUtils.showSnackar(buttonSave, "必填项不能为空！");
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
        //为系统的方向传感器注册监听器
        //mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
        //        SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        //取消注册传感器监听
        //mSensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 退出时销毁定位
        //mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mSearch.destroy();
        mMapView.onDestroy();
        mMapView = null;
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
        if (geoCodeResult == null || geoCodeResult.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(CardThirdItemActivity.this, "未能找到输入地址，请重新输入！", Toast.LENGTH_LONG)
                    .show();
            flag = false;
            return;
        }
        flag = true;
        double latitude = geoCodeResult.getLocation().latitude;
        double longitude = geoCodeResult.getLocation().longitude;
        cardThirdItemBean.setLat(String.valueOf(latitude));
        cardThirdItemBean.setLon(String.valueOf(longitude));
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


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        mSearch.geocode(new GeoCodeOption().city(textViewPlace.getText().toString()).address(String.valueOf(charSequence)));
        mSearch.setOnGetGeoCodeResultListener(this);
        //mBaiduMap.clear();
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    /**
     * 更新地图状态显示面板
     */
    private void updateMapState() {
        if (currentPt != null) {
            LatLng ptCenter = new LatLng(currentPt.longitude
                    , currentPt.latitude);
            //latlngToAddress(ptCenter);
            // 反Geo搜索
            String state = String.format(touchType + ",当前经度： %f 当前纬度：%f",
                    currentPt.longitude, currentPt.latitude);
            Log.v("uuuuuuu", "" + state);
            MarkerOptions ooA = new MarkerOptions().position(currentPt).icon(bdA);
            mBaiduMap.clear();
            mBaiduMap.addOverlay(ooA);
        }
    }

    private void latlngToAddress(LatLng latlng) {

        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(latlng));
        //设置地址或经纬度反编译后的监听,这里有两个回调方法,
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            //经纬度转换成地址
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(CardThirdItemActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                            .show();
                }
                editTextWhere.setText(result.getAddress().toString());
                Log.v("uuuuuuuu", "GetReverseGeoCodeResul" + result.getAddress());
                mBaiduMap.clear();
                mBaiduMap.addOverlay(new MarkerOptions().position(result.getLocation())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.icon_gcoding)));
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(result
                        .getLocation()));
                editTextWhere.setText(result.getAddress());
            }

            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度
                //String address=result.getAddress();
            }
        });
    }
    @Override
    public void showUpdateStateView(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }

    @Override
    public void showUpdateFailMsg(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }
}
