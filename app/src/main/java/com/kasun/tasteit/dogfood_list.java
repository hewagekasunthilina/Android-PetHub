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

public class dogfood_list extends AppCompatActivity {

    FloatingActionButton floatingActionButton;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<Petfood> dogfood_list;

    RecyclerView recyclerView;
    dogfoodViewAdapter dogfoodViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogfood_list);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.dogfood_insert);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(dogfood_list.this, insert_dogfood.class).putExtra("token", "new");
                startActivity(intent2);

            }
        });


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Dogfood");
        dogfood_list=new ArrayList<>();

        doReload(null);

    }

    private void doReload(View view) {
        final ProgressDialog dialog = ProgressDialog.show(dogfood_list.this, "",
                "Loading. Please wait...", true);
        dialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dogfood_list=new ArrayList<>();
                Iterable <DataSnapshot> iterable=dataSnapshot.getChildren();
                for(DataSnapshot dataSnapshot1:iterable){
                    Petfood petfood=dataSnapshot1.getValue(Petfood.class);
                    dogfood_list.add(petfood);

                    recyclerView=findViewById(R.id.viewdogrecyclerview);
                    dogfoodViewAdapter =new dogfoodViewAdapter(dogfood_list);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());

                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(dogfoodViewAdapter);
                }

                new dogfoodViewAdapter(dogfood_list).notifyItemRangeInserted(0, dogfood_list.size());

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }}
