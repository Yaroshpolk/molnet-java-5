package com.yaroshevich.app.model;

public class Employee {

    private final long id;

    private final String firstName;

    private final String secondName;

    private final String patronymic;

    private final int age;

    private final Address address;

    private final Shift shift;

    public Employee(long id, String firstName, String secondName, String patronymic, int age, Address address, Shift shift) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.age = age;
        this.address = address;
        this.shift = shift;
    }

    public Employee(String firstName, String secondName, String patronymic, int age, Address address, Shift shift) {
        this.id = 0;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.age = age;
        this.address = address;
        this.shift = shift;
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

    public Address getAddress() {
        return address;
    }

    public Shift getShift() {
        return shift;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", shift=" + shift +
                '}';
    }
}
