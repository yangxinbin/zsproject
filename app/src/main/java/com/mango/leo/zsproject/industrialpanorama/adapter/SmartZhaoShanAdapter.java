package com.mango.leo.zsproject.industrialpanorama.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.bean.ZhaoShangBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class SmartZhaoShanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnZhaoShanClickListener mOnZhaoShanClickListener;//自注册的接口给调用者用于点击逻辑
    private List<ZhaoShangBean> mData = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<ZhaoShangBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<ZhaoShangBean> m = new ArrayList<ZhaoShangBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }


    /**
     * 添加列表项     * @param item
     */
    public void addItem(ZhaoShangBean bean) {
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }
    public SmartZhaoShanAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.zhaoshang_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject() != null) {
                Log.v("yyyyy", "====pos======" + pos % 20);//
                ((ItemViewHolder) holder).z_title.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getName());
                ((ItemViewHolder) holder).z_content.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getSummary());
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).isRecommended()) {
                    ((ItemViewHolder) holder).im.setVisibility(View.VISIBLE);
                } else {
                    ((ItemViewHolder) holder).im.setVisibility(View.GONE);
                }
            }
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return position;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setOnZhaoShanClickListener(OnZhaoShanClickListener onItemnewsClickListener) {
        this.mOnZhaoShanClickListener = onItemnewsClickListener;
    }


    public ZhaoShangBean getItem(int position) {
        Log.v("oooooooooo", "--oo--" + mData.get(position).getResponseObject().getContent().get(position % 20).getName());
        return mData == null ? null : mData.get(position);
    }

    public interface OnZhaoShanClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView z_title, z_content, im;

        public ItemViewHolder(View v) {
            super(v);
            z_title = (TextView) v.findViewById(R.id.tv_zhaoshang);
            z_content = (TextView) v.findViewById(R.id.textView_zhaoshangC);
            im = (TextView) v.findViewById(R.id.imageView_flag);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnZhaoShanClickListener != null) {
                mOnZhaoShanClickListener.onItemClick(view, this.getLayoutPosition());
                Log.v("oooooooo", "---onb---" + this.getLayoutPosition());
            }
        }
    }
}
