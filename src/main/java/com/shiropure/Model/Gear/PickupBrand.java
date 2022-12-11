package com.shiropure.Model.Gear;

import java.time.OffsetDateTime;
import java.util.Arrays;

public class PickupBrand {
    public String pickupBrandImageUrl;
    public String brandName;
    public String brandGearPowerName;
    public String brandGearPowerDesc;
    public String brandGearPowerImageUrl;
    public OffsetDateTime saleEndTime;
    public String NextBrandName;
    public String NextBrandImageUrl;

    public GearOffer[] pickupGears;

    public PickupBrand(String pickupBrandImageUrl, String brandName, String brandGearPowerName, String brandGearPowerDesc, String brandGearPowerImageUrl, OffsetDateTime saleEndTime, String nextBrandName, String nextBrandImageUrl, GearOffer[] pickupGears) {
        this.pickupBrandImageUrl = pickupBrandImageUrl;
        this.brandName = brandName;
        this.brandGearPowerName = brandGearPowerName;
        this.brandGearPowerDesc = brandGearPowerDesc;
        this.brandGearPowerImageUrl = brandGearPowerImageUrl;
        this.saleEndTime = saleEndTime;
        NextBrandName = nextBrandName;
        NextBrandImageUrl = nextBrandImageUrl;
        this.pickupGears = pickupGears;
    }

    public String getPickupBrandImageUrl() {
        return pickupBrandImageUrl;
    }

    public void setPickupBrandImageUrl(String pickupBrandImageUrl) {
        this.pickupBrandImageUrl = pickupBrandImageUrl;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandGearPowerName() {
        return brandGearPowerName;
    }

    public void setBrandGearPowerName(String brandGearPowerName) {
        this.brandGearPowerName = brandGearPowerName;
    }

    public String getBrandGearPowerDesc() {
        return brandGearPowerDesc;
    }

    public void setBrandGearPowerDesc(String brandGearPowerDesc) {
        this.brandGearPowerDesc = brandGearPowerDesc;
    }

    public String getBrandGearPowerImageUrl() {
        return brandGearPowerImageUrl;
    }

    public void setBrandGearPowerImageUrl(String brandGearPowerImageUrl) {
        this.brandGearPowerImageUrl = brandGearPowerImageUrl;
    }

    public OffsetDateTime getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(OffsetDateTime saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public String getNextBrandName() {
        return NextBrandName;
    }

    public void setNextBrandName(String nextBrandName) {
        NextBrandName = nextBrandName;
    }

    public String getNextBrandImageUrl() {
        return NextBrandImageUrl;
    }

    public void setNextBrandImageUrl(String nextBrandImageUrl) {
        NextBrandImageUrl = nextBrandImageUrl;
    }

    public GearOffer[] getPickupGears() {
        return pickupGears;
    }

    public void setPickupGears(GearOffer[] pickupGears) {
        this.pickupGears = pickupGears;
    }

    @Override
    public String toString() {
        return "PickupBrand{" +
                "pickupBrandImageUrl='" + pickupBrandImageUrl + '\'' +
                ", brandName='" + brandName + '\'' +
                ", brandGearPowerName='" + brandGearPowerName + '\'' +
                ", brandGearPowerDesc='" + brandGearPowerDesc + '\'' +
                ", brandGearPowerImageUrl='" + brandGearPowerImageUrl + '\'' +
                ", saleEndTime=" + saleEndTime +
                ", NextBrandName='" + NextBrandName + '\'' +
                ", NextBrandImageUrl='" + NextBrandImageUrl + '\'' +
                ", pickupGears=" + Arrays.toString(pickupGears) +
                '}'+
                '\n';
    }
}
