package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.adapter.ListAndGirdDownAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardNinthItemActivity extends BaseCardActivity implements AdapterView.OnItemClickListener {
    public static final int TYPE9 = 9;
    @Bind(R.id.imageView9_back)
    ImageView imageView9Back;
    @Bind(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @Bind(R.id.button9_save)
    Button button9Save;
    @Bind(R.id.text_1)
    TextView text1;
    @Bind(R.id.down_1)
    RelativeLayout down1;
    @Bind(R.id.text_2)
    TextView text2;
    @Bind(R.id.down_2)
    RelativeLayout down2;
    @Bind(R.id.text_3)
    TextView text3;
    @Bind(R.id.down_3)
    RelativeLayout down3;
    @Bind(R.id.text_4)
    TextView text4;
    @Bind(R.id.down_4)
    RelativeLayout down4;
    @Bind(R.id.all_1)
    ConstraintLayout all1;
    private ListAndGirdDownAdapter adapter;
    private List<String> list1, list2, list3, list4;
    private PopupWindow popupWindow;
    private Dialog dialog;
    private int currentPosition1 = -1, currentPosition2 = -1, currentPosition3 = -1, currentPosition4 = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_ninth_item);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.imageView9_back, R.id.button9_save, R.id.down_1, R.id.down_2, R.id.down_3, R.id.down_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView9_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button9_save:
                break;
            case R.id.down_1:
                list1 = new ArrayList<>();
                list1.add("不限");
                list1.add("股权投资");
                list1.add("债券投资");
                list1.add("其它");
                showPopupWindow(this, list1, 1);
                adapter.setCheckItem(currentPosition1);
                break;
            case R.id.down_2:
                list2 = new ArrayList<>();
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
            case R.id.down_3:
                list3 = new ArrayList<>();
                list3.add("合资");
                list3.add("独资");
                list3.add("PPP");
                list3.add("其它");
                showPopupWindow(this, list3, 3);
                adapter.setCheckItem(currentPosition3);
                break;
            case R.id.down_4:
                list4 = new ArrayList<>();
                list4.add("5000万以下");
                list4.add("（含）5000万—1亿");
                list4.add("（含）1亿—10亿");
                list4.add("（含）10亿—100亿");
                list4.add("（含）100亿以上");
                showPopupWindow(this, list4, 4);
                adapter.setCheckItem(currentPosition4);
                break;
        }
    }

    private void showPopupWindow(Context context, List<String> listDate, int i) {
        View view = null;
        if (i == 2){
            //设置要显示的view
            view = LayoutInflater.from(context).inflate(R.layout.girdview_default_down, null);
            GridView gridView = view.findViewById(R.id.gv);
            adapter = new ListAndGirdDownAdapter(context, listDate,i);
            gridView.setAdapter(adapter);
            gridView.setId(i);
            gridView.setOnItemClickListener(this);
        }else {
            //设置要显示的view
            view = LayoutInflater.from(context).inflate(R.layout.listview_default_down, null);
            //此处可按需求为各控件设置属性
            ListView listView = view.findViewById(R.id.lv);
            adapter = new ListAndGirdDownAdapter(context, listDate);
            listView.setAdapter(adapter);
            listView.setId(i);
            listView.setOnItemClickListener(this);
        }
        /*popupWindow = new PopupWindow(view);
        //设置弹出窗口大小
        popupWindow.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        //必须设置以下两项，否则弹出窗口无法取消
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//不会遮罩
        //设置动画效果
        popupWindow.setAnimationStyle(R.style.AnimBottom);
        //设置显示位置,findViewById获取的是包含当前整个页面的view
        popupWindow.showAtLocation(findViewById(R.id.all), Gravity.BOTTOM, 0, 0);*/
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
                currentPosition1 = position;
                text1.setText(list1.get(position));
                dialog.dismiss();
                break;
            case 2:
                currentPosition2 = position;
                text2.setText(list2.get(position));
                dialog.dismiss();
                break;
            case 3:
                currentPosition3 = position;
                text3.setText(list3.get(position));
                dialog.dismiss();
                break;
            case 4:
                currentPosition4 = position;
                text4.setText(list4.get(position));
                dialog.dismiss();
                break;
        }
    }
}
