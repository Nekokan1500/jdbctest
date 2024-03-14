package com.arthur.learn.jdbc.bean;

import java.math.BigDecimal;

public class User {

    private String name;
    private String password;
    private String address;
    private String phone;
    private BigDecimal balance;
    
    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User [name=" + name + ", password=" + password + ", address=" + address + ", phone=" + phone
                + ", balance=" + balance + "]";
    }
}
