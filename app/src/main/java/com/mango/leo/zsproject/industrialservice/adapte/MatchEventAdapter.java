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
import com.bumptech.glide.request.RequestOptions;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.bean.MatchEventBean;
import com.mango.leo.zsproject.utils.DateUtil;
import com.mango.leo.zsproject.utils.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class MatchEventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventnewsClickListener mOnEventnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<MatchEventBean> mData = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<MatchEventBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<MatchEventBean> m = new ArrayList<MatchEventBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }


    /**
     * 添加列表项     * @param item
     */
    public void addItem(MatchEventBean bean) {
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }

    public MatchEventAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.event_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
    }
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null && mData.get(pos).getContent() != null && mData.get(pos).getContent().get(pos % 20) != null) {
                    ((ItemViewHolder) holder).e_place.setText(mData.get(pos).getContent().get(pos % 20).getLocation().getCity().toString());
                    ((ItemViewHolder) holder).e_time.setText(DateUtil.getDateToString(mData.get(pos).getContent().get(pos % 20).getStartTime(), "yyyy-MM-dd"));
                    if (mData.get(pos).getContent().get(pos % 20).getBanner() != null){
                        if (mData.get(pos).getContent().get(pos % 20).getBanner().getId() != null) {
                            Log.v("yyy", Urls.HOST + "/user-service/user/get/file?fileId=" + mData.get(pos).getContent().get(pos % 20).getBanner().getId());
                            Glide.with(context)
                                    .load(Urls.HOST + "/user-service/user/get/file?fileId=" + mData.get(pos).getContent().get(pos % 20).getBanner().getId())
                                    .apply(new RequestOptions().placeholder(R.drawable.gov))
                                    .into(((ItemViewHolder) holder).im);
                        }else {
                            ((ItemViewHolder) holder).im.setImageResource(R.drawable.gov);
                        }

                    }
                    if (mData.get(pos).getContent().get(pos % 20).isPopular()){
                        ((ItemViewHolder) holder).e_title.setText("         "+mData.get(pos).getContent().get(pos % 20).getName());
                        ((ItemViewHolder) holder).tv_state.setVisibility(View.VISIBLE);
                    }else {
                        ((ItemViewHolder) holder).e_title.setText("  "+mData.get(pos).getContent().get(pos % 20).getName());
                        ((ItemViewHolder) holder).tv_state.setVisibility(View.GONE);
                    }
                    if (mData.get(pos).getContent().get(pos % 20).getEndTime() < System.currentTimeMillis()){
                        ((ItemViewHolder) holder).tv_state.setVisibility(View.VISIBLE);
                        ((ItemViewHolder) holder).tv_state.setText("已过期");
                        ((ItemViewHolder) holder).tv_state.setTextColor(context.getResources().getColor(R.color.gray_b));
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

    public void setOnEventnewsClickListener(OnEventnewsClickListener onItemnewsClickListener) {
        this.mOnEventnewsClickListener = onItemnewsClickListener;
    }
    public MatchEventBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnEventnewsClickListener {
        public void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView e_title, e_place, e_time,tv_state;
        public ImageView im;

        public ItemViewHolder(View v) {
            super(v);
            e_title = (TextView) v.findViewById(R.id.tv_event);
            e_place = (TextView) v.findViewById(R.id.textView_p);
            e_time = (TextView) v.findViewById(R.id.textView_time);
            tv_state = (TextView) v.findViewById(R.id.tv_state);
            im = (ImageView) v.findViewById(R.id.im_pic);
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
