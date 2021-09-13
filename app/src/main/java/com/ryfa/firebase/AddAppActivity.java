package com.ryfa.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ryfa.firebase.databinding.ActivityAddAppBinding;
import com.ryfa.firebase.general.SharedPrefHandler;
import com.ryfa.firebase.model.App;

import java.util.ArrayList;
import java.util.Date;

public class AddAppActivity extends AppCompatActivity {

    ActivityAddAppBinding binding;
    Context context = this;
    ArrayList<App> list;
    boolean howEdit = false;
    AppDao appDao;
    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        appDao = AppDatabase.getAppDatabase(context).getAppDao();

        if (getIntent().getStringExtra("APP") != null) {
            howEdit = true;
            app = new Gson().fromJson(getIntent().getStringExtra("APP"), App.class);
            binding.etServerKay.setText(app.getServerKey());
            binding.etPackageName.setText(app.getPackageName());
            binding.etAppName.setText(app.getName());
        }else {
            howEdit = false;
            app = new App();
        }
        String json = SharedPrefHandler.getStringPreference(context, SharedPrefHandler.APPS, "");
        if (json.equals(""))
            list = new ArrayList<>();
        else
            list = new Gson().fromJson(json, new TypeToken<ArrayList<App>>() {
            }.getType());


        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.etAppName.getText().toString().isEmpty()) {
                    Snackbar.make(binding.layout, "نام برنامه را اضافه کنید", Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (binding.etPackageName.getText().toString().isEmpty()) {
                    Snackbar.make(binding.layout, "نام بسته را اضافه کنید", Snackbar.LENGTH_LONG).show();
                    return;
                }

                if (binding.etServerKay.getText().toString().isEmpty()) {
                    Snackbar.make(binding.layout, "کلید سرور را اضافه کنید", Snackbar.LENGTH_LONG).show();
                    return;
                }


                app.setName(binding.etAppName.getText().toString());
                app.setPackageName(binding.etPackageName.getText().toString());
                app.setServerKey(binding.etServerKay.getText().toString());
                app.setDate(new Date().getDate());
                if (!howEdit) {
                   appDao.addApp(app);
                } else {
                  appDao.update(app);
                }
                finish();

            }
        });
    }
}