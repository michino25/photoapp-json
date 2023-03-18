package com.example.photoapp;



import static android.support.constraint.Constraints.TAG;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoData {

    public static ArrayList<Photo> generatePhotoData() {
        ArrayList<Photo> photos = new ArrayList<>();

        return photos;
    }

    public static Photo getPhotoFromId(int id) {
        ArrayList<Photo> phs = generatePhotoData();
        for (int i = 0; i < phs.size(); i++)
            if (phs.get(i).getId() == id)
                return phs.get(i);
        return null;
    }



}

