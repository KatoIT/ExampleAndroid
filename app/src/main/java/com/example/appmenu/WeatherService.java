package com.example.appmenu;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class WeatherService extends Service {

    private String LOG_TAG = "WeatherService";
    // Lưu trữ dữ liệu
    private static final Map<String, String> weatherData = new HashMap<String, String>();
    private final IBinder binder = new LocalWeatherBinder();

    public class LocalWeatherBinder extends Binder {
        public WeatherService getService() {
            return WeatherService.this;
        }
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(LOG_TAG, "onReBind");
        super.onRebind(intent);
    }

    public WeatherService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        Log.i(LOG_TAG, "onBind");
        return this.binder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    public String getWeatherToday(String location){
        Date now = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dayString = dateFormat.format(now);
        String keyLocAndDay = location + "$" + dayString;
        String weather = weatherData.get(keyLocAndDay);
        if(weather != null){
            return weather;
        }
        String[] weathers = new String[] {"Nắng", "Mưa", "Nhiều mây", "Trời quang mây", "Nắng nhẹ"};

        int i = new Random().nextInt(5);
        weather = weathers[i];
        weatherData.put(keyLocAndDay,weather);
        return weather;
    }

}