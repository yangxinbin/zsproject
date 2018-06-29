package com.mango.leo.zsproject.industrialpanorama.show;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.address.AddressSelector;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseActivity extends Activity {

    @Bind(R.id.textView45)
    TextView textView45;
    @Bind(R.id.address)
    AddressSelector address;
    @Bind(R.id.button_city_re)
    Button buttonCityRe;
    @Bind(R.id.button_city_ok)
    Button buttonCityOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choose);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button_city_re, R.id.button_city_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_city_re:
                finish();
                break;
            case R.id.button_city_ok:
                finish();
                break;
        }
    }
}
