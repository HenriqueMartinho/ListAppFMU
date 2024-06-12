package com.projetofaculdade.listapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton floatingActionButton;
    LinearLayout layout, cardLayout;
    ListView listView;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        floatingActionButton = findViewById(R.id.addNewList);
        layout = findViewById(R.id.container);

        createDataBase();
        returnData();

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

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });
    }
    public void createDataBase(){
        try{
            db = openOrCreateDatabase("listDB", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS listas(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT" +
                    " , title VARCHAR" +
                    " , list TEXT)");
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void returnData(){
        Cursor cursor;
        listView = findViewById(R.id.listView);

        try{
            db = openOrCreateDatabase("listDB", MODE_PRIVATE, null);
            cursor = db.rawQuery("SELECT id, title, list FROM listDB", null);
            ArrayList<String> lists = new ArrayList<String>();
            ArrayAdapter adapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    lists
            );
            listView.setAdapter(adapter);
            cursor.moveToFirst();
            while(cursor!=null){
                lists.add(cursor.getString(1));
                cursor.moveToNext();
            }
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addCard() {
        View view = getLayoutInflater().inflate(R.layout.list_card, null);

        EditText title = view.findViewById(R.id.editTextTitle);
        EditText msg = view.findViewById(R.id.editTextMsg);

        ImageButton deleteButton = view.findViewById(R.id.deleteButton);
        ImageButton saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });
        layout.addView(view);
    }

    public void save(){
        View view = getLayoutInflater().inflate(R.layout.list_card, null);
        EditText title = findViewById(R.id.editTextTitle);
        EditText msg = findViewById(R.id.editTextMsg);

        try {
            db = openOrCreateDatabase("listDB", MODE_PRIVATE, null);
            String sql = "INSERT INTO listDB (title, msg) VALUES (?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, title.getText().toString());
            statement.executeInsert();
            statement.bindString(2, msg.getText().toString());
            statement.executeInsert();
            db.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        layout.removeView(view);
        returnData();

    }

    public void inserirDadosTemp(){

    }

}

