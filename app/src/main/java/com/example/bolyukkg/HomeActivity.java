package com.example.bolyukkg;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.bolyukkg.Adapters.HomeGridCategoryAdapter;
import com.example.bolyukkg.Callback.OnFilterResult;
import com.example.bolyukkg.Module.SimpleLoader;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private GridView mainCatGrid;
    private HomeGridCategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainCatGrid = findViewById(R.id.main_cat_grid);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocal();
    }

    private void loadLocal(){
        SimpleLoader.filter("category", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                mainCatGrid.setAdapter(new HomeGridCategoryAdapter(getApplicationContext(),arrayList));
            }
        });
    }
}