package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kasun.tasteit.Stables.ValidateEmail;

public class insert_dogfood extends AppCompatActivity {

    EditText foodName,foodBrand,foodPrice, foodExpdate,foodMandate;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Button adddogfood;
    String existkey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dogfood);


        foodName=findViewById(R.id.foodName);
        foodBrand=findViewById(R.id.foodBrand);
        foodPrice=findViewById(R.id.foodPrice);
        foodExpdate=findViewById(R.id.foodExpdate);
        foodMandate=findViewById(R.id.foodMandate);
        adddogfood=findViewById(R.id.adddogfood);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Dogfood");

        if(getIntent().getStringExtra("token").equals("edit")){
            existkey=getIntent().getStringExtra("id");
            foodName.setText(getIntent().getStringExtra("foodName"));
            foodBrand.setText(getIntent().getStringExtra("foodBrand"));
            foodPrice.setText(getIntent().getStringExtra("foodPrice"));
            foodExpdate.setText(getIntent().getStringExtra("foodExpdate"));
            foodMandate.setText(getIntent().getStringExtra("foodMandate"));


            adddogfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    editFeedback();
                }
            });
        }else{
            adddogfood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveFeedback();
                }
            });
        }
    }

    public void saveFeedback(){
        if(!foodName.getText().toString().isEmpty()){
            if(!foodBrand.getText().toString().isEmpty()){
                if(new ValidateEmail().isValid(foodBrand.getText().toString())){
                    if(!foodBrand.getText().toString().isEmpty()){
                        //if(ratingcount!=0.0){
                        String key= databaseReference.push().getKey();
                        databaseReference.child(key).setValue(new Pet(key,foodName.getText().toString(),foodBrand.getText().toString(),foodPrice.getText().toString(),foodExpdate.getText().toString(),foodMandate.getText().toString()));

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
        if(!foodName.getText().toString().isEmpty()){
            if(!foodBrand.getText().toString().isEmpty()){
                if(new ValidateEmail().isValid(foodBrand.getText().toString())){
                    if(!foodPrice.getText().toString().isEmpty()){
                        //if(ratingcount!=0.0){
                        databaseReference.child(existkey).setValue(new Pet(existkey,foodName.getText().toString(),foodBrand.getText().toString(),foodPrice.getText().toString(),foodExpdate.getText().toString(),foodMandate.getText().toString()));

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
        foodName.setText("");
        foodBrand.setText("");
        foodPrice.setText("");
        foodExpdate.setText("");
        foodMandate.setText("");

    }
}
