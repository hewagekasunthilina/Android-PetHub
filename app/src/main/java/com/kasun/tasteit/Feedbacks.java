package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kasun.tasteit.Model.Feedback;
import com.kasun.tasteit.Stables.ValidateEmail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Feedbacks extends AppCompatActivity {

    EditText name,email,sugg;
    RatingBar rate;
    float ratingcount;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button saveandeditbtn;
    String existkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbacks);


        name=findViewById(R.id.feedbackname);
        email=findViewById(R.id.feedbackemail);
        sugg=findViewById(R.id.feedbacksugg);
        rate=findViewById(R.id.feedbackrating);
        saveandeditbtn=findViewById(R.id.feedbacksaveandeditbtn);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("feedbacks");

        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingcount=rating;
            }
        });

        if(getIntent().getStringExtra("token").equals("edit")){
            existkey=getIntent().getStringExtra("id");
            name.setText(getIntent().getStringExtra("name"));
            email.setText(getIntent().getStringExtra("email"));
            sugg.setText(getIntent().getStringExtra("feed"));
            rate.setRating(Float.parseFloat(getIntent().getStringExtra("rate")+""));

            saveandeditbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editFeedback();
                }
            });
        }else{
            saveandeditbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveFeedback();
                }
            });
        }
    }

    public void saveFeedback(){
        if(!name.getText().toString().isEmpty()){
            if(!email.getText().toString().isEmpty()){
                if(new ValidateEmail().isValid(email.getText().toString())){
                    if(!sugg.getText().toString().isEmpty()){
                        if(ratingcount!=0.0){
                            String key= databaseReference.push().getKey();
                            databaseReference.child(key).setValue(new Feedback(key,name.getText().toString(),email.getText().toString(),sugg.getText().toString(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),Double.parseDouble(ratingcount+"")));

                            Toast.makeText(this, "Thank You So Much !", Toast.LENGTH_SHORT).show();
                            clearFeedbacks(null);
                        }else{
                            Toast.makeText(this, "Please rate us", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "We need your feedback", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Please fill valid email", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Please fill your email", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please fill your name", Toast.LENGTH_SHORT).show();
        }
    }

    public void editFeedback(){
        if(!name.getText().toString().isEmpty()){
            if(!email.getText().toString().isEmpty()){
                if(new ValidateEmail().isValid(email.getText().toString())){
                    if(!sugg.getText().toString().isEmpty()){
                        if(ratingcount!=0.0){
                            databaseReference.child(existkey).setValue(new Feedback(existkey,name.getText().toString(),email.getText().toString(),sugg.getText().toString(),new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()),Double.parseDouble(ratingcount+"")));

                            Toast.makeText(this, "Thank You So Much !", Toast.LENGTH_SHORT).show();
                            clearFeedbacks(null);
                        }else{
                            Toast.makeText(this, "Please rate us", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(this, "We need your feedback", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "Please fill valid email", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Please fill your email", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please fill your name", Toast.LENGTH_SHORT).show();
        }
    }

    public void clearFeedbacks(View view){
        name.setText("");
        email.setText("");
        sugg.setText("");
        rate.setRating(0);
    }
}
