package com.mango.leo.zsproject.personalcenter.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.login.UserActivity;
import com.mango.leo.zsproject.login.bean.UserMessageBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;


public class FragmentOfPersonalCenter extends Fragment {
    @Bind(R.id.shengbao)
    ConstraintLayout shengbao;
    @Bind(R.id.shouc)
    ConstraintLayout shouc;
    @Bind(R.id.baoming)
    ConstraintLayout baoming;
    @Bind(R.id.settings)
    ConstraintLayout settings;
    @Bind(R.id.constraintLayouthead1)
    ConstraintLayout constraintLayouthead1;
    @Bind(R.id.constraintLayouthead2)
    ConstraintLayout constraintLayouthead2;
    @Bind(R.id.cardView2)
    CardView cardView2;
    @Bind(R.id.cardView3)
    CardView cardView3;
    @Bind(R.id.bu_mes)
    Button buMes;
    @Bind(R.id.textView_userName)
    TextView textViewUserName;
    @Bind(R.id.imageView_state)
    ImageView imageViewState;
    @Bind(R.id.textView_gov)
    TextView textViewGov;
    @Bind(R.id.textView_job)
    TextView textViewJob;
    @Bind(R.id.imageVie_pic)
    CircleImageView imageViePic;
    @Bind(R.id.textView_phone)
    TextView textViewPhone;
    @Bind(R.id.textView_emile)
    TextView textViewEmile;
    @Bind(R.id.textView20)
    TextView textView20;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.personalcenter, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        sharedPreferences = getActivity().getSharedPreferences("CIFIT", MODE_PRIVATE);
        if (false/*sharedPreferences.getString("skip", "no").equals("yes")*/) {
            cardView3.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
        } else {
            cardView2.setVisibility(View.VISIBLE);
            cardView3.setVisibility(View.GONE);
        }
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        //头像
        textViewUserName.setText(bean.getResponseObject().getName());
        //身份
        textViewGov.setText(bean.getResponseObject().getCompany());
        textViewJob.setText(bean.getResponseObject().getDepartment());
        textViewPhone.setText(bean.getResponseObject().getUsername());
        textViewEmile.setText(bean.getResponseObject().getEmail());
        textView20.setText(bean.getResponseObject().getCompany());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bu_mes, R.id.constraintLayouthead1, R.id.constraintLayouthead2, R.id.shengbao, R.id.shouc, R.id.baoming, R.id.settings})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.constraintLayouthead1:
                intent = new Intent(getActivity(), UserChangeActivity.class);
                startActivity(intent);
                break;
            case R.id.constraintLayouthead2:
                intent = new Intent(getActivity(), GovActivity.class);//商务厅介绍
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.shengbao:
                break;
            case R.id.shouc:
                break;
            case R.id.baoming:
                break;
            case R.id.settings:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.bu_mes:
                intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
        }
    }
}
