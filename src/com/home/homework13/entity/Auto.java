package com.home.homework13.entity;

import java.util.Objects;

public class Auto {

    private int id;
    private String carModel;

    public Auto(int id, String carModel) {
        this.id = id;
        this.carModel = carModel;
    }

    public Auto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return id == auto.id &&
                Objects.equals(carModel, auto.carModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, carModel);
    }

    @Override
    public String toString() {
        return "Auto{" +
                "id=" + id +
                ", carModel='" + carModel + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }
}
