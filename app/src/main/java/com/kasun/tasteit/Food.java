package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;


public class Food extends AppCompatActivity {

    TextView food1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        food1 = (TextView) findViewById(R.id.dog_food);
        food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Food.this, dogfood_list.class);
                startActivity(intent1);
            }
        });
    }
}