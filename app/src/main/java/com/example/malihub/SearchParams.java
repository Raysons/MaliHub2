package com.example.rayson.malihub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by rayson 20/06/2019
 */

@JsonIgnoreProperties({"selection"})
class SearchParams implements Serializable
{
    public String getPropertyType() {
        return PropertyType;
    }

    public void setPropertyType(String propertyType) {
        PropertyType = propertyType;
    }

    public String getFurnished() {
        return Furnished;
    }

    public void setFurnished(String furnished) {
        Furnished = furnished;
    }

    public int getBedrooms() {
        return Bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        Bedrooms = bedrooms;
    }

    public int getMinArea() {
        return MinArea;
    }

    public void setMinArea(int minArea) {
        MinArea = minArea;
    }

    public int getMaxArea() {
        return MaxArea;
    }

    public void setMaxArea(int maxArea) {
        MaxArea = maxArea;
    }

    public int getMinBudget() {
        return MinBudget;
    }

    public void setMinBudget(int minBudget) {
        MinBudget = minBudget;
    }

    public int getMaxBudget() {
        return MaxBudget;
    }

    public void setMaxBudget(int maxBudget) {
        MaxBudget = maxBudget;
    }

    public String getListType() {
        return ListType;
    }

    public void setListType(String listType) {
        ListType = listType;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public SearchParams(String propertyType, String furnished, int bedrooms, int minArea, int maxArea, int minBudget, int maxBudget, String listType, String locality) {
        PropertyType = propertyType;
        Furnished = furnished;
        Bedrooms = bedrooms;
        MinArea = minArea;
        MaxArea = maxArea;
        MinBudget = minBudget;
        MaxBudget = maxBudget;
        ListType = listType;
        Locality = locality;
    }

    public SearchParams(String propertyType, String furnished, int bedrooms, int minArea, int maxArea, int minBudget, int maxBudget, String listType, String locality, String city, String state) {
        PropertyType = propertyType;
        Furnished = furnished;
        Bedrooms = bedrooms;
        MinArea = minArea;
        MaxArea = maxArea;
        MinBudget = minBudget;
        MaxBudget = maxBudget;
        ListType = listType;
        Locality = locality;
        City = city;
        State = state;
    }

    private String PropertyType;
    private String Furnished;
    private int Bedrooms;
    private int MinArea;
    private int MaxArea;
    private int MinBudget;
    private int MaxBudget;
    private String ListType;
    private String Locality;
    private String City;
    private String State;
}
