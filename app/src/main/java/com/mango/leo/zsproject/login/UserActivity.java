package com.mango.leo.zsproject.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.login.bean.TokenFromLonginBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.presenter.UserStatePresenter;
import com.mango.leo.zsproject.login.presenter.UserStatePresenterImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.AppUtils;

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
    private UserMessageBean userMessageBean;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private CityPickerView mPicker;
    private String cityString;

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
        userMessageBean = new UserMessageBean();
    }

    private void initDate() {
        if (userMessageBean.getResponseObject() != null) {
            userMessageBean.getResponseObject().setToken(sharedPreferences.getString("token", ""));
            userMessageBean.getResponseObject().setName(editTextName.getText().toString());
            userMessageBean.getResponseObject().setCompany(editTextCom.getText().toString());
            userMessageBean.getResponseObject().setUsername(editTextPho.getText().toString());
            userMessageBean.getResponseObject().setEmail(editTextEm.getText().toString());
            userMessageBean.getResponseObject().setDepartment(editTextPos.getText().toString());
            userMessageBean.getResponseObject().setPosition(cityString);//选择的
            //以下是定位获取的
            userMessageBean.getResponseObject().getLocation().setCountry("中国");
            userMessageBean.getResponseObject().getLocation().setCity("深圳");
            userMessageBean.getResponseObject().getLocation().setDistrict("南山");
        }

    }

    @OnClick({R.id.button, R.id.ski, R.id.city})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button:
                initDate();
                editor.putString("skip","no")
                        .commit();
                userStatePresenter.visitPwdUserState(this, 6, userMessageBean);
                break;
            case R.id.ski:
                editor.putString("skip","yes")
                        .commit();
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

                }
                //城市
                if (city != null) {
                    cityString = String.valueOf(city);
                }
                //地区
                if (district != null) {

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
    public void responeToken(TokenFromLonginBean bean) {

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
                    default:
                        break;
                }
            }
        }
    }
}