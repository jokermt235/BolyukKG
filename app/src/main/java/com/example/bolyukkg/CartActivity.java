package com.example.bolyukkg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.example.bolyukkg.Adapters.CartListAdapter;
import com.example.bolyukkg.Callback.ITranslateData;
import com.example.bolyukkg.Models.CartRepo;

import java.util.ArrayList;
import java.util.Map;

public class CartActivity extends AppCompatActivity implements ITranslateData {

    private Toolbar toolbar;
    private ListView cartList;
    private CartRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        toolbar = findViewById(R.id.cartToolbar);
        setSupportActionBar(toolbar);
        cartList  = findViewById(R.id.cartList);
        repo = new CartRepo(CartActivity.this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        repo.filter();
        super.onStart();
    }

    @Override
    public void onSaveItem(boolean status) {

    }

    @Override
    public void onFilerData(ArrayList<Map<String, Object>> data) {
        cartList.setAdapter(new CartListAdapter(CartActivity.this, data, this));
    }
}