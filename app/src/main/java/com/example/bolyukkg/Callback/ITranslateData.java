package com.example.bolyukkg.Callback;

import java.util.ArrayList;
import java.util.Map;

public interface ITranslateData {
    void onSaveItem(boolean status);
    void onFilerData(ArrayList<Map<String, Object>> data);
}
