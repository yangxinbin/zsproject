package com.mango.leo.zsproject.personalcenter.show.baoming;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;
import com.mango.leo.zsproject.eventexhibition.show.EventDetailActivity;
import com.mango.leo.zsproject.personalcenter.show.baoming.bean.SingUpBean;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.utils.DateUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SingUpedDetailActivity extends AppCompatActivity {

    @Bind(R.id.imageView_back)
    ImageView imageViewBack;
    @Bind(R.id.textView_eN)
    TextView textViewEN;
    @Bind(R.id.l_N)
    LinearLayout lN;
    @Bind(R.id.textView_where)
    TextView textViewWhere;
    @Bind(R.id.textView_time)
    TextView textViewTime;
    @Bind(R.id.textView_zhubannf)
    TextView textViewZhubannf;
    @Bind(R.id.textView_xiuban)
    TextView textViewXiuban;
    @Bind(R.id.tv_signle)
    TextView tvSignle;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.t_name)
    TextView tName;
    @Bind(R.id.t_de)
    TextView tDe;
    @Bind(R.id.t_phone)
    TextView tPhone;
    @Bind(R.id.t_position)
    TextView tPosition;
    @Bind(R.id.t_num)
    TextView tNum;
    @Bind(R.id.t_date)
    TextView tDate;
    @Bind(R.id.t_copy)
    TextView tCopy;
    @Bind(R.id.sign_up)
    Button signUp;
    private SingUpBean.ResponseObjectBean.ContentBean bean1;
    String pattern = "yyyy-MM-dd HH:mm:ss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_uped_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void eventSingUpedEventBus(SingUpBean.ResponseObjectBean.ContentBean bean) {
        Log.v("eeeeeeeeeeeeee","----e");
        bean1 = bean;
        if (bean == null) {
            return;
        }
        if (bean.getEvent() != null) {
            textViewEN.setText(bean.getEvent().getName());
            if (bean.getEvent().getLocation() != null) {
                textViewWhere.setText(bean.getEvent().getLocation().getCity() + bean.getEvent().getLocation().getDistrict() + bean.getEvent().getLocation().getAddress());
            }
            textViewTime.setText(DateUtil.getDateToString(bean.getEvent().getStartTime(), pattern) + "至" + DateUtil.getDateToString(bean.getEvent().getEndTime(), pattern));
            textViewZhubannf.setText(bean.getEvent().getOrganizer());
            StringBuffer stringBuffer = new StringBuffer();
            if (bean.getEvent().getCoorganizers() != null) {
                for (int i = 0; i < bean.getEvent().getCoorganizers().size(); i++) {
                    stringBuffer.append(bean.getEvent().getCoorganizers().get(i) + " ");
                }
            }
            textViewXiuban.setText(stringBuffer);
            int num = bean.getNumberOfTickets();
            Double price = bean.getEvent().getPrice();
            tvSignle.setText(String.valueOf(num));
            if (String.valueOf(price).startsWith("0.0") || String.valueOf(price).startsWith("0")){
                tvMoney.setText("免费");
            }else {
                tvMoney.setText(String.valueOf(price)+"元");
            }
            tNum.setText(bean.getEvent().getId());
            tDate.setText(DateUtil.getDateToString(bean.getPaymentOn(),"yyyy-MM-dd"));
        }
        if (bean.getContact() != null) {
            tName.setText(bean.getContact().getName());
            tDe.setText(bean.getContact().getDepartment());
            tPhone.setText(bean.getContact().getPhone());
            tPosition.setText(bean.getContact().getPosition());
        }


    }

    @OnClick({R.id.imageView_back, R.id.l_N, R.id.t_copy, R.id.sign_up})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_back:
                intent = new Intent(getBaseContext(), BaoMingActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.l_N:
                EventBus.getDefault().postSticky(bean1.getEvent());
                intent = new Intent(this, EventDetailActivity.class);
                intent.putExtra("id", bean1.getEvent().getId());
                startActivity(intent);
                break;
            case R.id.t_copy:
                //添加到剪切板
                ClipboardManager clipboardManager =
                        (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
                /**之前的应用过期的方法，clipboardManager.setText(copy);*/
                assert clipboardManager != null;
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null,bean1.getEvent().getId()));
                if (clipboardManager.hasPrimaryClip()){
                    clipboardManager.getPrimaryClip().getItemAt(0).getText();
                }
                AppUtils.showToast(this, "已复制订单号：" + bean1.getEvent().getId());
                break;
            case R.id.sign_up:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, BaoMingActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
