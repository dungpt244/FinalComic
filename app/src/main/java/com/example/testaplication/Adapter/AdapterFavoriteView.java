package com.example.testaplication.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testaplication.R;

import java.util.List;

public class AdapterFavoriteView extends BaseAdapter {
    private int layout;
    private List<ListFavoriteConstructor> list;
    private Context context;

    public AdapterFavoriteView(int layout, List<ListFavoriteConstructor> list, Context context) {
        this.layout = layout;
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(layout,null);
        ImageView image = view.findViewById(R.id.imagelv);
        TextView name = view.findViewById(R.id.namelv);
        TextView des = view.findViewById(R.id.authorlv);
        ListFavoriteConstructor get = list.get(i);
        String imageName = get.getImage();
        int resId =  ((Activity)context).getResources().getIdentifier(imageName,"drawable",((Activity)context).getPackageName());
        image.setImageResource(resId);
        name.setText(get.getName());
        des.setText(get.getDescription());
        return view;
    }
}
