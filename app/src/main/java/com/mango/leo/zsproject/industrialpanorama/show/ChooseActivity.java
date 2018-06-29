package com.mango.leo.zsproject.industrialpanorama.show;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.address.AddressSelector;
import com.mango.leo.zsproject.industrialpanorama.address.CityInterface;
import com.mango.leo.zsproject.industrialpanorama.address.OnItemClickListener;
import com.mango.leo.zsproject.industrialpanorama.bean.ChooseBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChooseActivity extends Activity {

    @Bind(R.id.textView45)
    TextView textView45;
    @Bind(R.id.address)
    AddressSelector address;
    @Bind(R.id.button_city_re)
    Button buttonCityRe;
    @Bind(R.id.button_city_ok)
    Button buttonCityOk;
    private ArrayList<ChooseBean.ResponseListBean> c1 = new ArrayList<>();
    private ArrayList<ChooseBean.ResponseListBean> c2 = new ArrayList<>();
    private ArrayList<ChooseBean.ResponseListBean> c3 = new ArrayList<>();
    private ArrayList<ChooseBean.ResponseListBean> c4 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city_choose);
        ButterKnife.bind(this);
        initCity("");
        //initChoose();
    }

    private void initChoose() {
        address.setTabAmount(4);
        address.setCities(c1);
        address.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                switch (tabPosition){
                    case 0:
                        addressSelector.setCities(c2);
                        AppUtils.showToast(getBaseContext(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                    case 1:
                        addressSelector.setCities(c3);
                        AppUtils.showToast(getBaseContext(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                    case 2:
                        addressSelector.setCities(c4);
                        AppUtils.showToast(getBaseContext(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                    case 3:
                        AppUtils.showToast(getBaseContext(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                }
            }
        });
        address.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
            @Override
            public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                switch (tab.getIndex()){
                    case 0:
                        addressSelector.setCities(c1);
                        break;
                    case 1:
                        addressSelector.setCities(c2);
                        break;
                    case 2:
                        addressSelector.setCities(c3);
                        break;
                }
            }

            @Override
            public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

            }
        });
    }

    private void initCity(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpUtils.doGet("http://47.106.184.121:9999/business-service/tool/list/regions?parent=" + s, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        mHandler.sendEmptyMessage(1);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            List<ChooseBean.ResponseListBean> beanList = ProjectsJsonUtils.readChooseBeans(response.body().string());//data是json字段获得data的值即对象数组
                            Message m = mHandler.obtainMessage();
                            m.obj = beanList;
                            m.what = 0;
                            m.sendToTarget();
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(1);
                        }
                    }
                });
            }
        }).start();
    }
    private final ChooseActivity.MyHandler mHandler = new ChooseActivity.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<ChooseActivity> mActivity;

        public MyHandler(ChooseActivity activity) {
            mActivity = new WeakReference<ChooseActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            ChooseActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        AppUtils.showToast(getBaseContext(), "地区加载成功");
                        List<ChooseBean.ResponseListBean> chooseList = (List<ChooseBean.ResponseListBean>) msg.obj;
                        for(int i =0;i<chooseList.size();i++){
                            Log.v("ccccccccc","  "+chooseList.get(i).getName());
                            c1.add(chooseList.get(i));
                            //c1.add("中国");
                        }
                        initChoose();
                        break;
                    case 1:
                        AppUtils.showToast(activity, "地区加载失败");
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        }
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
