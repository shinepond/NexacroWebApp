package com.shinepond.nwa;

import com.nexacro.NexacroUpdatorActivity;

import android.os.Bundle;

public class MainActivity extends NexacroUpdatorActivity {

    public MainActivity() {
        super();
        setBootstrapURL( BuildConfig.HOST + "/_android_/start_android.json");
        setProjectURL( BuildConfig.HOST + "/_android_/");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}