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

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter1 extends RecyclerView.Adapter<ImageAdapter1.ImageViewHolder> {



    private Context mContext;
    private List<Pet> mUploads;


    public ImageAdapter1(Context context, List<Pet> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.dog_image_item, parent, false );

        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {

        final int positionnew = position;

        Pet currentupload = mUploads.get(position);
        holder.mfamily.setText(currentupload.getFamilyName());
        holder.mmodel.setText(currentupload.getModel());
        holder.mage.setText(currentupload.getAge());
        holder.mnickname.setText(currentupload.getNickName());
        holder.mgender.setText(currentupload.getGender());
        Picasso.with(mContext)
                .load(currentupload.getImageUrl())
                .fit()
                .centerCrop()
                .into(holder.mimageView);


        holder.btndogdlt.setOnClickListener(new View.OnClickListener() {
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



                        Toast.makeText(context, "Dog Deleted", Toast.LENGTH_SHORT).show();
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
        public TextView mfamily;
        public TextView mmodel;
        public TextView mage;
        public TextView mnickname;
        public TextView mgender;
        public ImageView mimageView;
        Button btndogdlt;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            mfamily = itemView.findViewById(R.id.family1);
            mmodel = itemView.findViewById(R.id.model1);
            mage = itemView.findViewById(R.id.age1);
            mnickname = itemView.findViewById(R.id.nickname1);
            mgender = itemView.findViewById(R.id.gender1);
            mimageView = itemView.findViewById(R.id.image_view1);
            btndogdlt = (Button)  itemView.findViewById(R.id.dog_del_btn);
        }
    }
}
