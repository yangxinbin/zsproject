package com.mango.leo.zsproject.industrialservice.createrequirements.adapter;

/**
 * Created by admin on 2018/5/28.
 */
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by qzs on 2017/9/04.
 */
class RecycleAdapter4 extends RecyclerView.Adapter<RecycleAdapter4.MyViewHolder> {//
    private Context context;
    private List<String> list;
    public RecycleAdapter4(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_card4, parent,
                false));
        return holder;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText(list.get(position));
        holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 1) {
                    Snackbar.make(v, "此条目不能删除", Snackbar.LENGTH_SHORT).show();
                } else {
                    //        删除自带默认动画
                    removeData(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    // 添加数据
    public void addData(int position) {
//   在list中添加数据，并通知条目加入一条
        list.add(position, "我是商品" + position);
        //添加动画
        notifyItemInserted(position);
    }
    // 删除数据
    public void removeData(int position) {
        list.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView_edit,imageView_delete;
        TextView tv_name, tv_company,tv_phoneNum,tv_position,tv_email;
        //因为删除有可能会删除中间条目，然后会造成角标越界，所以必须整体刷新一下！
        public MyViewHolder(View view) {
            super(view);
            imageView_delete = view.findViewById(R.id.imageView_remove);
            imageView_edit = view.findViewById(R.id.imageView_edit);
            tv_name = (TextView) view.findViewById(R.id.textView_name);
            tv_company = (TextView) view.findViewById(R.id.textView_company);
            tv_phoneNum = (TextView) view.findViewById(R.id.textView_phonenum);
            tv_position = (TextView) view.findViewById(R.id.textView_position);
            tv_email = (TextView) view.findViewById(R.id.textView_email);
        }
    }
}

