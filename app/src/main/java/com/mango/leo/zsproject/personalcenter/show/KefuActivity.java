package com.mango.leo.zsproject.personalcenter.show;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.personalcenter.show.kefu.PhActivity;
import com.mango.leo.zsproject.personalcenter.show.kefu.QueActivity;
import com.mango.leo.zsproject.personalcenter.show.kefu.WeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KefuActivity extends AppCompatActivity {

    @Bind(R.id.imageView_kefuback)
    ImageView imageViewKefuback;
    @Bind(R.id.k1)
    RelativeLayout k1;
    @Bind(R.id.k2)
    RelativeLayout k2;
    @Bind(R.id.k3)
    RelativeLayout k3;
    @Bind(R.id.k4)
    RelativeLayout k4;
    @Bind(R.id.wx)
    RelativeLayout wx;
    @Bind(R.id.ph)
    RelativeLayout ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kefu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_kefuback, R.id.k1, R.id.k2, R.id.k3, R.id.k4, R.id.wx, R.id.ph})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.imageView_kefuback:
                finish();
                break;
            case R.id.k1:
                intent = new Intent(this, QueActivity.class);
                intent.putExtra("k1","如何修改登录密码？");
                intent.putExtra("k2","点击【我的】-【设置】-【账号安全】-【修改密码】-【输入原密码】-【输入新密码】-【确认新密码】，即可修改成功。");
                startActivity(intent);
                break;
            case R.id.k2:
                intent = new Intent(this, QueActivity.class);
                intent.putExtra("k1","忘记登录密码怎么办？");
                intent.putExtra("k2","点击【我的】-【设置】-【账号安全】-【修改密码】-【输入短信验证码】，即可修改密码。");
                startActivity(intent);
                break;
            case R.id.k3:
                intent = new Intent(this, QueActivity.class);
                intent.putExtra("k1","如何修改手机号？");
                intent.putExtra("k2","点击【我的】-【设置】-【账号安全】-【绑定新手机】-【输入短信验证码】，即可修改成功。");
                startActivity(intent);
                break;
            case R.id.k4:
                intent = new Intent(this, QueActivity.class);
                intent.putExtra("k1","如何修改邮箱？");
                intent.putExtra("k2","点击【我的】-【设置】-【账号安全】-【修改邮箱】-【输入新邮箱】-【获取新邮箱信验证码】，即可修改成功。");
                startActivity(intent);
                break;
            case R.id.wx:
                intent = new Intent(this, WeActivity.class);
                startActivity(intent);
                break;
            case R.id.ph:
                intent = new Intent(this, PhActivity.class);
                startActivity(intent);
                break;
        }
    }
}
