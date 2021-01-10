package com.example.mylinkopener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.TaskInfo;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Set_Image extends AppCompatActivity {

    private ImageView imageView;
    private Button select;
    private Button capture;

    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__image);

        imageView = findViewById(R.id.imageView);
        select = findViewById(R.id.btn1);
        capture = findViewById(R.id.btn2);
        EnableRuntimePermission();
        
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new   Intent(Intent.ACTION_PICK,MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, 2);
            }
        });

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE,"New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION," From the Camera");
                image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
                if(intent !=null)
                    startActivityForResult(intent, 1);
                else
                    Log.i("AWAIS", "onClick: There is no app to capture image");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                imageView.setImageURI(image_uri);
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                imageView.setImageURI(selectedImage);
            }
        }
    }

    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(Set_Image.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(Set_Image.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Set_Image.this,new String[]{
                    Manifest.permission.CAMERA}, 1);
        }
    }
}