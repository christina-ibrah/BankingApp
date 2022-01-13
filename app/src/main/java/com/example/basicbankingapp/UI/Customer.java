package com.example.basicbankingapp.UI;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.Adapter.CustomerListAdapter;
import com.example.basicbankingapp.DataBase.CustomerDatabase;
import com.example.basicbankingapp.Displaydata.DisplayCus;
import com.example.basicbankingapp.R;

import java.util.ArrayList;

public class Customer extends AppCompatActivity implements CustomerListAdapter.OnNoteLis {

    ArrayList<DisplayCus>custList =new ArrayList<>();;
    RecyclerView rcyview;
    CustomerListAdapter cusadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customers);
        rcyview = (RecyclerView) findViewById(R.id.rec);
        Displaydata();
    }
    @Override
    public void onNoteClick(int post) {
        Intent i=new Intent(Customer.this,CustomerInfo.class);
        i.putExtra("phone",custList.get(post).getPhone());
        startActivity(i);
    }
   public void Displaydata()
    {
        cusadapter=new CustomerListAdapter();
        custList.clear();
        CustomerDatabase db=new CustomerDatabase(this);
        Cursor c=db.readcusList();
        while(c.moveToNext())
        {
            String name=c.getString(1);
            String phone=c.getString(5);
            String balance=c.getString(7);
            DisplayCus cus=new DisplayCus(name,phone,balance);
            custList.add(cus);
        }
        rcyview.setAdapter(cusadapter);
        cusadapter.setList(custList);
        cusadapter.setNote(this);
        rcyview.setLayoutManager(new LinearLayoutManager(this));
    }

}
