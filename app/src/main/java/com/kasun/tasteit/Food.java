package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Food extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    TextView food1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.adddogfood2);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(Food.this, insert_dogfood.class);
                startActivity(intent4);

            }
        });

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