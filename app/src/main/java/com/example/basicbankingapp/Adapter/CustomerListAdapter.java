package com.example.basicbankingapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basicbankingapp.R;
import com.example.basicbankingapp.Displaydata.DisplayCus;

import java.util.ArrayList;
import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.CustomerViewHolder>{
     List<DisplayCus> custList=new ArrayList<>();
     OnNoteLis ON;


    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_list_item,parent,false),ON);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerListAdapter.CustomerViewHolder holder, int position) {
         holder.name.setText(custList.get(position).getName());
         holder.phone.setText(custList.get(position).getPhone());
         holder.bal.setText(custList.get(position).getBalance());


    }
    public void setNote(OnNoteLis Note)
    {
        this.ON=Note;
    }
   public void setList(ArrayList<DisplayCus> cusList)
   {
       this.custList=cusList;
       notifyDataSetChanged();
   }
    @Override
    public int getItemCount() {
        return custList.size();
    }


    public class CustomerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView phone;
        TextView bal;
        OnNoteLis ON;
        public CustomerViewHolder(@NonNull View itemView,OnNoteLis ON) {
            super(itemView);
            name=itemView.findViewById(R.id.name_cus);
            phone=itemView.findViewById(R.id.phone_txt);
            bal=itemView.findViewById(R.id.bal_txt);
            this.ON=ON;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            ON.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteLis{
        void onNoteClick(int post);
    }
}
