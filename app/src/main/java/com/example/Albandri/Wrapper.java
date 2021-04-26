package com.example.Albandri;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Wrapper extends AppCompatActivity {

    Button firebase ,weather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper);

        firebase = findViewById(R.id.fb);
        weather = findViewById(R.id.weather);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openWeather();
            }});

        firebase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             openWrapper();
            }});

    }

    public void openWrapper(){
        Intent intent = new Intent(this, FirebaseActivity.class);
        startActivity(intent);
    }

    public void openWeather(){
        Intent intent = new Intent(this, WeatherAPI.class);
        startActivity(intent);
    }
}