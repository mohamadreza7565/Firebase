package com.ryfa.firebase.general;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class JsonReader {


    public static String fromAssets (Context context, String jsonPath)
    {
        String jsonStr = null;
        try {
            InputStream is = context.getAssets().open(jsonPath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonStr = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return jsonStr;
    }



}
