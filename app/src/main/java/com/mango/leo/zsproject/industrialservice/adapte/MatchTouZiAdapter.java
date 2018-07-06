package com.mango.leo.zsproject.industrialservice.adapte;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.bean.MatchDataBean;
import com.mango.leo.zsproject.utils.CircleProgressBar;
import com.mango.leo.zsproject.utils.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class MatchTouZiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventnewsClickListener mOnEventnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<MatchDataBean> mData;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;
    private boolean hasMore;
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler
    private StringBuffer stringBuffer1, stringBuffer2;

    public void setmDate(List<MatchDataBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<MatchDataBean> m = new ArrayList<MatchDataBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {//add header
        mHeaderView = headerView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(MatchDataBean bean) {
        isShowFooter(false);
        if (mData != null) {
            mData.add(bean);
            hasMore = true;

        }
        this.notifyDataSetChanged();
    }

    public MatchTouZiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {//add header
            return new ItemViewHolder(mHeaderView);
        }
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.touzi_item, parent, false);
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
        //this.notifyDataSetChanged();
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public void isShowHeader(boolean showHeader) {
        this.mShowHeader = showHeader;
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;//add header
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null && mData.get(pos).getContent() != null && mData.get(pos).getContent().get(pos % 20) != null) {
                ((ItemViewHolder) holder).t_name.setText(mData.get(pos).getContent().get(pos % 20).getTitle());
                if (mData.get(pos).getContent().get(pos % 20).getCooperationStyles() != null)
                    ((ItemViewHolder) holder).t_moshi.setText("招商模式：" + mData.get(pos).getContent().get(pos % 20).getCooperationStyles().get(0));
                if (mData.get(pos).getContent().get(pos % 20).getInvestmentSize() != null)
                    ((ItemViewHolder) holder).t_money.setText("投资金额：" + mData.get(pos).getContent().get(pos % 20).getInvestmentSize().getCaption());
                stringBuffer1 = new StringBuffer();
                stringBuffer2 = new StringBuffer();
                if (mData.get(pos).getContent().get(pos%20).getCooperationStyles() != null){
                    for (int i=0;i<mData.get(pos).getContent().get(pos%20).getCooperationStyles().size();i++){
                        stringBuffer1.append(mData.get(pos).getContent().get(pos%20).getCooperationStyles().get(i) + " ");
                    }
                }
                if (mData.get(pos).getContent().get(pos%20).getFundType() != null){
                    for (int i=0;i<mData.get(pos).getContent().get(pos%20).getFundType().size();i++){
                        stringBuffer2.append(mData.get(pos).getContent().get(pos%20).getFundType().get(i) + " ");
                    }
                }
                ((ItemViewHolder) holder).t_way.setText("合作方式：" + stringBuffer1);
                ((ItemViewHolder) holder).t_type.setText("资金类型：" + stringBuffer2);
                ((ItemViewHolder) holder).circleProgressBar.setProgress(10);

                if (mData.get(pos).getContent().get(pos%20).getLogo().getId() != null) {
                    Log.v("yyy","iiiiiiiiii"+"http://47.106.184.121:9999/user-service/user/get/file?fileId="+mData.get(pos).getContent().get(pos % 20).getLogo().getId());
                    Glide.with(context).load(Urls.HOST+"/user-service/user/get/file?fileId="+mData.get(pos).getContent().get(pos % 20).getLogo().getId()).into(((ItemViewHolder) holder).im_pic);
                }
            }
        } else {
            // 如果查询数据发现并没有增加时，就显示没有更多数据了
            ((MatchTouZiAdapter.FooterViewHolder) holder).footTv.setText("没有更多数据了");
            // 然后通过延时加载模拟网络请求的时间，在500ms后执行
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 隐藏提示条
                    ((MatchTouZiAdapter.FooterViewHolder) holder).footTv.setVisibility(View.GONE);
                    // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                    hasMore = true;
                }
            }, 1000);
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        int isFooter = mShowFooter ? 1 : 0;
        int isHeader = mShowHeader ? 1 : 0;

        if (mData == null) {
            return isFooter + isHeader;
        }
        return mData.size() + isFooter + isHeader;
    }

    public void setOnEventnewsClickListener(OnEventnewsClickListener onItemnewsClickListener) {
        this.mOnEventnewsClickListener = onItemnewsClickListener;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public TextView footTv;

        public FooterViewHolder(View view) {
            super(view);
            footTv = (TextView) itemView.findViewById(R.id.more_data_msg);

        }
    }

    public MatchDataBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnEventnewsClickListener {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView t_name, t_moshi, t_money, t_way, t_type;
        public ImageView im_pic;
        public CircleProgressBar circleProgressBar;

        public ItemViewHolder(View v) {
            super(v);
            if (v == mHeaderView)
                return;
            t_name = (TextView) v.findViewById(R.id.t_name);
            t_moshi = (TextView) v.findViewById(R.id.t_moshi);
            t_money = (TextView) v.findViewById(R.id.t_money);
            t_way = (TextView) v.findViewById(R.id.t_way);
            t_type = (TextView) v.findViewById(R.id.t_type);
            circleProgressBar = (CircleProgressBar) v.findViewById(R.id.circle_bar);
            im_pic = (ImageView) v.findViewById(R.id.im_pic);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnEventnewsClickListener != null) {
                mOnEventnewsClickListener.onItemClick(view, this.getLayoutPosition());
            }
        }
    }
}
