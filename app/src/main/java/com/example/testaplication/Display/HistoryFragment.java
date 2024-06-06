package com.example.testaplication.Display;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.testaplication.Adapter.AdapterFavoriteView;
import com.example.testaplication.Adapter.ListFavoriteConstructor;
import com.example.testaplication.R;
import com.example.testaplication.Sqlite.MyDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {
    private View view;
    private ListView list_view_history;
    private MyDatabaseHelper sqliteOpenHelper;
    private List<ListFavoriteConstructor> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_history,container,false);
        list_view_history = view.findViewById(R.id.list_item_history);
        list = new ArrayList<>();
        sqliteOpenHelper = new MyDatabaseHelper(getContext());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MyUserName", Context.MODE_PRIVATE);
        String user_name = sharedPreferences.getString("email", "");
        list = sqliteOpenHelper.getResource2(user_name);
        AdapterFavoriteView adapter = new AdapterFavoriteView(R.layout.custom_listview,list,getContext());;
        list_view_history.setAdapter(adapter);
        int count = list.size();
        if(count == 0){
            Toast.makeText(getContext(), "Hôm Nay Bạn Chưa Xem Gì Cả", Toast.LENGTH_SHORT).show();
        }
        list_view_history.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int location, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                int index = location;
                builder.setTitle("Xóa Khỏi Lịch Sử Xem");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sqliteOpenHelper.delete(list.get(index).getImage(),user_name);
                        list.remove(index);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(getContext(), "Xóa Thành Công", Toast.LENGTH_SHORT).show();
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
        return view;
    }
}
