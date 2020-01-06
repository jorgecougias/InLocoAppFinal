package com.iteyes.placesproject;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.City;
import model.CityJSONParser;

public class JsonController {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONArray arr = new JSONArray(jsonText);

            return arr;
        } finally {
            is.close();
        }
    }

    public static List<City> readJsonListCity(String url) throws Exception {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            CityJSONParser cityJSONParser = new CityJSONParser();
            List<City> cityList = cityJSONParser.getCityList(jsonText);

            return cityList;
        } finally {
            is.close();
        }

    }




//    public static void main(String[] args) throws Exception {
//        List<City> cities = readJsonListCity("http://api.openweathermap.org/data/2.5/find?lat=55.5&lon=37.5&cnt=15&APPID=adb8e3486fe2c43287474e8d1db1d455");
//        for(City city:cities){
//            System.out.print(city.toString());
//        }
//    }
}
