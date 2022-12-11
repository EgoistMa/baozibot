package com.shiropure.Model.Gear;

import java.util.Arrays;

public class Gear {
    public String gearName;
    public GearPower primaryGearPower;
    public GearPower[] additionalGearPowers;
    public String imageUrl;
    public Brand brand;

    public Gear(String gearName, GearPower primaryGearPower, GearPower[] additionalGearPowers, String imageUrl, Brand brand) {
        this.gearName = gearName;
        this.primaryGearPower = primaryGearPower;
        this.additionalGearPowers = additionalGearPowers;
        this.imageUrl = imageUrl;
        this.brand = brand;
    }

    public String getGearName() {
        return gearName;
    }

    public void setGearName(String gearName) {
        this.gearName = gearName;
    }

    public GearPower getPrimaryGearPower() {
        return primaryGearPower;
    }

    public void setPrimaryGearPower(GearPower primaryGearPower) {
        this.primaryGearPower = primaryGearPower;
    }

    public GearPower[] getAdditionalGearPowers() {
        return additionalGearPowers;
    }

    public void setAdditionalGearPowers(GearPower[] additionalGearPowers) {
        this.additionalGearPowers = additionalGearPowers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Gear{" +
                "gearName='" + gearName + '\'' +
                ", primaryGearPower=" + primaryGearPower +
                ", additionalGearPowers=" + Arrays.toString(additionalGearPowers) +
                ", imageUrl='" + imageUrl + '\'' +
                ", brand=" + brand +
                '}'+
                '\n';
    }
}
