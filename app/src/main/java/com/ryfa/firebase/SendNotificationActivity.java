package com.ryfa.firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ryfa.firebase.adapters.NotificationItemRvAdapter;
import com.ryfa.firebase.databinding.ActivitySendNotificationBinding;
import com.ryfa.firebase.model.App;
import com.ryfa.firebase.model.NotificationItem;

import java.util.ArrayList;

public class SendNotificationActivity extends AppCompatActivity {

    ActivitySendNotificationBinding binding;
    Context context = this;
    NotificationItemRvAdapter adapter;
    App app;
    ArrayList<NotificationItem> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySendNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        app = new Gson().fromJson(getIntent().getStringExtra("APP"), App.class);

        binding.imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        list.add(new NotificationItem().setValue("").setKey(""));
        list.add(new NotificationItem().setValue("").setKey(""));

        binding.rvItems.setLayoutManager(new LinearLayoutManager(context));
        adapter = new NotificationItemRvAdapter(context, list);
        binding.rvItems.setAdapter(adapter);


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list.size(); i++) {
                    list.set(i, adapter.getList().get(i));
                }
                list.add(new NotificationItem().setValue("").setKey(""));
                adapter.notifyDataSetChanged();
            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (binding.etTitle.getText().toString().isEmpty()) {
                    Toast.makeText(context, "عنوان را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.etBody.getText().toString().isEmpty()) {
                    Toast.makeText(context, "متن پیام را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.etFcm.getText().toString().isEmpty()) {
                    Toast.makeText(context, "آیدی دستگاه را وارد کنید", Toast.LENGTH_SHORT).show();
                    return;
                }
                ArrayList<NotificationItem> notificationItems = new ArrayList<>();
                notificationItems.addAll(adapter.getList());
                notificationItems.add(new NotificationItem("regId", binding.etFcm.getText().toString()));
                notificationItems.add(new NotificationItem("title", binding.etTitle.getText().toString()));
                notificationItems.add(new NotificationItem("body", binding.etBody.getText().toString()));
                notificationItems.add(new NotificationItem("apiKey", app.getServerKey()));
                NotificationItem.sendNotification(context, notificationItems);
            }
        });

    }
}