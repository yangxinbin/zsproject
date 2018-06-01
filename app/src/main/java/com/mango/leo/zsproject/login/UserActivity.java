package com.mango.leo.zsproject.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.login.bean.TokenFromLonginBean;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.login.presenter.UserStatePresenter;
import com.mango.leo.zsproject.login.presenter.UserStatePresenterImpl;
import com.mango.leo.zsproject.login.view.UserStateView;
import com.mango.leo.zsproject.utils.AppUtils;
import com.ywp.addresspickerlib.AddressPickerView;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends BaseActivity implements AddressPickerView.OnAddressPickerSureListener,UserStateView {

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
    private AddressPickerView addressView;
    UserStatePresenter userStatePresenter;
    private UserMessageBean userMessageBean;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT",MODE_PRIVATE);
        userStatePresenter = new UserStatePresenterImpl(this);
        userMessageBean = new UserMessageBean();
    }
    private void initDate() {
        userMessageBean.getResponseObject().setToken(sharedPreferences.getString("token",""));
        userMessageBean.getResponseObject().setName(editTextName.getText().toString());
        userMessageBean.getResponseObject().setCompany(editTextCom.getText().toString());
        userMessageBean.getResponseObject().setUsername(editTextPho.getText().toString());
        userMessageBean.getResponseObject().setEmail(editTextEm.getText().toString());
        userMessageBean.getResponseObject().setDepartment(editTextPos.getText().toString());
        userMessageBean.getResponseObject().getLocation().setCountry("中国");
        userMessageBean.getResponseObject().getLocation().setCity("深圳");
        userMessageBean.getResponseObject().getLocation().setDistrict("南山");
    }
    @OnClick({R.id.button, R.id.ski, R.id.city})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button:
                initDate();
                userStatePresenter.visitPwdUserState(this,6, userMessageBean);
                break;
            case R.id.ski:
                intent = new Intent(this, ZsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.city:
                showPopupWindow(this);
                break;
        }
    }

    private void showPopupWindow(Context context) {
        //设置要显示的view
        View view = LayoutInflater.from(context).inflate(R.layout.city_select_default_down, null);
        addressView = view.findViewById(R.id.apvAddress);
        addressView.setOnAddressPickerSure(this);
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
    }

    @Override
    public void onSureClick(String address, String provinceCode, String cityCode, String districtCode) {
        dialog.dismiss();
        Log.v("zzzzzzzz","******"+provinceCode+"***"+cityCode+"!!!"+districtCode);
        tvLocation.setText(address);
    }

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
        }else {
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
