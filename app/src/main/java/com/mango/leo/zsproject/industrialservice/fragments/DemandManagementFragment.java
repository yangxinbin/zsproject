package com.mango.leo.zsproject.industrialservice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mango.leo.zsproject.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class DemandManagementFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.demandmanagement, container, false);
        return view;
    }
}
