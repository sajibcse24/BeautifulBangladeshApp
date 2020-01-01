package com.example.dell.beautyfulbangladesh;

import java.sql.Blob;

/**
 * Created by Dell on 3/7/2017.
 */

public class PlacePopulation {
    private String name;
    private String division;
    private String description;
    private String place_image;
    public PlacePopulation(String name, String division, String description) {
        this.name = name;
        this.division = division;
        this.description= description;
        //this.place_image= place_image;
    }

    public String getName() {
        return this.name;
    }

    public String getDivision() {
        return this.division;
    }
    public String getDescription() {
        return this.description;
    }
   // public String getImage() {
        //return this.place_image;
    //}
}