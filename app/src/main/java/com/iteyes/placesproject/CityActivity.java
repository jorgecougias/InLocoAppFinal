package com.iteyes.placesproject;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import model.City;

public class CityActivity extends AppCompatActivity {
    private City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        city = Singleton.getSingleton().getActiveCity();
        TextView tvName = (TextView) findViewById(R.id.city_name);
        TextView tvMin = (TextView) findViewById(R.id.temp_min);
        TextView tvMax = (TextView) findViewById(R.id.temp_max);
        TextView tvDesc = (TextView) findViewById(R.id.weather_condition);
        tvName.setText(city.getName());
        tvMin.setText("MIN: " + city.getMin());
        tvMax.setText("MAX: " + city.getMax());
        tvDesc.setText(city.getWeatherDescription());
    }
}
