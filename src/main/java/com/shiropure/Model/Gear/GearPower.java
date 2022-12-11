package com.shiropure.Model.Gear;

public class GearPower {
    public String name;
    public String imageUrl;

    public GearPower(String name, String imageUrl) {
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
        return "GearPower{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}'+
                '\n';
    }
}
