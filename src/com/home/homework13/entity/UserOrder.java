package com.home.homework13.entity;

import java.util.Objects;

public class UserOrder {

    private int id;
    private int userId;
    private String passportNumber;
    private int auto;

    public UserOrder(int id, int userId, String passportNumber, int auto) {
        this.id = id;
        this.userId = userId;
        this.passportNumber = passportNumber;
        this.auto = auto;
    }

    public UserOrder() {
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrder userOrder = (UserOrder) o;
        return id == userOrder.id &&
                userId == userOrder.userId &&
                auto == userOrder.auto &&
                Objects.equals(passportNumber, userOrder.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, passportNumber, auto);
    }

    @Override
    public String toString() {
        return "UserOrder{" +
                "id=" + id +
                ", userId=" + userId +
                ", passportNumber='" + passportNumber + '\'' +
                ", auto=" + auto +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public int getAuto() {
        return auto;
    }

    public void setAuto(int auto) {
        this.auto = auto;
    }
}
