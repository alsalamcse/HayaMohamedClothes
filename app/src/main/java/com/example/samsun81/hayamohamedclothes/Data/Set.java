package com.example.samsun81.hayamohamedclothes.Data;

/**
 * Created by user on 01/03/2018.
 */

public class Set {


    private String name;
    private String wather;
    private String occasion;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWather() {
        return wather;
    }

    public void setWather(String wather) {
        this.wather = wather;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getKeyId() {
        return KeyId;
    }

    public void setKeyId(String keyId) {
        KeyId = keyId;
    }

    public Set() {


    }


}