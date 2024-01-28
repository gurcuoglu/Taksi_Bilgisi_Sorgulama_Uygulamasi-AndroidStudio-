package com.example.yazlab222.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public  class Transaction {
    public  String extra;
    public String VendorID;
    public double total_amount;
    public long tpep_pickup_datetime;
    public double trip_distance;
    public Transaction() {
    }
}
