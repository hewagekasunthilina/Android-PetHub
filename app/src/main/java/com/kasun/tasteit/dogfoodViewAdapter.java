//package com.kasun.tasteit;
//
//import android.app.AlertDialog;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import java.util.ArrayList;
//import android.widget.ImageButton;
//
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.constraintlayout.widget.ConstraintLayout;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//
//import java.util.List;
//
//public class dogfoodViewAdapter extends RecyclerView.Adapter<dogfoodViewAdapter.dogfoodViewHolder> {
//
//
//
//    FirebaseDatabase firebaseDatabase;
//    DatabaseReference databaseReference;
//    List<Petfood> dogfoodList;
//
//    public class dogfoodViewHolder extends RecyclerView.ViewHolder {
//
//        TextView foodName,foodBrand, foodPrice, foodExpdate;
//        ImageButton delete_btn;
//        ConstraintLayout singlelayout;
//
//        public dogfoodViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            foodName = itemView.findViewById(R.id.textViewh);
//            foodBrand = itemView.findViewById(R.id.textViewg);
//            foodPrice = itemView.findViewById(R.id.textViewf);
//            foodExpdate = itemView.findViewById(R.id.textViewe);
//          //  gender = itemView.findViewById(R.id.textView4);
//            delete_btn = itemView.findViewById(R.id.btn_deletedogfood);
//            singlelayout = itemView.findViewById(R.id.singlelayout);
//        }
//    }
//
//    public dogfoodViewAdapter(ArrayList<Petfood> dogfoodlist) {
//        this.dogfoodList = dogfoodlist;
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("Dogfood");
//    }
//
//    @NonNull
//    @Override
//    public dogfoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dogfood_row, parent, false);
//        return new dogfoodViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull final dogfoodViewHolder holder, int position) {
//        final int positionnew = position;
//        final Petfood petfood = dogfoodList.get(position);
//
//        holder.foodName.setText(petfood.getFoodName());
//
////        if (petfood.getFoodBrand().length() > 35) {
////            holder.foodBrand.setText(petfood.getFoodBrand().substring(0, 15) + "..");
////        } else {
////            holder.foodBrand.setText(petfood.getFoodPrice());
////        }
////
////        if (petfood.getFoodexpDate().length() > 35) {
////            holder.foodPrice.setText(petfood.getFoodBrand().substring(0, 15) + "..");
////        } else {
////            holder.foodPrice.setText(petfood.getFoodBrand());
////        }
////
////        if (petfood.getFoodexpDate().length() > 35) {
////            holder.foodExpdate.setText(petfood.getFoodexpDate().substring(0, 15) + "..");
////        } else {
////            holder.foodExpdate.setText(petfood.getFoodexpDate());
////        }
////
////        final String key = petfood.getFoodId();
//
//
//        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                final Context context = v.getContext();
//
//                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                builder.setCancelable(true);
//                builder.setTitle("Are you sure ?");
//                builder.setMessage("Please confirm that you really need to delete");
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        databaseReference.child(key).removeValue();
//                        Toast.makeText(context, "Dog Deleted", Toast.LENGTH_SHORT).show();
//                        dogfoodList.remove(positionnew);
//                        notifyDataSetChanged();
//                    }
//                });
//                builder.create();
//                builder.show();
//
//            }
//        });
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return dogfoodList.size();
//    }
//
//
//    //update need to be fixed
//}