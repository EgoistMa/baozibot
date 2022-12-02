package com.shiropure.Model;

public class Weapon {
    String weaponName;
    String weaponUrl;

    public Weapon(String weaponName, String weaponUrl) {
        this.weaponName = weaponName;
        this.weaponUrl = weaponUrl;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getWeaponUrl() {
        return weaponUrl;
    }

    public void setWeaponUrl(String weaponUrl) {
        this.weaponUrl = weaponUrl;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "weaponName='" + weaponName + '\'' +
                ", weaponUrl='" + weaponUrl + '\'' +
                '}';
    }
}
