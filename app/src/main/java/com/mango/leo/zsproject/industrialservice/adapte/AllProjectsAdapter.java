package com.mango.leo.zsproject.industrialservice.adapte;

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
import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.bean.AllItemBean;
import com.mango.leo.zsproject.industrialservice.createrequirements.bean.AllProjectsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/5/11.
 */

public class AllProjectsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private OnItemnewsClickListener mOnItemnewsClickListener;//自注册的接口给调用者用于点击逻辑
    private List<AllProjectsBean> mData;
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_HEADER = 2;
    private boolean mShowFooter = true;
    private boolean mShowHeader = true;
    private View mHeaderView;
    private int type;
    private boolean hasMore = false;
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示
    private Handler mHandler = new Handler(Looper.getMainLooper()); //获取主线程的Handler

    public AllProjectsAdapter(Context applicationContext, int mType ) {
        this.context = applicationContext;
        this.type = mType;

    }

    public void setmDate(List<AllProjectsBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void reMove() {
        List<AllProjectsBean> m = new ArrayList<AllProjectsBean>();
        this.mData = m;
        this.notifyDataSetChanged();
    }

    public void setHeaderView(View headerView) {//add header
        mHeaderView = headerView;
    }

    /**
     * 添加列表项     * @param item
     */
    public void addItem(AllProjectsBean bean) {
        isShowFooter(false);
        if (mData != null) {
            mData.add(bean);
            hasMore = true;
        }
        this.notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        Log.v("gggggg", mData.size() + "--" + position + "   " + mData.get(0).getResponseObject().getContent().get(0).getName());
        isShowFooter(false);
        if (mData != null) {
            //mData.remove(position);
            /*for (int i = 0; i < mData.size(); i++) {
                if (i == position) {
                    mData.remove(i);
                    i--;
                }
            }*/
            this.notifyItemRemoved(position);
        }
        this.notifyDataSetChanged();
    }

    public AllProjectsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER) {//add header
            return new ItemViewHolder(mHeaderView);
        }
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.all_item, parent, false);
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
//            AllItemBean dm = mData.get(pos);//add header
//            if (dm == null) {
            //               return;
            //           }
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject() != null) {
                Log.v("yyyyy", "====pos======" + pos % 20);//
                ((ItemViewHolder) holder).allItemName.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getName());
                ((ItemViewHolder) holder).allItemContent.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getSummary());
                if (type == 0){
                    if (mData.get(pos).getResponseObject().getContent().get(pos % 20).getStage() == 2){
                        ((ItemViewHolder) holder).stage.setText("已审核");
                        ((ItemViewHolder) holder).edit.setVisibility(View.VISIBLE);
                        ((ItemViewHolder) holder).stage.setTextColor(context.getResources().getColor(R.color.color_green));
                    }else {
                        ((ItemViewHolder) holder).stage.setText("待审核");
                        ((ItemViewHolder) holder).edit.setVisibility(View.GONE);
                        ((ItemViewHolder) holder).stage.setTextColor(context.getResources().getColor(R.color.red));
                    }
                }
            }
        } else {
            // 之所以要设置可见，是因为我在没有更多数据时会隐藏了这个footView
            //((AllProjectsAdapter.FooterViewHolder) holder).footTv.setVisibility(View.VISIBLE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // 隐藏提示条
                    ((AllProjectsAdapter.FooterViewHolder) holder).footTv.setVisibility(View.GONE);
                    // 将fadeTips设置true
                    fadeTips = true;
                    // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                    hasMore = true;
                }
            }, 2000);
            if (hasMore == true) {
                // 不隐藏footView提示
                Log.v("rrrrrrrrr","----");
                hasMore = false;
                //if (mData.size() > 0) {
                // 如果查询数据发现增加之后，就显示正在加载更多
                ((AllProjectsAdapter.FooterViewHolder) holder).footTv.setText("正在加载...");
                // }
            } else {
                //if (mData.size() > 0) {
                // 如果查询数据发现并没有增加时，就显示没有更多数据了
                ((AllProjectsAdapter.FooterViewHolder) holder).footTv.setText("没有更多数据了");

                // 然后通过延时加载模拟网络请求的时间，在500ms后执行
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 隐藏提示条
                        ((AllProjectsAdapter.FooterViewHolder) holder).footTv.setVisibility(View.GONE);
                        // 将fadeTips设置true
                        fadeTips = true;
                        // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                        hasMore = true;
                    }
                }, 1000);
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
        int isFooter = mShowFooter ? 1 : 0;
        int isHeader = mShowHeader ? 1 : 0;

        if (mData == null) {
            return isFooter + isHeader;
        }
        return mData.size() + isFooter + isHeader;
    }

    public void setOnItemnewsClickListener(OnItemnewsClickListener onItemnewsClickListener) {
        this.mOnItemnewsClickListener = onItemnewsClickListener;
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private TextView footTv;

        public FooterViewHolder(View view) {
            super(view);
            footTv = (TextView) itemView.findViewById(R.id.more_data_msg);

        }
    }

    public AllProjectsBean getItem(int position) {
        Log.v("gggggg", mData.size() + "-2-" + position + "   " + mData.get(0).getResponseObject().getContent().get(0).getName());
        return mData == null ? null : mData.get(position);
    }

    public interface OnItemnewsClickListener {
        public void onItemClick(View view, int position);

        void onEditClick(View view, int position);

        void onDeleteClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView allItemName, allItemContent,stage;
        public Button edit, delete;
        RelativeLayout itV;
        public int flag = 0;

        public ItemViewHolder(View v) {
            super(v);
            if (v == mHeaderView)
                return;
            allItemName = (TextView) v.findViewById(R.id.allitemName);
            allItemContent = (TextView) v.findViewById(R.id.allitemContent);
            stage = (TextView) v.findViewById(R.id.stage);
            edit = (Button) v.findViewById(R.id.edit);
            delete = (Button) v.findViewById(R.id.delete);
            itV = (RelativeLayout) v.findViewById(R.id.item_content);

            if (type == 0) {
                edit.setVisibility(View.VISIBLE);
                delete.setVisibility(View.GONE);
            } else {
                edit.setVisibility(View.GONE);
                delete.setVisibility(View.VISIBLE);
            }
            if (type == 0) {
                edit.setText("修改");
                edit.setOnClickListener(this);
            } else {
                delete.setText("删除");
                delete.setOnClickListener(this);
            }
            itV.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.item_content:
                    mOnItemnewsClickListener.onItemClick(view, this.getLayoutPosition());
                    break;
                case R.id.edit:
                    if (flag == 0) {
                        Log.v("aaaaa", "__0");
                        LinearLayout.LayoutParams linearParams = null;
                        linearParams = (LinearLayout.LayoutParams) edit.getLayoutParams(); //取控件textView当前的布局参数
                        edit.setText("确认修改");
                        linearParams.width = 450;// 控件的宽强制设成
                        edit.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                    }
                    if (flag == 1) {
                        LinearLayout.LayoutParams linearParams = null;
                        linearParams = (LinearLayout.LayoutParams) edit.getLayoutParams(); //取控件textView当前的布局参数
                        edit.setText("修改");
                        linearParams.width = 350;// 控件的宽强制设成
                        edit.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                        mOnItemnewsClickListener.onEditClick(view, this.getLayoutPosition());
                        flag = 0;//屏蔽只能点一次
                        return;
                    }
                    flag = flag + 1;
                    break;
                case R.id.delete:
                    if (flag == 0) {
                        Log.v("aaaaa", "__0");
                        LinearLayout.LayoutParams linearParams = null;
                        linearParams = (LinearLayout.LayoutParams) delete.getLayoutParams(); //取控件textView当前的布局参数
                        delete.setText("确认删除");
                        linearParams.width = 450;// 控件的宽强制设成
                        delete.setLayoutParams(linearParams); //使设置好的布局参数应用到控件

                    }
                    if (flag == 1) {
                        LinearLayout.LayoutParams linearParams = null;
                        linearParams = (LinearLayout.LayoutParams) delete.getLayoutParams(); //取控件textView当前的布局参数
                        delete.setText("删除");
                        linearParams.width = 350;// 控件的宽强制设成
                        delete.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                        Log.v("gggggg", "--" + this.getLayoutPosition());
                        mOnItemnewsClickListener.onDeleteClick(view, this.getLayoutPosition());
                        flag = 0;//屏蔽只能点一次
                        return;
                    }
                    flag = flag + 1;
                    break;
                    /*case R.id.delete:
                        mOnEventnewsClickListener.onDeleteClick(view, this.getLayoutPosition());
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
