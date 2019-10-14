package com.stantwice.dietgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeightActivity extends AppCompatActivity {

    @OnClick(R.id.back_button)
    public void toSetting() {
        startActivity(new Intent(this, MainActivity.class));
    }
    @OnClick(R.id.submit_button)
    public void toWeight() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        ButterKnife.bind(this);

    }
}
