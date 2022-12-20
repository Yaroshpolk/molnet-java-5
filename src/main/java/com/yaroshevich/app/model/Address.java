package com.yaroshevich.app.model;

public class Address {

    private final int id;

    private final String address;

    private final District district;

    public Address(int id, String address, District district) {
        this.id = id;
        this.address = address;
        this.district = district;
    }

    public Address(String address, District district) {
        this.id = 0;
        this.address = address;
        this.district = district;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public District getDistrict() {
        return district;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", district=" + district +
                '}';
    }
}
