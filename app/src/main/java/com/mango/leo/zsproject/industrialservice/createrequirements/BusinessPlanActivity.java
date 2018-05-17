package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.bean.CardFirstItemBean;
import com.mango.leo.zsproject.utils.AppUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessPlanActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.imageViewback)
    ImageView imageViewback;
    @Bind(R.id.save)
    TextView save;
    @Bind(R.id.carfirst_content)
    ConstraintLayout carfirstContent;
    @Bind(R.id.carfirst)
    CardView carfirst;
    @Bind(R.id.carsecond_content)
    ConstraintLayout carsecondContent;
    @Bind(R.id.carsecond)
    CardView carsecond;
    @Bind(R.id.carthird_content)
    ConstraintLayout carthirdContent;
    @Bind(R.id.carthird)
    CardView carthird;
    @Bind(R.id.carfourth_content)
    ConstraintLayout carfourthContent;
    @Bind(R.id.carfourth)
    CardView carfourth;
    @Bind(R.id.carfifth_content)
    ConstraintLayout carfifthContent;
    @Bind(R.id.carfifth)
    CardView carfifth;
    @Bind(R.id.carsixth_content)
    ConstraintLayout carsixthContent;
    @Bind(R.id.carsixth)
    CardView carsixth;
    @Bind(R.id.carseventh_content)
    ConstraintLayout carseventhContent;
    @Bind(R.id.carseventh)
    CardView carseventh;
    @Bind(R.id.careighth_content)
    ConstraintLayout careighthContent;
    @Bind(R.id.careighth)
    CardView careighth;
    @Bind(R.id.carninth_content)
    ConstraintLayout carninthContent;
    @Bind(R.id.carninth)
    CardView carninth;
    @Bind(R.id.send)
    Button send;
    private TextView title;
    private TextView content;
    private ImageView slider;
    CardFirstItemBean bean1;
    private TextView textView_edit;
    // 声明存储首选项 对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_plan);
        ButterKnife.bind(this);
        bean1 = new CardFirstItemBean();
        EventBus.getDefault().register(this);
        //initFirstItem();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void card1EventBus(CardFirstItemBean bean) {
        this.bean1 = bean;
        carfirstContent.setVisibility(View.GONE);
        carfirst.setEnabled(false);
        //渲染card1布局
        View item1 = LayoutInflater.from(this).inflate(R.layout.carditem1, null);
        carfirst.addView(item1);
        title = (TextView) item1.findViewById(R.id.textView_card1Name);
        content = (TextView) item1.findViewById(R.id.textView_card1Content);
        textView_edit = (TextView) item1.findViewById(R.id.textView_edit);
        slider = (ImageView) item1.findViewById(R.id.slider_ad);
        title.setText(bean.getItemName());
        content.setText(bean.getItemContent());
        if (bean.getItemImagePath().size() != 0) {
            slider.setVisibility(View.VISIBLE);
            slider.setImageBitmap(getSDCardImg(bean.getItemImagePath().get(0).getPath()));
        } else {
            slider.setVisibility(View.GONE);
        }
        textView_edit.setOnClickListener(this);
    }

    public static Bitmap getSDCardImg(String imagePath) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
//获取资源图片
        return BitmapFactory.decodeFile(imagePath, opt);
    }

    @OnClick({R.id.imageViewback, R.id.save, R.id.carfirst, R.id.carsecond, R.id.carthird, R.id.carfourth, R.id.carfifth, R.id.carsixth, R.id.carseventh, R.id.careighth, R.id.carninth, R.id.send})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageViewback:
                finish();
                break;
            case R.id.save:
                break;
            case R.id.carfirst:
                intent = new Intent(this, CardFirstItemActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.carsecond:
                break;
            case R.id.carthird:
                break;
            case R.id.carfourth:
                break;
            case R.id.carfifth:
                break;
            case R.id.carsixth:
                break;
            case R.id.carseventh:
                break;
            case R.id.careighth:
                break;
            case R.id.carninth:
                break;
            case R.id.send:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        EventBus.getDefault().postSticky(bean1);
        Intent intent = new Intent(this, CardFirstItemActivity.class);
        startActivity(intent);
        finish();
    }
}
