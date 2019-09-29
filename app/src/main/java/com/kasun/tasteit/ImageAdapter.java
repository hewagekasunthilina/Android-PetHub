package com.kasun.tasteit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {



    private Context mContext;
    private List<Upload> mUploads;


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
        Upload uploadcurrent = mUploads.get(position);
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


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.text_name);
            textOwner = itemView.findViewById(R.id.text_owner);
            textPrice = itemView.findViewById(R.id.text_price);
            textContact = itemView.findViewById(R.id.text_contact);
            textDate = itemView.findViewById(R.id.text_date);
            imageView = itemView.findViewById(R.id.image_view);

        }
    }
}
