package com.kasun.tasteit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kasun.tasteit.Model.Feedback;

import java.util.ArrayList;

public class dog_list extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<Pet> dog_list;

    RecyclerView recyclerView;
    dogViewAdapter dogViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog_list);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.dog_insert);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(dog_list.this, insert_pet.class).putExtra("token", "new");
                startActivity(intent2);

            }
        });


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Dogs");
        dog_list=new ArrayList<>();

        doReload(null);

    }

    private void doReload(View view) {
        final ProgressDialog dialog = ProgressDialog.show(dog_list.this, "",
                "Loading. Please wait...", true);
        dialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dog_list=new ArrayList<>();
                Iterable <DataSnapshot> iterable=dataSnapshot.getChildren();
                for(DataSnapshot dataSnapshot1:iterable){
                    Pet pet=dataSnapshot1.getValue(Pet.class);
                    dog_list.add(pet);

                    recyclerView=findViewById(R.id.viewdogrecyclerview);
                    dogViewAdapter =new dogViewAdapter(dog_list);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(dogViewAdapter);
                }

                new dogViewAdapter(dog_list).notifyItemRangeInserted(0, dog_list.size());

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }}
