package com.example.bolyukkg.Models;

import android.content.Context;

import com.example.bolyukkg.Callback.OnSaveResult;

public interface IModel {
    void save(Cart cart,  final OnSaveResult result);
    void update(Cart cart);
    void delete(Cart cart);
}
