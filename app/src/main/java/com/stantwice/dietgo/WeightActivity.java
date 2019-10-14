package com.stantwice.dietgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeightActivity extends AppCompatActivity {

    Button backBtn,submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        init();

    }

    void init(){
        backBtn = findViewById(R.id.back_button);
        submitBtn = findViewById(R.id.submit_button);


        sendOnClick(backBtn, new Intent(WeightActivity.this, MainActivity.class));
        sendOnClick(submitBtn, new Intent(WeightActivity.this, MainActivity.class));


    }

    void sendOnClick(View v, final Intent a){

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(a);
                finish();
            }
        });

    }
}
