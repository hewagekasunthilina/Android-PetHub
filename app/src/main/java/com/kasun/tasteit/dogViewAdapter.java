package com.kasun.tasteit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.List;

public class dogViewAdapter extends RecyclerView.Adapter<dogViewAdapter.dogViewHolder> {



    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<Pet> dogList;

    public class dogViewHolder extends RecyclerView.ViewHolder {

        TextView familyName, model, nickName, age, gender;
        ImageButton delete_btn;
        ConstraintLayout singlelayout;

        public dogViewHolder(@NonNull View itemView) {
            super(itemView);

            familyName = itemView.findViewById(R.id.textView14);
            model = itemView.findViewById(R.id.textView13);
            nickName = itemView.findViewById(R.id.textView12);
            age = itemView.findViewById(R.id.textView11);
            gender = itemView.findViewById(R.id.textView4);
            delete_btn = itemView.findViewById(R.id.delete_btn);
            singlelayout = itemView.findViewById(R.id.singlelayout);
        }
    }

    public dogViewAdapter(ArrayList<Pet> doglist) {
        this.dogList = doglist;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Dogs");
    }

    @NonNull
    @Override
    public dogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_row, parent, false);
        return new dogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final dogViewHolder holder, int position) {
        final int positionnew = position;
        final Pet pet = dogList.get(position);

       holder.familyName.setText(pet.getFamilyName());

        if (pet.getModel().length() > 35) {
            holder.model.setText(pet.getModel().substring(0, 15) + "..");
        } else {
            holder.model.setText(pet.getModel());
        }

        if (pet.getAge().length() > 35) {
            holder.age.setText(pet.getModel().substring(0, 15) + "..");
        } else {
            holder.age.setText(pet.getModel());
        }

        if (pet.getGender().length() > 35) {
            holder.gender.setText(pet.getModel().substring(0, 15) + "..");
        } else {
            holder.gender.setText(pet.getModel());
        }

        final String key = pet.getId();


        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
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
                        Toast.makeText(context, "Dog Deleted", Toast.LENGTH_SHORT).show();
                        dogList.remove(positionnew);
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
        return dogList.size();
    }


    //update need to be fixed
}