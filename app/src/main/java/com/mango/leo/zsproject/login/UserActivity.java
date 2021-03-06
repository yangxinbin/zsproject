package com.mango.leo.zsproject.login;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.bean.ErrorBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardThirdItemActivity;
import com.mango.leo.zsproject.login.bean.BeForeUserMesBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.presenter.UserStatePresenter;
import com.mango.leo.zsproject.login.presenter.UserStatePresenterImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


//import com.ywp.addresspickerlib.AddressPickerView;

public class UserActivity extends BaseActivity implements /*AddressPickerView.OnAddressPickerSureListener,*/UserStateView {

    @Bind(R.id.button)
    Button button;
    @Bind(R.id.ski)
    TextView ski;
    @Bind(R.id.city)
    RelativeLayout city;
    @Bind(R.id.editText_name)
    EditText editTextName;
    @Bind(R.id.editText_pho)
    EditText editTextPho;
    @Bind(R.id.editText_com)
    EditText editTextCom;
    @Bind(R.id.editText_pos)
    EditText editTextPos;
    @Bind(R.id.editText_em)
    EditText editTextEm;
    @Bind(R.id.tv_location)
    TextView tvLocation;
    Dialog dialog;
    //private AddressPickerView addressView;
    UserStatePresenter userStatePresenter;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private CityPickerView mPicker;
    private String cityString, districtString, provinceString;
    private String token;
    private UserMessageBean.ResponseObjectBean.LocationBean locationBean;
    private BeForeUserMesBean beForeUserMesBean;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private static final int REQUEST_CODE = 1;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        //申明对象
        mPicker = new CityPickerView();
        mPicker.init(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userStatePresenter = new UserStatePresenterImpl(this);
        beForeUserMesBean = new BeForeUserMesBean();
        /*if (Build.VERSION.SDK_INT >= 23) {
            needPermission();
        } else {
            setLocat();
        }*/
    }

    public void needPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(UserActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_PHONE_STATE}, REQUEST_CODE);
        } else {
            setLocat();
        }
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
                    setLocat();
                } else {
                    // 没有获取到权限，做特殊处理
                    AppUtils.showToast(this, "没有权限,请手动开启定位权限");
                }
                break;
            default:
                break;
        }
    }

    private void setLocat() {

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//可选，设置定位模式，默认高精度
//LocationMode.Hight_Accuracy：高精度；
//LocationMode. Battery_Saving：低功耗；
//LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
//可选，设置返回经纬度坐标类型，默认gcj02
//gcj02：国测局坐标；
//bd09ll：百度经纬度坐标；
//bd09：百度墨卡托坐标；
//海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(1000);
//可选，设置发起定位请求的间隔，int类型，单位ms
//如果设置为0，则代表单次定位，即仅定位一次，默认为0
//如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
//可选，设置是否使用gps，默认false
//使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
//可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
//可选，定位SDK内部是一个service，并放到了独立进程。
//设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
//可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
//可选，7.2版本新增能力
//如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
//可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        mLocationClient.setLocOption(option);
//mLocationClient为第二步初始化过的LocationClient对象
//需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
//更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        editTextName.setText(bean.getResponseObject().getUsername());
    }

    private void initDate() {
        beForeUserMesBean.setToken(sharedPreferences.getString("token", ""));
        beForeUserMesBean.setName(editTextName.getText().toString());
        beForeUserMesBean.setDepartment(editTextCom.getText().toString());
        beForeUserMesBean.setUsername(editTextPho.getText().toString());
        beForeUserMesBean.setEmail(editTextEm.getText().toString());
        beForeUserMesBean.setPosition(editTextPos.getText().toString());
        if (cityString != null) {
            beForeUserMesBean.setCity(cityString);//选择的
        }
        if (districtString != null) {
            beForeUserMesBean.setDistrict(districtString);//选择的
        }
        if (provinceString != null) {
            beForeUserMesBean.setProvince(provinceString);//选择的
        }

        //以下是定位获取的
        //定位获取
        /*beForeUserMesBean.setType("0");
        beForeUserMesBean.setCountry("中国");
        beForeUserMesBean.setProvince("广东省");
        beForeUserMesBean.setCity("深圳");
        beForeUserMesBean.setDistrict("南山");
        beForeUserMesBean.setAddress("科技园");
        beForeUserMesBean.setLon("23.0");
        beForeUserMesBean.setLat("33.0");*/
    }

    @OnClick({R.id.button, R.id.ski, R.id.city})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button:
                initDate();
                editor.putString("skip", "no")
                        .commit();
                userStatePresenter.visitPwdUserState(this, 6, beForeUserMesBean);
                break;
            case R.id.ski:
                editor.putString("skip", "yes")
                        .commit();
