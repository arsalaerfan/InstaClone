package com.example.instaclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LLGSfImkbRefVajgEC7I7QAMx3y2YyzmiJ5mxlhd")
                .clientKey("oXxu6K8ho4q9ZPSQo2lLdWEl8hpjjPsrg3GTNU1v")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
