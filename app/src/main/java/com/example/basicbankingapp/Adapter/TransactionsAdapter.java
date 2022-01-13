package com.example.basicbankingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.Displaydata.DisplayTrans;

import java.util.ArrayList;
import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionViewHolder>{
    List<DisplayTrans> transList=new ArrayList<>();

    @NonNull
    @Override
    public TransactionsAdapter.TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TransactionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.transcations_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        holder.to.setText(transList.get(position).getToname());
        holder.from.setText(transList.get(position).getFromname());
        holder.money.setText(transList.get(position).getMoney());
    }

    @Override
    public int getItemCount() {
        return transList.size();
    }
    public void setList(ArrayList<DisplayTrans> transList)
    {
        this.transList=transList;
        notifyDataSetChanged();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView to;
        TextView from;
        TextView money;
        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            to=itemView.findViewById(R.id.toname);
            from=itemView.findViewById(R.id.fromname);
            money=itemView.findViewById(R.id.money);
        }

    }
}
