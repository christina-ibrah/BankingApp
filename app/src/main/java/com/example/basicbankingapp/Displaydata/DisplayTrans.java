package com.example.basicbankingapp.Displaydata;

public class DisplayTrans
{
        private String fromname;
        private String toname;
        private String money;
        public DisplayTrans(String fromname,String toname,String money) {
        this.fromname=fromname;
        this.toname=toname;
        this.money=money;
        }

    public String getFromname() {
        return fromname;
    }

    public void setFromname(String fromname) {
        this.fromname = fromname;
    }

    public String getToname() {
        return toname;
    }

    public void setToname(String toname) {
        this.toname = toname;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
