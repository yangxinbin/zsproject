package com.mango.leo.zsproject.personalcenter.adapter;

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
import com.mango.leo.zsproject.personalcenter.bean.MyEventBean;
import com.mango.leo.zsproject.personalcenter.show.baoming.adapter.SingedUpEventAdapter;
import com.mango.leo.zsproject.utils.DateUtil;
import com.mango.leo.zsproject.utils.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class ShouCangEventAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventnewsClickListener mOnEventnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<MyEventBean> mData = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public void setmDate(List<MyEventBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<MyEventBean> m = new ArrayList<MyEventBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }
    /**
     * 添加列表项     * @param item
     */
    public void addItem(MyEventBean bean) {
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }

    public ShouCangEventAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.eventshoucang_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject() != null) {
                Log.v("yyyyy", "====pos======" + pos % 20);//
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getLocation() != null) {
                    ((ItemViewHolder) holder).e_place.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getLocation().getCity().toString());
                }
//                ((ItemViewHolder) holder).e_time.setText(mData.get(pos).getResponseObject().getContent().get(pos%20).getCreatedOn().toString());
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity() != null) {
                    ((ItemViewHolder) holder).e_time.setText(DateUtil.getDateToString(mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getStartTime(), "yyyy-MM-dd"));
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getBanner() != null) {
                        Glide.with(context).load(Urls.HOST + "/user-service/user/get/file?fileId=" + mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getBanner().getId()).apply(new RequestOptions().placeholder(R.drawable.gov)).into(((ShouCangEventAdapter.ItemViewHolder) holder).im);
                    }else {
                        ((ShouCangEventAdapter.ItemViewHolder) holder).im.setImageResource(R.drawable.gov);
                    }
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().isPopular()){
                        ((ItemViewHolder) holder).e_title.setText("         "+mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getName());
                        ((ItemViewHolder) holder).tv_state.setVisibility(View.VISIBLE);
                    }else {
                        ((ItemViewHolder) holder).e_title.setText("  "+mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getName());
                        ((ItemViewHolder) holder).tv_state.setVisibility(View.GONE);
                    }
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getEntity().getEndTime() < System.currentTimeMillis()){
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

    public MyEventBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnEventnewsClickListener {
        void onItemClick(View view, int position);

        void onCancelingShouCangClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView e_title, e_place, e_time,tv_state;
        public ImageView im;
        private Button del;
        private RelativeLayout all;
        public int flag = 0;

        public ItemViewHolder(View v) {
            super(v);
            e_title = (TextView) v.findViewById(R.id.tv_event);
            e_place = (TextView) v.findViewById(R.id.textView_p);
            e_time = (TextView) v.findViewById(R.id.textView_time);
            im = (ImageView) v.findViewById(R.id.im_pic);
            del = (Button) v.findViewById(R.id.canceling_shoucang);
            all = (RelativeLayout) v.findViewById(R.id.all);
            tv_state = (TextView) v.findViewById(R.id.tv_state_s);
            del.setText("取消收藏");
            all.setOnClickListener(this);
            del.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mOnEventnewsClickListener != null) {
                switch (view.getId()) {
                    case R.id.all:
                        mOnEventnewsClickListener.onItemClick(view, this.getLayoutPosition());
                        break;
                    case R.id.canceling_shoucang:
                        if (flag == 0) {
                            Log.v("aaaaa", "__0");
                            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) del.getLayoutParams(); //取控件textView当前的布局参数
                            linearParams.width = 400;// 控件的宽强制设成
                            del.setText("确认取消收藏");
                            del.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                        }
                        if (flag == 1) {
                            Log.v("aaaaa", "__1");
                            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) del.getLayoutParams(); //取控件textView当前的布局参数
                            linearParams.width = 300;// 控件的宽强制设成
                            del.setText("取消收藏");
                            del.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                            mOnEventnewsClickListener.onCancelingShouCangClick(view, this.getLayoutPosition());
                            flag = 0;//屏蔽只能点一次
                            return;
                        }
                        flag = flag + 1;
                        break;
                    /*case R.id.del:
                        mOnEventnewsClickListener.onDeleteClick(view, this.getLayoutPosition());
                        break;*/

                }
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
