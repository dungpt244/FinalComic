package com.example.testaplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testaplication.Manga.Manga;
import com.example.testaplication.R;

import java.util.List;

public class MangaAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Manga> list;

    public MangaAdapter(Context context, int layout, List<Manga> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<Manga> getList() {
        return list;
    }

    public void setList(List<Manga> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(layout, null);

        Manga manga = list.get(i);
        ImageView image = view.findViewById(R.id.image);
        image.setImageResource(manga.getImage());

        TextView name = view.findViewById(R.id.name);
        name.setText(manga.getName());

        return view;
    }
}
