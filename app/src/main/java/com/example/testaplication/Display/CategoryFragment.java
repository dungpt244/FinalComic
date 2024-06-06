package com.example.testaplication.Display;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testaplication.Adapter.CategoryApdater;
import com.example.testaplication.Manga.Category;
import com.example.testaplication.Manga.MangaCategory;
import com.example.testaplication.R;
import com.example.testaplication.Sqlite.CategoryDataScoure;
import com.example.testaplication.Sqlite.CategorySQLiteHelper;

import java.util.List;

public class CategoryFragment extends Fragment {
    private View view;
    private ListView listView;
    private List<Category> categoryList;
    private CategoryDataScoure categoryDataScoure;
    private CategoryApdater categoryApdater;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.layout_category_fragment,container,false);
         context = getContext();
         mapping();

         String databasePath = context.getDatabasePath("app_manga.db").getPath();
         Log.d("CategoryFragment", "Duong dan: "  + databasePath);

         categoryDataScoure = new CategoryDataScoure(context);
         categoryDataScoure.open();

         categoryList = categoryDataScoure.getAllCategory();
         categoryApdater = new CategoryApdater(context, R.layout.layout_category, categoryList);
         listView.setAdapter(categoryApdater);

         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 Category category = (Category) categoryApdater.getItem(i);
                 Intent intent = new Intent(getContext(), MangaCategory.class);
                 Bundle bundle = new Bundle();
                 bundle.putString("category", category.getName());
                 intent.putExtras(bundle);
                 startActivity(intent);
             }
         });

         return view;
    }

    private void mapping() {
        listView = (ListView) view.findViewById(R.id.listview_category);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryDataScoure.close();
    }
}
