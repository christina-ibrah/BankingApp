package com.example.basicbankingapp.Displaydata;

public class DisplayCus {
    private String name;
    private String phone;
    private String balance;
    public DisplayCus(String name,String phone,String balance)
    {
        this.name=name;
        this.phone=phone;
        this.balance=balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

}
