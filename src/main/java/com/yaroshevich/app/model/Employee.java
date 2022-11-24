package com.yaroshevich.app.model;

public class Employee {

    private final long id;

    private final String firstName;

    private final String secondName;

    private final String patronymic;

    private final int age;

    private final int addressId;

    private final int shiftId;

    public Employee(long id, String firstName, String secondName, String patronymic, int age, int addressId, int shiftId) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.age = age;
        this.addressId = addressId;
        this.shiftId = shiftId;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public int getAge() {
        return age;
    }

    public int getAddressId() {
        return addressId;
    }

    public int getShiftId() {
        return shiftId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", addressId=" + addressId +
                ", shiftId=" + shiftId +
                '}';
    }
}
