package com.ryfa.firebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryfa.firebase.adapters.AppsRvAdapter;
import com.ryfa.firebase.databinding.ActivityMainBinding;
import com.ryfa.firebase.general.SharedPrefHandler;
import com.ryfa.firebase.model.App;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    Context context = this;
    AppDao appDao;
    List<App> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appDao = AppDatabase.getAppDatabase(context).getAppDao();

        binding.fabAddApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, AddAppActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        list = appDao.getApps();

        binding.rvApps.setLayoutManager(new LinearLayoutManager(context));
        binding.rvApps.setAdapter(new AppsRvAdapter(context, list).setOnItemClickListener(new AppsRvAdapter.OnItemClickListener() {
            @Override
            public void onDelete(int position, App app) {
                appDao.deleteApp(app);
                list.remove(position);
                binding.rvApps.getAdapter().notifyItemRemoved(position);
                binding.rvApps.getAdapter().notifyDataSetChanged();
            }
        }));
        binding.llEmpty.setVisibility(list.isEmpty() ? View.VISIBLE : View.GONE);

    }

}