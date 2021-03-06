package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.adapters.DuoXuanAdapter;
import com.mango.leo.zsproject.adapters.ListAndGirdDownAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardNinthItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardNinthItemActivity extends BaseCardActivity implements AdapterView.OnItemClickListener, View.OnClickListener, UpdateItemView {
    public static final int TYPE9 = 9;
    @Bind(R.id.imageView9_back)
    ImageView imageView9Back;
    @Bind(R.id.constraintLayout)
    RelativeLayout constraintLayout;
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
    @Bind(R.id.editText_other)
    EditText editTextOther;
    @Bind(R.id.textView_num)
    TextView textViewNum;
    private ListAndGirdDownAdapter adapter;
    private List<String> list1, list2, list3, list4;
    private PopupWindow popupWindow;
    private Dialog dialog;
    private int currentPosition1 = -1, currentPosition2 = -1, currentPosition3 = -1, currentPosition4 = -1;
    private Map<Integer, Boolean> gvChooseMap3 = new HashMap<Integer, Boolean>();
    private Map<Integer, Boolean> gvChooseMap4 = new HashMap<Integer, Boolean>();
    private DuoXuanAdapter adapter3, adapter4;
    int num = 200;//限制的最大字数
    private CardNinthItemBean cardNinthItemBean;
    private CardNinthItemBean bean9;
    private StringBuffer stringBuffer1, stringBuffer2;
    private List<String> listwhy, listtype;
    private UpdateItemPresenter updateItemPresenter;
    boolean flag = false;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_ninth_item);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //editTextOther.setFilters(new InputFilter[]{new InputFilter.LengthFilter(200)});
        editeNum();
        intDate();
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardNinthItemBean = new CardNinthItemBean();
        stringBuffer1 = new StringBuffer();
        stringBuffer2 = new StringBuffer();
        listwhy = new ArrayList<>();
        listtype = new ArrayList<>();
        flag = getIntent().getBooleanExtra("flag", false);
        EventBus.getDefault().register(this);
    }

    private void intDate() {
        list1 = new ArrayList<>();
        list1.add("招商");
        list1.add("引资");
        list1.add("招商引资");
        list2 = new ArrayList<>();
        list2.add("1000万以下");
        list2.add("1000万—5000万");
        list2.add("（含）5000万—1亿");
        list2.add("（含）1亿—10亿");
        list2.add("（含）10亿—50亿");
        list2.add("（含）50亿—100亿");
        list2.add("（含）100亿—500亿");
        list2.add("（含）500亿—1000亿");
        list2.add("（含）1000亿以上");
        list3 = new ArrayList<>();
        list3.add("不限");
        list3.add("合资");
        list3.add("独资");
        list3.add("合作");
        list3.add("租赁");
        list3.add("股权转让");
        list3.add("其它");
        list4 = new ArrayList<>();
        list4.add("资金类型");
        list4.add("个人资金");
        list4.add("企业资金");
        list4.add("天使投资");
        list4.add("VC投资");
        list4.add("PE投资");
        list4.add("小额贷款");
        list4.add("典当公司");
        list4.add("担保公司");
        list4.add("金融租赁");
        list4.add("投资公司");
        list4.add("商业银行");
        list4.add("基金公司");
        list4.add("证券公司");
        list4.add("信托公司");
        list4.add("资产管理");
        list4.add("其他资金");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card9EventBus(CardNinthItemBean bean) {
        if (flag) {
            if (gvChooseMap3 != null || gvChooseMap4 != null) {
                gvChooseMap3.clear();
                gvChooseMap4.clear();
            }
/*
        }*/
            Log.v("99999", bean.getMoney() + "--s---");
            bean9 = bean;
            for (int a = 0; a < list1.size(); a++) {
                if (bean.getMoshi().equals(list1.get(a))) {
                    currentPosition1 = a;
                }
            }
            text1.setText(bean.getMoshi());
            text2.setText(bean.getMoney());
            cardNinthItemBean.setMoshi(bean.getMoshi());
            cardNinthItemBean.setMoney(bean.getMoney());
            for (int b = 0; b < list2.size(); b++) {
                if (bean.getMoney().equals(list2.get(b))) {
                    currentPosition2 = b;
                }
            }
            List<String> l3 = new ArrayList<>();
            for (int k = 0; k < bean.getWhy().size(); k++) {
                stringBuffer1.append(bean.getWhy().get(k) + " ");
                l3.add(bean.getWhy().get(k));
                if (flag) {
                    for (int n = 0; n < list3.size(); n++) {
                        Log.v("4444499", "--1---");
                        if (bean.getWhy().get(k).toString().equals(list3.get(n))) {
                            Log.v("4444499", "--2---" + n);
                            gvChooseMap3.put(n, true);
                        }
                    }
                }
            }
            text3.setText(stringBuffer1);
            cardNinthItemBean.setWhy(l3);
            List<String> l4 = new ArrayList<>();
            for (int i = 0; i < bean.getType().size(); i++) {
                stringBuffer2.append(bean.getType().get(i) + " ");
                l4.add(bean.getType().get(i));
                if (flag) {
                    for (int j = 0; j < list4.size(); j++) {
                        if (bean.getType().get(i).toString().equals(list4.get(j))) {
                            gvChooseMap4.put(j, true);
                        }
                    }
                }
            }
            text4.setText(stringBuffer2);
            cardNinthItemBean.setType(l4);
            editTextOther.setText(bean.getQita());
/*        adapter.setList(bean.getItemImagePath());
        if (bean.getItemImagePath() != null) {
            selectList = bean.getItemImagePath();
        }*/
            //cardFirstItemBean.setProjectId(bean.getProjectId());
        }
    }

    public void initBean() {
        cardNinthItemBean.setQita(editTextOther.getText().toString());
    }

    private void editeNum() {
        editTextOther.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int number = num - editable.length();
                textViewNum.setText("剩余" + number + "字");
                selectionStart = editTextOther.getSelectionStart();
                selectionEnd = editTextOther.getSelectionEnd();
                if (temp.length() > num) {
                    editable.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    editTextOther.setText(editable);
                    editTextOther.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.imageView9_back, R.id.button9_save, R.id.textView_delete9, R.id.down_1, R.id.down_2, R.id.down_3, R.id.down_4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView9_back:
                Intent intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button9_save:
                initBean();
                Log.v("99999", cardNinthItemBean.toString() + "___");
                if (!"请选择".equals(text1.getText().toString()) && !"请选择".equals(text2.getText().toString()) && !"请选择".equals(text3.getText().toString()) && !"请选择".equals(text4.getText().toString()) && cardNinthItemBean != null) {
                    updateItemPresenter.visitUpdateItem(this, TYPE9, cardNinthItemBean);//更新后台数据
                    flag = false;
                } else {
                    AppUtils.showSnackar(button9Save, "必填项不能为空！");
                }
                break;
            case R.id.textView_delete9:
                text1.setText("");
                text2.setText("");
                text3.setText("");
                text4.setText("");
                editTextOther.setText("");
                break;
            case R.id.down_1:
                showPopupWindow(this, list1, 1);
                adapter.setCheckItem(currentPosition1);
                break;
            case R.id.down_2:
                showPopupWindow(this, list2, 2);
                adapter.setCheckItem(currentPosition2);
                break;//
            case R.id.down_3:
                showPopupWindow(this, list3, 3);
                adapter3.setCheckItem(gvChooseMap3);
                break;
            case R.id.down_4:
                showPopupWindow(this, list4, 4);
                adapter4.setCheckItem(gvChooseMap4);
                break;
        }
    }

    private void showPopupWindow(Context context, List<String> listDate, int i) {
        View view = null;
        if (i == 3) {//
            //设置要显示的view
            view = LayoutInflater.from(context).inflate(R.layout.mutil_girdview_default_down3, null);
            GridView gridView3 = view.findViewById(R.id.gv3);
            ImageView imageViewDelete3 = view.findViewById(R.id.imageView_delete3);
            Button buttonGo3 = view.findViewById(R.id.button_go3);
            imageViewDelete3.setOnClickListener(this);
            buttonGo3.setOnClickListener(this);
            //此处可按需求为各控件设置属性
            adapter3 = new DuoXuanAdapter(context, listDate);
            gridView3.setAdapter(adapter3);
            gridView3.setId(i);
            gridView3.setOnItemClickListener(this);
        }
        if (i == 4) {//多选
            //设置要显示的view
            view = LayoutInflater.from(context).inflate(R.layout.mutil_girdview_default_down4, null);
            GridView gridView4 = view.findViewById(R.id.gv4);
            ImageView imageViewDelete4 = view.findViewById(R.id.imageView_delete4);
            Button buttonGo4 = view.findViewById(R.id.button_go4);
            imageViewDelete4.setOnClickListener(this);
            buttonGo4.setOnClickListener(this);
            //此处可按需求为各控件设置属性
            adapter4 = new DuoXuanAdapter(context, listDate);
            gridView4.setAdapter(adapter4);
            gridView4.setId(i);
            gridView4.setOnItemClickListener(this);
        }
        if (i == 1 || i == 2) {
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
                text1.setTextColor(getResources().getColor(R.color.secondblack));
                cardNinthItemBean.setMoshi(list1.get(position));
                dialog.dismiss();
                break;
            case 2:
                currentPosition2 = position;
                text2.setText(list2.get(position));
                text2.setTextColor(getResources().getColor(R.color.secondblack));
                cardNinthItemBean.setMoney(list2.get(position));
                switch (position) {
                    case 0:
                        editor.putString("min", "");
                        editor.putString("max", "1000");
                        editor.putString("caption", list2.get(0));
                        editor.commit();
                        break;
                    case 1:
                        editor.putString("min", "1000");
                        editor.putString("max", "5000");
                        editor.putString("caption", list2.get(1));
                        editor.commit();
                        break;
                    case 2:
                        editor.putString("min", "5000");
                        editor.putString("max", "10000");
                        editor.putString("caption", list2.get(2));
                        editor.commit();
                        break;
                    case 3:
                        editor.putString("min", "10000");
                        editor.putString("max", "100000");
                        editor.putString("caption", list2.get(3));
                        editor.commit();
                        break;
                    case 4:
                        editor.putString("min", "100000");
                        editor.putString("max", "500000");
                        editor.putString("caption", list2.get(4));
                        editor.commit();
                        break;
                    case 5:
                        editor.putString("min", "500000");
                        editor.putString("max", "1000000");
                        editor.putString("caption", list2.get(5));
                        editor.commit();
                        break;
                    case 6:
                        editor.putString("min", "1000000");
                        editor.putString("max", "5000000");
                        editor.putString("caption", list2.get(6));
                        editor.commit();
                        break;
                    case 7:
                        editor.putString("min", "5000000");
                        editor.putString("max", "10000000");
                        editor.putString("caption", list2.get(7));
                        editor.commit();
                        break;
                    case 8:
                        editor.putString("min", "10000000");
                        editor.putString("max", "");
                        editor.putString("caption", list2.get(8));
                        editor.commit();
                        break;
                }
                dialog.dismiss();
                break;
            case 3:
                if (view.isActivated()) {
                    view.setActivated(false);
                    gvChooseMap3.put(position, false);
                } else {
                    view.setActivated(true);
                    gvChooseMap3.put(position, true);
                }
                adapter3.setCheckItem(gvChooseMap3);
                break;
            case 4:
                if (view.isActivated()) {
                    view.setActivated(false);
                    gvChooseMap4.put(position, false);
                } else {
                    view.setActivated(true);
                    gvChooseMap4.put(position, true);
                }
                adapter4.setCheckItem(gvChooseMap4);
                break;
        }
        initBean();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_delete3:
                dialog.dismiss();
                break;
            case R.id.button_go3:
                listwhy.clear();
                if (gvChooseMap3.size() == 0)//如果map为0或者，map里面的全是false表示一个也没有选中。
                {
                    AppUtils.showToast(this, "请选择领域");
                    return;
                }
                StringBuffer sb3 = new StringBuffer();
                //遍历map
                for (Map.Entry<Integer, Boolean> entry : gvChooseMap3.entrySet()) {
                    int strkey = entry.getKey();
                    boolean flag = entry.getValue();
                    if (flag == true) {
                        sb3.append(list3.get(strkey) + "  ");
                        Log.v("yyyyyy", strkey + "**********" + sb3);
                        listwhy.add(list3.get(strkey));
                    }
                    cardNinthItemBean.setWhy(listwhy);
                }
                if (sb3.length() > 0) {
                    //说明有爱好的内容。否则就提示选择爱好
                } else {
                    AppUtils.showToast(this, "请选择领域");
                    return;
                }
                text3.setText(sb3);
                text3.setTextColor(getResources().getColor(R.color.secondblack));
                dialog.dismiss();
                break;
            case R.id.imageView_delete4:
                dialog.dismiss();
                break;
            case R.id.button_go4:
                listtype.clear();
                if (gvChooseMap4.size() == 0)//如果map为0或者，map里面的全是false表示一个也没有选中。
                {
                    AppUtils.showToast(this, "请选择投资方式");
                    return;
                }
                StringBuffer sb4 = new StringBuffer();
                //遍历map
                for (Map.Entry<Integer, Boolean> entry : gvChooseMap4.entrySet()) {
                    int strkey = entry.getKey();
                    boolean flag = entry.getValue();
                    if (flag == true) {
                        sb4.append(list4.get(strkey) + "  ");
                        Log.v("yyyyyy", strkey + "**********" + sb4);
                        listtype.add(list4.get(strkey));
                        cardNinthItemBean.setType(listtype);
                    }
                }
                if (sb4.length() > 0) {
                    //说明有爱好的内容。否则就提示选择爱好
                } else {
                    AppUtils.showToast(this, "请选择资金类型");
                    return;
                }
                text4.setText(sb4);
                text4.setTextColor(getResources().getColor(R.color.secondblack));
                dialog.dismiss();
                break;
        }

    }

    @Override
    public void showUpdateStateView(final String string) {
        if (string == "SAVE SUCCESS"){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getApplicationContext(), string);
                    saveOk();
                }
            });
        }
        if (string == "SAVE FAILURE"){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AppUtils.showToast(getApplicationContext(), string);
                    saveErrorDialog();
                }
            });
        }

    }
    public void saveOk(){
        EventBus.getDefault().postSticky(cardNinthItemBean);
        Intent intent = new Intent(this, BusinessPlanActivity.class);
        startActivity(intent);
        finish();
    }
    private void saveErrorDialog() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(CardNinthItemActivity.this);
        alert.setTitle("招商引资");
        alert.setMessage("保存失败，请检查网络是否连接？");
        alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.create().show();
    }
    @Override
    public void showUpdateFailMsg(final String string) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getBaseContext(), string);
                saveErrorDialog();
            }
        });
    }
}
