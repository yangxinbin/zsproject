package com.mango.leo.zsproject.industrialservice.createrequirements.carditems;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.utils.AppUtils;
import com.mango.leo.zsproject.viewutil.LinedEditText;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CardFirstItemActivity extends AppCompatActivity {

    @Bind(R.id.imageView1_back)
    ImageView imageView1Back;
    @Bind(R.id.textView1_save)
    TextView textView1Save;
    @Bind(R.id.item_title)
    EditText itemTitle;
    @Bind(R.id.item_content)
    LinedEditText itemContent;
    @Bind(R.id.add_iv_photo)
    ImageView addIvPhoto;
    @Bind(R.id.add_iv_show)
    ImageView addIvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_first_item);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.imageView1_back, R.id.textView1_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView1_back:
                finish();
                break;
            case R.id.textView1_save:
                break;
        }
    }

    /**
     * 检查权限
     */
    /*private void checkSelfPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {
                AppUtils.showSnackar(addIvPhoto, "需要读写权限");
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        } else {
            if (image == null) {
                MultiImageSelector.create()
                        .showCamera(true)
                        .single()
                        .start(this, 0);
            } else {
                AppUtils.showSnackar(addIvPhoto, "你已经选择了一张图片");
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    MultiImageSelector.create()
                            .showCamera(true) // 是否显示相机. 默认为显示
                            .single() // 单选模式
                            .multi() // 多选模式, 默认模式;
                            .start(this, 0);
                } else {
                    AppUtils.showSnackar(addIvPhoto, "没有授予读写权限，导出失败,请到设置中手动打开");
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode == 0) {
            List<String> selectPaths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            if (selectPaths.size() != 0) {
                String imagePath = selectPaths.get(0);
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                addIvShow.setVisibility(View.VISIBLE);
                addIvShow.setImageBitmap(bitmap);
                image = imagePath;
            } else {
                addIvShow.setVisibility(View.GONE);
            }
        }
    }*/
}
