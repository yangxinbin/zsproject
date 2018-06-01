package com.mango.leo.zsproject.datacenter.fragments;

import android.content.Context;
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
import com.mango.leo.zsproject.ZsActivity;
import com.mango.leo.zsproject.industrialservice.adapte.DemandManagementAdapter;
import com.mango.leo.zsproject.utils.DropDownAdapter;
import com.mango.leo.zsproject.viewutil.DropdownMenuLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/5/11.
 */

public class InvestorFragment extends Fragment implements ZsActivity.FragmentBackListener {
    @Bind(R.id.dropdownmenu)
    DropdownMenuLayout dropdownmenu;
    private String headers[] = {"行业", "资金类型", "投资金额", "投资方式"};
    private List<View> popViews = new ArrayList<View>();
    private String hangye[] = {"行业1", "行业2", "行业3", "行业2"};
    private String ways[] = {"行业sdf1", "行dsf业2", "行asdf业3", "行df业2"};
    private String where[] = {"行gg业1", "行gg业2", "行业s3", "行g业2"};
    private String how[] = {"行cc业1", "行cc业2", "行cc业3", "行cc业2"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.investor, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }
    private void initViews() {
        ListView lvHangye = new ListView(getActivity());
        lvHangye.setDividerHeight(0);
        lvHangye.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(hangye)));

        ListView lvWays = new ListView(getActivity());
        lvWays.setDividerHeight(0);
        lvWays.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(ways)));

        ListView lvWhere = new ListView(getActivity());
        lvWhere.setDividerHeight(0);
        lvWhere.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(where)));

        ListView lvHow = new ListView(getActivity());
        lvHow.setDividerHeight(0);
        lvHow.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(how)));
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
        dropdownmenu.setDropDownMemu(Arrays.asList(headers), popViews, iv);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ZsActivity) {
            ((ZsActivity) context).setBackListener(this);
            ((ZsActivity) context).setInterception(true);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (getActivity() instanceof ZsActivity) {
            ((ZsActivity) getActivity()).setBackListener(null);
            ((ZsActivity) getActivity()).setInterception(false);
        }
    }

    @Override
    public void onbackForward() {
        // 处理fragment的返回事件
        dropdownmenu.closeMenu();
    }
}
