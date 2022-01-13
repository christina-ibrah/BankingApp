package com.example.basicbankingapp.UI;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.basicbankingapp.R;

public class SendDialog extends AppCompatDialogFragment {
    EditText amount;
    SendDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity()) ;
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.layout_dialog,null);


        builder.setView(view).setPositiveButton("Send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               String money=amount.getText().toString();
               listener.applyTexts(money);
            }
        });
        amount=view.findViewById(R.id.amount_txt);
        return  builder.create();

    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        try {
            listener=(SendDialogListener) context;
        } catch (ClassCastException e) {
           throw  new ClassCastException(context.toString()+"must implement SendDialogListerner");
        }
    }
    public interface  SendDialogListener
    {
        void applyTexts(String amount);
    }
}
