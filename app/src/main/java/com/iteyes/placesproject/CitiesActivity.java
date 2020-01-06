package com.iteyes.placesproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import model.City;
import com.iteyes.placesproject.R;

import java.util.ArrayList;
import java.util.List;

public class CitiesActivity extends AppCompatActivity {
    private List<City> cityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        ListView listView = (ListView) findViewById(R.id.list_cities);

        cityList = Singleton.getSingleton().getCityList();
        ArrayList<String> listaNome = new ArrayList<>();

        for (City city : cityList) {

            listaNome.add(city.getName());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNome);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openCityActivity(position);
            }
        });
    }

    private void openCityActivity(int index){
        Singleton.getSingleton().setActiveCity(index);
        Intent intent = new Intent(this, CityActivity.class);
        startActivity(intent);
    }

}
