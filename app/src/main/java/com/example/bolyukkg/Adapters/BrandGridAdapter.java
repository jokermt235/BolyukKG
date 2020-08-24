package com.example.bolyukkg.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bolyukkg.BrandActivity;
import com.example.bolyukkg.Callback.OnImageDownloadResult;
import com.example.bolyukkg.Module.SimpleImageLoader;
import com.example.bolyukkg.R;

import java.util.ArrayList;
import java.util.Map;

public class BrandGridAdapter extends BaseAdapter {

    private static final String TAG = "BrandGridAdapter";
    private Context mContext;
    private ViewHolder vh;

    private ArrayList<Map<String, Object>> collections;

    public  BrandGridAdapter(Context c, ArrayList<Map<String, Object>> collections){
        this.mContext = c;
        this.collections = collections;
    }
    @Override
    public int getCount() {
        return collections.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        View view = null;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final ViewHolder viewHolder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.brand_grid_item, viewGroup ,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = (View)convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.name.setText((String)collections.get(i).get("name"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BrandActivity.class);
                //mContext.startActivity(intent);
            }
        });
        return view;
    }

    private class ViewHolder {
        public final ImageView imageView;
        final TextView name;
        final TextView textViewId;
        ViewHolder(View view){
            imageView = view.findViewById(R.id.brand_grid_item_image);
            name = view.findViewById(R.id.brand_grid_item_title);
            textViewId = view.findViewById(R.id.collection_id);
        }
    }
}
