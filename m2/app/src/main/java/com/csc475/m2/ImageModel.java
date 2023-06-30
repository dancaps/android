package com.csc475.m2;

public class ImageModel {

    private String image_name;
    private int imgId;

    public ImageModel(String image_name, int imgId) {
        this.image_name = image_name;
        this.imgId = imgId;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}

