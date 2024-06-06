package com.example.testaplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.example.testaplication.Display.HomeFragment;
import com.example.testaplication.R;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    private Context mContext;
    private List<Photo> listPhoto;

    public PhotoAdapter(Context mContext, List<Photo> listPhoto) {
        this.mContext = mContext;
        this.listPhoto = listPhoto;
    }

    public PhotoAdapter(HomeFragment homeFragment, List<Photo> list) {

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo,container,false);
        ImageView imgPhoto = view.findViewById(R.id.imgphoto);
        Photo photo = listPhoto.get(position);
        if(photo!=null){
            Glide.with(mContext).load(photo.getResourceId()).into(imgPhoto);
        }
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object) ;

    }
    @Override
    public int getCount() {
            return listPhoto.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
