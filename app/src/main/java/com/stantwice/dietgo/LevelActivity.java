package com.stantwice.dietgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LevelActivity extends AppCompatActivity {
    Button left,right;
    ImageView characterView;
    int image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        left = findViewById(R.id.left_btn);
        right = findViewById(R.id.right_btn);
        characterView = findViewById(R.id.character_image);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image = (image-1)%4;
                setImage(image);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image = (image+1)%4;
                setImage(image);
            }
        });
    }

    public void setImage(int image){
        if(image == 0){
            characterView.setImageResource(R.drawable.character);
        }else if(image == 1){
            characterView.setImageResource(R.drawable.char2);
        }else if(image == 2){
            characterView.setImageResource(R.drawable.char3);
        }else if(image == 3){
            characterView.setImageResource(R.drawable.char4);
        }
    }
}
