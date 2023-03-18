package com.example.photoapp;

import static android.support.constraint.Constraints.TAG;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    GridView gridview;
    private AdapterView.OnItemClickListener onitemclick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), ViewPhotoActivity.class);
            intent.putExtra("id", gridview.getAdapter().getItemId(position));
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();
        ApiService.apiService.groupList().enqueue(new Callback<ArrayList<Photo>>() {


            @Override
            public void onResponse(Call<ArrayList<Photo>> call, Response<ArrayList<Photo>> response) {
                try {

                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                    ArrayList<Photo> LatLngData = response.body();
                    Log.v(TAG, "LOGS" + LatLngData.size());
                    ArrayList<Photo> photos = new ArrayList<>();
                    for (int i = 0; i < LatLngData.size(); i++) {
                        int id = LatLngData.get(i).getId();
                        String image = LatLngData.get(i).getSource_photo();
                        String title = LatLngData.get(i).getTitle_photo();
                        String detail = LatLngData.get(i).getDescription_photo();
                        photos.add(new Photo(id,image,title,detail));
                    }

                    gridview = findViewById(R.id.gridview);
                    PhotoAdapter adapter = new PhotoAdapter(photos, getApplicationContext());
                    gridview.setAdapter(adapter);
                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            final Photo photo = photos.get(position);
                            Intent intent = new Intent(getApplicationContext(), ViewPhotoActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("object_photo",photo);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        }
                    });



                } catch (Exception e) {
                    Log.d("onResponse", "There is an error");
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Photo>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

}