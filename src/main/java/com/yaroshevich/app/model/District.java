package com.yaroshevich.app.model;

public class District {

    private final int id;

    private final String name;

    private final District parent;

    public District(int id, String name, District parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public District(int id, String name) {
        this.id = id;
        this.name = name;
        this.parent = null;
    }

    public District getParent() {
        return parent;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
