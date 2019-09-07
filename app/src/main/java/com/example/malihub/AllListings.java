package com.example.rayson.malihub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rayson 20/06/2019
 */
class AllListings
{
    static final List<Serializable_PropData> All_Listings = new ArrayList<>();
    static Serializable_PropData ListingFactory(HashMap dataSnapshot)
    {
        Serializable_PropData current_listing = new Serializable_PropData();
        Iterator it = dataSnapshot.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry ds = (Map.Entry)it.next();
            if(ds.getKey() == "prop_name")
                current_listing.setProp_name(ds.getValue().toString());
            if(ds.getKey() == "list_type")
                current_listing.setList_type(ds.getValue().toString());
            if(ds.getKey() == "prop_type")
                current_listing.setProp_type(ds.getValue().toString());
            if(ds.getKey() == "prop_price")
                current_listing.setProp_price(ds.getValue().toString());
            if(ds.getKey() == "prop_bed")
                current_listing.setProp_bed(ds.getValue().toString());
            if(ds.getKey() == "prop_AvailDate")
                current_listing.setProp_AvailDate(ds.getValue().toString());
            if(ds.getKey() == "prop_city")
                current_listing.setProp_city(ds.getValue().toString());
            if(ds.getKey() == "prop_state")
                current_listing.setProp_state(ds.getValue().toString());
            if(ds.getKey() == "prop_description")
                current_listing.setProp_description(ds.getValue().toString());
            if(ds.getKey() == "prop_furnished")
                current_listing.setProp_furnished(ds.getValue().toString());
            if(ds.getKey() == "imageURLs")
                current_listing.setImageURLs(ds.getValue().toString());
            if(ds.getKey() == "prop_owner")
                current_listing.setProp_owner(ds.getValue().toString());
            if(ds.getKey() == "prop_address")
                current_listing.setProp_address(ds.getValue().toString());
            if(ds.getKey() == "prop_amenities")
                current_listing.setProp_amenities(ds.getValue().toString());
            if(ds.getKey() == "prop_area")
                current_listing.setProp_area(ds.getValue().toString());
            it.remove();
        }
        return  current_listing;
    }
}

