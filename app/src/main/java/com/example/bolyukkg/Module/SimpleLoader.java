package com.example.bolyukkg.Module;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bolyukkg.Callback.OnFilterResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class SimpleLoader {
    private static  String TAG = "SimpleLoader";
    public static void filter(final  String collection, String key, Object value ,final OnFilterResult result){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(collection).whereEqualTo(key, value ).orderBy("added", Query.Direction.DESCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG + "_" + collection, "Loaded success");
                    ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot document : querySnapshot) {
                        Map<String, Object> map = document.getData();
                        map.put("_ref", document.getId());
                        arrayList.add(map);
                        Log.d(TAG, document.getData().toString());
                    }
                    result.onResult(arrayList);
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
    public static void filter(final  String collection,final OnFilterResult result){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(collection).orderBy("added", Query.Direction.DESCENDING)
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG + "_" + collection, "Loaded success");
                    ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot document : querySnapshot) {
                        Map<String, Object> map = document.getData();
                        map.put("_ref", document.getId());
                        arrayList.add(map);
                        Log.d(TAG, document.getData().toString());
                    }
                    result.onResult(arrayList);
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
}
