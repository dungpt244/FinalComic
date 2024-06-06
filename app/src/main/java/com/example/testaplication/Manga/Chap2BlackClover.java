package com.example.testaplication.Manga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testaplication.API.APIBlackClover;
import com.example.testaplication.API.GetImageAPI;
import com.example.testaplication.Adapter.RecycleViewAdapter;
import com.example.testaplication.R;
import com.example.testaplication.Adapter.Image;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Chap2BlackClover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chap2_black_clover);
        recyclerView = findViewById(R.id.recyleView);
        add_list();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        RecycleViewAdapter adapter = new RecycleViewAdapter(chapter2);
        recyclerView.setAdapter(adapter);
        textBack = findViewById(R.id.imgback);
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chap2BlackClover.this, BlackCloverChap1.class);
                startActivity(intent);
                finish();
            }
        });
        setChap = findViewById(R.id.setChap);
        setChap.setText("Chapter 2");
        imgNext = findViewById(R.id.imgnext);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chap2BlackClover.this, BlackCloverChapter3.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void add_list(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        GetImageAPI api_get = new Retrofit.Builder().baseUrl("https://myjsons.com/").addConverterFactory(GsonConverterFactory.create(gson)).build().create(GetImageAPI.class);
        Call<APIBlackClover> call = api_get.getBlackCloverImages();
        call.enqueue(new Callback<APIBlackClover>() {
            @Override
            public void onResponse(Call<APIBlackClover> call, Response<APIBlackClover> response) {
                if (response.isSuccessful()) {
                    APIBlackClover blackCloverData = response.body();
                    for(int i = 0;i<blackCloverData.getChapter2().size();i++){
                        chapter2.add(new Image(blackCloverData.getChapter2().get(i)));
                    }
                    RecycleViewAdapter adapter = new RecycleViewAdapter(chapter2);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(Chap2BlackClover.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIBlackClover> call, Throwable t) {

            }
        });

    }
    private RecyclerView recyclerView;
    private List<Image> chapter2 = new ArrayList<>();
    private ImageView textBack;

    private Spinner spinner;
    private TextView setChap;
    private ImageView imgNext;
}