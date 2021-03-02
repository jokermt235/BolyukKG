package com.example.bolyukkg.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bolyukkg.Callback.ITranslateData;
import com.example.bolyukkg.Callback.OnImageDownloadResult;
import com.example.bolyukkg.Models.CartRepo;
import com.example.bolyukkg.Module.SimpleImageLoader;
import com.example.bolyukkg.R;

import java.util.ArrayList;
import java.util.Map;

public class CartListAdapter  extends BaseAdapter {
    private static final String TAG = "CartListAdapter";
    private Context mContext;
    private CartListAdapter.ViewHolder vh;
    private ITranslateData translateData;
    private CartRepo cartRepo;

    private ArrayList<Map<String, Object>> collections;

    public  CartListAdapter(Context c, ArrayList<Map<String, Object>> collections, ITranslateData translateData){
        this.mContext = c;
        this.collections = collections;
        this.translateData = translateData;
        this.cartRepo = new CartRepo(c, translateData);
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
            view = inflater.inflate(R.layout.cart_list_item, viewGroup ,false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            view = (View)convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        SimpleImageLoader.loadImages("detail",(String)collections.get(i).get("detailId") , new OnImageDownloadResult(){
            @Override
            public void onResult(ArrayList<Bitmap> items) {
                super.onResult(items);
                Log.d(TAG, items.toString());
                if(!items.isEmpty()) {
                    viewHolder.imageView.setImageBitmap(items.get(0));
                }
            }
        });

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartRepo.remove((String)collections.get(i).get("_ref"));
            }
        });

        viewHolder.cartName.setText((String)collections.get(i).get("name"));
        viewHolder.cartArticle.setText((String)collections.get(i).get("article"));
        String price = (long)collections.get(i).get("price") + " " + (String)collections.get(i).get("currency");
        viewHolder.cartPrice.setText(price);
        String count = (long)collections.get(i).get("amount") + " "  + (String)collections.get(i).get("unit");
        viewHolder.cartCount.setText(count);
        view.setEnabled(false);
        view.setClickable(false);
        return view;
    }

    private class ViewHolder {
        final ImageView imageView;
        final TextView cartName;
        final TextView cartPrice;
        final TextView cartArticle;
        final TextView cartCount;
        final ImageView remove;
        ViewHolder(View view){
            imageView   = view.findViewById(R.id.cartImage);
            cartName    = view.findViewById(R.id.cartName);
            cartPrice   = view.findViewById(R.id.cartPrice);
            cartArticle = view.findViewById(R.id.cartArticle);
            cartCount   = view.findViewById(R.id.cartCount);
            remove      = view.findViewById(R.id.cartRemove);
        }
    }
}
