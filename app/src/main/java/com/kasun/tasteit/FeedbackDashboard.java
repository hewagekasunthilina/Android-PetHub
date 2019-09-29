package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FeedbackDashboard extends AppCompatActivity {


    TextView txtbtn22;
    TextView txtbtn33;
    TextView txtbtn44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_dashboard);

        txtbtn44 = (TextView) findViewById(R.id.suggestion);
        txtbtn44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(FeedbackDashboard.this, Feedbacks.class)
                .putExtra("token", "new");
                startActivity(intent4);

            }
        });

        txtbtn22 = (TextView) findViewById(R.id.dashboard_food);
        txtbtn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(FeedbackDashboard.this, Complains.class);
                startActivity(intent1);

            }
        });

        txtbtn33 = (TextView) findViewById(R.id.myFeedback);
        txtbtn33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(FeedbackDashboard.this, FeedbackAndComplains.class);
                startActivity(intent2);

            }
        });

    }
}
