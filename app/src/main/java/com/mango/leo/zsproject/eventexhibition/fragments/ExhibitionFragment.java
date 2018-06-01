package com.mango.leo.zsproject.eventexhibition.fragments;

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

public class ExhibitionFragment extends Fragment implements ZsActivity.FragmentBackListener {
    @Bind(R.id.dropdownmenu)
    DropdownMenuLayout dropdownmenu;
    private String headers[] = {"时间", "地区", "类型"};
    private List<View> popViews = new ArrayList<View>();
    private String times[] = {"行业sdf1", "行dsf业2", "行asdf业3", "行df业2"};
    private String wheres[] = {"行gg业1", "行gg业2", "行业s3", "行g业2"};
    private String whats[] = {"行cc业1", "行cc业2", "行cc业3", "行cc业2"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.exhibition, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }
    private void initViews() {
        ListView lvTime = new ListView(getActivity());
        lvTime.setDividerHeight(0);
        lvTime.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(times)));

        ListView lvWhere = new ListView(getActivity());
        lvWhere.setDividerHeight(0);
        lvWhere.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(wheres)));

        ListView lvWhat = new ListView(getActivity());
        lvWhat.setDividerHeight(0);
        lvWhat.setAdapter(new DropDownAdapter(getActivity(), Arrays.asList(whats)));
/*        lvHangye.setOnClickListener((View.OnClickListener) getActivity());
        lvWays.setOnClickListener((View.OnClickListener) getActivity());
        lvWhere.setOnClickListener((View.OnClickListener) getActivity());
        lvHow.setOnClickListener((View.OnClickListener) getActivity());*/
        popViews.add(lvTime);
        popViews.add(lvWhere);
        popViews.add(lvWhat);

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
