package com.example.yazlab222;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.yazlab222.model.Transaction;
import com.example.yazlab222.databinding.Type1ItemBinding;
import com.example.yazlab222.model.Type1Model;

import java.util.ArrayList;

class Type1Adapter extends  RecyclerView.Adapter<Type1Adapter.Holder> {

    private final ArrayList<Type1Model> type1Models;
    public Type1Adapter(ArrayList<Type1Model> list) {
        this.type1Models = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(Type1ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Type1Model type1Model = type1Models.get(position);
        holder.type1ItemBinding.date.setText(type1Model.dateString);
        holder.type1ItemBinding.distance.setText(String.valueOf(type1Model.trip_distance));
    }

    @Override
    public int getItemCount() {
        return type1Models.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        private final Type1ItemBinding type1ItemBinding;
        public Holder(Type1ItemBinding type1ItemBinding) {
            super(type1ItemBinding.getRoot());
            this.type1ItemBinding = type1ItemBinding;
        }
    }

}
