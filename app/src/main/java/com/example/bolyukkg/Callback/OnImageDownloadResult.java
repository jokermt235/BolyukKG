package com.example.bolyukkg.Callback;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class OnImageDownloadResult {
    private ArrayList<Bitmap> items;
    public void onResult(ArrayList<Bitmap> items){
        this.items = items;
    }
}
