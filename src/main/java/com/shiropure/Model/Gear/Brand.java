package com.shiropure.Model.Gear;

public class Brand {
    public String name;
    public String imageUrl;

    public Brand(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}'+
                '\n';
    }
}
