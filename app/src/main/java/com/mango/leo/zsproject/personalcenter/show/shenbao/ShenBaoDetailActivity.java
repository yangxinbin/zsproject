package com.mango.leo.zsproject.personalcenter.show.shenbao;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.IdBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShenBaoDetailActivity extends BaseActivity {

    @Bind(R.id.imageView_sback)
    ImageView imageViewSback;
    @Bind(R.id.textView_name)
    TextView textViewName;
    @Bind(R.id.textView_c)
    TextView textViewC;
    @Bind(R.id.textView_p)
    TextView textViewP;
    @Bind(R.id.textView_po)
    TextView textViewPo;
    @Bind(R.id.textView_e)
    TextView textViewE;
    @Bind(R.id.textView_co)
    TextView textViewCo;
    @Bind(R.id.textView_PName)
    TextView textViewPName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shen_bao_detail);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void EventBus(ShenBaoBean.ResponseObjectBean.ContentBean bean) {
        if (bean == null) {
            return;
        }
        if (bean.getRequesterType().equals("investor")) {
            textViewPName.setText("申报角色：投资方");
        } else {
            textViewPName.setText("申报角色：项目方");
        }
        textViewCo.setText(bean.getDescription());//说明
        if (bean.getContactInfo() != null) {
            textViewName.setText(bean.getContactInfo().getName());
            textViewC.setText(bean.getContactInfo().getDepartment());
            textViewP.setText(bean.getContactInfo().getPhone());
            textViewPo.setText(bean.getContactInfo().getPosition());
            textViewE.setText(bean.getContactInfo().getEmail());
            textViewCo.setText(bean.getDescription());
        }
    }

    @OnClick(R.id.imageView_sback)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }
}
