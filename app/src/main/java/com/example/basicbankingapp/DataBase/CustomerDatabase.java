package com.example.basicbankingapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CustomerDatabase extends SQLiteOpenHelper {
   private static String databaseName="DB";
   SQLiteDatabase DB;

    public CustomerDatabase(Context c)
    {
        super(c,databaseName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Customers(ID integer primary key autoincrement," +
                "name text not null," +
                "email text not null," +
                "age text not null," +
                "address text not null," +
                "phone text not null," +
                "account_no text not null," +
                "current_balance text not null)");

        db.execSQL("create table Transcations(TransID integer primary key autoincrement," +
                "fromname text not null," +
                "toname text not null," +
                "amount text not null)");

        db.execSQL("insert into " + "Customers" + " values(0,'Nour Mahmed','Nour@gmail.com','32','india','01216975412', '10','5000')");
        db.execSQL("insert into " + "Customers" + " values(1,'Nancy Magdy', 'Nancy@gmail.com','22','London','01226894213', '11','1500')");
        db.execSQL("insert into " + "Customers" + " values(2,'David Ibrahim', 'David@gmail.com','25','cairo','012365479621', '12','1000')");
        db.execSQL("insert into " + "Customers" + " values(3,'Suzy Wellam', 'Suzy@gmail.com','30','Berlin','01246987423', '13','600')");
        db.execSQL("insert into " + "Customers" + " values(4,'Jack Welson', 'Jack@gmail.com','35','United state','01259476215', '14','20000')");
        db.execSQL("insert into " + "Customers" + " values(5,'Nade', 'Nade@gmail.com','24','cairo','01269844223', '15','3500')");
        db.execSQL("insert into " + "Customers" + " values(6,'Mariam', 'Mariam@gmail.com','22','cairo','01279844223', '16','1500')");
        db.execSQL("insert into " + "Customers" + " values(7,'Christina', 'Christina@gmail.com','22','cairo','01289854223', '17','90000')");
        db.execSQL("insert into " + "Customers" + " values(8,'Ibrahim', 'Ibrahim@gmail.com','40','cairo','01295576920', '18','25300')");
        db.execSQL("insert into " + "Customers" + " values(9,'Youssef', 'Youssef@gmail.com','35','London','01205689056', '19','20355')");
        db.execSQL("insert into " + "Customers" + " values(10,'Catherine', 'Catherine@gmail.com','23','cairo','01297621500', '20','2900')");
        db.execSQL("insert into " + "Customers" + " values(11,'Nader Ehab', 'Nader@gmail.com','50','cairo','01200679126', '9','3600')");
        db.execSQL("insert into " + "Customers" + " values(12,'Haidy ', 'Haidy@gmail.com','24','London','01201679126', '8','7000')");
        db.execSQL("insert into " + "Customers" + " values(13,'Mark', 'Mark@gmail.com','22','cairo','01202679126', '7','90000')");
        db.execSQL("insert into " + "Customers" + " values(14,'Mary', 'MaryKoky@gmail.com','22','ALex','01203999126', '6','10000')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Customers");
        db.execSQL("drop table if exists Transcations");
    }

    public Cursor readcusList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Customers", null);
        return cursor;
    }

    public Cursor readdata(String p){
        SQLiteDatabase db = this.getWritableDatabase();
        String [] phone=new String[]{p};
        Cursor cursor = db.rawQuery("select * from Customers where phone = ?", phone);
        return cursor;
    }


    public void insertTranser(String From,String To,String amount)
    {
        ContentValues row = new ContentValues();
        row.put("fromname", From);
        row.put("toname", To);
        row.put("amount", amount);
        DB=getWritableDatabase();
        DB.insert("Transcations", null, row);
        DB.close();
    }

    public Cursor readtransList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Transcations", null);
        return cursor;
    }

    public void updataamount(String bal,String phone)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues row=new ContentValues();
        row.put("current_balance",bal);
        db.update("Customers",row,"phone" + " = ? ",new String[]{phone});
    }


}
