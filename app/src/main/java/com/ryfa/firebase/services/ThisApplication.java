package com.ryfa.firebase.services;

import android.app.Application;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ryfa.firebase.R;


/**
 * Created by MRA on 6/6/2017.
 */

public class ThisApplication extends Application {

    private static ThisApplication mInstance;
    //    private SharedPref sharedPref;
    private FirebaseAnalytics mFirebaseAnalytics;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            obtainFirebaseToken();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        sharedPref = new SharedPref(this);

        // obtain regId & registering device to server
        obtainFirebaseToken();

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    public static synchronized ThisApplication getInstance() {
        return mInstance;
    }

    private void obtainFirebaseToken() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        String token = FirebaseInstanceId.getInstance().getToken();
        if (TextUtils.isEmpty(token)) {
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable, 10 * 1000);
        } else {
            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w("MAIN_TOKEN", "Fetching FCM registration token failed", task.getException());
                                return;
                            }

                            // Get new FCM registration token
                            String token = task.getResult();

                            // Log and toast
                            String msg = getString(R.string.msg_token_fmt, token);
                            Log.e("MAIN_TOKEN", msg);
                            sendRegistrationToServer(token);
                        }
                    });

        }
    }

    private void sendRegistrationToServer(String token) {
        Log.d("FCM_TOKEN", token + "");
    }


}
