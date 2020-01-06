package com.iteyes.placesproject;

import java.util.List;

import model.City;

public class Singleton {
    private static Singleton instance;
    private List<City> cityList;
    private City activeCity;

    private Singleton(){
    }

    public static synchronized Singleton getSingleton(){
        if(instance == null){
            instance = new Singleton();
        }
        return instance;
    }

    public List<City> getCityList(){
        return cityList;
    }

    public void setCityList(List<City> list){
        cityList = list;
    }

    public City getActiveCity(){
        return activeCity;
    }

    public void setActiveCity(City city){
        activeCity = city;
    }

    public void setActiveCity(int index){
        activeCity = getCityList().get(index);
    }



}