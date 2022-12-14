package com.yaroshevich.app.model;

public class District {

    private final int id;

    private final String name;

    private final int parent_id;

    private final String parentName;

    public District(int id, String name, int parent_id, String parentName) {
        this.id = id;
        this.name = name;
        this.parent_id = parent_id;
        this.parentName = parentName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getParent_id() {
        return parent_id;
    }

    public String getParentName() {
        return parentName;
    }

    @Override
    public String toString() {
        return "District{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent_id=" + parent_id +
                ", parentName='" + parentName + '\'' +
                '}';
    }
}
