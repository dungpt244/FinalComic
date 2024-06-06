package com.example.testaplication.Manga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.testaplication.API.APIBlackClover;
import com.example.testaplication.API.GetImageAPI;
import com.example.testaplication.Adapter.RecycleViewAdapter;
import com.example.testaplication.R;
import com.example.testaplication.Adapter.Image;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlackCloverChap1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_clover_chap1);
       get_Image();
        listView = findViewById(R.id.recyleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        listView.setLayoutManager(gridLayoutManager);
        RecycleViewAdapter adapter = new RecycleViewAdapter(chapter1);
        listView.setAdapter(adapter);
        imgNext = findViewById(R.id.imgnext);
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BlackCloverChap1.this, Chap2BlackClover.class);
                startActivity(intent);
                finish();
            }
        });

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(BlackCloverChap1.this, android.R.layout.simple_spinner_item,chapter);
        spinner.setAdapter(adapter2);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 1:
                        Intent intent = new Intent(BlackCloverChap1.this, Chap2BlackClover.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    private void get_Image(){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        GetImageAPI apiGet = new Retrofit.Builder()
                .baseUrl("https://myjsons.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(GetImageAPI.class);
        Call<APIBlackClover> call = apiGet.getBlackCloverImages();
        call.enqueue(new Callback<APIBlackClover>() {
            @Override
            public void onResponse(Call<APIBlackClover> call, Response<APIBlackClover> response) {
                if (response.isSuccessful()) {
                    APIBlackClover blackCloverData = response.body();
                    for(int i = 0;i<blackCloverData.getChapter1().size();i++){
                        chapter1.add(new Image(blackCloverData.getChapter1().get(i)));
                    }
                    RecycleViewAdapter adapter = new RecycleViewAdapter(chapter1);
                    listView.setAdapter(adapter);
                } else {
                    Toast.makeText(BlackCloverChap1.this, "", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIBlackClover> call, Throwable t) {

            }
        });
    }
    public static String[] chapter = {"Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5"};

    private RecyclerView listView;
    private List<Image> chapter1 = new ArrayList<>();
    private ImageView imgNext;
    private Spinner spinner;
}



