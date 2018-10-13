package com.home.homework13.entity;

import java.util.Objects;

public class DelStatus {

    private int id;
    private String value;

    public DelStatus(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public DelStatus() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DelStatus delStatus = (DelStatus) o;
        return id == delStatus.id &&
                Objects.equals(value, delStatus.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value);
    }

    @Override
    public String toString() {
        return "DelStatus{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
