package com.stantwice.dietgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.statistics_button)
    public void toStat() {
        startActivity(new Intent(this, StatisticActivity.class));
    }
    @OnClick(R.id.report_button)
    public void toReport() {
        startActivity(new Intent(this, ReportActivity.class));
    }
    @OnClick(R.id.setting_button)
    public void toSetting() {
        startActivity(new Intent(this, SettingActivity.class));
    }
    @OnClick(R.id.weight_button)
    public void toWeight() {
        startActivity(new Intent(this, WeightActivity.class));
    }
    @OnClick(R.id.shop_button)
    public void toShop() {
        startActivity(new Intent(this, ShopActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}