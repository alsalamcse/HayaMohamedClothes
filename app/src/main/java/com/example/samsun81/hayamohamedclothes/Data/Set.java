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
    private  boolean toVote=false;
    private int like;
    private int dislike;
    private String email;


    public Set(String name, String wather,String occasion) {
        this.name = name;
        this.wather = wather;
        this.occasion=occasion;
        imgPath = null;
    }

    public Set() {


    }
    public Set(String name, String wather, String occasion, String imgPath, String keyId, int like, int dislike, String email) {
        this.name = name;
        this.wather = wather;
        this.occasion=occasion;
        this.imgPath = imgPath;
        this.KeyId = keyId;
        this.like=like;
        this.dislike = dislike;
        this.email=email;

    }
    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setToVote(boolean toVote) {
        this.toVote = toVote;
    }

    public boolean isToVote() {
        return toVote;
    }
}