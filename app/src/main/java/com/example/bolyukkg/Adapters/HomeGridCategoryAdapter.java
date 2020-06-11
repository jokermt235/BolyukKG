package com.example.bolyukkg.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bolyukkg.R;

import java.util.ArrayList;
import java.util.Map;

public class HomeGridCategoryAdapter extends BaseAdapter {

    private Context mContext;

    private ArrayList<Map<String, Object>> collections;

    public  HomeGridCategoryAdapter(Context c, ArrayList<Map<String, Object>> collections){
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

        ViewHolder viewHolder;

        if (convertView == null) {
            view = inflater.inflate(R.layout.home_category_grid_item, viewGroup ,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = (View)convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        //viewHolder.imageView.setImageBitmap(collections.get(i).getImage());
        //viewHolder.textView.setText(collections.get(i).getTitle());
        //viewHolder.textViewId.setText(String.valueOf(collections.get(i).getId()));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(mContext, BrandActivity.class);
                //intent.putExtra("collection_id",collections.get(i).getId());
                //mContext.startActivity(intent);
            }
        });
        return view;
    }

    private class ViewHolder {
        final ImageView imageView;
        final TextView textView;
        final TextView textViewId;
        ViewHolder(View view){
            imageView = view.findViewById(R.id.main_cat_item_image);
            textView = view.findViewById(R.id.main_cat_item_title);
            textViewId = view.findViewById(R.id.collection_id);
        }
    }
}
