package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class equipment_dashboard extends AppCompatActivity {

    TextView txtbtn6;

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

        txtbtn6 = (TextView) findViewById(R.id.arr1);
        txtbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(equipment_dashboard.this, dog_list.class);
                startActivity(intent4);
            }
        });




    }
}
