package com.mango.leo.zsproject.personalcenter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/6/5 0005.
 */

public class ShouCang1Fragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.shou_cang1, container, false);
        return view;
    }
}
