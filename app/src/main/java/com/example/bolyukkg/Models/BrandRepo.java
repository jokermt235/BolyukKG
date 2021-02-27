package com.example.bolyukkg.Models;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.bolyukkg.Callback.IBrandRepo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class BrandRepo  extends  FireModel{
    private   static  String TAG = "BrandRepo";
    private IBrandRepo activity;
    private   Context context;
    public BrandRepo(Context c ,IBrandRepo activity){
        super(c);
        this.context = c;
        this.activity = activity;
    }

    public void filter(){
        this.getDb().collection("brand").orderBy("added", Query.Direction.DESCENDING)
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
                    activity.onFilterBrand(arrayList);
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
}
