package com.example.appmenu;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Service2Activity extends AppCompatActivity {

    private WeatherService weatherService;
    private boolean binded = false;
    EditText editTextLocation;
    Button buttonWeather;
    TextView textViewWeather;
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WeatherService.LocalWeatherBinder binder = (WeatherService.LocalWeatherBinder) service;
            weatherService = binder.getService();
            binded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binded = false;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        // tao Intent
        Intent intent = new Intent(Service2Activity.this, WeatherService.class);
        // BinService
        this.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (binded){
            this.unbindService(serviceConnection);
            binded = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2);

        buttonWeather = (Button) findViewById(R.id.buttonXem);
        textViewWeather = (TextView) findViewById(R.id.textViewWeather2);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);

        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowWeather();
            }
        });
    }

    public void ShowWeather(){
        String location = this.editTextLocation.getText().toString();
        String weather = this.weatherService.getWeatherToday(location);
        this.textViewWeather.setText(weather);
    }
}