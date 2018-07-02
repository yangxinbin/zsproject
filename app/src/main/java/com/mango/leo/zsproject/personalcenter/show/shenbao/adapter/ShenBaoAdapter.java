package com.mango.leo.zsproject.personalcenter.show.shenbao.adapter;

/**
 * Created by admin on 2018/6/1.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;
import com.mango.leo.zsproject.personalcenter.show.shenbao.bean.ShenBaoBean;

import java.util.List;

/**
 * Created by
 */

public class ShenBaoAdapter extends RecyclerView.Adapter<ShenBaoAdapter.mViewHolder> {

    private List<ShenBaoBean> list;
    private Context context;
    private View mHeaderView;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private List<ShenBaoBean> mData;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;

    public ShenBaoAdapter(Context context) {
        this.context = context;
    }

    public void setmDate(List<ShenBaoBean> data) {
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
        final int pos = getRealPosition(holder);
        if (getItemViewType(position) == TYPE_HEADER) return;
        if (holder instanceof mViewHolder) {
            if (mData.get(pos).getResponseObject().getContent() != null) {
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getProject() != null){
                    holder.tv_title.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getProject().getName());
                }
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getContactInfo() != null){
                    holder.tv_p.setText("申报人："+mData.get(pos).getResponseObject().getContent().get(pos % 20).getContactInfo().getName());
                }
            }
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public View getHeaderView() {
        return mHeaderView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(ShenBaoBean bean) {
        isShowFooter(false);
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
        //this.notifyDataSetChanged();
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if (!mShowFooter && !mShowHeader) {
            return TYPE_ITEM;
        }
        if (position == 0) {
            return TYPE_HEADER;//add header
        }
        if ((position + 1 == getItemCount() || mHeaderView == null) && isShowFooter()) { //加载到最后不显示footter
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    class mViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_p;

        public mViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.textView_title);
            tv_p = itemView.findViewById(R.id.textView_p);

        }
    }
}  
