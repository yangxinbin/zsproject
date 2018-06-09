package com.mango.leo.zsproject.personalcenter.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.login.UserActivity;
import com.mango.leo.zsproject.login.bean.UserMessageBean;
import com.mango.leo.zsproject.personalcenter.show.baoming.BaoMingActivity;
import com.mango.leo.zsproject.personalcenter.show.shengbao.ShengBaoActivity;
import com.mango.leo.zsproject.personalcenter.show.userchange.MesActivity;
import com.mango.leo.zsproject.utils.Urls;

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
    @Bind(R.id.imageView20)
    ImageView imageView20;
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
    @Bind(R.id.textView_userName_nomes)
    TextView textViewUserNameNomes;
    @Bind(R.id.textView_wheres)
    TextView textViewWheres;
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private UserMessageBean bean1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.personalcenter, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        sharedPreferences = getActivity().getSharedPreferences("CIFIT", MODE_PRIVATE);
        if (sharedPreferences.getString("skip", "no").equals("yes")) {
            cardView3.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
        }
        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void userMessageEventBus(UserMessageBean bean) {
        Log.v("uuuuu", bean.getResponseObject().getName() + "__rrrr__" + bean.getResponseObject().getCompany());
        bean1 = bean;
        //身份
        textViewGov.setText(bean.getResponseObject().getDepartment());
        textViewJob.setText(bean.getResponseObject().getPosition());
        textViewPhone.setText(bean.getResponseObject().getUsername());
        textViewEmile.setText(bean.getResponseObject().getEmail());
        textView20.setText(bean.getResponseObject().getDepartment());
        textViewWheres.setText(bean.getResponseObject().getLocation().getProvince() + "-" + bean.getResponseObject().getLocation().getCity() + "-" + bean.getResponseObject().getLocation().getDistrict());
        if (bean.getResponseObject().getName() == null) {
            textViewUserName.setText(bean.getResponseObject().getUsername());
            textViewUserNameNomes.setText(bean.getResponseObject().getUsername());
        } else {
            Log.v("uuuuu", "_pp_rrrr__");
            textViewUserName.setText(bean.getResponseObject().getName());
        }
        if (bean.getResponseObject().getTenant() != null) {//认证
            constraintLayouthead2.setVisibility(View.VISIBLE);
            imageViewState.setVisibility(View.VISIBLE);
        } else {//非认证
            constraintLayouthead2.setVisibility(View.GONE);
            imageViewState.setVisibility(View.GONE);
        }
        //头像
        if (bean.getResponseObject().getAvator().getId() != null) {//认证
            Glide.with(this).load(Urls.HOST+"/user-service/user/get/file?fileId="+ bean.getResponseObject().getAvator().getId()).into(imageViePic);
        }

        if (bean.getResponseObject().getName() == null || bean.getResponseObject().getEmail() == null || bean.getResponseObject().getUsername() == null) {
            Log.v("yxbbb", "****");
            cardView3.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.GONE);
        }

        if (bean.getResponseObject().getEmail() == null) {
            imageView20.setVisibility(View.GONE);
            textViewEmile.setVisibility(View.GONE);
        } else {
            Log.v("uuuuu", "_eee_rrrr__");

            imageView20.setVisibility(View.VISIBLE);
            textViewEmile.setVisibility(View.VISIBLE);
            textViewEmile.setText(bean.getResponseObject().getEmail());
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.image_msg5,R.id.bu_mes, R.id.constraintLayouthead1, R.id.constraintLayouthead2, R.id.shengbao, R.id.shouc, R.id.baoming,R.id.kefu, R.id.settings})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.image_msg5:
                intent = new Intent(getActivity(), MesActivity.class);//商务厅介绍
                startActivity(intent);
                break;
            case R.id.constraintLayouthead1:
                intent = new Intent(getActivity(), UserChangeActivity.class);
                startActivity(intent);
                break;
            case R.id.constraintLayouthead2:
                intent = new Intent(getActivity(), GovActivity.class);//商务厅介绍
                startActivity(intent);
                break;
            case R.id.shengbao:
                intent = new Intent(getActivity(), ShengBaoActivity.class);//商务厅介绍
                startActivity(intent);
                break;
            case R.id.shouc:
                intent = new Intent(getActivity(), ShouCangActivity.class);//商务厅介绍
                startActivity(intent);
                break;
            case R.id.baoming:
                intent = new Intent(getActivity(), BaoMingActivity.class);//商务厅介绍
                startActivity(intent);
                break;
            case R.id.kefu:
                intent = new Intent(getActivity(), KefuActivity.class);//商务厅介绍
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.bu_mes:
                if (bean1 != null) {
                    EventBus.getDefault().postSticky(bean1);
                }
                intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                break;
        }
    }
}
