package com.mango.leo.zsproject.datacenter.adapter;

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
import com.mango.leo.zsproject.datacenter.bean.TouZiBean;
import com.mango.leo.zsproject.utils.Urls;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class TouZiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnEventnewsClickListener mOnEventnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<TouZiBean> mData = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler
    private StringBuffer stringBuffer1, stringBuffer2,stringBuffer3;

    public void setmDate(List<TouZiBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<TouZiBean> m = new ArrayList<TouZiBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }


    /**
     * 添加列表项     * @param item
     */
    public void addItem(TouZiBean bean) {
        if (mData != null) {
            mData.add(bean);
        }
        this.notifyDataSetChanged();
    }

    public TouZiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.touzi_item, parent, false);
            ItemViewHolder vh = new ItemViewHolder(v);
            return vh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        final int pos = getRealPosition(holder);
        if (holder instanceof ItemViewHolder) {
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject().getContent() != null && mData.get(pos).getResponseObject().getContent().get(pos % 20) != null) {
                ((ItemViewHolder) holder).t_name.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getTitle());
                if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getCooperationStyles() != null)
                    ((ItemViewHolder) holder).t_mo.setText("投资金额：" + mData.get(pos).getResponseObject().getContent().get(pos % 20).getInvestmentSize().getCaption());
                stringBuffer1 = new StringBuffer();
                stringBuffer2 = new StringBuffer();
                stringBuffer3 = new StringBuffer();
                if (mData.get(pos).getResponseObject().getContent().get(pos%20).getInvestmentMethod() != null){
                    for (int i=0;i<mData.get(pos).getResponseObject().getContent().get(pos%20).getInvestmentMethod().size();i++){
                        stringBuffer1.append(mData.get(pos).getResponseObject().getContent().get(pos%20).getInvestmentMethod().get(i) + " ");
                    }
                }
                if (mData.get(pos).getResponseObject().getContent().get(pos%20).getFundType() != null){
                    for (int j=0;j<mData.get(pos).getResponseObject().getContent().get(pos%20).getFundType().size();j++){
                        stringBuffer2.append(mData.get(pos).getResponseObject().getContent().get(pos%20).getFundType().get(j) + " ");
                    }
                }
                if (mData.get(pos).getResponseObject().getContent().get(pos%20).getIndustries() != null){
                    for (int k=0;k<mData.get(pos).getResponseObject().getContent().get(pos%20).getIndustries().size();k++){
                        stringBuffer3.append(mData.get(pos).getResponseObject().getContent().get(pos%20).getIndustries().get(k).getName() + " ");
                    }
                }
                ((ItemViewHolder) holder).t_way.setText("投资方式：" + stringBuffer1);
                ((ItemViewHolder) holder).t_t.setText("投资类型：" + stringBuffer2);
                ((ItemViewHolder) holder).t_h.setText("投资行业：" + stringBuffer3);
               // ((ItemViewHolder) holder).circleProgressBar.setProgress(10);

                if (mData.get(pos).getResponseObject().getContent().get(pos%20).getLogo().getId() != null) {
                    Log.v("yyy","iiiiiiiiii"+"http://47.106.184.121:9999/user-service/user/get/file?fileId="+mData.get(pos).getResponseObject().getContent().get(pos % 20).getLogo().getId());
                    Glide.with(context).load(Urls.HOST+"/user-service/user/get/file?fileId="+mData.get(pos).getResponseObject().getContent().get(pos % 20).getLogo().getId()).into(((ItemViewHolder) holder).im_pic);
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
    public TouZiBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnEventnewsClickListener {
        void onItemClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView t_name, t_mo, t_t, t_way, t_h;
        public ImageView im_pic;
       // public CircleProgressBar circleProgressBar;

        public ItemViewHolder(View v) {
            super(v);
            t_name = (TextView) v.findViewById(R.id.t_name);
            t_mo = (TextView) v.findViewById(R.id.t_mo);
            t_t = (TextView) v.findViewById(R.id.t_t);
            t_way = (TextView) v.findViewById(R.id.t_way);
            t_h = (TextView) v.findViewById(R.id.t_h);
           // circleProgressBar = (CircleProgressBar) v.findViewById(R.id.circle_bar);
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
