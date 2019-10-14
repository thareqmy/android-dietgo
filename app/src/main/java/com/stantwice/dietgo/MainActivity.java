package com.stantwice.dietgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button statBtn,reportBtn,settingBtn,weightBtn,shopBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }

    void init(){
        statBtn = findViewById(R.id.statistics_button);
        reportBtn = findViewById(R.id.report_button);
        settingBtn = findViewById(R.id.setting_button);
        weightBtn = findViewById(R.id.weight_button);
        shopBtn = findViewById(R.id.shop_button);

        sendOnClick(statBtn, new Intent(MainActivity.this, StatisticActivity.class));
        sendOnClick(reportBtn, new Intent(MainActivity.this, ReportActivity.class));
        sendOnClick(settingBtn, new Intent(MainActivity.this, SettingActivity.class));
        sendOnClick(weightBtn, new Intent(MainActivity.this, WeightActivity.class));
        sendOnClick(shopBtn, new Intent(MainActivity.this, ShopActivity.class));

    }

    void sendOnClick(View v, final Intent a){

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(a);
            }
        });

    }
}