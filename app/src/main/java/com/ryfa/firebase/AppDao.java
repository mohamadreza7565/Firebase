package com.ryfa.firebase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ryfa.firebase.model.App;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AppDao {

    @Insert
    long addApp(App app);

    @Delete
    int deleteApp(App app);

    @Update
    int update(App app);

    @Query("SELECT * FROM tbl_app")
    List<App> getApps();

    @Query("SELECT * FROM tbl_app WHERE id = :id")
    List<App> searchApp(String id);

}
