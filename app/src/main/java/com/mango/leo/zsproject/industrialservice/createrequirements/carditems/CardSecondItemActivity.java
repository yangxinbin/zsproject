package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.adapters.DuoXuanAdapter;
import com.mango.leo.zsproject.adapters.GirdDownAdapter;
import com.mango.leo.zsproject.industrialservice.createrequirements.BusinessPlanActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.ChanyLingyuBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.basecard.BaseCardActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBeanObj;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenter;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.presenter.UpdateItemPresenterImpl;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.view.UpdateItemView;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.StaggeredGridView;
import com.mango.leo.zsproject.utils.ACache;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.URLEncoderURI;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
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

public class CardSecondItemActivity extends BaseCardActivity implements UpdateItemView, View.OnClickListener, StaggeredGridView.OnItemClickListener {
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
    private CardSecondItemBeanObj.CardSecondItemBean cardSecondItemBean;
    private GirdDownAdapter adapter;
    private DuoXuanAdapter adapter2;
    private Dialog dialog;
    private List<String> list1, list2, date2;
    private String date1;
    private Map map2;
    private Map<Integer, Boolean> gvChooseMap = new HashMap<Integer, Boolean>();
    private int currentPosition1 = -1;
    private String projectId;
    private int mtype = 0;
    private List<CardSecondItemBeanObj.CardSecondItemBean> beans2;
    private int position;
    private List<String> bl2;
    private boolean flag = false;
    private ACache mCache;
    private boolean first;
    private int type;
    private CardSecondItemBeanObj card2Bean;

    //private List<Integer> currentPosition2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_second_item);
        ButterKnife.bind(this);
        updateItemPresenter = new UpdateItemPresenterImpl(this);
        cardSecondItemBean = new CardSecondItemBeanObj.CardSecondItemBean();
        card2Bean = new CardSecondItemBeanObj();
        beans2 = new ArrayList<>();
        if (beans2 != null) {
            beans2.clear();
        }
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        position = getIntent().getIntExtra("position", 0);
        bl2 = new ArrayList<>();
        if (flag) {
            getChan(textViewChanye.getText().toString(), 1);//接着请求
        }else {
            getChan("", 0);
        }
/*        if (!textViewChanye.getText().toString().startsWith("请")){
            getChan(textViewChanye.getText().toString(), 1);//接着请求
        }*/
        flag = getIntent().getBooleanExtra("flag", false);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card2EventBus(CardSecondItemBeanObj bean) {
        if (flag) {
            beans2 = bean.getContent();
            if (beans2.size() == position) {
                return;
            }
            textViewChanye.setText(bean.getContent().get(position).getChangye());
            StringBuffer stringBufferL = new StringBuffer();
            for (int i = 0; i < bean.getContent().get(position).getLingyuList().size(); i++) {
                stringBufferL.append(bean.getContent().get(position).getLingyuList().get(i)+" ");
            }
            textViewLingyu.setText(stringBufferL);
           // getChan(beans2.get(position).getChanye(), 1);//接着请求
        /*editTextPhoneNumber.setText(bean.get(position).getPhoneNumber());
        editTextPosition.setText(bean.get(position).getPosition());
        editTextEmail.setText(bean.get(position).getEmail());*/
            // cardFourthItemBean.setProjectId(bean.get(position).getProjectId());
        }
    }

    private void initDate() {
        cardSecondItemBean.setChangye(textViewChanye.getText().toString());
        cardSecondItemBean.setLingyuList(bl2);
        if (beans2.size() == position) {
            beans2.add(position, cardSecondItemBean);//第几个修改第几个
        } else {
            beans2.set(position, cardSecondItemBean);//第几个修改第几个
        }
        card2Bean.setContent(beans2);
    }

