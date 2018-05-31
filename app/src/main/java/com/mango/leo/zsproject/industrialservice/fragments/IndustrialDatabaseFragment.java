package com.mango.leo.zsproject.industrialservice.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.adapte.DemandManagementAdapter;
import com.mango.leo.zsproject.utils.DropDownAdapter;
import com.mango.leo.zsproject.viewutil.DropdownMenuLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/10 0010.
 */

public class IndustrialDatabaseFragment extends Fragment {
    @Bind(R.id.dropdownmenu)
    DropdownMenuLayout dropdownMenuLayout;
    private String headers[] = {"行业", "融资方式", "地区", "阶段"};
    private List<View> popViews = new ArrayList<View>();
    private String hangye[] = {"行业1", "行业2", "行业3", "行业2"};
    private String ways[] = {"行业sdf1", "行dsf业2", "行asdf业3", "行df业2"};
    private String where[] = {"行gg业1", "行gg业2", "行业s3", "行g业2"};
    private String how[] = {"行cc业1", "行cc业2", "行cc业3", "行cc业2"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.industrialdatabase, container, false);
        // dropdownMenuLayout = view.findViewById(R.id.dropdownmenu);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        ListView lvHangye = new ListView(getActivity());
        lvHangye.setDividerHeight(0);
        lvHangye.setAdapter(new DropDownAdapter(getActivity(), hangye));

        ListView lvWays = new ListView(getActivity());
        lvWays.setDividerHeight(0);
        lvWays.setAdapter(new DropDownAdapter(getActivity(), ways));

        ListView lvWhere = new ListView(getActivity());
        lvWhere.setDividerHeight(0);
        lvWhere.setAdapter(new DropDownAdapter(getActivity(), where));

        ListView lvHow = new ListView(getActivity());
        lvHow.setDividerHeight(0);
        lvHow.setAdapter(new DropDownAdapter(getActivity(), how));
/*        lvHangye.setOnClickListener((View.OnClickListener) getActivity());
        lvWays.setOnClickListener((View.OnClickListener) getActivity());
        lvWhere.setOnClickListener((View.OnClickListener) getActivity());
        lvHow.setOnClickListener((View.OnClickListener) getActivity());*/
        popViews.add(lvHangye);
        popViews.add(lvWays);
        popViews.add(lvWhere);
        popViews.add(lvHow);

        ImageView iv = new ImageView(getActivity());
        iv.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        dropdownMenuLayout.setDropDownMemu(Arrays.asList(headers), popViews, iv);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
