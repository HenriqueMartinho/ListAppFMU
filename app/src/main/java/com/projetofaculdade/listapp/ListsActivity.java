package com.projetofaculdade.listapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        floatingActionButton = findViewById(R.id.addNewList);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    break;
                case R.id.history:
                    startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                    break;
                case R.id.pieCharts:
                    startActivity(new Intent(getApplicationContext(), PieChartsActivity.class));
                    break;
            }
            return true;
        });
    }

        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.list_toolbar_top,menu);
            MenuItem searchButton = menu.findItem(R.id.search);
            SearchView searchView = (SearchView) searchButton.getActionView();

            return super.onCreateOptionsMenu(menu);
        }

}

