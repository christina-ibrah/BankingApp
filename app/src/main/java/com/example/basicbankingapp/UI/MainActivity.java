package com.example.basicbankingapp.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;

import com.example.basicbankingapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button cus_view=(Button) findViewById(R.id.view_cus);
        Button trans_view=(Button) findViewById(R.id.view_trans);

        cus_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Customer.class);
                startActivity(i);
            }
        });

        trans_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i=new Intent(MainActivity.this,Transactions.class);
                    startActivity(i);
                }
        });
    }
}