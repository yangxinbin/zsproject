package com.mango.leo.zsproject.personalcenter.show.baoming.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.eventexhibition.adapter.EventAdapter;
import com.mango.leo.zsproject.personalcenter.show.baoming.bean.SingUpBean;
import com.mango.leo.zsproject.utils.DateUtil;
import com.mango.leo.zsproject.utils.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class SingedUpEventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventnewsClickListener mOnEventnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<SingUpBean> mData = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<SingUpBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<SingUpBean> m = new ArrayList<SingUpBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }


    /**
     * 添加列表项     * @param item
     */
    public void addItem(SingUpBean bean) {
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }

    public SingedUpEventAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.eventsinged_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject() != null) {
                Log.v("yyyyy", "====pos======" + pos % 20);//
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getLocation() != null) {
                    ((ItemViewHolder) holder).e_place.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getLocation().getCity().toString());
                }
//                ((ItemViewHolder) holder).e_time.setText(mData.get(pos).getResponseObject().getContent().get(pos%20).getCreatedOn().toString());
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent() != null) {
                    ((ItemViewHolder) holder).e_time.setText(DateUtil.getDateToString(mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getStartTime(), "yyyy-MM-dd"));
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getBanner() != null) {
                        Log.v("bbbbbbbbbb","----"+pos);
                        Glide.with(context).load(Urls.HOST + "/user-service/user/get/file?fileId=" + mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getBanner().getId()).apply(new RequestOptions().placeholder(R.drawable.gov)).into(((ItemViewHolder) holder).im);
                    }else {
                        ((ItemViewHolder) holder).im.setImageResource(R.drawable.gov);
                    }
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().isPopular()) {
                        ((ItemViewHolder) holder).e_title.setText("         "+mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getName());
                        ((ItemViewHolder) holder).tv_state.setVisibility(View.VISIBLE);
                    } else {
                        ((ItemViewHolder) holder).e_title.setText("  "+mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getName());
                        ((ItemViewHolder) holder).tv_state.setVisibility(View.GONE);
                    }
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEvent().getEndTime() < System.currentTimeMillis()){
                        ((EventAdapter.ItemViewHolder) holder).tv_state.setVisibility(View.VISIBLE);
                        ((EventAdapter.ItemViewHolder) holder).tv_state.setText("已过期");
                        ((EventAdapter.ItemViewHolder) holder).tv_state.setTextColor(context.getResources().getColor(R.color.gray_b));
                    }
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
    public SingUpBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnEventnewsClickListener {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView e_title, e_place, e_time, tv_state;
        public ImageView im;
        private RelativeLayout all;
        public int flag = 0;

        public ItemViewHolder(View v) {
            super(v);
            e_title = (TextView) v.findViewById(R.id.tv_event);
            e_place = (TextView) v.findViewById(R.id.textView_p);
            e_time = (TextView) v.findViewById(R.id.textView_time);
            im = (ImageView) v.findViewById(R.id.im_pic);
            all = (RelativeLayout) v.findViewById(R.id.all);
            tv_state = (TextView) v.findViewById(R.id.tv_state_s);
            all.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnEventnewsClickListener != null) {
                switch (view.getId()) {
                    case R.id.all:
                        mOnEventnewsClickListener.onItemClick(view, this.getLayoutPosition());
                        break;
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
