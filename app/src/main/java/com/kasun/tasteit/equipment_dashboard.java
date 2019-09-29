package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class equipment_dashboard extends AppCompatActivity {
    TextView txtbtn;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_dashboard);

        // setTitle("Equpment Dashboard");

        floatingActionButton = (FloatingActionButton) findViewById(R.id.add_btn);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(equipment_dashboard.this, insert_equipment.class);
                startActivity(intent1);

            }
        });

        txtbtn = (TextView) findViewById(R.id.category1);
        txtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(equipment_dashboard.this, ImagesActivity.class);
                startActivity(intent);

            }
        });


    }
}
