package com.mango.leo.zsproject.personalcenter.show.userchange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.base.BaseActivity;
import com.mango.leo.zsproject.personalcenter.show.UserChangeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DepartmentActivity extends BaseActivity {

    @Bind(R.id.textView_cancel)
    TextView textViewCancel;
    @Bind(R.id.textView_save)
    TextView textViewSave;
    @Bind(R.id.editText_change)
    EditText editTextChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);
        ButterKnife.bind(this);
        editTextChange.setText(getIntent().getStringExtra("department"));

    }

    @OnClick({R.id.textView_cancel, R.id.textView_save})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.textView_cancel:
                intent = new Intent(this, UserChangeActivity.class);
                startActivity(intent);
                finish();
            case R.id.textView_save:

                break;
        }
    }
}
