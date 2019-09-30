package com.kasun.tasteit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    FirebaseDatabase mDatabaseRef;
    DatabaseReference databaseReference;

    private Context mContext;
    private List<Upload> mUploads;



    public ImageAdapter(ArrayList<Upload> up) {
        this.mUploads = up;
        mDatabaseRef = FirebaseDatabase.getInstance();
        databaseReference = mDatabaseRef.getReference("uploads");
    }
    public ImageAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {



        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false );
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        final int positionnew = position;

//        property_key = getRef(i).getKey();
        final Upload uploadimage = mUploads.get(position);

        final Upload uploadcurrent = mUploads.get(position);
        holder.textName.setText(uploadcurrent.getEquipmentname());
        holder.textOwner.setText(uploadcurrent.getOwnername());
        holder.textPrice.setText(uploadcurrent.getPrice());
        holder.textContact.setText(uploadcurrent.getContactnumber());
        holder.textDate.setText(uploadcurrent.getPublishdate());
        Picasso.with(mContext)
                .load(uploadcurrent.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.imageView);



        holder.txtbtnw.setOnClickListener(new View.OnClickListener() {
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


//                        String key = databaseReference.push().getKey();
                      //  databaseReference.child("").removeValue();



                        Toast.makeText(context, "Equipment Deleted", Toast.LENGTH_SHORT).show();
                        mUploads.remove(positionnew);
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
        return mUploads.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView textName;
        public TextView textOwner;
        public TextView textPrice;
        public TextView textContact;
        public TextView textDate;
        public ImageView imageView;
        public Button txtbtnw;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            textOwner = itemView.findViewById(R.id.text_owner);
            textPrice = itemView.findViewById(R.id.text_price);
            textContact = itemView.findViewById(R.id.text_contact);
            textDate = itemView.findViewById(R.id.text_date);
            imageView = itemView.findViewById(R.id.image_view);

            txtbtnw = (Button)  itemView.findViewById(R.id.delete_equipment);

        }
    }
}
