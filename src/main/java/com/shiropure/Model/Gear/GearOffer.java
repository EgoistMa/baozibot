package com.shiropure.Model.Gear;

import java.time.OffsetDateTime;

public class GearOffer {
    public OffsetDateTime saleEndTime;
    public double price;
    public Gear gear;

    public GearOffer(OffsetDateTime saleEndTime, double price, Gear gear) {
        this.saleEndTime = saleEndTime;
        this.price = price;
        this.gear = gear;
    }

    public OffsetDateTime getSaleEndTime() {
        return saleEndTime;
    }

    public void setSaleEndTime(OffsetDateTime saleEndTime) {
        this.saleEndTime = saleEndTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Gear getGear() {
        return gear;
    }

    public void setGear(Gear gear) {
        this.gear = gear;
    }

    @Override
    public String toString() {
        return "GearOffer{" +
                "saleEndTime=" + saleEndTime +
                ", price=" + price +
                ", gear=" + gear +
                '}'+
                '\n';
    }
}