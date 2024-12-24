package com.example.recyclevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import android.text.method.ScrollingMovementMethod;



public class MainActivity extends AppCompatActivity {

    private ArrayList<DataModel> dataSet;
    private ArrayList<DataModel> filteredDataSet = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LayoutInflater inflater = LayoutInflater.from(this);
        View cardRowView = inflater.inflate(R.layout.cardrow, null);
        TextView charInfoTextView = cardRowView.findViewById(R.id.Charinfo);
        charInfoTextView.setMovementMethod(new ScrollingMovementMethod());

        dataSet = new ArrayList<>();
        recyclerView =  findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        for ( int i =0 ; i < myData.nameArray.length ; i++){
            dataSet.add(new DataModel(
                    myData.nameArray[i],
                    myData.versionArray[i],
                    myData.drawableArray[i],
                    myData.id_[i]
            ));
        }



        filteredDataSet.addAll(dataSet);
        adapter = new CustomeAdapter(filteredDataSet);
        recyclerView.setAdapter(adapter);

        searchView = findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }

        });
    }

    private void filter(String text) {
        List<DataModel> filteredList = dataSet.stream()
                .filter(item -> item.getName().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());

        filteredDataSet.clear();
        filteredDataSet.addAll(filteredList);
        adapter.notifyDataSetChanged();
    }
}









