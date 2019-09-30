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

public class insert_pet extends AppCompatActivity  {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText mfamilyName, mmodel, mage, mnickname, mgender;
    Button madddogbtn, mchoose_img1;
    TextView mshow_upload1;
    ProgressBar mprogress_bar1;
    Uri mImageUri;
    ImageView mimageView1;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mUploadTask;

//    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_pet);

        mfamilyName = (EditText) findViewById(R.id.familyName);
        mmodel = (EditText) findViewById(R.id.model);
        mage = (EditText) findViewById(R.id.age);
        mnickname = (EditText) findViewById(R.id.nickName);
        mgender = (EditText) findViewById(R.id.gender);
        mimageView1 = (ImageView) findViewById(R.id.image_view1);

        mshow_upload1 = (TextView) findViewById(R.id.show_upload);
        mprogress_bar1 = (ProgressBar) findViewById(R.id.progress_bar1);
        mStorageRef = FirebaseStorage.getInstance().getReference("Dog_List");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Dog_List");

        //spinner = (Spinner) findViewById(R.id.spinner);

        madddogbtn = (Button) findViewById(R.id.adddogbtn);
        mchoose_img1 = (Button) findViewById(R.id.choose_img1);

        mchoose_img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

        madddogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(insert_pet.this, "Upload in Progress", Toast.LENGTH_SHORT).show();

                }else {
                    uploadFile();
                }

            }
        });
//        mshow_upload1.setOnClickListener(new View.OnClickListener() {
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

            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mimageView1);
        }
    }

    private String getFileExtension(Uri uri){

        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile(){

        if(mImageUri != null){
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mprogress_bar1.setProgress(0);

                                }
                            }, 500);

                            Toast.makeText(insert_pet.this, "Upload Succesfull", Toast.LENGTH_LONG).show();
                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful()) ;
                            Uri downloadUrl = urlTask.getResult();



                            Pet pet = new Pet(mfamilyName.getText().toString().trim(), mmodel.getText().toString().trim(), mage.getText().toString().trim(),mnickname.getText().toString().trim(),mgender.getText().toString().trim(), downloadUrl.toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(pet);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(insert_pet.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mprogress_bar1.setProgress((int) progress);

                        }
                    });

        }else{

            Toast.makeText(this, "No File Selected", Toast.LENGTH_SHORT).show();
        }

    }
    private void openImagesActivity(){
        Intent intent = new Intent(this,dog_list.class);
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

