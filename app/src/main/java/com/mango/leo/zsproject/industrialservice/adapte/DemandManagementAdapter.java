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
    private boolean hasMore;
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
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
        hasMore = true;
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
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;//add header
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
//            DemandManagementBean dm = mData.get(pos);//add header
//            if (dm == null) {
            //               return;
            //           }
            if (((ItemViewHolder) holder) != null) {
                ((ItemViewHolder) holder).itemName.setText("sdfsd");
                ((ItemViewHolder) holder).numCompany.setText("50");
                ((ItemViewHolder) holder).numInvestmentInstitution.setText("120");
                ((ItemViewHolder) holder).numInvestmentActivities.setText("200");
            }
        } else {
            // 之所以要设置可见，是因为我在没有更多数据时会隐藏了这个footView
            ((FooterViewHolder) holder).footTv.setVisibility(View.VISIBLE);
            if (hasMore == true) {
                // 不隐藏footView提示
                fadeTips = false;
                //if (mData.size() > 0) {
                    // 如果查询数据发现增加之后，就显示正在加载更多
                    ((FooterViewHolder) holder).footTv.setText("正在加载...");
               // }
            }else {
                //if (mData.size() > 0) {
                    // 如果查询数据发现并没有增加时，就显示没有更多数据了
                    ((FooterViewHolder) holder).footTv.setText("没有更多数据了");

                    // 然后通过延时加载模拟网络请求的时间，在500ms后执行
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 隐藏提示条
                            ((FooterViewHolder) holder).footTv.setVisibility(View.GONE);
                            // 将fadeTips设置true
                            fadeTips = true;
                            // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                            hasMore = true;
                        }
                    }, 500);
                //}
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

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        public TextView footTv;

        public FooterViewHolder(View view) {
            super(view);
            footTv = (TextView) itemView.findViewById(R.id.more_data_msg);

        }
    }

    public DemandManagementBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public void setOnItemnewsClickListener(OnItemnewsClickListener onItemnewsClickListener) {
        this.mOnItemnewsClickListener = onItemnewsClickListener;
    }

    public interface OnItemnewsClickListener {
        void onItemClick1(View view, int position);

        void onItemClick2(View view, int position);

        void onCancelingMatchClick(View view, int position);

        // void onDeleteClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView itemName, numCompany, numInvestmentInstitution, numInvestmentActivities;
        public LinearLayout stateButton, stateButton2;
        public Button canceling_match/*, delete*/;
        public int flag = 0;

        public ItemViewHolder(View v) {
            super(v);
            if (v == mHeaderView)
                return;
            itemName = (TextView) v.findViewById(R.id.item_name);
            numCompany = (TextView) v.findViewById(R.id.num_Company);
            stateButton = v.findViewById(R.id.stateButton);
            stateButton2 = v.findViewById(R.id.stateButton2);
            canceling_match = v.findViewById(R.id.canceling_match);
            // delete = v.findViewById(R.id.delete);
            numInvestmentInstitution = (TextView) v.findViewById(R.id.num_Investment_Institution);
            numInvestmentActivities = (TextView) v.findViewById(R.id.num_Investment_Activities);
            stateButton.setOnClickListener(this);
            stateButton2.setOnClickListener(this);
            // delete.setOnClickListener(this);
            canceling_match.setOnClickListener(this);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnItemnewsClickListener != null) {
                switch (view.getId()) {
                    case R.id.stateButton:
                        mOnItemnewsClickListener.onItemClick1(view, this.getLayoutPosition());
                        break;
                    case R.id.stateButton2:
                        mOnItemnewsClickListener.onItemClick2(view, this.getLayoutPosition());
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
