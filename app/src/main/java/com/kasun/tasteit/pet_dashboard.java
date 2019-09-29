package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class pet_dashboard extends AppCompatActivity {

    TextView btn1;
    TextView btn2;
    TextView btn3;
    TextView btn4;
    TextView btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_dashboard);

        btn1 = (TextView) findViewById(R.id.dog);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(pet_dashboard.this, dog_list.class);
                startActivity(intent);

            }
        });

        btn2 = (TextView) findViewById(R.id.cat);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(pet_dashboard.this, dog_list.class);
                startActivity(intent2);

            }
        });
    }
}
