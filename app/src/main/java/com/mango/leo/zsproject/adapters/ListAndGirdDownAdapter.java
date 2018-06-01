package com.mango.leo.zsproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import java.util.List;

/**
 * Created by admin on 2018/5/24.
 */

public class ListAndGirdDownAdapter extends BaseAdapter {//
    private Context context;
    private List<String> stringList;
    private int checkItemPosition = -1;
    private int flag = -1;

    public ListAndGirdDownAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    public ListAndGirdDownAdapter(Context context, List<String> stringList, int i) {
        this.context = context;
        this.stringList = stringList;
        this.flag = i;
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
            viewHolder.co = containView.findViewById(R.id.con);
            containView.setTag(viewHolder);
        }
        if (flag == 2) {
            viewHolder.tv.setTextSize(12);
            viewHolder.co.setBackgroundResource(R.drawable.button_gray);
            viewHolder.tv.setTextColor(context.getResources().getColor(R.color.black));
        }
        fillValue(position, viewHolder);
        return containView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.tv.setText(stringList.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                if (flag == 2) {
                    viewHolder.tv.setTextSize(12);
                    viewHolder.co.setBackgroundResource(R.drawable.button_red);
                }
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                if (flag == 2) {
                    viewHolder.tv.setTextSize(12);
                    viewHolder.co.setBackgroundResource(R.drawable.button_gray);
                }
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.black));
            }
        }
    }

    class ViewHolder {
        TextView tv;
        LinearLayout co;
    }
}
