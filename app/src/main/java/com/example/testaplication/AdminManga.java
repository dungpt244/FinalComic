package com.example.testaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.testaplication.Adapter.AdapterCustom;
import com.example.testaplication.Adapter.AdapterManga;
import com.example.testaplication.Sqlite.MangaSQLiteHelper;
import com.example.testaplication.Sqlite.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AdminManga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db = new MangaSQLiteHelper(AdminManga.this);
        listView = findViewById(R.id.listview);
        list = db.get_data();
        AdapterCustom adapter = new AdapterCustom(AdminManga.this,R.layout.custom_list_view,list);
        listView.setAdapter(adapter);
    }
    private ListView listView;
    private MangaSQLiteHelper db;
    private List<AdapterManga> list = new ArrayList<>();
}