package com.example.bolyukkg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

import com.example.bolyukkg.Adapters.BrandGridAdapter;
import com.example.bolyukkg.Callback.IBrandRepo;
import com.example.bolyukkg.Callback.IRetrieveData;
import com.example.bolyukkg.Callback.ITranslateData;
import com.example.bolyukkg.Models.BrandRepo;
import com.example.bolyukkg.Models.CartRepo;

import java.util.ArrayList;
import java.util.Map;

public class BrandActivity extends AppCompatActivity implements
        ITranslateData , IRetrieveData , IBrandRepo {

    private GridView brandGridView;
    private Toolbar toolbar;
    private TextView brandCat;
    private Menu menu;
    private String category;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        brandGridView = findViewById(R.id.brandGridView);
        toolbar = findViewById(R.id.brandToolbar);
        setSupportActionBar(toolbar);
        brandCat = findViewById(R.id.brandCat);
        category = getIntent().getStringExtra("titleCat");
        id       = getIntent().getStringExtra("idCat");
    }

    @Override
    protected void onStart() {
        super.onStart();
        brandCat.setText(getIntent().getStringExtra("titleCat"));
        loadLocal();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu2, menu);
        this.menu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_toolbar_menu2_cart : startActivity(new Intent(BrandActivity.this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private  void loadLocal(){
        new CartRepo(BrandActivity.this , this).filter();
        new BrandRepo(BrandActivity.this , this).filter();
    }

    @Override
    public void onSaveItem(boolean status) {

    }

    @Override
    public void onFilerData(ArrayList<Map<String, Object>> data) {
        menu.findItem(R.id.home_toolbar_menu2_amount).setTitle(String.valueOf(data.size()));
    }

    @Override
    public String getCat() {
        return category;
    }

    @Override
    public String getBrand() {
        return "";
    }

    @Override
    public String getCatId() {
        return id;
    }

    @Override
    public void onFilterBrand(ArrayList<Map<String, Object>> data) {
        brandGridView.setAdapter(new BrandGridAdapter(BrandActivity.this, data, this));
    }
}