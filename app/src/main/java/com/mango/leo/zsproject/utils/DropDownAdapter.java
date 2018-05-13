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
    private String[] list;

    public DropDownAdapter(Context context, String[] list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.length;
    }

    @Override
    public Object getItem(int position) {
        return list[position];
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
        holder.tv.setText(list[position]);
        return convertView;
    }

    public static class MyViewHolder {
        ImageView iv;
        TextView tv;
    }
}
