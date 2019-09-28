package com.kasun.tasteit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kasun.tasteit.Model.Complain;
import com.kasun.tasteit.Stables.ValidateEmail;

public class Complains extends AppCompatActivity {

    EditText name,email,sugg;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complains);

        name=findViewById(R.id.complainname);
        email=findViewById(R.id.complainemail);
        sugg=findViewById(R.id.complainteext);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("complains");
    }


    public void saveComplain(View view){
        if(!name.getText().toString().isEmpty()){
            if(!email.getText().toString().isEmpty()){
                if(new ValidateEmail().isValid(email.getText().toString())){
                    if(!sugg.getText().toString().isEmpty()){
                        String key= databaseReference.push().getKey();
                        databaseReference.child(key).setValue(new Complain(key,name.getText().toString(),email.getText().toString(),sugg.getText().toString()));

                        Toast.makeText(this, "Sorry For The Mistake & Thank You So Much", Toast.LENGTH_SHORT).show();
                        clearComplain(null);

                    }else{
                        Toast.makeText(this, "We need your complain", Toast.LENGTH_SHORT).show();
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

    public void clearComplain(View view){
        name.setText("");
        email.setText("");
        sugg.setText("");
    }
}
