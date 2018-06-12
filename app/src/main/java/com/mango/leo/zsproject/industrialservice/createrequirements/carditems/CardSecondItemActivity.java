package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.adapters.DuoXuanAdapter;
import com.mango.leo.zsproject.adapters.GirdDownAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.ChanyLingyuBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.ResponseListBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CardSecondItemActivity extends BaseCardActivity implements UpdateItemView, AdapterView.OnItemClickListener, View.OnClickListener {
    public static final int TYPE2 = 2;
    @Bind(R.id.imageView2_back)
    ImageView imageView2Back;
    @Bind(R.id.constraintLayout)
    RelativeLayout constraintLayout;
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
    private DuoXuanAdapter adapter2;
    private Dialog dialog;
    private List<String> list1, list2, date2;
    private String date1;
    private Map map2;
    private Map<Integer, Boolean> gvChooseMap = new HashMap<Integer, Boolean>();
    private int currentPosition1 = -1;
    private String projectId;
    //private List<Integer> currentPosition2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_second_item);
        ButterKnife.bind(this);
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardSecondItemBean = new CardSecondItemBean();
        list1 = new ArrayList<>();
        getChan("");
    }

    private void getChan(String parm) {
        String url = "http://192.168.1.166:9999/business-service/tool//list/industries?parent="+parm;
        HttpUtils.doGet(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               // listener.onFailure("FAILURE", e);
                Log.v("2222222222222222","__--_");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final List<ChanyLingyuBean.ResponseListBean> bean = ProjectsJsonUtils.readJsonCBeans(response.body().string(), "");//data是json字段获得data的值即对象数组
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0;i<bean.size();i++)
                            list1.add(String.valueOf(bean.get(i).getName()));
                        }
                    });
                    Log.v("2222222222222222","__vvvvv_"+bean.toString());
                } catch (Exception e) {
                    Log.e("yyyyy", "Exception = " + e);
                }
            }
        });
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
                /*list1.add("不限");
                list1.add("金融");
                list1.add("企业服务");
                list1.add("技术");
                list1.add("汽车交通");
                list1.add("房产居家");
                list1.add("医疗健康");
                list1.add("硬件");
                list1.add("体育");
                list1.add("其它");*/
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
                adapter2.setCheckItem(gvChooseMap);
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
            ImageView imageViewDelete2 = view.findViewById(R.id.imageView_delete2);
            Button buttonGo2 = view.findViewById(R.id.button_go2);
            imageViewDelete2.setOnClickListener(this);
            buttonGo2.setOnClickListener(this);
            //此处可按需求为各控件设置属性
            adapter2 = new DuoXuanAdapter(context, listDate);
            gridView.setAdapter(adapter2);
            gridView.setId(i);
            gridView.setOnItemClickListener(this);
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
                if (view.isActivated()) {
                    view.setActivated(false);
                    gvChooseMap.put(position, false);
                } else {
                    view.setActivated(true);
                    gvChooseMap.put(position, true);
                }
                adapter2.setCheckItem(gvChooseMap);
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
            case R.id.imageView_delete2:
                dialog.dismiss();
                break;
            case R.id.button_go2:
                Log.v("yyyyyy", "****date1****" + date1);

                if (gvChooseMap.size() == 0)//如果map为0或者，map里面的全是false表示一个也没有选中。
                {
                    AppUtils.showToast(this, "请选择领域");
                    return;
                }
                StringBuffer sb = new StringBuffer();
                //遍历map
                for (Map.Entry<Integer, Boolean> entry : gvChooseMap.entrySet()) {
                    int strkey = entry.getKey();
                    boolean flag = entry.getValue();
                    if (flag == true) {
                        sb.append(list2.get(strkey) + "  ");
                        Log.v("yyyyyy", strkey + "**********" + sb);
                    }
                }
                if (sb.length() > 0) {
                    //说明有爱好的内容。否则就提示选择爱好
                } else {
                    AppUtils.showToast(this, "请选择领域");
                    return;
                }
                textViewLingyu.setText(sb);
                dialog.dismiss();
                break;
        }
    }
}