    private void getChan(String parm, final int type) {
        this.type = type;
        if (type == 0) {
            parm = "";
        }
        if (type == 1) {
            try {
                parm = URLEncoderURI.encode(parm, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        final String finalParm = parm;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = Urls.HOST + "/business-service/tool//list/industries?parent=" + finalParm;
                HttpUtils.doGet(url, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        // listener.onFailure("FAILURE", e);
                        Log.v("2222222222222222", "__--_");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try {
                            List<ChanyLingyuBean.ResponseListBean> bean = null;
                            if (type == 0) {
                                bean = ProjectsJsonUtils.readJsonCBeans(response.body().string(), getBaseContext());//data是json字段获得data的值即对象数组
                            }
                            if (type == 1) {
                                bean = ProjectsJsonUtils.readJsonCBeans(response.body().string());//data是json字段获得data的值即对象数组
                            }
                            /*runOnUiThread(new Runnable() {
                                @Override
                                public void run() {*/
                            Message message = mHandler.obtainMessage();
                            message.obj = bean;
                            message.what = 0;
                            mHandler.sendMessage(message);
                                    /*for (int i = 0;i<bean.size();i++){
                                        if (type == 0){
                                            list1.add(String.valueOf(bean.get(i).getName()));
                                        }if (type == 1){
                                            list2.add(String.valueOf(bean.get(i).getName()));
                                        }
                                    }*/
                             /*   }
                            });*/
                            //Log.v("2222222222222222", "__vvvvv_" + bean.toString());
                        } catch (Exception e) {
                            Log.e("yyyyy", "Exception = " + e);
                        }
                    }
                });
            }
        }).start();
    }

    private final CardSecondItemActivity.MyHandler mHandler = new CardSecondItemActivity.MyHandler(this);

    @Override
    public void onItemClick(StaggeredGridView parent, View view, int position, long id) {
        switch (parent.getId()) {
            case 1:
                date1 = list1.get(position);
                currentPosition1 = position;
                adapter.setCheckItem(position);
                textViewChanye.setText(date1);
                dialog.dismiss();
                getChan(textViewChanye.getText().toString(), 1);//接着请求
                break;
            case 2:
                if (first){

                }
                if (view.isPressed()) {
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

    private class MyHandler extends Handler {
        private final WeakReference<CardSecondItemActivity> mActivity;

        public MyHandler(CardSecondItemActivity activity) {
            mActivity = new WeakReference<CardSecondItemActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CardSecondItemActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        list1.clear();
                        list2.clear();
                        List<ChanyLingyuBean.ResponseListBean> bean2List = (List<ChanyLingyuBean.ResponseListBean>) msg.obj;
                        for (int i = 0; i < bean2List.size(); i++) {
                            if (type == 0) {
                                list1.add(String.valueOf(bean2List.get(i).getName()));
                            }
                            if (type == 1) {
                                list2.add(String.valueOf(bean2List.get(i).getName()));
                            }
                        }
                        // AppUtils.showToast(getActivity(), "获取招商信息失败");
                        break;
                    case 1:
                        // AppUtils.showToast(getActivity(), "获取招商信息成功");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @OnClick({R.id.imageView2_back, R.id.button2_save, R.id.chanye, R.id.lingyu,R.id.textView_delete2})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView2_back:
                intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2_save:
                initDate();
                Log.v("222222222222", textViewChanye.getText().length() + "--initDate--" + beans2.toString());
                if (!textViewChanye.getText().toString().startsWith("请") && !textViewLingyu.getText().toString().startsWith("请") && cardSecondItemBean != null) {
                    flag = false;
                    updateItemPresenter.visitUpdateItem(this, TYPE2, beans2);//更新后台数据
                    EventBus.getDefault().postSticky(card2Bean);
                    Log.v("2222222222111", "" + beans2.size());
                    EventBus.getDefault().unregister(this);
                    intent = new Intent(this, BusinessPlanActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AppUtils.showToast(this, "请添加产业领域");
                }
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
                list1.clear();
                mCache = ACache.get(this);
                List<ChanyLingyuBean.ResponseListBean> beanC = ProjectsJsonUtils.readJsonCBeans(mCache.getAsString("changye"));
                for (int i = 0; i < beanC.size(); i++) {
                    list1.add(String.valueOf(beanC.get(i).getName()));
                }
                Log.v("222222222", mCache.getAsString("changye") + "----" + list1.size());
                showPopupWindow(this, list1, 1);
                adapter.setCheckItem(currentPosition1);
                break;
            case R.id.lingyu:
                //list2 = new HashMap();
/*                list2.add("不限");
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
                list2.add("其它");*/
                Log.v("2222222222222","!!!!!!"+textViewChanye.getText().toString());
                getChan(textViewChanye.getText().toString(), 1);//接着请求
                if (list2.size() != 0) {
                    showPopupWindow(this, list2, 2);
                    adapter2.setCheckItem(gvChooseMap);
                }
                break;
            case R.id.textView_delete2:
                beans2.remove(position);
                EventBus.getDefault().postSticky(card2Bean);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        updateItemPresenter.visitUpdateItem(getBaseContext(), TYPE2, beans2);//更新后台数据
                    }
                }).start();
                intent = new Intent(this, BusinessPlanActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    @Override
    public void showUpdateStateView(final String string) {
/*        Intent intent = new Intent(this, BusinessPlanActivity.class);
        startActivity(intent);
        finish();*/
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AppUtils.showToast(getApplicationContext(), string);
                // finish();
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
            StaggeredGridView gridView = view.findViewById(R.id.gv);
            ImageView imageViewDelete1 = view.findViewById(R.id.imageView_delete1);
            //Button buttonGo1 = view.findViewById(R.id.button_go1);
            imageViewDelete1.setOnClickListener(this);
            //buttonGo1.setOnClickListener(this);
            adapter = new GirdDownAdapter(context, listDate, i);
            gridView.setAdapter(adapter);
            gridView.setId(i);
            gridView.setOnItemClickListener(this);
        }
        if (i == 2) {//多选
            //设置要显示的view
            view = LayoutInflater.from(context).inflate(R.layout.mutil_girdview_default_down, null);
            StaggeredGridView gridView = view.findViewById(R.id.gv);
            ImageView imageViewDelete2 = view.findViewById(R.id.imageView_delete2);
            Button buttonGo2 = view.findViewById(R.id.button_go2);
            Button buttonRe2 = view.findViewById(R.id.button_re);
            imageViewDelete2.setOnClickListener(this);
            buttonGo2.setOnClickListener(this);
            buttonRe2.setOnClickListener(this);
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
/*
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
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_delete1:
                dialog.dismiss();
                break;
/*            case R.id.button_go1:
                Log.v("yyyyyy", "****date1****" + date1);
                break;*/
            case R.id.imageView_delete2:
                dialog.dismiss();
                break;
            case R.id.button_re:
/*                for (int i = 0; i < list2.size(); i++) {
                    gvChooseMap.put(i, false);
                }*/
                gvChooseMap.clear();
                adapter2.setCheckItem(gvChooseMap);
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
                if(bl2 != null){
                    bl2.clear();
                }
                for (Map.Entry<Integer, Boolean> entry : gvChooseMap.entrySet()) {
                    int strkey = entry.getKey();
                    boolean flag = entry.getValue();
                    if (flag == true) {
                        sb.append(list2.get(strkey) + " ");
                        bl2.add(list2.get(strkey));
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
