package com.example.bolyukkg.Module;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.bolyukkg.Callback.OnImageDownloadResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class SimpleImageLoader {
    private static String TAG = "SimpleImageLoader";
    public static  void loadImages(String collection , final String uid , final OnImageDownloadResult result) {
        if (uid!= null) {
            StorageReference storageRef = FirebaseStorage.getInstance().getReference();
            StorageReference islandRef = storageRef.child(collection+"/" + uid);
            final ArrayList<Bitmap> images = new ArrayList<>();
            Log.d(TAG, collection+"/" + uid);
            islandRef.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
                @Override
                public void onSuccess(ListResult listResult) {
                    StorageReference iRef = null;
                    for (StorageReference item : listResult.getItems()) {
                        if (item != null) {
                            iRef = item;
                            if (iRef != null) {
                                final long ONE_MEGABYTE = 1024 * 1024;
                                iRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                                    @Override
                                    public void onSuccess(byte[] bytes) {
                                        // Data for "images/island.jpg" is returns, use this as needed
                                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                                        images.add(bmp);
                                        result.onResult(images);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception exception) {
                                        // Handle any errors
                                        Log.d(TAG, "On storage failure");
                                    }
                                });
                            }
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
