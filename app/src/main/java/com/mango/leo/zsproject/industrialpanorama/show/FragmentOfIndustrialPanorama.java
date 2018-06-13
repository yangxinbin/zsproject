package com.mango.leo.zsproject.industrialpanorama.show;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.adapters.ListAndGirdDownAdapter;
import com.mango.leo.zsproject.industrialpanorama.bean.CityS;
import com.mango.leo.zsproject.industrialpanorama.fragments.CityIntroductionFragment;
import com.mango.leo.zsproject.industrialpanorama.fragments.InvestmentInformationFragment;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;


public class FragmentOfIndustrialPanorama extends Fragment implements AdapterView.OnItemClickListener {
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
    private ListAndGirdDownAdapter adapter;
    private Dialog dialog;
    private List<String> listDate;
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
        initDatas();
        init();
        return view;
    }


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


    private void showPopupWindow(Context context, List<String> listDate) {
        //设置要显示的view
        View view = LayoutInflater.from(context).inflate(R.layout.listview_default_down, null);
        //此处可按需求为各控件设置属性
        ListView listView = view.findViewById(R.id.lv);
        adapter = new ListAndGirdDownAdapter(context, listDate);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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
                    CityS cityS = new CityS();
                    cityS.setCity(cityString.substring(0,cityString.length()-1));
                    EventBus.getDefault().postSticky(cityS);
                   /* editor.putString("position", cityString)
                            .commit();*/
                }
                //地区
                if (district != null) {
                    districtString = String.valueOf(district);
                }
                city_t.setText("" + city + district);
            }

            @Override
            public void onCancel() {
                AppUtils.showToast(getContext(), "城市选择已取消");
            }
        });

        //显示
        mPicker.showCityPicker();
    }

    private void newShare() {
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
        oks.setImageUrl("http:\\/\\/04.imgmini.eastday.com\\/mobile\\/20180610\\/20180610002454_6ab3e88e0c01455e287a65eda6bf7b1a_8_mwpm_03200403.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://192.168.1.166:8080/jetc/#/iosactivityDetail/:"+cityString);
        // comment是我对这条分享的评论，仅在人人网使用
        oks.setComment("评论");
        // 启动分享GUI
        oks.show(getActivity());
    }

    @OnClick({R.id.image_msg2, R.id.city})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_msg2:
                newShare();
                break;
            case R.id.city:
                showSeleteCity();
                break;
        }
    }
}
