package com.kasun.tasteit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kasun.tasteit.Model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackViewAdapter extends RecyclerView.Adapter<FeedbackViewAdapter.FeedbackViewHolder> {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Feedback> feeedbacklist;

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        TextView name, email, feedback;
        RatingBar ratingBar;
        ImageButton deletebtn;
        ConstraintLayout singlelayout;

        public FeedbackViewHolder(@NonNull View itemview) {
            super(itemview);

            name = itemview.findViewById(R.id.singlename);
            email = itemview.findViewById(R.id.singleemail);
            feedback = itemview.findViewById(R.id.singlemessage);
            ratingBar = itemview.findViewById(R.id.singlerating);
            deletebtn = itemview.findViewById(R.id.deletebtn);
            singlelayout = itemview.findViewById(R.id.singlelayout);
        }
    }

    public FeedbackViewAdapter(ArrayList<Feedback> feeedbacklist) {
        this.feeedbacklist = feeedbacklist;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("feedbacks");
    }

    @NonNull
    @Override
    public FeedbackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_single_row_content, parent, false);
        return new FeedbackViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedbackViewHolder holder, int position) {
        final int positionnew = position;
        final Feedback feedback = feeedbacklist.get(position);

        holder.name.setText(feedback.getName());

        if (feedback.getEmail().length() > 20) {
            holder.email.setText(feedback.getEmail().substring(0, 15) + "..");
        } else {
            holder.email.setText(feedback.getEmail());
        }

        if (feedback.getSuggession().length() > 35) {
            holder.feedback.setText(feedback.getSuggession().substring(0, 15) + "..");
        } else {
            holder.feedback.setText(feedback.getSuggession());
        }


        float f = Float.parseFloat(feedback.getRatingCount() + "");

        holder.ratingBar.setRating(f);

        final String key = feedback.getId();

        holder.singlelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Context context = v.getContext();

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Are you sure ?");
                builder.setMessage("Please confirm that you really need to edit");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent(context, Feedbacks.class)
                                .putExtra("id", key)
                                .putExtra("name", feedback.getName())
                                .putExtra("email", feedback.getEmail())
                                .putExtra("feed", feedback.getSuggession())
                                .putExtra("rate", feedback.getRatingCount() + "")
                                .putExtra("token", "edit")
                        );
                    }
                });
                builder.create();
                builder.show();
            }
        });

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Context context = v.getContext();

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("Are you sure ?");
                builder.setMessage("Please confirm that you really need to delete");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference.child(key).removeValue();
                        Toast.makeText(context, "Feedback Deleted", Toast.LENGTH_SHORT).show();
                        feeedbacklist.remove(positionnew);
                        notifyDataSetChanged();
                    }
                });
                builder.create();
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return feeedbacklist.size();
    }
}