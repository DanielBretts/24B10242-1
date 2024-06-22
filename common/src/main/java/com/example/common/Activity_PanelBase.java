package com.example.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.common.databinding.ActivityPanelBinding;

import java.util.List;

public class Activity_PanelBase extends AppCompatActivity {
    private ActivityPanelBinding binding;

    private MyObjectAdapter adapter;
    protected SharedPreferencesHelper preferencesHelper;
    protected List<Match> matches;

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPanelBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        matches = preferencesHelper.getMatches();
        adapter = new MyObjectAdapter(this, matches);
        binding.gamesListView.setAdapter(adapter);
        int counter = preferencesHelper.getMatches().size();
        binding.includeMatchesWatched.tvInteger.setText(String.valueOf(counter));
        binding.submitButton.setOnClickListener(view1 -> addNewMatch());

         preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(key.equals("matches")){
                    updateUI();
                }
            }
        };


        setContentView(view);
    }

    private void updateUI() {
        binding.team1EditText.setText(null);
        binding.team2EditText.setText(null);
        binding.includeMatchesWatched.tvInteger.setText(String.valueOf(preferencesHelper.getMatches().size()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        preferencesHelper.getSharedPreferences().registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        preferencesHelper.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    private void addNewMatch(){
        if(verifyInputs()){
            Match m = new Match(binding.team1EditText.getText().toString(),binding.team2EditText.getText().toString());
            Log.d("addNewMatch: ","added "+m.toString());
            preferencesHelper.saveMatch(m);
            adapter.updateData(preferencesHelper.getMatches());
        }else{
            Toast.makeText(getApplicationContext(),"Text fields cannot be empty", Toast.LENGTH_SHORT).show();
        }
    }



    private boolean verifyInputs() {
        return !(binding.team1EditText.getText().toString().isEmpty() || binding.team2EditText.getText().toString().isEmpty());
    }
}