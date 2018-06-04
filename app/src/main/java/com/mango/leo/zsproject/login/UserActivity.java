package com.mango.leo.zsproject.login;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
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
    private String cityString;
    private String token;
    private UserMessageBean.ResponseObjectBean.LocationBean locationBean;
    private BeForeUserMesBean beForeUserMesBean;

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
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        editTextName.setText(bean.getResponseObject().getUsername());
    }
    private void initDate() {
        beForeUserMesBean.setToken(sharedPreferences.getString("token", ""));
        beForeUserMesBean.setName(editTextName.getText().toString());
        beForeUserMesBean.setCompany(editTextCom.getText().toString());
        beForeUserMesBean.setUsername(editTextPho.getText().toString());
        beForeUserMesBean.setEmail(editTextEm.getText().toString());
        beForeUserMesBean.setDepartment(editTextPos.getText().toString());
        beForeUserMesBean.setPosition(cityString);//选择的
        //以下是定位获取的
            //定位获取
        beForeUserMesBean.setCountry("中国");
        beForeUserMesBean.setCity("深圳");
        beForeUserMesBean.setDistrict("南山");

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
    public void responeUserMessage(UserMessageBean bean) {
        if (bean != null) {
            EventBus.getDefault().postSticky(bean);
        }//Token在前一步注册时保存了
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
        finish();
    }
}
