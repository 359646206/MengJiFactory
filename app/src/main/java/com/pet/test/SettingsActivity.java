package com.pet.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //加载PrefFragment
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PrefFragment()).commit();
    }
}
