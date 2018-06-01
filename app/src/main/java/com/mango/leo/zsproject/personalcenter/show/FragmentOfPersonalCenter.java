package com.mango.leo.zsproject.personalcenter.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.personalcenter, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.constraintLayouthead1,R.id.shengbao, R.id.shouc, R.id.baoming, R.id.settings})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.constraintLayouthead1:
                intent = new Intent(getActivity(), UserChangeActivity.class);
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
                getActivity().finish();
                break;
        }
    }

}
