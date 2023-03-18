package com.example.photoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ViewPhotoActivity extends AppCompatActivity {

    ImageView iv_detail;
    TextView tv_detail_title, tv_detail_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_photo);

        //getSupportActionBar().hide();

        iv_detail = findViewById(R.id.iv_detail);
        tv_detail_title = findViewById(R.id.tv_detail_title);
        tv_detail_description = findViewById(R.id.tv_detail_description);


        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            return;
        }
        Photo photo = (Photo) bundle.get("object_photo");


//        //new DownloadImage(iv_detail).execute((getPhotoFromId(id, generatePhotoData()).getSource_photo()));
          Picasso.get().load(photo.getSource_photo()).resize(400, 500).centerCrop().into(iv_detail);
          tv_detail_title.setText(photo.getTitle_photo());
          tv_detail_description.setText(photo.getDescription_photo());
    }
}