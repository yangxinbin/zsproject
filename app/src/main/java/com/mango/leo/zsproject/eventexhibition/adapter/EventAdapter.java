package com.mango.leo.zsproject.eventexhibition.adapter;

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
import com.mango.leo.zsproject.eventexhibition.bean.EventBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class EventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventnewsClickListener mOnEventnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<EventBean> mData;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;
    private boolean hasMore;
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<EventBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }
    public void reMove(){
        List<EventBean> m = new ArrayList<EventBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {//add header
        mHeaderView = headerView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(EventBean bean) {
        isShowFooter(false);
        if (mData != null){
            mData.add(bean);
            hasMore = true;

        }
        this.notifyDataSetChanged();
    }

    public EventAdapter(Context context) {
        this.context = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {//add header
            return new ItemViewHolder(mHeaderView);
        }
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
        }
        else
        {
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
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_HEADER) return;//add header
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
//            AllItemBean dm = mData.get(pos);//add header
//            if (dm == null) {
 //               return;
 //           }
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject()  != null) {
                Log.v("yyyyy", "====pos======"+pos%20);//
                ((ItemViewHolder) holder).e_title.setText(mData.get(pos).getResponseObject().getContent().get(pos%20).getName());
                ((ItemViewHolder) holder).e_place.setText(mData.get(pos).getResponseObject().getContent().get(pos%20).getLocation().getCity().toString());
//                ((ItemViewHolder) holder).e_time.setText(mData.get(pos).getResponseObject().getContent().get(pos%20).getCreatedOn().toString());
                if (mData.get(pos).getResponseObject().getContent().get(pos%20).getBanner().getId() != null) {
                    Glide.with(context).load("http://192.168.1.166:9999/user-service/user/get/file?fileId="+mData.get(pos).getResponseObject().getContent().get(pos % 20).getBanner().getId()).into(((ItemViewHolder) holder).im);
                }
            }
        }else {
            //if (mData.size() > 0) {
            // 如果查询数据发现并没有增加时，就显示没有更多数据了
            ((EventAdapter.FooterViewHolder) holder).footTv.setText("没有更多数据了");

            // 然后通过延时加载模拟网络请求的时间，在500ms后执行
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 隐藏提示条
                    ((EventAdapter.FooterViewHolder) holder).footTv.setVisibility(View.GONE);
                    // 将fadeTips设置true
                    fadeTips = true;
                    // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                    hasMore = true;
                }
            }, 500);
            //}
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

    public EventBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }
    public interface OnEventnewsClickListener {
        public void onItemClick(View view, int position);
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView e_title,e_place,e_time;
        public ImageView im;
        public ItemViewHolder(View v) {
            super(v);
            if(v == mHeaderView)
                return;
            e_title = (TextView) v.findViewById(R.id.tv_event);
            e_place = (TextView) v.findViewById(R.id.textView_p);
            e_time = (TextView) v.findViewById(R.id.textView_time);
            im = (ImageView)v.findViewById(R.id.im_pic);
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
