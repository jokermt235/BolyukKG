package com.example.bolyukkg.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.bolyukkg.Callback.IRetrieveData;
import com.example.bolyukkg.Callback.OnImageDownloadResult;
import com.example.bolyukkg.DetailActivity;
import com.example.bolyukkg.Module.SimpleImageLoader;
import com.example.bolyukkg.R;
import java.util.ArrayList;
import java.util.Map;

public class BrandGridAdapter extends BaseAdapter {

    private static final String TAG = "BrandGridAdapter";
    private Context mContext;
    private ViewHolder vh;
    PopupMenu popupMenu;
    private  IRetrieveData retrieveData;

    private ArrayList<Map<String, Object>> collections;

    public  BrandGridAdapter(Context c, ArrayList<Map<String, Object>> collections , IRetrieveData retrieveData){
        this.mContext = c;
        this.collections = collections;
        this.retrieveData = retrieveData;
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
        final LayoutInflater inflater = (LayoutInflater) mContext
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
        SimpleImageLoader.loadImages("brand",(String)collections.get(i).get("id") , new OnImageDownloadResult(){
            @Override
            public void onResult(ArrayList<Bitmap> items) {
                super.onResult(items);
                Log.d(TAG, items.toString());
                if(!items.isEmpty()) {
                    viewHolder.imageView.setImageBitmap(items.get(0));
                }
            }
        });

        viewHolder.name.setText((String)collections.get(i).get("name"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, view.toString());
                popupMenu = new PopupMenu(mContext,viewHolder.imageView);
                Menu menu = popupMenu.getMenu();
                ArrayList<Map<String, Object>> models = (ArrayList<Map<String, Object>>) collections.get(i).get("models");
                if(models != null) {
                    for (int i = 0; i < models.size(); i++)
                    {
                        menu.add((String) models.get(i).get("name"));
                    }
                }
                popupMenu.getMenuInflater().inflate(R.menu.menu_model, menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("titleCat", retrieveData.getCat());
                        intent.putExtra("idCat", retrieveData.getCatId());
                        intent.putExtra("titleBrand", (String)collections.get(i).get("name"));
                        intent.putExtra("idBrand",  (String)collections.get(i).get("id"));
                        intent.putExtra("titleModel", item.getTitle());
                        mContext.startActivity(intent);
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
        return view;
    }

    private class ViewHolder {
        final ImageView imageView;
        final TextView name;
        final TextView textViewId;
        final PopupMenu modelMenu;
        ViewHolder(View view){
            imageView = view.findViewById(R.id.brand_grid_item_image);
            name = view.findViewById(R.id.brand_grid_item_title);
            textViewId = view.findViewById(R.id.collection_id);
            modelMenu = null;
        }
    }
}
