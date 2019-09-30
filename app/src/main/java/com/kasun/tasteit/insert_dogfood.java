package com.kasun.tasteit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class insert_dogfood extends AppCompatActivity  {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText mfoodName, mfoodBrand, mfoodPrice, mfoodExpDate, mfoodManDate;
    Button madddogfood, mchoose_img2;
    TextView mshow_upload;
    ProgressBar mprogress_bar2;
    Uri mImageUri2;
    ImageView mimageView;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

//    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_dogfood);

        mfoodName = (EditText) findViewById(R.id.foodName);
        mfoodBrand = (EditText) findViewById(R.id.foodBrand);
        mfoodPrice = (EditText) findViewById(R.id.foodPrice);
        mfoodExpDate = (EditText) findViewById(R.id.foodExpDate);
        mfoodExpDate = (EditText) findViewById(R.id.foodManDate);
        mimageView = (ImageView) findViewById(R.id.image_view2);

        //mshow_upload = (TextView) findViewById(R.id.show_upload);
        mprogress_bar2 = (ProgressBar) findViewById(R.id.progress_bar);
        mStorageRef = FirebaseStorage.getInstance().getReference("Dog_food");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Dog_food");

        //spinner = (Spinner) findViewById(R.id.spinner);

        madddogfood = (Button) findViewById(R.id.adddogfood);
        mchoose_img2 = (Button) findViewById(R.id.choose_img2);

        mchoose_img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

        madddogfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(insert_dogfood.this, "Upload in Progress", Toast.LENGTH_SHORT).show();

                }else {
                    uploadFile();
                }

            }
        });
//        mshow_upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openImagesActivity();
//
//            }
//        });
    }

    private void openFileChooser(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){

            mImageUri2 = data.getData();

            Picasso.with(this).load(mImageUri2).into(mimageView);
        }
    }

    private String getFileExtension(Uri uri){

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){

        if(mImageUri2 != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri2));

            mUploadTask = fileReference.putFile(mImageUri2)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mprogress_bar2.setProgress(0);

                                }
                            }, 500);

                            Toast.makeText(insert_dogfood.this, "Upload Succesfull", Toast.LENGTH_LONG).show();
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful()) ;
                            Uri downloadUrl = urlTask.getResult();



                            Petfood petfood = new Petfood(mfoodName.getText().toString().trim(), mfoodBrand.getText().toString().trim(), mfoodPrice.getText().toString().trim(),mfoodExpDate.getText().toString().trim(),mfoodManDate.getText().toString().trim(), downloadUrl.toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(petfood);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(insert_dogfood.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mprogress_bar2.setProgress((int) progress);

                        }
                    });

        }else{

            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }

    }
    private void openImagesActivity(){
        Intent intent = new Intent(this,ImagesActivity.class);
        startActivity(intent);
    }
}











































//setTitle("Add Equipment");

//        spinner = (Spinner) findViewById(R.id.spinner_equipment);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.category_equipment, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);



//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String text = parent.getItemAtPosition(position).toString();
//        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }

