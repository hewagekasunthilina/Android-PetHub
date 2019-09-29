package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kasun.tasteit.Model.Feedback;
import com.kasun.tasteit.Stables.ValidateEmail;

import java.text.SimpleDateFormat;
import java.util.Date;

public class insert_pet extends AppCompatActivity {

    EditText familyName,model,nickName, age,gender;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button addpet;
    String existkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pet);


        familyName=findViewById(R.id.familyname);
        model=findViewById(R.id.model);
        nickName=findViewById(R.id.nickname);
        age=findViewById(R.id.age);
        gender=findViewById(R.id.gender);
        addpet=findViewById(R.id.addpet);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Dogs");

//        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//            @Override
//            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                ratingcount=rating;
//            }
//        });

        if(getIntent().getStringExtra("token").equals("edit")){
            existkey=getIntent().getStringExtra("id");
            familyName.setText(getIntent().getStringExtra("familyName"));
            model.setText(getIntent().getStringExtra("model"));
            nickName.setText(getIntent().getStringExtra("nickName"));
            age.setText(getIntent().getStringExtra("age"));
            gender.setText(getIntent().getStringExtra("gender"));


            addpet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editFeedback();
                }
            });
        }else{
            addpet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveFeedback();
                }
            });
        }
    }

    public void saveFeedback(){
        if(!familyName.getText().toString().isEmpty()){
            if(!model.getText().toString().isEmpty()){
                if(new ValidateEmail().isValid(model.getText().toString())){
                    if(!nickName.getText().toString().isEmpty()){
                        //if(ratingcount!=0.0){
                            String key= databaseReference.push().getKey();
                            databaseReference.child(key).setValue(new Pet(key,familyName.getText().toString(),model.getText().toString(),nickName.getText().toString(),age.getText().toString(),gender.getText().toString()));

                            Toast.makeText(this, "Thank You So Much !", Toast.LENGTH_SHORT).show();
                            clearPet(null);
//                        }else{
//                            Toast.makeText(this, "Please rate us", Toast.LENGTH_SHORT).show();
                        //}
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
        if(!familyName.getText().toString().isEmpty()){
            if(!model.getText().toString().isEmpty()){
                if(new ValidateEmail().isValid(model.getText().toString())){
                    if(!nickName.getText().toString().isEmpty()){
                        //if(ratingcount!=0.0){
                            databaseReference.child(existkey).setValue(new Pet(existkey,familyName.getText().toString(),model.getText().toString(),nickName.getText().toString(),age.getText().toString(),gender.getText().toString()));

                            Toast.makeText(this, "Thank You So Much !", Toast.LENGTH_SHORT).show();
                            clearPet(null);
//                        }else{
//                            Toast.makeText(this, "Please rate us", Toast.LENGTH_SHORT).show();
//                        }
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

    public void clearPet(View view){
        familyName.setText("");
        model.setText("");
        nickName.setText("");
        age.setText("");
        gender.setText("");

    }
}
