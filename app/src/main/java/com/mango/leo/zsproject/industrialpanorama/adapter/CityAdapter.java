package com.mango.leo.zsproject.industrialpanorama.adapter;

/**
 * Created by admin on 2018/6/1.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialpanorama.bean.CityBean;
import com.mango.leo.zsproject.utils.Urls;

import java.util.List;

/**
 * Created by
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.mViewHolder> {

    private List<CityBean.ResponseObjectBean.IntroductionBean> list;
    private Context context;
    private View mHeaderView;
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    public CityAdapter(Context context) {
        this.context = context;
    }

    public void setmDate(List<CityBean.ResponseObjectBean.IntroductionBean> data) {
        Log.v("bbbbbb","___"+data.size());
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
        CityBean.ResponseObjectBean.IntroductionBean dataBean = list.get(position);
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (holder instanceof mViewHolder) {
            if (list.get(position).getPhoto() != null) {
                if (list.get(position).getPhoto().getId() != null || !TextUtils.isEmpty(list.get(position).getPhoto().getId())) {
                    holder.img.setVisibility(View.VISIBLE);
                    Glide.with(context).load(Urls.HOST + "/user-service/user/get/file?fileId=" + list.get(position).getPhoto().getId()).into(holder.img);
                } else {
                    holder.img.setVisibility(View.GONE);
                }

            }
            if (list.get(position) != null) {
                if (list.get(position).getTitle() != null || !TextUtils.isEmpty(list.get(position).getTitle())) {
                    holder.tv_title.setVisibility(View.VISIBLE);
                    holder.tv_title.setText(list.get(position).getTitle());
                } else {
                    holder.tv_title.setVisibility(View.GONE);
                }
                if (list.get(position).getDetail() != null || !TextUtils.isEmpty(list.get(position).getDetail())) {
                    holder.tv_content.setVisibility(View.VISIBLE);
                    holder.tv_content.setText(list.get(position).getDetail());
                } else {
                    holder.tv_content.setVisibility(View.GONE);
                }
            }

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
        TextView tv_title, tv_content;

        public mViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_title = itemView.findViewById(R.id.textView_title);
            tv_content = itemView.findViewById(R.id.textView_content);

        }
    }
}  
