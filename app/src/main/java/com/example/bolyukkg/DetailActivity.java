package com.example.bolyukkg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.bolyukkg.Adapters.DetailListAdapter;
import com.example.bolyukkg.Callback.ITranslateData;
import com.example.bolyukkg.Models.DetailRepo;

import java.util.ArrayList;
import java.util.Map;

public class DetailActivity extends AppCompatActivity implements ITranslateData {

    private final String TAG = "DetailActivity";
    private ListView detailList;
    private Toolbar toolbar;
    private DetailRepo mDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        detailList = findViewById(R.id.detailList);
        toolbar = findViewById(R.id.detailToolbar);
        setSupportActionBar(toolbar);
        mDetail = new DetailRepo(DetailActivity.this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_toolbar_item_cart : startActivity(new Intent(DetailActivity.this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDetail.filter();
    }


    @Override
    public void onSaveItem(boolean status) {
        if(status){

        }
    }

    @Override
    public void onFilerData(ArrayList<Map<String, Object>> data) {
        Log.d(TAG, data.toString());
        detailList.setAdapter(new DetailListAdapter(DetailActivity.this, data, DetailActivity.this));
    }
}