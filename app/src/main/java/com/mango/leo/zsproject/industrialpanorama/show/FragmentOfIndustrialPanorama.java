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
import android.widget.Spinner;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.adapters.ListAndGirdDownAdapter;
import com.mango.leo.zsproject.industrialpanorama.fragments.CityIntroductionFragment;
import com.mango.leo.zsproject.industrialpanorama.fragments.InvestmentInformationFragment;
import com.mango.leo.zsproject.utils.ViewPageAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    TextView city;
    private List<String> mDatas;
    private ListAndGirdDownAdapter adapter;
    private Dialog dialog;
    private List<String> listDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.industrialpanorama, container, false);
        ButterKnife.bind(this, view);
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

    @OnClick(R.id.city)
    public void onViewClicked() {
        listDate = new ArrayList<>();
        listDate.add("深圳");
        listDate.add("北京");
        listDate.add("上海");
        listDate.add("广州");
        showPopupWindow(getActivity(), listDate);
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
        city.setText(listDate.get(position));
        dialog.dismiss();
    }
}
