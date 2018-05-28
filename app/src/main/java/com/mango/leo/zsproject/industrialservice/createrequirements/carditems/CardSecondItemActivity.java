package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.adapter.GirdDownAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardSecondItemActivity extends BaseCardActivity implements UpdateItemView, AdapterView.OnItemClickListener, View.OnClickListener, AbsListView.MultiChoiceModeListener {
    public static final int TYPE2 = 2;
    @Bind(R.id.imageView2_back)
    ImageView imageView2Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button2_save)
    Button buttonSave;
    @Bind(R.id.chanye)
    RelativeLayout chanye;
    @Bind(R.id.lingyu)
    RelativeLayout lingyu;
    @Bind(R.id.textView_chanye)
    TextView textViewChanye;
    @Bind(R.id.textView_lingyu)
    TextView textViewLingyu;
    private UpdateItemPresenter updateItemPresenter;
    private CardSecondItemBean cardSecondItemBean;
    private GirdDownAdapter adapter;
    private Dialog dialog;
    private List<String> list1, list2, date2;
    private String date1;
    private Map map2 ;

    private int currentPosition1 = -1, currentPosition2 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_second_item);
        ButterKnife.bind(this);
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardSecondItemBean = new CardSecondItemBean();
    }

    @OnClick({R.id.imageView2_back, R.id.button2_save, R.id.chanye, R.id.lingyu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView2_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2_save:
                updateItemPresenter.visitUpdateItem(this, TYPE2, cardSecondItemBean);//更新后台数据
                break;
            case R.id.chanye:
                list1 = new ArrayList<>();
                list1.add("不限");
                list1.add("种子轮");
                list1.add("天使轮");
                list1.add("pre-A轮");
                list1.add("A轮");
                list1.add("B轮");
                list1.add("C轮");
                list1.add("D轮");
                list1.add("E轮");
                list1.add("新四板");
                list1.add("IPO上市");
                list1.add("其它");
                showPopupWindow(this, list1, 1);
                adapter.setCheckItem(currentPosition1);
                break;
            case R.id.lingyu:
                list2 = new ArrayList<>();
                //list2 = new HashMap();
                list2.add("不限");
                list2.add("种子轮");
                list2.add("天使轮");
                list2.add("pre-A轮");
                list2.add("A轮");
                list2.add("B轮");
                list2.add("C轮");
                list2.add("D轮");
                list2.add("E轮");
                list2.add("新四板");
                list2.add("IPO上市");
                list2.add("其它");
                showPopupWindow(this, list2, 2);
                adapter.setCheckItem(currentPosition2);
                break;
        }
    }

    @Override
    public void showUpdateStateView(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }

    @Override
    public void showUpdateFailMsg(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    private void showPopupWindow(Context context, List<String> listDate, int i) {
        View view = null;
        if (i == 1) {//单选
            //设置要显示的view
            view = LayoutInflater.from(context).inflate(R.layout.single_girdview_default_down, null);
            GridView gridView = view.findViewById(R.id.gv);
            ImageView imageViewDelete1 = view.findViewById(R.id.imageView_delete1);
            Button buttonGo1 = view.findViewById(R.id.button_go1);
            imageViewDelete1.setOnClickListener(this);
            buttonGo1.setOnClickListener(this);
            adapter = new GirdDownAdapter(context, listDate, i);
            gridView.setAdapter(adapter);
            gridView.setId(i);
            gridView.setOnItemClickListener(this);
        }
        if (i == 2) {//多选
            //设置要显示的view
            view = LayoutInflater.from(context).inflate(R.layout.mutil_girdview_default_down, null);
            GridView gridView = view.findViewById(R.id.gv);
            //此处可按需求为各控件设置属性
            gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
            adapter = new GirdDownAdapter(context, listDate,i);
            gridView.setAdapter(adapter);
            gridView.setId(i);
            gridView.setMultiChoiceModeListener(this);
        }
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
        switch (adapterView.getId()) {
            case 1:
                date1 = list1.get(position);
                currentPosition1 = position;
                adapter.setCheckItem(position);
                break;
            case 2:
                //date2 = list2.get(position);
                //currentPosition2 = position;
                //adapter.chiceState(position);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_delete1:
                dialog.dismiss();
                break;
            case R.id.button_go1:
                Log.v("yyyyyy", "****date1****" + date1);
                textViewChanye.setText(date1);
                dialog.dismiss();
                break;
        }
    }

    @Override
    public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

    }

    @Override
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    @Override
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    @Override
    public void onDestroyActionMode(ActionMode actionMode) {

    }
}
