package com.example.bolyukkg.Models;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;

import com.example.bolyukkg.Callback.ITranslateData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class CartRepo extends FireModel {

    private static  String TAG = "CartRepo";
    private ITranslateData translateData;
    public CartRepo(Context c, ITranslateData translateData){
        super(c);
        this.translateData = translateData;
    }
    public void save(Map<String, Object> data) {

        data.put("detailId", data.get("id"));
        data.put("added", new Date().getTime());
        data.put("androidId", this.getAndroidId());
        this.getDb().collection("cart").add(data).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                translateData.onSaveItem(true);
            }
        })
        .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding document", e);
            }
        });
    }
    public void filter() {
        Log.d(TAG, this.getAndroidId());
        this.getDb().collection("cart").whereEqualTo("androidId", this.getAndroidId()).orderBy("added", Query.Direction.DESCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot document : querySnapshot) {
                        Map<String, Object> map = document.getData();
                        map.put("_ref", document.getId());
                        arrayList.add(map);
                    }
                    translateData.onFilerData(arrayList);
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }

    public void remove(String doc){
        if(doc != null) {
            this.getDb().collection("cart").document(doc).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    filter();
                }
            });
        }
    }
}
