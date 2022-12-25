package com.yaroshevich.app.model;

public class Shift {

    private final int id;

    private final String start;

    private final String end;

    public Shift(int id, String start, String end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public Shift(String start, String end) {
        this.id = 0;
        this.start = start;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public String getStart() {
        return start == null ? null : start.substring(0, 5);
    }

    public String getEnd() {
        return end == null ? null : end.substring(0, 5);
    }

    @Override
    public String toString() {
        return "Shift{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                '}';
    }
}
