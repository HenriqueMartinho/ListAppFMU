package com.projetofaculdade.listapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home:
                    break;
                case R.id.lists:
                    startActivity(new Intent(getApplicationContext(), ListsActivity.class));
                    break;
                case R.id.charts:
                    startActivity(new Intent(getApplicationContext(), ChartsActivity.class));
                    break;
            }
            return true;
        });
    }

//    public void onResume(){
//        super.onResume();
//    }

}