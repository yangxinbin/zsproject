package com.mango.leo.zsproject.personalcenter.show.kefu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhActivity extends AppCompatActivity {

    @Bind(R.id.imageView_ph)
    ImageView imageViewPh;
    @Bind(R.id.p1)
    RelativeLayout p1;
    @Bind(R.id.p2)
    RelativeLayout p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ph);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView_ph, R.id.p1, R.id.p2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_ph:
                finish();
                break;
            case R.id.p1:
                callPhone();
                break;
            case R.id.p2:
                callPhone();
                break;
        }
    }

    private void callPhone() {
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setIcon(R.drawable.icon)//设置标题的图片
                .setTitle("客服")//设置对话框的标题
                .setMessage("拨打客服电话：18123603216")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        callPhone("18123603216");
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
    /**
     * 拨打电话（直接拨打电话）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

}
