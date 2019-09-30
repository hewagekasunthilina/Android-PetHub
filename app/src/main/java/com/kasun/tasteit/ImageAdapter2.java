package com.kasun.tasteit;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter2 extends RecyclerView.Adapter<ImageAdapter2.ImageViewHolder> {




    private Context mContext;
    private List<Petfood> mUploads;


    public ImageAdapter2(Context context, List<Petfood> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.dogfood_row, parent, false );

        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        final int positionnew = position;

        Petfood currentupload = mUploads.get(position);
        holder.mfoodname.setText(currentupload.getFoodName());
        holder.mfoodbrand.setText(currentupload.getFoodBrand());
        holder.mfoodprice.setText(currentupload.getFoodPrice());
        holder.mfoodexpdate.setText(currentupload.getFoodExpDate());
        holder.mfoodmandate.setText(currentupload.getFoodManDate());
        Picasso.with(mContext)
                .load(currentupload.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.mimageView);


        holder.btndeletedogfood.setOnClickListener(new View.OnClickListener() {
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



                        Toast.makeText(context, "Dog Food Deleted", Toast.LENGTH_SHORT).show();
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
        public TextView mfoodname;
        public TextView mfoodbrand;
        public TextView mfoodprice;
        public TextView mfoodexpdate;
        public TextView mfoodmandate;
        public ImageView mimageView;
        Button btndeletedogfood;


        @SuppressLint("WrongViewCast")
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            mfoodname = itemView.findViewById(R.id.food_name);
            mfoodbrand = itemView.findViewById(R.id.food_brand);
            mfoodprice = itemView.findViewById(R.id.food_price);
            mfoodexpdate = itemView.findViewById(R.id.foodexp_date);
            mfoodmandate = itemView.findViewById(R.id.foodman_date);
            mimageView = itemView.findViewById(R.id.image_view2);
            btndeletedogfood = (Button)  itemView.findViewById(R.id.deletedogfood);
        }
    }
}
