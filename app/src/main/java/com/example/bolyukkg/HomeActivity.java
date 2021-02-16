package com.example.bolyukkg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.GridView;

import com.example.bolyukkg.Adapters.BannerViewAdapter;
import com.example.bolyukkg.Adapters.HomeGridCategoryAdapter;
import com.example.bolyukkg.Callback.OnFilterResult;
import com.example.bolyukkg.Module.SimpleLoader;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private final String TAG = "HomeActivity";
    private GridView mainCatGrid;
    private SliderView bannerSlider;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mainCatGrid = findViewById(R.id.main_cat_grid);
        bannerSlider = findViewById(R.id.imageSlider);
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
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
                mainCatGrid.setAdapter(new HomeGridCategoryAdapter(HomeActivity.this,arrayList));
            }
        });
        SimpleLoader.filter("banner", new OnFilterResult(){
            @Override
            public void onResult(ArrayList<Map<String, Object>> arrayList) {
                super.onResult(arrayList);
                bannerSlider.setSliderAdapter(new BannerViewAdapter(HomeActivity.this, arrayList));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.storage_toolbar_menu, menu);
        menu.getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Log.d(TAG, "Syncronized clicked");
                return false;
            }
        });
        return true;
    }*/
}