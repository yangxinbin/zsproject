package com.mango.leo.zsproject.login;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.ywp.addresspickerlib.AddressPickerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity extends AppCompatActivity implements AddressPickerView.OnAddressPickerSureListener {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.ski, R.id.city})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button:
                intent = new Intent(this, ZsActivity.class);
                startActivity(intent);
                finish();
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
        tvLocation.setText(address);
    }
}
