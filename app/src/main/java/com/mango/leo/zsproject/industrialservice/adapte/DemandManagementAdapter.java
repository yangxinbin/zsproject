package com.mango.leo.zsproject.industrialservice.adapte;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.bean.DemandManagementBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class DemandManagementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnItemnewsClickListener mOnItemnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<DemandManagementBean> mData;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;

    public void setmDate(List<DemandManagementBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }
    public void reMove(){
        List<DemandManagementBean> m = new ArrayList<DemandManagementBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {//add header
        mHeaderView = headerView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(DemandManagementBean bean) {
        mData.add(bean);
        this.notifyDataSetChanged();
    }

    public DemandManagementAdapter(Context context) {
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {//add header
            Log.v("yxbbb","ddddddddd");
            return new ItemViewHolder(mHeaderView);
        }
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.demandmanagement_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
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
    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public void isShowHeader(boolean showHeader) {
        this.mShowHeader = showHeader;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;//add header
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
//            DemandManagementBean dm = mData.get(pos);//add header
//            if (dm == null) {
 //               return;
 //           }
            if (((ItemViewHolder) holder) != null) {
                ((ItemViewHolder) holder).itemName.setText("sdfsd");
                ((ItemViewHolder) holder).state.setText("来自");
                ((ItemViewHolder) holder).itemTime.setText("时间");
                ((ItemViewHolder) holder).numCompany.setText("50");
                ((ItemViewHolder) holder).numInvestmentInstitution.setText("120");
                ((ItemViewHolder) holder).numInvestmentActivities.setText("200");

                if (true) {
                    ((ItemViewHolder) holder).itemType.setImageResource(R.drawable.newrequirement_icon_zhaoshang);
                } else {
                    Glide.with(context)
                            .load(R.drawable.newrequirement_icon_zhaoshang)
                            .placeholder(R.drawable.newrequirement_icon_zhaoshang)//图片加载出来前，显示的图片
                            .error(R.drawable.newrequirement_icon_zhaoshang)//图片加载失败后，显示的图片
                            .into(((ItemViewHolder) holder).itemType);
                }
            }
        }
    }
    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }
    @Override
    public int getItemCount() {
/*        int isFooter = mShowFooter ? 1 : 0;
        int isHeader = mShowHeader ? 1 : 0;

        if (mData == null) {
            return isFooter + isHeader;
        }
        return mData.size() + isFooter + isHeader;*/
        return 6;
    }
    public void setOnItemnewsClickListener(OnItemnewsClickListener onItemnewsClickListener) {
        this.mOnItemnewsClickListener = onItemnewsClickListener;
    }
    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }
    }

    public DemandManagementBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }
    public interface OnItemnewsClickListener {
        public void onItemClick(View view, int position);
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemName,state,itemTime,numCompany,numInvestmentInstitution,numInvestmentActivities;
        public ImageView itemType;

        public ItemViewHolder(View v) {
            super(v);
            if(v == mHeaderView)
                return;
            itemName = (TextView) v.findViewById(R.id.item_name);
            state = (TextView) v.findViewById(R.id.item_state);
            itemTime = (TextView) v.findViewById(R.id.item_time);
            numCompany = (TextView) v.findViewById(R.id.num_Company);
            numInvestmentInstitution = (TextView) v.findViewById(R.id.num_Investment_Institution);
            numInvestmentActivities = (TextView) v.findViewById(R.id.num_Investment_Activities);
            itemType = (ImageView) v.findViewById(R.id.item_state_img);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemnewsClickListener != null) {
                mOnItemnewsClickListener.onItemClick(view, this.getLayoutPosition());
            }
        }
    }
}
