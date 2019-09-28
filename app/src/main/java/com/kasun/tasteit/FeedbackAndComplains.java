package com.kasun.tasteit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kasun.tasteit.Model.Feedback;

import java.util.ArrayList;

public class FeedbackAndComplains extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ArrayList<Feedback> feedbacklist;

    RecyclerView recyclerView;
    FeedbackViewAdapter feedbackViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_and_complains);


        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("feedbacks");
        feedbacklist=new ArrayList<>();

        doReload(null);

    }

    private void doReload(View view) {
        final ProgressDialog dialog = ProgressDialog.show(FeedbackAndComplains.this, "",
                "Loading. Please wait...", true);
        dialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                feedbacklist=new ArrayList<>();
                Iterable <DataSnapshot> iterable=dataSnapshot.getChildren();
                for(DataSnapshot dataSnapshot1:iterable){
                    Feedback feedback=dataSnapshot1.getValue(Feedback.class);
                    feedbacklist.add(feedback);
                    recyclerView=findViewById(R.id.viewfeedbacksrecyclerview);
                    feedbackViewAdapter=new FeedbackViewAdapter(feedbacklist);
                    RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(feedbackViewAdapter);
                }

                new FeedbackViewAdapter(feedbacklist).notifyItemRangeInserted(0, feedbacklist.size());

                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }}
