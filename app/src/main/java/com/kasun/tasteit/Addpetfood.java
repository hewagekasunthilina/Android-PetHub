package com.kasun.tasteit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Addpetfood extends AppCompatActivity {

    EditText foodcate, foodsubcate, foodbrand, foodprice, expdate;
    Button addpetfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_petfood);

        setTitle("Add New Pet food");

        foodcate = (EditText) findViewById(R.id.food_cate);
        foodsubcate = (EditText) findViewById(R.id.food_subcate);
        foodbrand = (EditText) findViewById(R.id.food_brand);
        foodprice = (EditText) findViewById(R.id.food_price);
        expdate = (EditText) findViewById(R.id.exp_date);

        addpetfood = (Button) findViewById(R.id.button_add);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_Petfood = database.getReference("Petfood");

        addpetfood.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final ProgressDialog mDialog = new ProgressDialog(Addpetfood.this);
                mDialog.setMessage("Please Waiting...");
                mDialog.show();

                table_Petfood.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(foodbrand.getText().toString()).exists())
                        {
                            mDialog.dismiss();
                            Toast.makeText(Addpetfood.this, "Data Added Successfully!", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            mDialog.dismiss();
                            Petfood pet = new Petfood(foodcate.getText().toString(),foodsubcate.getText().toString(),foodprice.getText().toString(),expdate.getText().toString(),foodbrand.getText().toString());
                            table_Petfood.child(foodbrand.getText().toString()).setValue(pet);

                            Toast.makeText(Addpetfood.this, "Data Added Successfully!", Toast.LENGTH_SHORT).show();
                            finish();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }
}