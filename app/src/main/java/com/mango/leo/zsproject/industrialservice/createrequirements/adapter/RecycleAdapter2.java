package com.mango.leo.zsproject.industrialservice.createrequirements.adapter;

/**
 * Created by admin on 2018/5/28.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardSecondItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.view.FlowLayout;

import java.util.List;

/**
 * Created by qzs on 2017/9/04.
 */
public class RecycleAdapter2 extends RecyclerView.Adapter<RecycleAdapter2.MyViewHolder> {
    private Context context;
    private List<CardSecondItemBean> list;
    private RecycleAdapter2.OnCard2ClickListener mOnCard2ClickListener;//自注册的接口给调用者用于点击逻辑
    private int mtype;

    public RecycleAdapter2(Context context, List<CardSecondItemBean> list, int type) {
        this.context = context;
        this.list = list;
        this.mtype = type;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                context).inflate(R.layout.item_card2, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_name.setText(list.get(position).getChanye());
        LayoutInflater mInflater = LayoutInflater.from(context);
        for (int i = 0; i < list.get(position).getLingyuList().size(); i++)
        {
            TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                    holder.flowLayout, false);
            tv.setText(list.get(position).getLingyuList().get(i));
            holder.flowLayout.addView(tv);
        }
 /*       holder.tv_phoneNum.setText(list.get(position).getPhoneNumber());
        holder.tv_company.setText(list.get(position).getCompany());
        holder.tv_position.setText(list.get(position).getPosition());
        holder.tv_email.setText(list.get(position).getEmail());*/
        /*holder.imageView_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.size() == 1) {
                    Snackbar.make(v, "此条目不能删除", Snackbar.LENGTH_SHORT).show();
                } else {
                    //        删除自带默认动画
                    removeData(position);
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // 添加数据
    public void addData(int position) {
//   在list中添加数据，并通知条目加入一条
        //list.add(position, "我是" + position);
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

    public void setOnItemnewsClickListener(RecycleAdapter2.OnCard2ClickListener onItemnewsClickListener) {
        this.mOnCard2ClickListener = onItemnewsClickListener;
    }

    public interface OnCard2ClickListener {
        public void onItem2Click(View view, int position);
    }

    /**
     * ViewHolder的类，用于缓存控件
     */
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView_edit;
        TextView tv_name;
        RelativeLayout item2;
        FlowLayout flowLayout;

        //因为删除有可能会删除中间条目，然后会造成角标越界，所以必须整体刷新一下！
        public MyViewHolder(View view) {
            super(view);
            item2 = view.findViewById(R.id.item2);
            imageView_edit = view.findViewById(R.id.imageView_edit);
            tv_name = (TextView) view.findViewById(R.id.textView_chanye);
            flowLayout = view.findViewById(R.id.id_flowlayout);
            if (mtype == 1 || mtype == 2){
                imageView_edit.setVisibility(View.INVISIBLE);
            }else {
                item2.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View view) {
            if (mOnCard2ClickListener != null) {
                mOnCard2ClickListener.onItem2Click(view, this.getLayoutPosition());
            }
        }
    }
}

