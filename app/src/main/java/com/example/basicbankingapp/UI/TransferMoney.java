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


public class TransferMoney  extends AppCompatActivity implements CustomerListAdapter.OnNoteLis {

        ArrayList<DisplayCus> custList =new ArrayList<>();;
        RecyclerView rcyview;
        CustomerListAdapter cusadapter;
        String amount;
        String phone;
        CustomerDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transermoney);

        rcyview=(RecyclerView) findViewById(R.id.rcytransfer);
        Bundle extras = getIntent().getExtras();

        if (extras != null)
        {
            phone=extras.getString("phone");
            amount = extras.getString("amount");
        }
        displaydata();
    }

   public void displaydata()
   {
       cusadapter=new CustomerListAdapter();
       custList.clear();
       db=new CustomerDatabase(this);
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

    @Override
    public void onNoteClick(int post) {
        updatabalance(post);
        Intent i=new Intent(TransferMoney.this,MainActivity.class);
        startActivity(i);
    }

    public void updatabalance(int post)
    {
        String tophone=custList.get(post).getPhone();
        String toname=null;
        Cursor cursor=db.readdata(tophone);
        while(cursor.moveToNext())
        {
            toname=cursor.getString(1);
        }
        String fromname = null;
        Cursor c=db.readdata(phone);
        while(c.moveToNext())
        {
            fromname=c.getString(1);
        }
        CustomerDatabase db=new CustomerDatabase(this);
        db.insertTranser(fromname,toname,amount);
        double bal=Double.parseDouble(custList.get(post).getBalance());
        double amo=Double.parseDouble(amount);
        double res=bal+amo;
        db.updataamount(String.valueOf(res),tophone);
    }

}


