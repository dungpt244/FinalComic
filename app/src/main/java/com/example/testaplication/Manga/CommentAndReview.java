package com.example.testaplication.Manga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.testaplication.Adapter.AdapterContent;
import com.example.testaplication.Adapter.Review;
import com.example.testaplication.R;
import com.example.testaplication.Sqlite.ReviewAndComment;

import java.util.ArrayList;
import java.util.List;

public class CommentAndReview extends AppCompatActivity {
    private ListView listView;
    private Button buttonSend;
    private EditText text;
    private AdapterContent adapter;
    private int index = -1;
    private Button edit;
    private ReviewAndComment db = new ReviewAndComment(CommentAndReview.this);
    private   List<Review> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_and_review);
        listView = findViewById(R.id.listViewComment);
        buttonSend = findViewById(R.id.textSend);
        edit = findViewById(R.id.buttonEdit);
        String manga = getIntent().getStringExtra("Name");
        text = findViewById(R.id.textComment);
        SharedPreferences sharedPreferences = getSharedPreferences("MyUserName", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("email", "");
         list = db.getData(manga);
         adapter = new AdapterContent(CommentAndReview.this,list, R.layout.custom_comment);
        listView.setAdapter(adapter);
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String getText = text.getText().toString();
                    if(getText.isEmpty()){
                        Toast.makeText(CommentAndReview.this, "Nội Dung Không Được Để Trống", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        db.InsertComment(user_name,manga,getText);
                        list.add(new Review(0,user_name,manga,getText));
                        adapter.notifyDataSetChanged();
                        text.setText("");
                    }


            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                index = i;
                if(list.get(index).getUsername().equals(user_name)){
                    showPopupMenu(view);
                    String comment = list.get(i).getCommnent();
                    text.setText(comment);
                }
                else{
                    Toast.makeText(CommentAndReview.this, "Bạn Không Thể Sửa Bình Luận Của Người Khác", Toast.LENGTH_SHORT).show();
                }
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getComment  = text.getText().toString();
                if(db.updateComment(getComment,user_name,manga)){
                    list.get(index).setCommnent(getComment);
                    adapter.notifyDataSetChanged();
                    text.setText("");
                }
            }
        });

    }
    private void showPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_delete) {
                    list.remove(index);
                    db.deleteRow(index);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }
        });

        popupMenu.show();
    }
}