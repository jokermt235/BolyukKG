package com.example.bolyukkg.Models;

import android.graphics.Bitmap;

public class Cart {
    private String id;
    private String detailId;
    private long amount;
    private long price;
    private String currency;
    private String unit;
    private Bitmap image;

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public long getPrice() {
        return price;
    }

    public Bitmap getImage() {
        return image;
    }

    public long getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDetailId() {
        return detailId;
    }

    public String getUnit() {
        return unit;
    }
}
