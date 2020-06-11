package com.example.bolyukkg.Callback;

import java.util.ArrayList;
import java.util.Map;

public class OnFilterResult {
    private ArrayList<Map<String, Object>> arrayList = new ArrayList<>();
    public void onResult( ArrayList<Map<String, Object>> arrayList){
        this.arrayList = arrayList;
    }
}
