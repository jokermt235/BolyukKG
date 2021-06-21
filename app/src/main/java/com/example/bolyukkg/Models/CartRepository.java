package com.example.bolyukkg.Models;

import android.content.Context;

import com.example.bolyukkg.Callback.ITranslateData;

public class CartRepository implements ICartRepo {
    private ITranslateData translateData;
    public CartRepository(Context c, ITranslateData translateData){
        this.translateData = translateData;
    }
    @Override
    public void save() {
    }

    @Override
    public void filter() {
    }

    @Override
    public void remove() {
    }
}
