package com.example.bolyukkg.Models;

import android.content.Context;
import android.provider.Settings;

import com.google.firebase.firestore.FirebaseFirestore;

public class FireModel {
    private Context context;
    private FirebaseFirestore db;
    private String androidId;
    public FireModel(Context c){
        this.androidId = Settings.Secure.getString(c.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        this.context = c;
        this.db = FirebaseFirestore.getInstance();
    }

    public String getAndroidId(){
        return this.androidId;
    }

    public FirebaseFirestore getDb() {
        return db;
    }
}