/*                EventBus.getDefault().postSticky(ProjectsJsonUtils.readJsonUserMessageBeans("{\n" +
                        "    \"responseObject\": {\n" +
                        "        \"id\": \"5b10f1b2bc2ab90f3c3cff15\",\n" +
                        "        \"username\": \"13417304551\",\n" +
                        "        \"name\": \"tom\",\n" +
                        "        \"company\": \"芒果\",\n" +
                        "        \"position\": \"深圳\",\n" +
                        "        \"department\": \"市场\",\n" +
                        "        \"email\": \"295249259@qq.com\",\n" +
                        "        \"type\": \"0\",\n" +
                        "        \"avator\": null,\n" +
                        "        \"location\": {\n" +
                        "            \"country\": \"中国\",\n" +
                        "            \"province\": \"广东\",\n" +
                        "            \"city\": \"深圳\",\n" +
                        "            \"district\": \"南山\",\n" +
                        "            \"address\": \"东方科技大厦\",\n" +
                        "            \"lon\": 34.1,\n" +
                        "            \"lat\": 45.2\n" +
                        "        },\n" +
                        "        \"govtTenant\": null,\n" +
                        "        \"token\": \"eyJhbGciOiJIUzUxMiJ9.eyJnb3Z0VGVuYW50IjpudWxsLCJhdWRpZW5jZSI6Im1vYmlsZSIsImNyZWF0ZWQiOjE1Mjc4MzgzMTE1OTksImlkIjoiNWIxMGYxYjJiYzJhYjkwZjNjM2NmZjE1IiwiZXhwIjo0MTE5ODM4MzExLCJ1c2VybmFtZSI6IjEzNDE3MzA0NTUwIn0.xKfy-0sxpSgyNOmPcrP0JFet1KlZokOBDKRMXBCnx1jkTTb5_vJBZCghimy6plEqnWMgLLb9GDUHVQ1v9CzNkA\"\n" +
                        "    },\n" +
                        "    \"responseList\": null,\n" +
                        "    \"totalRecords\": null,\n" +
                        "    \"currentPage\": null,\n" +
                        "    \"totalPages\": null\n" +
                        "}"));*/
                intent = new Intent(this, ZsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.city:
                showSeleteCity();
                break;
        }
    }

    private void showSeleteCity() {
        //添加默认的配置，不需要自己定义
        CityConfig cityConfig = new CityConfig.Builder().build();
        mPicker.setConfig(cityConfig);
        //监听选择点击事件及返回结果
        mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {

            @Override
            public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                //省份
                if (province != null) {
                    provinceString = String.valueOf(province);
                }
                //城市
                if (city != null) {
                    cityString = String.valueOf(city);
                   /* editor.putString("position", cityString)
                            .commit();*/
                }
                //地区
                if (district != null) {
                    districtString = String.valueOf(district);
                }
                tvLocation.setText(province + "-" + city + "-" + district);
            }

            @Override
            public void onCancel() {
                AppUtils.showToast(getApplicationContext(), "城市选择已取消");
            }
        });

        //显示
        mPicker.showCityPicker();
    }
    /*private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(UserActivity.this)
                .textSize(14)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .titleTextColor("#696969")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("广东省")
                .city("深圳市")
                .district("南山区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                //为TextView赋值
                tvLocation.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }*/
/*    private void showPopupWindow(Context context) {
        //设置要显示的view
        View view = LayoutInflater.from(context).inflate(R.layout.city_select_default_down, null);
        *//*addressView = view.findViewById(R.id.apvAddress);
        addressView.setOnAddressPickerSure(this);*//*
        dialog = new Dialog(context, R.style.dialog);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        //设置弹出窗口大小
        window.setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        //设置显示位置
        window.setGravity(Gravity.BOTTOM);
        //设置动画效果
        window.setWindowAnimations(R.style.AnimBottom);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }*/

    /*@Override
    public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
        dialog.dismiss();
        Log.v("zzzzzzzz","******"+provinceCode+"***"+cityCode+"!!!"+districtCode);
        tvLocation.setText(address);
    }*/

    @Override
    public void showStateView(String string) {
//里面不能更新UI
        Intent intent;
        if (string.equals("MES_SUCCESS")) {
            mHandler.sendEmptyMessage(0);
            // Toast.makeText(this, s, Toast.LENGTH_LONG);
            intent = new Intent(this, ZsActivity.class);
            startActivity(intent);
            finish();
        } else {
            mHandler.sendEmptyMessage(1);
        }
    }

    @Override
    public void showVisitFailMsg(String string) {
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void responeUserMessage(UserMessageBean bean) {
        if (bean != null) {
            Log.v("uuuuu", "____");
            EventBus.getDefault().postSticky(bean);
        }//Token在前一步注册时保存了
    }
    @Override
    public void responeErrorUserMessage(final ErrorBean bean) {
        if (bean != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getBaseContext(), bean.getMessage());
                }
            });
        }
    }
    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明
            //获取详细地址信息
            String addr = location.getAddrStr();    //获取详细地址信息
           /* String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息*/
            if (addr != null) {
               /* beForeUserMesBean.setCountry(location.getCountry());
                beForeUserMesBean.setProvince(location.getProvince());
                beForeUserMesBean.setCity(location.getCity());
                beForeUserMesBean.setDistrict(location.getDistrict());
                beForeUserMesBean.setAddress(location.getStreet());
                beForeUserMesBean.setLon(String.valueOf(location.getLongitude()));
                beForeUserMesBean.setLat(String.valueOf(location.getLatitude()));*/
                Log.v("zzzzz", "____" + addr);
                //mHandler.sendEmptyMessage(2);
                //flag = -1;
            } else {
                //flag = -1;
                //mHandler.sendEmptyMessage(3);
            }

        }
    }

    private final UserActivity.MyHandler mHandler = new UserActivity.MyHandler(this);

    private static class MyHandler extends Handler {
        private final WeakReference<UserActivity> mActivity;

        public MyHandler(UserActivity activity) {
            mActivity = new WeakReference<UserActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            UserActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(activity, "个人信息保存成功");
                        break;
                    case 1:
                        AppUtils.showToast(activity, "个人信息保存失败");
                        break;
                    case 2:
                        AppUtils.showToast(activity, "定位成功");
                        break;
                    case 3:
                        AppUtils.showToast(activity, "定位失败");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}
