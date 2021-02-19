package com.example.bolyukkg.Models;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bolyukkg.Callback.OnSaveResult;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CartRepo extends FireModel  implements IModel{

    private static  String TAG = "CartRepo";
    public CartRepo(Context c){
        super(c);
    }
    @Override
    public void save(Cart cart, final OnSaveResult result) {
        final Map<String, Object> data = new HashMap<>();
        data.put("amount", cart.getAmount());
        data.put("currency", cart.getCurrency());
        data.put("datailId", cart.getDetailId());
        data.put("price", cart.getPrice());
        data.put("unit", cart.getUnit());
        data.put("uid", cart.getId());
        data.put("androidId", this.getAndroidId());
        data.put("added", new Date().getTime());
        this.getDb().collection("cart").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                result.onSave(true);
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public void delete(Cart cart) {

    }
}
