package com.mango.leo.zsproject.personalcenter.show.shengbao.adapter;

/**
 * Created by admin on 2018/6/1.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.personalcenter.show.shengbao.bean.ShengBaoBean;

import java.util.List;

/**
 * Created by
 */

public class ShengBaoAdapter extends RecyclerView.Adapter<ShengBaoAdapter.mViewHolder> {

    private List<ShengBaoBean> list;
    private Context context;
    private View mHeaderView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public ShengBaoAdapter(Context context) {
        this.context = context;
    }
    public void setmDate(List<ShengBaoBean> data) {
        this.list = data;
        this.notifyDataSetChanged();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new mViewHolder(mHeaderView);
        View inflate = View.inflate(context, R.layout.plan_item, null);
        mViewHolder holder = new mViewHolder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        ShengBaoBean dataBean = list.get(position);
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (holder instanceof mViewHolder) {
           //Glide.with(context).load(dataBean.getUserImg()).into(holder.img);
           // holder.tv_title.setText();
           // holder.tv_p.setText();
        }
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title,tv_p;

        public mViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.textView_title);
            tv_p = itemView.findViewById(R.id.textView_p);

        }
    }
}  
