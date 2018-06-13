package com.mango.leo.zsproject.industrialpanorama.show;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.URLEncoderURI;

import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FragmentOfIndustrialPanorama extends Fragment {
    @Bind(R.id.image_msg2)
    ImageView imageMsg2;
    @Bind(R.id.imageView_chcity)
    ImageView imageViewChcity;
    @Bind(R.id.city)
    TextView city_t;
    /*    @Bind(R.id.web_city)
        WebView webview;*/
    private CityPickerView mPicker;
    private String provinceString, cityString, districtString;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.industrialpanorama, container, false);
        ButterKnife.bind(this, view);
        //申明对象
        mPicker = new CityPickerView();
        mPicker.init(getActivity());
        //init("深圳");
        return view;
    }
/*
    private void init(String parm) {
        webview.setVisibility(View.VISIBLE);
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        try {
            webview.loadUrl("http://192.168.1.166:8080/jetc/#/iosCityIntroduction/:" + URLEncoderURI.encode(parm, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
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
                city_t.setText(province + "" + city + district);
            }

            @Override
            public void onCancel() {
                AppUtils.showToast(getContext(), "城市选择已取消");
            }
        });

        //显示
        mPicker.showCityPicker();
    }

    @OnClick(R.id.city)
    public void onViewClicked() {
        showSeleteCity();
       // init(cityString);
    }
}
