package com.mango.leo.zsproject.industrialservice.adapte;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<DemandManagementBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
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
            return new ItemViewHolder(mHeaderView);
        }
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.demandmanagement_item, parent, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
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
        if ((position + 1 == getItemCount() || mHeaderView == null)) { //加载到最后不显示footter
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;//add header
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null) {
                if (mData.get(pos).getContent().get(pos % 20).getProject() != null) {
                    ((ItemViewHolder) holder).itemName.setText(mData.get(pos).getContent().get(pos % 20).getProject().getName());
                }
                if (mData.get(pos).getContent().get(pos % 20).getEvents() != null)
                    ((ItemViewHolder) holder).numCompany.setText(String.valueOf(mData.get(pos).getContent().get(pos % 20).getInvestmentPlan().size()));
                if (mData.get(pos).getContent().get(pos % 20).getInvestmentPlan() != null)
                    ((ItemViewHolder) holder).numInvestmentInstitution.setText(String.valueOf(mData.get(pos).getContent().get(pos % 20).getBusinessPlan().size()));
                if (mData.get(pos).getContent().get(pos % 20).getBusinessPlan() != null)
                    ((ItemViewHolder) holder).numInvestmentActivities.setText(String.valueOf(mData.get(pos).getContent().get(pos % 20).getEvents().size()));
                ((ItemViewHolder) holder).canceling_match.setVisibility(View.GONE);//先屏蔽
            }
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        int isHeader = mShowHeader ? 1 : 0;
        return mData.size() + isHeader;
    }


    public DemandManagementBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemnewsClickListener(OnItemnewsClickListener onItemnewsClickListener) {
        this.mOnItemnewsClickListener = onItemnewsClickListener;
    }

    public interface OnItemnewsClickListener {
        void onItemClick(View view, int position);

        void onItemClick1(View view, int position);

        void onItemClick2(View view, int position);

        void onItemClick3(View view, int position);

        void onCancelingMatchClick(View view, int position);

        // void onDeleteClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemName, numCompany, numInvestmentInstitution, numInvestmentActivities;
        public LinearLayout stateButton, l1, l2, l3;
        public Button canceling_match/*, delete*/;
        public int flag = 0;

        public ItemViewHolder(View v) {
            super(v);
            if (v == mHeaderView)
                return;
            itemName = (TextView) v.findViewById(R.id.item_name);
            numCompany = (TextView) v.findViewById(R.id.num_Company);
            stateButton = v.findViewById(R.id.stateButton);
            l1 = v.findViewById(R.id.l1);
            l2 = v.findViewById(R.id.l2);
            l3 = v.findViewById(R.id.l3);
            canceling_match = v.findViewById(R.id.canceling_match);
            // delete = v.findViewById(R.id.delete);
            numInvestmentInstitution = (TextView) v.findViewById(R.id.num_Investment_Institution);
            numInvestmentActivities = (TextView) v.findViewById(R.id.num_Investment_Activities);
            stateButton.setOnClickListener(this);
            l1.setOnClickListener(this);
            l2.setOnClickListener(this);
            l3.setOnClickListener(this);
            // delete.setOnClickListener(this);
            canceling_match.setOnClickListener(this);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemnewsClickListener != null) {
                switch (view.getId()) {
                    case R.id.stateButton:
                        mOnItemnewsClickListener.onItemClick(view, this.getLayoutPosition());
                        break;
                    case R.id.l1:
                        mOnItemnewsClickListener.onItemClick1(view, this.getLayoutPosition());
                        break;
                    case R.id.l2:
                        mOnItemnewsClickListener.onItemClick2(view, this.getLayoutPosition());
                        break;
                    case R.id.l3:
                        mOnItemnewsClickListener.onItemClick3(view, this.getLayoutPosition());
                        break;
                    case R.id.canceling_match:
                        if (flag == 0) {
                            Log.v("aaaaa", "__0");
                            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) canceling_match.getLayoutParams(); //取控件textView当前的布局参数
                            linearParams.width = 400;// 控件的宽强制设成
                            canceling_match.setText("确认取消匹配");
                            canceling_match.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                        }
                        if (flag == 1) {
                            Log.v("aaaaa", "__1");
                            mOnItemnewsClickListener.onCancelingMatchClick(view, this.getLayoutPosition());
                            flag = 0;//屏蔽只能点一次
                        }
                        flag = flag + 1;
                        break;
                    /*case R.id.delete:
                        mOnItemnewsClickListener.onDeleteClick(view, this.getLayoutPosition());
                        break;*/

                }
            }
        }

        /**
         * dp转为px
         *
         * @param context  上下文
         * @param dipValue dp值
         * @return
         */
        private int dip2px(Context context, float dipValue) {
            Resources r = context.getResources();
            return (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, dipValue, r.getDisplayMetrics());
        }
    }
}
