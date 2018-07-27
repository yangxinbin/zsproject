package com.mango.leo.zsproject.personalcenter.show.shenbao.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class ShenBaoAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnClickListener mOnClickListener;//自注册的接口给调用者用于点击逻辑
    private List<ShenBaoBean> mData = new ArrayList<>();
    public ShenBaoAdapter (Context applicationContext, int mType ) {
        this.context = applicationContext;
    }

    public void setmDate(List<ShenBaoBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<ShenBaoBean> m = new ArrayList<ShenBaoBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }


    /**
     * 添加列表项     * @param item
     */
    public void addItem(ShenBaoBean bean) {
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        if (mData != null) {
            //mData.remove(position);
            /*for (int i = 0; i < mData.size(); i++) {
                if (i == position) {
                    mData.remove(i);
                    i--;
                }
            }*/
            this.notifyItemRemoved(position);
        }
        this.notifyDataSetChanged();
    }

    public ShenBaoAdapter (Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.plan_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
//            AllItemBean dm = mData.get(pos);//add header
//            if (dm == null) {
            //               return;
            //           }
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject() != null) {
                if (mData.get(pos).getResponseObject().getContent() != null) {
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getProject() != null) {
                        ((ItemViewHolder) holder).tv_title.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getProject().getName());
                    }
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getContactInfo() != null) {
                        ((ItemViewHolder) holder).tv_p.setText("申报人：" + mData.get(pos).getResponseObject().getContent().get(pos % 20).getContactInfo().getName());
                    }
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

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public ShenBaoBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnClickListener {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_title, tv_p;
        CardView plan_shenbao;


        public ItemViewHolder(View v) {
            super(v);
            tv_title = itemView.findViewById(R.id.textView_title);
            tv_p = itemView.findViewById(R.id.textView_p);
            plan_shenbao = itemView.findViewById(R.id.plan_shenbao);
            plan_shenbao.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                    mOnClickListener.onItemClick(view, this.getLayoutPosition());
        }
    }

}
