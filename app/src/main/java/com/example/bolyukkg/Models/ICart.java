package com.example.bolyukkg.Models;

import android.content.Context;

public interface ICart {
    void save(Cart cart, Context context);
    void update(Cart cart);
    void delete(Cart cart);
}
