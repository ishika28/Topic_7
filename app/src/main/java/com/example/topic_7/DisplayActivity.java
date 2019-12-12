package com.example.topic_7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listDictionary;
    private Map<String, String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        listDictionary = findViewById(R.id.listDictionary);
        dictionary= new HashMap<>();

        //read all the word from word.txt file
        readFromFile();
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,new ArrayList<String>(dictionary.keySet()));

        listDictionary.setAdapter(adapter);

        listDictionary.setOnItemClickListener(this);

    }






    private void readFromFile(){
        try {
            FileInputStream fos = openFileInput("words.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line= "";
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("->");
                dictionary.put(parts[0], parts[1]);
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Getting the current position
        String key = parent.getItemAtPosition(position).toString();
        //Getting the meaning of curremnt position key
        String meaning = dictionary.get(key);

        //Intent will call meaning from dictionaryactivity
        Intent intent = new Intent(DisplayActivity.this, MainActivity.class);

        //we have to pass the
        intent.putExtra("meaning",meaning);
        startActivity(intent);
    }



}
