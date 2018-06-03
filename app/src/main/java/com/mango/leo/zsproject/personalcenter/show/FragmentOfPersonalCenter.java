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

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.login.UserActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.personalcenter, container, false);
        ButterKnife.bind(this, view);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.bu_mes,R.id.constraintLayouthead1, R.id.constraintLayouthead2, R.id.shengbao, R.id.shouc, R.id.baoming, R.id.settings})
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
