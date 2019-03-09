package com.example.aly.appclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mFFq4Be3DNgQdakVbr6g0KF5s82rKcwp4akUjRQs")
                // if defined
                .clientKey("MPyTKd6xXAHuUlYWwsPuh7dngF4EG2A1O1SdCRkN")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}