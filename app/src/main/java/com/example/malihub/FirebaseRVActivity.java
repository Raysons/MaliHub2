package com.example.rayson.malihub;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;
//import android.support.multidex.*;

/**
 * Created by rayson 20/06/2019
 */
public class FirebaseRVActivity extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
}
