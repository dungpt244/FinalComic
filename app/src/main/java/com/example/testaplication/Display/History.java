package com.example.testaplication.Display;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testaplication.Adapter.AdapterFavoriteView;
import com.example.testaplication.Adapter.ListFavoriteConstructor;
import com.example.testaplication.Adapter.PhotoCustomListView;
import com.example.testaplication.R;
import com.example.testaplication.Sqlite.MyDatabaseHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        list_view_history = findViewById(R.id.list_item_history);
        list = new ArrayList<>();
        sqliteOpenHelper = new MyDatabaseHelper(History.this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyUserName", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("email", "");
        list = sqliteOpenHelper.getResource2(user_name);
        AdapterFavoriteView adapter = new AdapterFavoriteView(R.layout.custom_listview,list,History.this);;
        list_view_history.setAdapter(adapter);
        int count = list.size();
        if(count == 0){
            Toast.makeText(History.this, "Hôm Nay Bạn Chưa Xem Gì Cả", Toast.LENGTH_SHORT).show();
        }
        list_view_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int location, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(History.this);
                int index = location;
                builder.setTitle("Xóa Khỏi Lịch Sử Xem");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqliteOpenHelper.delete(list.get(index).getImage(),user_name);
                        list.remove(index);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(History.this, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
        }
    private ListView list_view_history;
    private MyDatabaseHelper sqliteOpenHelper;
    private List<ListFavoriteConstructor> list;

}