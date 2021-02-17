package com.example.bolyukkg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.bolyukkg.Adapters.BrandGridAdapter;
import com.example.bolyukkg.Callback.OnFilterResult;
import com.example.bolyukkg.Module.SimpleLoader;

import java.util.ArrayList;
import java.util.Map;

public class BrandActivity extends AppCompatActivity {

    private GridView brandGridView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        brandGridView = findViewById(R.id.brandGridView);
        toolbar = findViewById(R.id.brandToolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocal();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_toolbar_item_cart : startActivity(new Intent(BrandActivity.this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private  void loadLocal(){
        SimpleLoader.filter("brand", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                if(!arrayList.isEmpty()){
                    brandGridView.setAdapter(new BrandGridAdapter(BrandActivity.this, arrayList));
                }
            }
        });
    }
}