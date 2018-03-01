package com.example.samsun81.hayamohamedclothes.Data;

/**
 * Created by user on 01/03/2018.
 */

public class Set {


    private String name;

    private String wather;
    private String imgPath;
    private String KeyId;


    public Set(String name, String wather) {
        this.name = name;
        this.wather = wather;
        imgPath = null;
    }

    public Set(String name, String wather, String imgPath, String keyId) {
        this.name = name;
        this.wather = wather;
        this.imgPath = imgPath;
        this.KeyId = keyId;


    }

    public Set() {


    }


}