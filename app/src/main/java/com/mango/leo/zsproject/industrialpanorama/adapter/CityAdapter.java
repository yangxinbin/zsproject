package com.mango.leo.zsproject.industrialpanorama.adapter;

/**
 * Created by admin on 2018/6/1.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.bean.CityBean;
import com.mango.leo.zsproject.industrialservice.bean.DemandManagementBean;

import java.util.List;

/**
 * Created by
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.mViewHolder> {

    private List<CityBean> list;
    private Context context;
    private View mHeaderView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public CityAdapter(Context context) {
        this.context = context;
    }
    public void setmDate(List<CityBean> data) {
        this.list = data;
        this.notifyDataSetChanged();
    }

    @Override
    public mViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) return new mViewHolder(mHeaderView);
        View inflate = View.inflate(context, R.layout.city_item, null);
        mViewHolder holder = new mViewHolder(inflate);

        return holder;
    }

    @Override
    public void onBindViewHolder(mViewHolder holder, int position) {
        CityBean dataBean = list.get(position);
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (holder instanceof mViewHolder) {
           //Glide.with(context).load(dataBean.getUserImg()).into(holder.img);
           // holder.tv_title.setText();
           // holder.tv_content.setText();
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

        ImageView img;
        TextView tv_title,tv_content;

        public mViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_title = itemView.findViewById(R.id.textView_title);
            tv_content = itemView.findViewById(R.id.textView_content);

        }
    }
}  
