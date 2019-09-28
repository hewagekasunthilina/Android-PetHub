package com.kasun.tasteit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class insert_equipment extends AppCompatActivity  {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText equipmentname, ownername, price, contactnumber, publishdate;
    Button addequipment, button6;
    TextView show_upload;
    ProgressBar progress_bar;
    Uri ImageUri;
    ImageView imageView;

//    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_equipment);

        equipmentname = (EditText) findViewById(R.id.equipmentname);
        ownername = (EditText) findViewById(R.id.ownername);
        price = (EditText) findViewById(R.id.price);
        contactnumber = (EditText) findViewById(R.id.contactnumber);
        publishdate = (EditText) findViewById(R.id.publishdate);
        imageView = (ImageView) findViewById(R.id.image_view);

        show_upload = (TextView) findViewById(R.id.show_upload);
        progress_bar = (ProgressBar) findViewById(R.id.progress_bar);

        //spinner = (Spinner) findViewById(R.id.spinner);

        addequipment = (Button) findViewById(R.id.addequipment);
        button6 = (Button) findViewById(R.id.button6);

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

        addequipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        show_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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

            ImageUri = data.getData();

            Picasso.with(this).load(ImageUri).into(imageView);
        }
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

