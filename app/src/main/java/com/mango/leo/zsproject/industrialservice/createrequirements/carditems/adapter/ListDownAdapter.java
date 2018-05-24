package com.mango.leo.zsproject.industrialservice.createrequirements.carditems.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import java.util.List;

/**
 * Created by admin on 2018/5/24.
 */

public class ListDownAdapter extends BaseAdapter {
    private Context context;
    private List<String> stringList;
    private int checkItemPosition = -1;

    public ListDownAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    public void setCheckItem(int position) {
        checkItemPosition = position;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return stringList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View containView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (containView != null) {
            viewHolder = (ViewHolder) containView.getTag();
        } else {
            containView = LayoutInflater.from(context).inflate(R.layout.item_default_down, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = containView.findViewById(R.id.tv);
            containView.setTag(viewHolder);
        }
        fillValue(position, viewHolder);
        return containView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.tv.setText(stringList.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.black));
            }
        }
    }

    class ViewHolder {
        TextView tv;
    }
}
