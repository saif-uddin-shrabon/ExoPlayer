package com.example.testiptv;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class channelList extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<model> dataholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_list);


        recyclerView = findViewById(R.id.recview);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this,numberOfColumns));

        Cursor cursor = new dbmanager(this).readAllData();

        dataholder = new ArrayList<>(); // Initialize the ArrayList before adding data to it

        while (cursor.moveToNext()){
            model obj =  new model(cursor.getString(1),cursor.getString(2),cursor.getString(3));
            dataholder.add(obj);
        }

        myadapter adapter= new myadapter(dataholder);
        recyclerView.setAdapter(adapter);
    }
}