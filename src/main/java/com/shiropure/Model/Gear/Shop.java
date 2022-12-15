package com.shiropure.Model.Gear;

public class Shop {
    public PickupBrand pickupBrand;
    public GearOffer[] limitedGears;

    public Shop(PickupBrand pickupBrand, GearOffer[] limitedGears) {
        this.pickupBrand = pickupBrand;
        this.limitedGears = limitedGears;
    }

    public PickupBrand getPickupBrand() {
        return pickupBrand;
    }

    public void setPickupBrand(PickupBrand pickupBrand) {
        this.pickupBrand = pickupBrand;
    }

    public GearOffer[] getLimitedGears() {
        return limitedGears;
    }

    public void setLimitedGears(GearOffer[] limitedGears) {
        this.limitedGears = limitedGears;
    }
}
