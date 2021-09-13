package com.ryfa.firebase.model;

import android.content.Context;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.ryfa.firebase.api.QueryBuilder;
import com.ryfa.firebase.api.ServerResult;

import java.util.ArrayList;

public class NotificationItem {


    String key;
    String value;

    public NotificationItem(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public NotificationItem() {
    }

    public String getKey() {
        return key;
    }

    public NotificationItem setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public NotificationItem setValue(String value) {
        this.value = value;
        return this;
    }

    public static final void sendNotification(Context context, ArrayList<NotificationItem> notificationItems) {

        RequestParams requestParams = new RequestParams();

        for (int i = 0; i < notificationItems.size(); i++) {
            NotificationItem notificationItem = notificationItems.get(i);
            if (!notificationItem.getKey().trim().equals("") && !notificationItem.getValue().trim().equals(""))
                requestParams.put(notificationItem.getKey(), notificationItem.getValue());
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.setParams(requestParams);

        queryBuilder.findInBackground(QueryBuilder.POST, "Notification/sendNotification", new QueryBuilder.FindCallback() {
            @Override
            public void done(ServerResult serverResult, int statusCode) {
                Toast.makeText(context, serverResult.getMessage(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void done(String result, int statusCode) {

            }
        });

    }

}
