package com.example.bolyukkg.Models;

import android.content.Context;

import com.example.bolyukkg.Module.SimpleLoader;

import java.util.HashMap;
import java.util.Map;

public class CartRepo implements ICart {
    @Override
    public void save(Cart cart, Context c) {
        final Map<String, Object> data = new HashMap<>();
    }

    @Override
    public void update(Cart cart) {

    }

    @Override
    public void delete(Cart cart) {

    }
}
