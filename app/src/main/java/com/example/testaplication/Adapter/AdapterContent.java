package com.example.testaplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testaplication.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterContent extends BaseAdapter {
    Context context;
    List<Review> list;
    int layout;

    public AdapterContent(Context context, List<Review> list, int layout) {
        this.context = context;
        this.list = list;
        this.layout = layout;
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
        TextView name = view.findViewById(R.id.nameComment);
        TextView comment = view.findViewById(R.id.content);
        Review get = list.get(i);
        name.setText("Tài Khoản: "+get.getUsername());
        comment.setText(get.getCommnent());
        return view;
    }
}
