package com.example.bolyukkg.Models;

import java.util.Map;

public interface ICartRepoex extends ICartRepo {
    public void setData(Map<String, Object> data);
    public  void setDoc(String doc);
}
