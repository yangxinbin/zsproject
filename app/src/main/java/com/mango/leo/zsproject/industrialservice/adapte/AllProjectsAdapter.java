package com.mango.leo.zsproject.industrialservice.adapte;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

    public AllProjectsAdapter(Context applicationContext, int mType) {
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
        }
        this.notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        isShowFooter(false);
        if (mData != null) {
            mData.remove(position);
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
        this.notifyDataSetChanged();
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
//            AllItemBean dm = mData.get(pos);//add header
//            if (dm == null) {
            //               return;
            //           }
            if (((ItemViewHolder) holder) != null && mData.get(pos).getResponseObject() != null) {
                Log.v("yyyyy", "====pos======" + pos % 20);//
                ((ItemViewHolder) holder).allItemName.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getName());
                ((ItemViewHolder) holder).allItemContent.setText(mData.get(pos).getResponseObject().getContent().get(pos % 20).getSummary());
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

        public FooterViewHolder(View view) {
            super(view);
        }
    }

    public AllProjectsBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }

    public interface OnItemnewsClickListener {
        public void onItemClick(View view, int position);

        void onEditClick(View view, int position);

        void onDeleteClick(View view, int position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView allItemName, allItemContent;
        public Button edit, delete;
        public int flag = 0;

        public ItemViewHolder(View v) {
            super(v);
            if (v == mHeaderView)
                return;
            allItemName = (TextView) v.findViewById(R.id.allitemName);
            allItemContent = (TextView) v.findViewById(R.id.allitemContent);
            edit = (Button) v.findViewById(R.id.edit);
            delete = (Button) v.findViewById(R.id.delete);
            if (type == 0) {
                edit.setVisibility(View.VISIBLE);
                delete.setVisibility(View.GONE);
            } else {
                edit.setVisibility(View.GONE);
                delete.setVisibility(View.VISIBLE);
            }
            v.setOnClickListener(this);
            edit.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.stateButton:
                    mOnItemnewsClickListener.onItemClick(view, this.getLayoutPosition());
                    break;
                case R.id.edit:
                    mOnItemnewsClickListener.onEditClick(view, this.getLayoutPosition());
                    break;
                case R.id.delete:
                    if (flag == 0) {
                        Log.v("aaaaa", "__0");
                        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) delete.getLayoutParams(); //取控件textView当前的布局参数
                        linearParams.width = 400;// 控件的宽强制设成
                        if (type == 0){
                            delete.setText("确认修改");
                        }else {
                            delete.setText("确认删除");
                        }
                        delete.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
                    }
                    if (flag == 1) {
                        mOnItemnewsClickListener.onDeleteClick(view, this.getLayoutPosition());
                        flag = 0;//屏蔽只能点一次
                    }
                    flag = flag +1;
                    break;
                    /*case R.id.delete:
                        mOnEventnewsClickListener.onDeleteClick(view, this.getLayoutPosition());
                        break;*/

            }
        }
    }
}
