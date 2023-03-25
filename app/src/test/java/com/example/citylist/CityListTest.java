package com.example.citylist;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class CityListTest {
    private CityList mockCityList() {
        CityList cityList = new CityList();
        cityList.add(mockCity());
        return cityList;
    }

    private City mockCity() {
        return new City("Edmonton", "AB");
    }

    @Test
    public void testAdd() {
        CityList cityList = mockCityList();
        assertEquals(1, cityList.getCities("city").size());

        City city = new City("Regina", "SK");
        cityList.add(city);

        assertEquals(2, cityList.getCities("city").size());
        assertTrue(cityList.getCities("city").contains(city));
    }

    @Test
    public void testAddException() {
        CityList cityList = new CityList();
        City city = mockCity();
        cityList.add(city);

        assertThrows(IllegalArgumentException.class, () -> {
            cityList.add(city);
        });
    }

    @Test
    public void testDelete(){
        CityList citylist = new CityList();
        citylist.add(new City("Mirpur", "Dhaka"));

        City city=new City("Jessore", "Khulna");

        citylist.add(city);

        assertEquals(2, citylist.getCities("city").size());
        assertTrue(citylist.getCities("city").contains(city));

        citylist.delete(city);

        assertEquals(1, citylist.getCities("city").size());
        assertTrue(!citylist.getCities("city").contains(city));
    }

    @Test
    public void testDeleteException(){
        CityList citylist = new CityList();
        citylist.add(new City("Mirpur", "Dhaka"));

        City city=new City("Jessore", "Khulna");

        assertEquals(1, citylist.getCities("city").size());

        assertThrows(IllegalArgumentException.class, () -> {
            citylist.delete(city);
        });
    }


    @Test
    public void testCount(){
        CityList citylist = new CityList();
        City city=new City("Mirpur", "Dhaka");

        citylist.add(city);
        assertEquals(1, citylist.count());

        citylist.add(new City("Jessore", "Khulna"));
        assertEquals(2, citylist.count());

        citylist.delete(city);
        assertEquals(1, citylist.count());

    }

    @Test
    public void testSort(){
        CityList citylist = new CityList();
        citylist.add(new City("Mirpur", "Dhaka"));
        citylist.add(new City("Jessore", "Khulna"));
        citylist.add(new City("Cumilla", "Chittagong"));

        List<City> orderByCity=citylist.getCities("city");

        assertEquals("Cumilla", orderByCity.get(0).getCityName());
        assertEquals("Jessore", orderByCity.get(1).getCityName());
        assertEquals("Mirpur", orderByCity.get(2).getCityName());

        List<City> orderByProvince=citylist.getCities("province");

        assertEquals("Cumilla", orderByProvince.get(0).getCityName());
        assertEquals("Mirpur", orderByProvince.get(1).getCityName());
        assertEquals("Jessore", orderByProvince.get(2).getCityName());

    }

    @Test
    public void testGetCities() {
        CityList cityList = mockCityList();
        assertEquals(0, mockCity().compareTo(cityList.getCities("city").get(0)));

        City city = new City("Charlottetown", "Prince Edward Island");
        cityList.add(city);

        assertEquals(0, city.compareTo(cityList.getCities("city").get(0)));
        assertEquals(0, mockCity().compareTo(cityList.getCities("city").get(1)));
    }
}
