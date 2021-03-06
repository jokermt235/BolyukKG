package com.example.bolyukkg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.example.bolyukkg.Adapters.BannerViewAdapter;
import com.example.bolyukkg.Adapters.HomeGridCategoryAdapter;
import com.example.bolyukkg.Callback.ITranslateData;
import com.example.bolyukkg.Callback.OnFilterResult;
import com.example.bolyukkg.Models.CartRepo;
import com.example.bolyukkg.Module.SimpleLoader;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements ITranslateData {
    private final String TAG = "HomeActivity";
    private GridView mainCatGrid;
    private SliderView bannerSlider;
    private Toolbar toolbar;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainCatGrid = findViewById(R.id.main_cat_grid);
        bannerSlider = findViewById(R.id.imageSlider);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        loadLocal();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadLocal(){
        SimpleLoader.filter("category", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                mainCatGrid.setAdapter(new HomeGridCategoryAdapter(HomeActivity.this,arrayList));
            }
        });
        SimpleLoader.filter("banner", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                bannerSlider.setSliderAdapter(new BannerViewAdapter(HomeActivity.this, arrayList));
            }
        });
        CartRepo  cartRepo = new CartRepo(HomeActivity.this , this);
        cartRepo.filter();
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
            case R.id.home_toolbar_menu2_cart : startActivity(new Intent(HomeActivity.this, CartActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveItem(boolean status) {
    }

    @Override
    public void onFilerData(ArrayList<Map<String, Object>> data) {
        menu.findItem(R.id.home_toolbar_menu2_amount).setTitle(String.valueOf(data.size()));
    }
}