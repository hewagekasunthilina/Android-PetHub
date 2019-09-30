package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class dashboard extends AppCompatActivity {

    TextView txtbtn;
    TextView txtbtn2;
    TextView txtbtn3;
    TextView txtbtn4;
    TextView txtbtn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtbtn = (TextView) findViewById(R.id.arr);
        txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dashboard.this, pet_dashboard.class);
                startActivity(intent);

            }
        });

        txtbtn5 = (TextView) findViewById(R.id.dashboard_food);
        txtbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(dashboard.this, Food.class);
                startActivity(intent2);
            }
        });

        txtbtn2 = (TextView) findViewById(R.id.arr2);
        txtbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(dashboard.this, insert_pet.class);
                startActivity(intent2);
            }
        });

        txtbtn3 = (TextView) findViewById(R.id.arr3);
        txtbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(dashboard.this, equipment_dashboard.class);
                startActivity(intent3);
            }
        });

        txtbtn4 = (TextView) findViewById(R.id.arr4);
        txtbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(dashboard.this, FeedbackDashboard.class);
                startActivity(intent4);
            }
        });

        txtbtn5 = (TextView) findViewById(R.id.arr5);
        txtbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(dashboard.this, contactUs.class);
                startActivity(intent5);
            }
        });

    }
}