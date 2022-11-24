package com.yaroshevich.app.model;

public class Address {

    private final int id;

    private final String address;

    private final String district;

    private final String region;

    public Address(int id, String address, String district, String region) {
        this.id = id;
        this.address = address;
        this.district = district;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getDistrict() {
        return district;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", district='" + district + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
