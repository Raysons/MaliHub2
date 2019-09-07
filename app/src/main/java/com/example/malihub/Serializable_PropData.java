package com.example.rayson.malihub;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by rayson 20/06/2019
 */

@JsonIgnoreProperties({"selection"})
class Serializable_PropData implements Serializable
{
    private String prop_name;
    private String list_type;
    private String prop_type;
    private String prop_price;
    private String prop_bed;
    private String prop_AvailDate;
    private String prop_city;
    private String prop_state;
    private String prop_description;
    private String prop_furnished;
    private String imageURLs;
    private String prop_owner;
    private String prop_address;
    private String prop_amenities;
    private String prop_area;

    public String getProp_area() {
        return prop_area;
    }

    public void setProp_area(String prop_area) {
        this.prop_area = prop_area;
    }

    public String getProp_bed() {
        return prop_bed;
    }

    public void setProp_bed(String prop_bed) {
        this.prop_bed = prop_bed;
    }

    public String getProp_price() {
        return prop_price;
    }

    public void setProp_price(String prop_price) {
        this.prop_price = prop_price;
    }

    public String getProp_type() {
        return prop_type;
    }

    public void setProp_type(String prop_type) {
        this.prop_type = prop_type;
    }

    public String getProp_name() {
        return prop_name;
    }

    public void setProp_name(String prop_name) {
        this.prop_name = prop_name;
    }

    public String getProp_address() {
        return prop_address;
    }

    public void setProp_address(String prop_address) {
        this.prop_address = prop_address;
    }

    public String getProp_amenities() {
        return prop_amenities;
    }

    public void setProp_amenities(String prop_amenities) {
        this.prop_amenities = prop_amenities;
    }

    public String getProp_AvailDate() {
        return prop_AvailDate;
    }

    public void setProp_AvailDate(String prop_AvailDate) {
        this.prop_AvailDate = prop_AvailDate;
    }

    public String getProp_city() {
        return prop_city;
    }

    public void setProp_city(String prop_city) {
        this.prop_city = prop_city;
    }

    public String getProp_state() {
        return prop_state;
    }

    public void setProp_state(String prop_state) {
        this.prop_state = prop_state;
    }

    public String getProp_description() {
        return prop_description;
    }

    public void setProp_description(String prop_description) {
        this.prop_description = prop_description;
    }

    public String getProp_furnished() {
        return prop_furnished;
    }

    public void setProp_furnished(String prop_furnished) {
        this.prop_furnished = prop_furnished;
    }

    public String getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(String imageURLs) {
        this.imageURLs = imageURLs;
    }

    public String getProp_owner() {
        return prop_owner;
    }

    public void setProp_owner(String prop_owner) {
        this.prop_owner = prop_owner;
    }

    public String getList_type() {
        return list_type;
    }

    public void setList_type(String list_type) {
        this.list_type = list_type;
    }

    public Serializable_PropData()
    {
    }

    public Serializable_PropData(String prop_name, String list_type, String prop_type, String prop_price, String prop_bed, String prop_AvailDate, String prop_city, String prop_state, String prop_description, String prop_furnished, String imageURLs, String prop_owner, String prop_address, String prop_amenities, String prop_area) {
        this.prop_name = prop_name;
        this.list_type = list_type;
        this.prop_type = prop_type;
        this.prop_price = prop_price;
        this.prop_bed = prop_bed;
        this.prop_AvailDate = prop_AvailDate;
        this.prop_city = prop_city;
        this.prop_state = prop_state;
        this.prop_description = prop_description;
        this.prop_furnished = prop_furnished;
        this.imageURLs = imageURLs;
        this.prop_owner = prop_owner;
        this.prop_address = prop_address;
        this.prop_amenities = prop_amenities;
        this.prop_area = prop_area;
    }
}