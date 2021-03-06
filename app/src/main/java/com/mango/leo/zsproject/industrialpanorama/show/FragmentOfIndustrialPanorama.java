package com.mango.leo.zsproject.industrialpanorama.show;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.adapters.ListAndGirdDownAdapter;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.industrialpanorama.address.AddressSelector;
import com.mango.leo.zsproject.industrialpanorama.address.CityInterface;
import com.mango.leo.zsproject.industrialpanorama.address.OnItemClickListener;
import com.mango.leo.zsproject.industrialpanorama.bean.ChooseBean;
import com.mango.leo.zsproject.industrialpanorama.bean.CityS;
import com.mango.leo.zsproject.industrialpanorama.fragments.CityIntroductionFragment;
import com.mango.leo.zsproject.industrialpanorama.fragments.InvestmentInformationFragment;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class FragmentOfIndustrialPanorama extends Fragment {
    @Bind(R.id.tabLayout2)
    TabLayout tabLayout2;
    @Bind(R.id.image_msg2)
    ImageView imageMsg2;
    @Bind(R.id.viewPager2)
    ViewPager viewPager2;
    List<Fragment> mfragments = new ArrayList<Fragment>();
    @Bind(R.id.imageView_chcity)
    ImageView imageViewChcity;
    @Bind(R.id.city)
    TextView city_t;
    private List<String> mDatas;
    /*    private ListAndGirdDownAdapter adapter;
        private Dialog dialog;
        private List<String> listDate;
        private CityPickerView mPicker;
        private String provinceString, cityString, districtString;
        private ArrayList<String> c1 = new ArrayList<>();
        private ArrayList<String> c2 = new ArrayList<>();
        private ArrayList<String> c3 = new ArrayList<>();
        private ArrayList<String> c4 = new ArrayList<>();*/
    private String cityString;
    private CityS cityBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.industrialpanorama, container, false);
        ButterKnife.bind(this, view);
        cityString = "深圳";
        EventBus.getDefault().register(this);
        initDatas();
        init();
//        initCity("");
        return view;
    }

/*    private void initCity(final String s) {
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
                            //List<ChooseBean.ResponseListBean> beanList = ProjectsJsonUtils.readChooseBeans(response.body().string());//data是json字段获得data的值即对象数组
                            Message m = mHandler.obtainMessage();
                           // m.obj = beanList;
                            m.what = 0;
                            m.sendToTarget();
                        } catch (Exception e) {
                            mHandler.sendEmptyMessage(1);
                        }
                    }
                });
            }
        }).start();
    }*/

/*    private final FragmentOfIndustrialPanorama.MyHandler mHandler = new FragmentOfIndustrialPanorama.MyHandler(this);

    private class MyHandler extends Handler {
        private final WeakReference<FragmentOfIndustrialPanorama> mActivity;

        public MyHandler(FragmentOfIndustrialPanorama activity) {
            mActivity = new WeakReference<FragmentOfIndustrialPanorama>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            FragmentOfIndustrialPanorama activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        //AppUtils.showToast(getActivity(), "地区加载成功");
                        List<ChooseBean.ResponseListBean> chooseList = (List<ChooseBean.ResponseListBean>) msg.obj;
                        for(int i =0;i<1;i++){
                            //c1.add(chooseList.get(i).getName());
                            c1.add("中国");
                        }
                        break;
                    case 1:
                        AppUtils.showToast(getActivity(), "地区加载失败");
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        }
    }*/

    private void initDatas() {
        mDatas = new ArrayList<String>(Arrays.asList("       城市介绍       ", "       招商信息       "/*, "招商计划", "定制需求"*/));
    }

    private void init() {
        tabLayout2.setTabMode(TabLayout.MODE_FIXED);
        ViewPageAdapter vp = new ViewPageAdapter(getFragmentManager(), mfragments, mDatas);
        tabLayout2.setupWithViewPager(viewPager2);
        mfragments.add(new CityIntroductionFragment());
        mfragments.add(new InvestmentInformationFragment());
        // mfragments.add(new InvestmentPlanFragment());
        // mfragments.add(new CustomRequirementsFragment());
        viewPager2.setAdapter(vp);
        viewPager2.setCurrentItem(0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CityS bean) {
        if (bean == null){
            return;
        }
        cityString = bean.getCity();
        city_t.setText(bean.getCity()+bean.getDistrict());
    }
/*    private void showPopupWindow(Context context) {
        //设置要显示的view
        View view = LayoutInflater.from(context).inflate(R.layout.city_choose, null);
        Button button_city_re = view.findViewById(R.id.button_city_re);
        Button button_city_ok = view.findViewById(R.id.button_city_ok);
        ImageView imageView_delete_city = view.findViewById(R.id.imageView_delete_city);

*//*        Spinner spinner_all = view.findViewById(R.id.spinner_all);
        Spinner spinner_p = view.findViewById(R.id.spinner_p);
        Spinner spinner_c = view.findViewById(R.id.spinner_c);
        Spinner spinner_s = view.findViewById(R.id.spinner_s);
        spinner_p.setOnItemSelectedListener(this);
        spinner_c.setOnItemSelectedListener(this);
        spinner_s.setOnItemSelectedListener(this);*//*

        AddressSelector addressSelector = (AddressSelector) view.findViewById(R.id.address);
        addressSelector.setTabAmount(4);
        addressSelector.setCities(c1);
        addressSelector.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                switch (tabPosition){
                    case 0:
                        addressSelector.setCities(c2);
                        AppUtils.showToast(getActivity(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                    case 1:
                        addressSelector.setCities(c3);
                        AppUtils.showToast(getActivity(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                    case 2:
                        addressSelector.setCities(c4);
                        AppUtils.showToast(getActivity(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                    case 3:
                        AppUtils.showToast(getActivity(),"tabPosition ："+tabPosition+" "+city.getCityName());
                        break;
                }
            }
        });


        imageView_delete_city.setOnClickListener(this);
        button_city_re.setOnClickListener(this);
        button_city_ok.setOnClickListener(this);

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
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        city_t.setText(listDate.get(position));
        dialog.dismiss();
    }*/

    private void newShare(String cityString) {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        //oks.setTitle(title);
        // titleUrl QQ和QQ空间跳转链接
        //oks.setTitleUrl(newsurl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("城市介绍");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImageUrl(Urls.HOST + "/user-service/user/get/file?fileId=5b1f70641233c531ec362024");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://47.106.184.121/jetc/#/iosCityIntroduction/:" + cityString);
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("评论");
        // 启动分享GUI
        oks.show(getActivity());
    }

    @OnClick({R.id.image_msg2, R.id.city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_msg2:
                newShare(cityString);
                break;
            case R.id.city:
                //AppUtils.showToast(getActivity(),"tabPosition");
                //showPopupWindow(getActivity());
                Intent intent = new Intent(getActivity(), ChooseActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

/*    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_delete_city:
                dialog.dismiss();
                break;
            case R.id.button_city_re:
                break;
            case R.id.button_city_ok:

                dialog.dismiss();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (view.getId()) {
            case R.id.spinner_p:
                break;
            case R.id.spinner_c:
                break;
            case R.id.spinner_s:
                break;
            default:
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}
