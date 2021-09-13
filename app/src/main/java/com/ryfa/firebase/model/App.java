package com.ryfa.firebase.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_app")
public class App {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    @ColumnInfo(name = "package_name")
    public String packageName;
    @ColumnInfo(name = "server_key")
    public String serverKey;
    public long date;

    public long getDate() {
        return date;
    }

    public App setDate(long date) {
        this.date = date;
        return this;
    }

    public long getId() {
        return id;
    }

    public App setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public App setName(String name) {
        this.name = name;
        return this;
    }

    public String getPackageName() {
        return packageName;
    }

    public App setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public String getServerKey() {
        return serverKey;
    }

    public App setServerKey(String serverKey) {
        this.serverKey = serverKey;
        return this;
    }
}
