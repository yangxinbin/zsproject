package com.mango.leo.zsproject.eventexhibition.show;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.bean.ErrorBean;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.util.adderView;
import com.mango.leo.zsproject.industrialservice.createrequirements.util.ProjectsJsonUtils;
import com.mango.leo.zsproject.personalcenter.bean.MyEventBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.DateUtil;
import com.mango.leo.zsproject.utils.HttpUtils;
import com.mango.leo.zsproject.utils.Urls;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EventRegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.textView_where)
    TextView textViewWhere;
    @Bind(R.id.textView_time)
    TextView textViewTime;
    @Bind(R.id.textView_zhubannf)
    TextView textViewZhubannf;
    @Bind(R.id.textView_xiuban)
    TextView textViewXiuban;
    @Bind(R.id.editText1)
    EditText editText1;
    @Bind(R.id.editText2)
    EditText editText2;
    @Bind(R.id.editText3)
    EditText editText3;
    @Bind(R.id.editText4)
    EditText editText4;
    @Bind(R.id.editText5)
    EditText editText5;
    @Bind(R.id.event_nofree)
    RelativeLayout eventNofree;
    @Bind(R.id.img_piao1)
    ImageView imgPiao1;
    @Bind(R.id.event_free)
    RelativeLayout eventFree;
    @Bind(R.id.howtoplay)
    RelativeLayout howtoplay;
    @Bind(R.id.sign_up)
    Button signUp;
    @Bind(R.id.textView_eN)
    TextView textView_eN;
    @Bind(R.id.tv_signle)
    TextView tvSignle;
    @Bind(R.id.tv_all)
    TextView tvAll;
    @Bind(R.id.storage)
    adderView storage;
    @Bind(R.id.textView_pay)
    TextView textViewPay;
    private int position;
    String pattern = "yyyy-MM-dd HH:mm:ss";
    private EventBean.ResponseObjectBean.ContentBean bean1;
    private SharedPreferences sharedPreferences;
    private int tickNum;
    private int price = 0;
    private Dialog dialog;
    private MyEventBean.ResponseObjectBean.ContentBean.EntityBean bean2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);
        sharedPreferences = getSharedPreferences("CIFIT", MODE_PRIVATE);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initAmountView();
    }

    private void initAmountView() {
        storage.setOnValueChangeListene(new adderView.OnValueChangeListener() {
            @Override
            public void onValueChange(int value) {
                tvAll.setText(price * value + "元");
                tickNum = value;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventRegistrationEventBus(EventBean.ResponseObjectBean.ContentBean bean) {
        bean1 = bean;
        if (bean != null) {
            if (bean.getPrice() == 0) {//免费
                tickNum = 1;
                signUp.setEnabled(true);//使能按钮
                eventNofree.setVisibility(View.GONE);
                howtoplay.setVisibility(View.GONE);
                eventFree.setVisibility(View.VISIBLE);
            } else {
                //signUp.setEnabled(false);
                eventFree.setVisibility(View.GONE);
                eventNofree.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.VISIBLE);
            }
            Log.v("yxbb", bean.getPrice() + "__y___" + bean.getName());
            textView_eN.setText(bean.getName());
            textViewWhere.setText(bean.getLocation().getCity() + bean.getLocation().getDistrict() + bean.getLocation().getAddress());
            textViewTime.setText(DateUtil.getDateToString(bean.getStartTime(), pattern) + "至" + DateUtil.getDateToString(bean.getEndTime(), pattern));
            textViewZhubannf.setText(bean.getOrganizer());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < bean.getCoorganizers().size(); i++) {
                stringBuffer.append(bean.getCoorganizers().get(i) + " ");
            }
            textViewXiuban.setText(stringBuffer);
            price = bean.getPrice();
            tvSignle.setText(String.valueOf(price) + "元");
            tvAll.setText(String.valueOf(price) + "元");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventRegistrationEventBus(MyEventBean.ResponseObjectBean.ContentBean.EntityBean bean) {//收藏列表值
        bean2 = bean;
        if (bean != null) {
            if (bean.getPrice() == 0) {//免费
                tickNum = 1;
                signUp.setEnabled(true);//使能按钮
                eventNofree.setVisibility(View.GONE);
                howtoplay.setVisibility(View.GONE);
                eventFree.setVisibility(View.VISIBLE);
            } else {
                //signUp.setEnabled(false);
                eventFree.setVisibility(View.GONE);
                eventNofree.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.VISIBLE);
            }
            Log.v("yxbb", bean.getPrice() + "__y___" + bean.getName());
            textView_eN.setText(bean.getName());
            textViewWhere.setText(bean.getLocation().getCity() + bean.getLocation().getDistrict() + bean.getLocation().getAddress());
            textViewTime.setText(DateUtil.getDateToString(bean.getStartTime(), pattern) + "至" + DateUtil.getDateToString(bean.getEndTime(), pattern));
            textViewZhubannf.setText(bean.getOrganizer());
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < bean.getCoorganizers().size(); i++) {
                stringBuffer.append(bean.getCoorganizers().get(i) + " ");
            }
            textViewXiuban.setText(stringBuffer);
            price = bean.getPrice();
            tvSignle.setText(String.valueOf(price) + "元");
            tvAll.setText(String.valueOf(price) + "元");
        }
    }

    @OnClick({R.id.imageView_back, R.id.howtoplay, R.id.sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.howtoplay:
                showPopupWindow(this);
                break;
            case R.id.sign_up:
                if (!TextUtils.isEmpty(editText1.getText().toString()) && !TextUtils.isEmpty(editText2.getText().toString()) && !TextUtils.isEmpty(editText3.getText().toString()) && !TextUtils.isEmpty(editText4.getText().toString()) && !TextUtils.isEmpty(editText5.getText().toString())) {
                    registration();//报名
                } else {
                    AppUtils.showSnackar(signUp, "报名信息不能为空！");
                }
                break;
        }
    }

    private void showPopupWindow(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.pay_down, null);
        RelativeLayout wechat_pay = view.findViewById(R.id.wechat_pay);
        RelativeLayout alipay_pay = view.findViewById(R.id.alipay_pay);
        wechat_pay.setOnClickListener(this);
        alipay_pay.setOnClickListener(this);
        dialog = new Dialog(context);
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

    private void registration() {
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        //String eventStr = gs.toJson(bean1.getResponseObject().getContent().get(position));
        if (bean1 != null) {
            Log.v("llll", "--1");
            mapParams.put("eventId",  bean1.getId());
        }else {
            Log.v("llll", "--2");
            mapParams.put("eventId",  bean2.getId());
        }
       // mapParams.put("eventId", /*eventStr*/ bean1.getId());//这个id一样
        mapParams.put("status", "");
        mapParams.put("registeBy", sharedPreferences.getString("userName", ""));
        mapParams.put("username", editText1.getText().toString());
        mapParams.put("mobile", editText2.getText().toString());
        mapParams.put("phone", editText2.getText().toString());
        mapParams.put("position", editText3.getText().toString());
        mapParams.put("department", editText4.getText().toString());
        mapParams.put("email", editText5.getText().toString());
        mapParams.put("feePaid", "0");
        mapParams.put("token", sharedPreferences.getString("token", ""));

        HttpUtils.doPost(Urls.HOST_BUYEVENT, mapParams, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("doPostAll", "^^^^^onFailure^^^^^");
                mHandler.sendEmptyMessage(0);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (String.valueOf(response.code()).startsWith("2")) {
                    mHandler.sendEmptyMessage(1);
                } else {
                    Log.v("doPostAll", "^^else^^^onFailure^^^^^" + response.code());
                    ErrorBean singedEventBean = ProjectsJsonUtils.readJsonErrorBean(response.body().string());//data是json字段获得data的值即对象数组
                    Message message = mHandler.obtainMessage();
                    message.obj = singedEventBean;
                    message.what = 0;
                    message.sendToTarget();
                    //mHandler.sendEmptyMessage(0);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    private final MyHandler mHandler = new MyHandler(this);

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wechat_pay:
                textViewPay.setText("微信支付");
                dialog.dismiss();
                break;
            case R.id.alipay_pay:
                textViewPay.setText("支付宝支付");
                dialog.dismiss();
                break;
        }
    }

    private class MyHandler extends Handler {
        private final WeakReference<EventRegistrationActivity> mActivity;

        public MyHandler(EventRegistrationActivity activity) {
            mActivity = new WeakReference<EventRegistrationActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            EventRegistrationActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case 0:
                        ErrorBean singedEventBean = (ErrorBean) msg.obj;
                        AppUtils.showToast(activity, singedEventBean.getMessage());
                        break;
                    case 1:
                        //AppUtils.showToast(activity, "报名成功");
                        showSuccess(activity);
                        break;
                    case 2:
                        //AppUtils.showToast(activity, "令牌保存成功");
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private void showSuccess(Activity activity) {
        AlertDialog dialog = new AlertDialog.Builder(activity)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("报名成功")//设置对话框的标题
                .setMessage("恭喜您报名成功！")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //EventBus.getDefault().postSticky(bean1);
                        Intent intent = new Intent(getApplicationContext(), EventDetailActivity.class);
                        if (bean1 != null) {//这个id一样
                            intent.putExtra("id", bean1.getId());
                        } else {
                            intent.putExtra("id", bean2.getId());
                        }
                        startActivity(intent);
                        finish();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
}
