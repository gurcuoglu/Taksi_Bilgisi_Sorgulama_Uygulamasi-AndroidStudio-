package com.example.yazlab222;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yazlab222.databinding.Type2ItemBinding;
import com.example.yazlab222.model.Transaction;
import com.example.yazlab222.model.Type2Model;

import java.util.ArrayList;

class Type2Adapter extends  RecyclerView.Adapter<Type2Adapter.Holder> {

    private final ArrayList<Type2Model> type2Models;
    public Type2Adapter(ArrayList<Type2Model> list) {
        this.type2Models = list;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(Type2ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Type2Model type2Model = type2Models.get(position);
        holder.type2ItemBinding.date.setText(type2Model.dateString);
        holder.type2ItemBinding.amount.setText(String.valueOf(type2Model.avg_amount));
    }

    @Override
    public int getItemCount() {
        return type2Models.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        private final Type2ItemBinding type2ItemBinding;
        public Holder(Type2ItemBinding type2ItemBinding) {
            super(type2ItemBinding.getRoot());
            this.type2ItemBinding = type2ItemBinding;
        }
    }

}
