package com.example.testaplication.Display;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.testaplication.Adapter.AdapterFavoriteView;
import com.example.testaplication.Adapter.ListFavoriteConstructor;
import com.example.testaplication.Adapter.PhotoCustomListView;
import com.example.testaplication.R;
import com.example.testaplication.Sqlite.MyDatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ListFavorite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_favorite);
        list = findViewById(R.id.list_view_favorite);
        SharedPreferences sharedPreferences = getSharedPreferences("MyUserName", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("email", "");

        sqliteOpenHelper = new MyDatabaseHelper(ListFavorite.this);
        list_favorite = sqliteOpenHelper.getResource(user_name);
        AdapterFavoriteView adapter = new AdapterFavoriteView(R.layout.custom_listview,list_favorite,ListFavorite.this);
        list.setAdapter(adapter);
    }
    private ListView list;
    List<ListFavoriteConstructor> list_favorite = new ArrayList<>();
    private MyDatabaseHelper sqliteOpenHelper;

}