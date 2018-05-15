package com.mango.leo.zsproject.industrialservice.createrequirements;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mango.leo.zsproject.R;
import com.mango.leo.zsproject.industrialservice.createrequirements.carditems.CardFirstItemActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessPlanActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_plan);
        ButterKnife.bind(this);
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
}
