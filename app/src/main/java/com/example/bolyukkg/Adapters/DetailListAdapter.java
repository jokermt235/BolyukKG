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
import com.example.bolyukkg.Callback.ITranslateData;
import com.example.bolyukkg.Callback.OnImageDownloadResult;
import com.example.bolyukkg.Callback.OnSaveResult;
import com.example.bolyukkg.Models.Cart;
import com.example.bolyukkg.Models.CartRepo;
import com.example.bolyukkg.Module.SimpleImageLoader;
import com.example.bolyukkg.R;

import java.util.ArrayList;
import java.util.Map;

public class DetailListAdapter extends BaseAdapter {

    private static final String TAG = "DetailListViewAdapter";
    private Context mContext;
    private ViewHolder vh;
    private ITranslateData translateData;

    private ArrayList<Map<String, Object>> collections;

    public  DetailListAdapter(Context c, ArrayList<Map<String, Object>> collections, ITranslateData translateData){
        this.mContext = c;
        this.collections = collections;
        this.translateData = translateData;
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
            view = inflater.inflate(R.layout.detail_list_item, viewGroup ,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = (View)convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        SimpleImageLoader.loadImages("detail",(String)collections.get(i).get("uid") , new OnImageDownloadResult(){
            @Override
            public void onResult(ArrayList<Bitmap> items) {
                super.onResult(items);
                Log.d(TAG, items.toString());
                if(!items.isEmpty()) {
                    viewHolder.imageView.setImageBitmap(items.get(0));
                }
            }
        });

        viewHolder.detailName.setText((String)collections.get(i).get("name"));
        viewHolder.detailArticle.setText((String)collections.get(i).get("article"));
        viewHolder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cart  cart = new Cart();
                cart.setDetail((String)collections.get(i).get("name"));
                cart.setPrice((long)collections.get(i).get("price"));
                cart.setArticle((String)collections.get(i).get("article"));
                CartRepo cartRepo = new CartRepo(mContext);
                cartRepo.save(cart, new OnSaveResult(){
                    @Override
                    public void onSave(boolean saved) {
                        super.onSave(saved);
                        translateData.onSaveItem(true);
                    }
                });
            }
        });
        String price = (long)collections.get(i).get("price") + " " + (String)collections.get(i).get("currency");
        viewHolder.detailPrice.setText(price);
        view.setEnabled(false);
        view.setClickable(false);
        return view;
    }

    private class ViewHolder {
        public final ImageView imageView;
        final ImageView cart;
        final TextView detailName;
        final TextView detailPrice;
        final TextView detailArticle;
        ViewHolder(View view){
            imageView     = view.findViewById(R.id.detailImage);
            detailName    = view.findViewById(R.id.detailName);
            detailPrice   = view.findViewById(R.id.detailPrice);
            detailArticle = view.findViewById(R.id.detailArticle);
            cart          = view.findViewById(R.id.detailCart);
        }
    }
}

