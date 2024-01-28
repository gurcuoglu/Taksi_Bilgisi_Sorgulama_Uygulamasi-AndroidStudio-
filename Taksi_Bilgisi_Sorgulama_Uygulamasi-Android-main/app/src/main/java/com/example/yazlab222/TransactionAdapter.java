package com.example.yazlab222;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yazlab222.databinding.TaxiTransactionItemBinding;
import com.example.yazlab222.model.Transaction;

import java.util.ArrayList;

class TransactionAdapter extends  RecyclerView.Adapter<TransactionAdapter.Holder> {

    private final ArrayList<Transaction> transactions;
    public TransactionAdapter(ArrayList<Transaction> list) {
        this.transactions = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(TaxiTransactionItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.taxiTransactionItemBinding.extra.setText(transaction.extra);
        holder.taxiTransactionItemBinding.vendorId.setText(transaction.VendorID);
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }


    class Holder extends RecyclerView.ViewHolder {
        private TaxiTransactionItemBinding taxiTransactionItemBinding;
        public Holder(TaxiTransactionItemBinding taxiTransactionItemBinding) {
            super(taxiTransactionItemBinding.getRoot());
            this.taxiTransactionItemBinding = taxiTransactionItemBinding;
        }
    }

}
