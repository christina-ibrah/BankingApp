package com.example.basicbankingapp.UI;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.Adapter.TransactionsAdapter;
import com.example.basicbankingapp.DataBase.CustomerDatabase;
import com.example.basicbankingapp.Displaydata.DisplayTrans;
import com.example.basicbankingapp.R;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity {
    RecyclerView rcy;
    ArrayList<DisplayTrans> transList;
    TransactionsAdapter adapter;
    CustomerDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transsactions);
        rcy=(RecyclerView)findViewById(R.id.rcyall);

        DisplayTransList();
    }

    public void DisplayTransList()
    {
        transList=new ArrayList<>();
        adapter=new TransactionsAdapter();
        db=new CustomerDatabase(this);
        transList.clear();
        Cursor c=db.readtransList();
        while(c.moveToNext())
        {
            String from=c.getString(1);
            String to=c.getString(2);
            String money=c.getString(3);
            DisplayTrans cus=new DisplayTrans(from,to,money);
            transList.add(cus);
        }
        rcy.setAdapter(adapter);
        adapter.setList(transList);
        rcy.setLayoutManager(new LinearLayoutManager(this));
    }
}
