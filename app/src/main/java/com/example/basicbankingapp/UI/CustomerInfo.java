package com.example.basicbankingapp.UI;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.basicbankingapp.DataBase.CustomerDatabase;
import com.example.basicbankingapp.R;

public class CustomerInfo extends AppCompatActivity implements SendDialog.SendDialogListener {

    TextView name;
    TextView email;
    TextView phone;
    TextView address;
    TextView age;
    TextView account_num;
    TextView curr_bal;
    CustomerDatabase db;
    Button transfer;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_info);

         initdata();
         Displayinfo();
    }

    public void initdata()
    {
        name=(TextView)findViewById(R.id.name);
        email=(TextView) findViewById(R.id.email);
        phone=(TextView) findViewById(R.id.phone);
        address=(TextView)findViewById(R.id.address);
        age=(TextView) findViewById(R.id.age);
        account_num=(TextView) findViewById(R.id.account);
        curr_bal=(TextView) findViewById(R.id.balance);
        transfer=(Button)findViewById(R.id.transfer_btn);
    }

    public void Displayinfo()
    {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("phone");
            db=new CustomerDatabase(this);
            Cursor c=db.readdata(value);

            while(c.moveToNext())
            {
                name.setText(c.getString(1));
                email.setText(c.getString(2));
                age.setText(c.getString(3));
                address.setText(c.getString(4));
                phone.setText(c.getString(5));
                account_num.setText(c.getString(6));
                curr_bal.setText(c.getString(7));
            }

            transfer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog();
                }
            });

        }
    }

    public void openDialog()
    {
        SendDialog send=new SendDialog();
        send.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void applyTexts(String amount) {
        //return balance,name from database
        String bal=null;
        Cursor c=db.readdata(value);
        while(c.moveToNext())
        {
            bal=c.getString(7);
        }
        updatabal(bal,amount);
    }

    public void updatabal(String bal,String amount)
    {
        String result=calculatebalance(bal,amount);
        if(result==null) {
            Toast.makeText(getApplicationContext(),"you don't have that amount",Toast.LENGTH_LONG).show();
        }
        else {
            db.updataamount(result,value);
            Intent i = new Intent(CustomerInfo.this, TransferMoney.class);
            i.putExtra("phone", value);
            i.putExtra("amount", amount);
            Log.d("phone", value);
            Log.d("amunt", amount);
            startActivity(i);
        }
    }

    public String calculatebalance(String bal,String amount)
    {
        double balance=Double.parseDouble(bal);
        double send=Double.parseDouble(amount);
        double result=balance-send;
        if(result>=0)
        {
            return String.valueOf(result);
        }
        else
        {
            return null;
        }
    }
}
