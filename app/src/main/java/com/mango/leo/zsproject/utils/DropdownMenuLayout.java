package com.mango.leo.zsproject.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/13 0013.
 */

public class DropdownMenuLayout extends LinearLayout {
    //顶部菜单布局
    private LinearLayout tabMenuView;
    //底部容器，内容 遮罩 菜单弹出
    private FrameLayout containerViewFrameLayout;
    //内容
    private View contentView;
    //遮罩
    private View maskView;
    //菜单弹出
    private FrameLayout popupMenuViews;
    //分割线颜色
    private int dividerColor;
    //文本选中颜色
    private int textsSelectedColor = 0xff890c85;
    //文本未选中颜色
    private int textsUnSelectedColor = 0xff111111;
    //遮罩颜色
    private int maskColor = 0x88888888;
    //菜单颜色
    private int menuBackgroundColor = 0xffffffff;
    //水平分割线颜色
    private int underlineColor = 0xffcccccc;
    //字体大小
    private Integer menuTextSize = 14;
    //tab选中图标
    private Drawable menuSelectedIcon;
    //tab未选中图标
    private Drawable menuUnSelectedIcon;
    //菜单项选中位置，初始没有选中为-1
    private int currentTabPosition = -1;

    public DropdownMenuLayout(Context context) {
        super(context, null);
    }

    public DropdownMenuLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownMenuLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.dropDownMemu);
        if (a != null) {
            underlineColor = a.getColor(R.styleable.dropDownMemu_underlineColor, underlineColor);
            dividerColor = a.getColor(R.styleable.dropDownMemu_dividerColor, dividerColor);
            textsSelectedColor = a.getColor(R.styleable.dropDownMemu_textSeletedlineColor, textsSelectedColor);
            textsUnSelectedColor = a.getColor(R.styleable.dropDownMemu_textUnSeletedlineColor, textsUnSelectedColor);
            menuBackgroundColor = a.getColor(R.styleable.dropDownMemu_menuBackgroudColor, menuBackgroundColor);
            maskColor = a.getColor(R.styleable.dropDownMemu_maskColor, maskColor);
            menuTextSize = a.getDimensionPixelSize(R.styleable.dropDownMemu_menuTextSize, menuTextSize);
            menuSelectedIcon = a.getDrawable(R.styleable.dropDownMemu_menuSelectedIcon);
            menuUnSelectedIcon = a.getDrawable(R.styleable.dropDownMemu_menuUnSelectedIcon);
            a.recycle();//回收
        }

        initViews(context);
    }

    private void initViews(Context context) {
        //创建顶部菜单指示
        tabMenuView = new LinearLayout(context);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tabMenuView.setOrientation(HORIZONTAL);
        tabMenuView.setBackgroundColor(menuBackgroundColor);
        tabMenuView.setLayoutParams(lp);
        addView(tabMenuView, 0);
        //创建下划线
        View underlineView = new View(context);
        underlineView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp2px(1.0f)));
        underlineView.setBackgroundColor(underlineColor);
        addView(underlineView, 1);
        //初始化containerView
        containerViewFrameLayout = new FrameLayout(context);
        containerViewFrameLayout.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        addView(containerViewFrameLayout, 2);
    }

    private int dp2px(float v) {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, dm);
    }

    /*
    * 初始化DropDownMemu显示具体内容
    * */
    public void setDropDownMemu(List<String> tabTexts, List<View> popuViews, View contentView) {
        this.contentView = contentView;
        if (tabTexts.size() != popuViews.size()) {
            throw new IllegalArgumentException("导航下拉菜单数与弹出框数不相等");
        }
        for (int i = 0; i < tabTexts.size(); i++) {
            addTab(tabTexts, i);
        }
        containerViewFrameLayout.addView(contentView, 0);
        maskView = new View(getContext());
        maskView.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        maskView.setBackgroundColor(maskColor);
        maskView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                closeMenu();
            }
        });
        maskView.setVisibility(View.GONE);
        containerViewFrameLayout.addView(maskView, 1);
        popupMenuViews = new FrameLayout(getContext());
       // popupMenuViews.setBackgroundColor(menuBackgroundColor);
        popupMenuViews.setVisibility(View.GONE);
        for (int i = 0; i < popuViews.size(); i++) {
            popuViews.get(i).setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
            popupMenuViews.addView(popuViews.get(i), i);
        }
        containerViewFrameLayout.addView(popupMenuViews, 2);
    }

    private void addTab(List<String> tabTexts, int index) {
        final TextView tab = new TextView(getContext());
        tab.setSingleLine();
        tab.setEllipsize(TextUtils.TruncateAt.END);//文字过长显示省略号
        tab.setGravity(Gravity.CENTER);//居中
        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, menuTextSize);
        tab.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f));
        tab.setTextColor(textsUnSelectedColor);
        tab.setCompoundDrawablesWithIntrinsicBounds(null, null, menuUnSelectedIcon, null);
        tab.setText(tabTexts.get(index));
        tab.setPadding(dp2px(5), dp2px(12), dp2px(5), dp2px(12));
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                swithchMenu(tab);
            }
        });
        tabMenuView.addView(tab);
        //添加分割线
        Log.v("yxbbbb",tabTexts.get(index)+"----"+index+"-------------"+tabTexts.size());
        if (index < tabTexts.size()-1) {
            //创建下划线
            Log.v("yxbbbb","-------------");
            View view = new View(getContext());
            view.setLayoutParams(new LayoutParams(dp2px(0.5f), ViewGroup.LayoutParams.MATCH_PARENT));
            view.setBackgroundColor(dividerColor);
            tabMenuView.addView(view);
        }
    }

    /*
    * 切换菜单
    * */
    private void swithchMenu(View targetView) {
        for (int i = 0; i < tabMenuView.getChildCount(); i = i + 2) {//跳过分割线
            if (targetView == tabMenuView.getChildAt(i)) {
                if (currentTabPosition == i) {//关闭菜单
                    closeMenu();
                } else {//跳出菜单 二种情况
                    if (currentTabPosition == -1) {//初始状态
                        popupMenuViews.setVisibility(View.VISIBLE);
                        popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_in));
                        maskView.setVisibility(View.VISIBLE);
                        maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_in));
                        popupMenuViews.getChildAt(i / 2).setVisibility(View.VISIBLE);
                    } else {
                        popupMenuViews.getChildAt(i / 2).setVisibility(View.VISIBLE);
                    }
                    currentTabPosition = i;
                    ((TextView) tabMenuView.getChildAt(i)).setTextColor(textsSelectedColor);
                    ((TextView) tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(null, null, menuSelectedIcon, null);
                }
            } else {
                ((TextView) tabMenuView.getChildAt(i)).setTextColor(textsUnSelectedColor);
                ((TextView) tabMenuView.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(null, null, menuUnSelectedIcon, null);
                popupMenuViews.getChildAt(i / 2).setVisibility(View.GONE);
            }
        }
    }

    /*
    * 关闭菜单
    * */
    private void closeMenu() {
        if (currentTabPosition != 1) {
            ((TextView) tabMenuView.getChildAt(currentTabPosition)).setTextColor(textsUnSelectedColor);
            ((TextView) tabMenuView.getChildAt(currentTabPosition)).setCompoundDrawablesWithIntrinsicBounds(null, null, menuUnSelectedIcon, null);
            popupMenuViews.setVisibility(View.GONE);
            popupMenuViews.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_menu_out));
            maskView.setVisibility(View.GONE);
            maskView.setAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.dd_mask_out));
            currentTabPosition = -1;
        }
    }
}
