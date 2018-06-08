package com.mango.leo.zsproject.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserBackActivity extends AppCompatActivity {

    @Bind(R.id.imageView_userback)
    ImageView imageViewUserback;
    @Bind(R.id.editText_ba)
    EditText editTextBa;
    @Bind(R.id.send_back)
    Button sendBack;
    int num = 200;//限制的最大字数
    @Bind(R.id.textView_numba)
    TextView textViewNumba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_back);
        ButterKnife.bind(this);
        editeNum();
    }

    private void editeNum() {
        editTextBa.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                temp = charSequence;
            }

            @Override
            public void afterTextChanged(Editable editable) {
                int number = num - editable.length();
                textViewNumba.setText("剩余" + number + "字");
                selectionStart = editTextBa.getSelectionStart();
                selectionEnd = editTextBa.getSelectionEnd();
                if (temp.length() > num) {
                    editable.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    editTextBa.setText(editable);
                    editTextBa.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }

    @OnClick({R.id.imageView_userback, R.id.send_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView_userback:
                finish();
                break;
            case R.id.send_back:
                //上传
                break;
        }
    }
}
