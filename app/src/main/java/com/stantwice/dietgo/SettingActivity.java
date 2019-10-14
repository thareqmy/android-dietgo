package com.stantwice.dietgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity {

    Button backBtn,saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        init();
    }

    void init(){
        backBtn = findViewById(R.id.back_button);
        saveBtn = findViewById(R.id.save_button);


        sendOnClick(backBtn, new Intent(SettingActivity.this, MainActivity.class));
        sendOnClick(saveBtn, new Intent(SettingActivity.this, MainActivity.class));


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