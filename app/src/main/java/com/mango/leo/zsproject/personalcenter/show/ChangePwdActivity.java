package com.mango.leo.zsproject.personalcenter.show;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePwdActivity extends BaseActivity {

    @Bind(R.id.imageView_change_back)
    ImageView imageViewChangeBack;
    @Bind(R.id.editText_beforpwd)
    EditText editTextBeforpwd;
    @Bind(R.id.editText_newpwd)
    EditText editTextNewpwd;
    @Bind(R.id.editText_newpwdok)
    EditText editTextNewpwdok;
    @Bind(R.id.button2_finish)
    Button button2Finish;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        ButterKnife.bind(this);
        sharedPreferences = getSharedPreferences("CIFIT",MODE_PRIVATE);

    }

    @OnClick({R.id.imageView_change_back, R.id.button2_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_change_back:
                finish();
                break;
            case R.id.button2_finish:
                //修改密码
                changePwd();
                finish();
                break;
        }
    }
    private void changePwd() {

    }
}
