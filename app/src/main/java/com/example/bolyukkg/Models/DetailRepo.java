package com.example.bolyukkg.Models;
import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.example.bolyukkg.Callback.ITranslateData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class DetailRepo extends  FireModel {
    private final static String TAG = "DetailRepo";
    private Context context;
    private ITranslateData translateData;
    public DetailRepo(Context context, ITranslateData iTranslateData){
        super(context);
        this.context = context;
        this.translateData = iTranslateData;
    }
    public void filter(String idCat, String idBrand , final String name) {
        CollectionReference ref = this.getDb().collection("detail");
        Query query = ref.whereEqualTo("_category", idCat)
                .whereEqualTo("_brand", idBrand);
        query.orderBy("name", Query.Direction.DESCENDING).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot document : querySnapshot) {
                        Map<String, Object> map = document.getData();
                        map.put("_ref", document.getId());
                        String _name = (String)map.get("name");
                        if(name != null) {
                            if (_name.toLowerCase().contains(name.toLowerCase())) {
                                arrayList.add(map);
                            }
                        }else{
                            arrayList.add(map);
                        }
                    }
                    translateData.onFilerData(arrayList);
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
    }
}
