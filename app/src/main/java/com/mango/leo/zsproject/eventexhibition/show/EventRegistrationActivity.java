package com.mango.leo.zsproject.eventexhibition.show;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
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

public class EventRegistrationActivity extends AppCompatActivity {

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
    @Bind(R.id.tv_signle)
    TextView tvSignle;
    @Bind(R.id.tv_all)
    TextView tvAll;
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
    @Bind(R.id.textView61)
    TextView textView61;
    private int position;
    String pattern = "yyyy-MM-dd HH:mm:ss";
    private EventBean bean1;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);
        sharedPreferences = getSharedPreferences("CIFIT",MODE_PRIVATE);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventRegistrationEventBus(EventBean bean) {
        bean1 = bean;
        position = getIntent().getIntExtra("position", -1);
        Log.v("yxbb", bean.getResponseObject().getContent().get(position).getName() + "__position__" + position);
        if (bean != null) {
            if (String.valueOf(bean.getResponseObject().getContent().get(position).getPrice()) == "0") {//免费
                signUp.setEnabled(true);//使能按钮
                eventNofree.setVisibility(View.GONE);
                howtoplay.setVisibility(View.GONE);
                eventFree.setVisibility(View.VISIBLE);
            } else {
                signUp.setEnabled(false);
                eventFree.setVisibility(View.GONE);
                eventNofree.setVisibility(View.VISIBLE);
                howtoplay.setVisibility(View.VISIBLE);
            }
            Log.v("yxbb", bean.getResponseObject().getContent().get(position).getPrice() + "__y___" + bean.getResponseObject().getContent().get(position).getName());
            textView61.setText(bean.getResponseObject().getContent().get(position).getName());
            textViewWhere.setText(bean.getResponseObject().getContent().get(position).getLocation().getCity() + bean.getResponseObject().getContent().get(position).getLocation().getDistrict() + bean.getResponseObject().getContent().get(position).getLocation().getAddress());
            textViewTime.setText(DateUtil.getDateToString((Integer) bean.getResponseObject().getContent().get(position).getCreatedOn(), pattern));
            textViewZhubannf.setText(bean.getResponseObject().getContent().get(position).getOrganizer());
            textViewXiuban.setText((CharSequence) bean.getResponseObject().getContent().get(position).getPublished());

        }
    }

    @OnClick({R.id.imageView_back, R.id.howtoplay, R.id.sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_back:
                finish();
                break;
            case R.id.howtoplay:
                break;
            case R.id.sign_up:
                registration();//报名
                break;
        }
    }

    private void registration() {
        final HashMap<String, String> mapParams = new HashMap<String, String>();
        mapParams.clear();
        mapParams.put("event", bean1.getResponseObject().getContent().get(position).getId());
        mapParams.put("status", "");
        mapParams.put("registeBy", sharedPreferences.getString("userName",""));

        mapParams.put("username", editText1.getText().toString());
        mapParams.put("mobile", editText2.getText().toString());
        mapParams.put("phone", editText2.getText().toString());
        mapParams.put("position", editText3.getText().toString());
        mapParams.put("department", editText4.getText().toString());
        mapParams.put("email", editText5.getText().toString());

        mapParams.put("paymentDateTime", DateUtil.getCurDate(pattern));
        mapParams.put("feePaid", String.valueOf(bean1.getResponseObject().getContent().get(position).getPrice()));
        mapParams.put("token", sharedPreferences.getString("token",""));

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
                    Log.v("doPostAll", response.body().string() + "^^else^^^onFailure^^^^^" + response.code());
                    mHandler.sendEmptyMessage(0);
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

    private final EventRegistrationActivity.MyHandler mHandler = new EventRegistrationActivity.MyHandler(this);

    private static class MyHandler extends Handler {
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
                        AppUtils.showToast(activity, "报名失败");
                        break;
                    case 1:
                        //AppUtils.showToast(activity, "报名成功");
                        showSuccess();
                        break;
                    case 2:
                        //AppUtils.showToast(activity, "令牌保存成功");
                        break;
                    default:
                        break;
                }
            }
        }

        private void showSuccess() {

        }
    }
}