package com.example.football;
import android.os.Bundle;
import com.example.common.Activity_PanelBase;
import com.example.common.SharedPreferencesHelper;

public class Activity_Panel extends Activity_PanelBase {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferencesHelper = new SharedPreferencesHelper(getApplicationContext());
        super.onCreate(savedInstanceState);


//        setContentView(R.layout.activity_panel);
    }
}