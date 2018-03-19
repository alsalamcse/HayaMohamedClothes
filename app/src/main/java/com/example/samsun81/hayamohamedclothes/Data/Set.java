package com.example.samsun81.hayamohamedclothes.Data;

import android.net.Uri;
import android.widget.Button;

import java.net.URI;

/**
 * Created by user on 01/03/2018.
 */

public class Set {


    private String name;
    private String wather;
    private String occasion;
    private String imgPath;
    private String KeyId;
    private  boolean toVove=false;
    private int like;
    private int dilike;
    private String email;


    public Set(String name, String wather,String occasion) {
        this.name = name;
        this.wather = wather;
        this.occasion=occasion;
        imgPath = null;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDilike() {
        return dilike;
    }

    public void setDilike(int dilike) {
        this.dilike = dilike;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set(String name, String wather, String occasion, String imgPath, String keyId, int like, int dilike, String email) {
        this.name = name;
        this.wather = wather;
        this.occasion=occasion;
        this.imgPath = imgPath;
        this.KeyId = keyId;
        this.like=like;
        this.dilike=dilike;
        this.email=email;

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