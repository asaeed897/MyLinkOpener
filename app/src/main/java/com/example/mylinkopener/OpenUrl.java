package com.example.mylinkopener;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OpenUrl extends AppCompatActivity {

    private EditText input;
    private Button load;
    private Button select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_url);

        load = findViewById(R.id.button);
        input = findViewById(R.id.input);
        select = findViewById(R.id.button2);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL = input.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                Intent chooser = Intent.createChooser(intent,"Choose an Application");
                // if there is any browser then start activity
                if(intent.resolveActivity(getPackageManager())!= null)
                    startActivity(chooser);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Set_Image.class);
                startActivity(intent);
            }
        });
    }
}