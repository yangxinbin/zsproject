package com.mango.leo.zsproject.utils;

/**
 * Created by Administrator on 2018/5/14 0014.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import java.util.List;

/**
 * 常见的ListView的Adapter适配器
 * Created by 南尘 on 16-7-28.
 */
public class DropDownAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private int checkItemPosition = -1;

    public DropDownAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    public void setCheckItem(int position) {
        checkItemPosition = position;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.drop_item,viewGroup,false);
            holder = new MyViewHolder();
            holder.tv = (TextView) convertView.findViewById(R.id.drop_id);
            convertView.setTag(holder);
        } else {
            holder = (MyViewHolder) convertView.getTag();
        }
        holder.tv.setText(list.get(position));
        fillValue(position, holder);
        return convertView;
    }

    private void fillValue(int position, MyViewHolder viewHolder) {
        viewHolder.tv.setText(list.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
                //viewHolder.tv.setTextSize(18);
                //viewHolder.co.setBackgroundResource(R.drawable.button_red);
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                //viewHolder.tv.setTextSize(16);
               // viewHolder.co.setBackgroundResource(R.drawable.button_gray);
                viewHolder.tv.setTextColor(context.getResources().getColor(R.color.black));
            }
        }
    }
    private int dp2px(float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static class MyViewHolder {
        TextView tv;
    }
}
