package com.example.yazlab222;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yazlab222.databinding.ActivityMainBinding;
import com.example.yazlab222.model.Transaction;
import com.example.yazlab222.model.Type2Model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.yazlab222.model.Type1Model;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private boolean isStarted;
    private boolean isTransactionsLoaded;
    private int isClicked;
    private Button button_type3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = mainBinding.getRoot();
        setContentView(view);

        FirebaseApp.initializeApp(this);
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        mainBinding.rvTransaction.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        mainBinding.buttonType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicked = 1;
                check();
            }
        });

        mainBinding.buttonType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicked = 2;
                check();
            }
        });
        mainBinding.buttonType3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isClicked = 3;
                check();
            }
        });
        database.child("taxi").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            //transaction yükleniyor
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    if(task.getResult().getChildrenCount() > 0 ){
                        for (DataSnapshot item : task.getResult().getChildren()) {
                            transactions.add(item.getValue(Transaction.class));
                        }
                    }

                    isTransactionsLoaded = true;
                    check();

                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }


            }
        });
    }

    private void check(){
        if(isTransactionsLoaded){
            if(isClicked != 0){
                isStarted = true;
                if(isClicked == 1){
                    type1();
                }else if(isClicked==2){
                    type2();
                }else{
                    type3();
                }
            }
        }else{
            mainBinding.progress.setVisibility(View.VISIBLE);
        }
    }



    private void type1(){
        double trip1=0.0;
        double trip2=0.0;
        double trip3=0.0;
        double trip4=0.0;
        double trip5=0.0;
        double temp;
        double temp2;
        long day1=0;
        long day2=0;
        long day3=0;
        long day4=0;
        long day5=0;
        long temp3;
        long temp4;
        for(Transaction transaction : transactions) {
            if(transaction.trip_distance>=trip3){
                if(transaction.trip_distance>=trip2){
                    if(transaction.trip_distance>=trip1){
                        temp = trip1;
                        temp3=day1;

                        trip1 = transaction.trip_distance;
                        day1= transaction.tpep_pickup_datetime;

                        temp2 = trip2;
                        temp4 = day2;

                        trip2 = temp;
                        day2=temp3;

                        temp = trip3;
                        temp3 = day3;

                        trip3 = temp2;
                        day3 = temp4;

                        temp2 = trip4;
                        temp4 = day4;

                        trip4 = temp;
                        day4 = temp3;

                        trip5 = temp2;
                        day5 = temp4;
                    }
                    else{
                        temp2 = trip2;
                        temp4 = day2;

                        trip2 = transaction.trip_distance;
                        day2=transaction.tpep_pickup_datetime;

                        temp = trip3;
                        temp3 = day3;

                        trip3 = temp2;
                        day3 = temp4;

                        temp2 = trip4;
                        temp4 = day4;

                        trip4 = temp;
                        day4 = temp3;

                        trip5 = temp2;
                        day5 = temp4;
                    }

                }else{
                    temp = trip3;
                    temp3 = day3;

                    trip3 = transaction.trip_distance;
                    day3 = transaction.tpep_pickup_datetime;

                    temp2 = trip4;
                    temp4 = day4;

                    trip4 = temp;
                    day4 = temp3;

                    trip5 = temp2;
                    day5 = temp4;
                }

            }
            else{
                if(transaction.trip_distance<=trip4){
                    if(transaction.trip_distance>=trip5){
                        trip5=transaction.trip_distance;
                        day5=transaction.tpep_pickup_datetime;
                    }

                }
                else{
                    temp2 = trip4;
                    temp4 = day4;

                    trip4 = transaction.trip_distance;
                    day4 = transaction.tpep_pickup_datetime;

                    trip5 = temp2;
                    day5 = temp4;
                }
            }
        }

        getDateWithoutTimeUsingCalendar(day1);
        int j = 0;
        HashMap<Date, Type1Model> type1ModelHashMap = new HashMap<>();
        Date date1 = getDateWithoutTimeUsingCalendar(day1);
        Date date2 = getDateWithoutTimeUsingCalendar(day2);
        Date date3 = getDateWithoutTimeUsingCalendar(day3);
        Date date4 = getDateWithoutTimeUsingCalendar(day4);
        Date date5 = getDateWithoutTimeUsingCalendar(day5);

        for(Transaction transaction : transactions) {
            Type1Model type1Model = new Type1Model();

            if (j==0){
                type1Model.date = date1;
                type1Model.trip_distance=trip1;
                type1ModelHashMap.put(date1,type1Model);

            }
            if (j==1){
                type1Model.date = date2;
                type1Model.trip_distance=trip2;
                type1ModelHashMap.put(date2,type1Model);
            }
            if (j==2){
                type1Model.date = date3;
                type1Model.trip_distance=trip3;
                type1ModelHashMap.put(date3,type1Model);
            }if (j==3){
                type1Model.date = date4;
                type1Model.trip_distance=trip4;
                type1ModelHashMap.put(date4,type1Model);
            }if (j==4){
                type1Model.date = date5;
                type1Model.trip_distance=trip5;
                type1ModelHashMap.put(date5,type1Model);
            }
            j++;


        }
        ArrayList<Type1Model> type1ModelArrayList = new ArrayList<>();
        for(Type1Model type1Model : type1ModelHashMap.values()){
            type1Model.dateString = getDateFormat(type1Model.date);
            type1ModelArrayList.add(type1Model);

        }

        Type1Adapter type1Adapter = new Type1Adapter(type1ModelArrayList);
        mainBinding.rvTransaction.setAdapter(type1Adapter);
        mainBinding.rvTransaction.setVisibility(View.VISIBLE);
        mainBinding.buttonType1.setVisibility(View.GONE);
        mainBinding.buttonType2.setVisibility(View.GONE);
        mainBinding.progress.setVisibility(View.GONE);
        button_type3.setVisibility(View.GONE);
    }


    private void type2(){
        HashMap<Date, Type2Model> type2ModelHashMap = new HashMap<>();
        for(Transaction transaction : transactions){
            Date date = getDateWithoutTimeUsingCalendar(transaction.tpep_pickup_datetime);
            Type2Model type2Model;
            if(type2ModelHashMap.containsKey(date)){
                type2Model= type2ModelHashMap.get(date);
                if(type2Model != null){
                    type2Model.size = type2Model.size + 1;
                    type2Model.total_amount = type2Model.total_amount + transaction.total_amount;
                }
            }else{
                type2Model = new Type2Model();
                type2Model.size = 1;
                type2Model.total_amount = transaction.total_amount;
                type2Model.date = date;
            }

            type2ModelHashMap.put(date,type2Model);
        }

        for(Date key : type2ModelHashMap.keySet()){
            Type2Model type2Model = type2ModelHashMap.get(key);
            double avgAmount = round(type2Model.total_amount / type2Model.size,2);
            type2Model.avg_amount = avgAmount;
            type2ModelHashMap.put(key,type2Model);
        }

        int i = 0;
        Date startDate = null;
        Date endDate = null;
        ArrayList<Type2Model> type2ModelArrayList = new ArrayList<>();
        for(Type2Model type2Model : sortByComparator(type2ModelHashMap,true).values()){
            if(i == 1){
                startDate = type2Model.date;
                type2Model.dateString = getDateFormat(type2Model.date);
                type2ModelArrayList.add(type2Model);
            }
            if(i == 2){
                endDate = type2Model.date;
                type2Model.dateString = getDateFormat(type2Model.date);
                type2ModelArrayList.add(type2Model);
            }
            i++;
            if( i > 2 && type2Model.date.after(startDate) && type2Model.date.before(endDate)){
                type2Model.dateString = getDateFormat(type2Model.date);
                type2ModelArrayList.add(type2Model);
            }
        }

        Collections.sort(type2ModelArrayList, (o1, o2) -> o1.date.compareTo(o2.date));
        Type2Adapter type2Adapter = new Type2Adapter(type2ModelArrayList);
        mainBinding.rvTransaction.setAdapter(type2Adapter);
        mainBinding.rvTransaction.setVisibility(View.VISIBLE);
        mainBinding.buttonType1.setVisibility(View.GONE);
        mainBinding.buttonType2.setVisibility(View.GONE);
        mainBinding.progress.setVisibility(View.GONE);
        button_type3.setVisibility(View.GONE);
    }


    private void type3(){
        button_type3 =findViewById(R.id.button_type3);

        button_type3.setOnClickListener(new View.OnClickListener(){

                public void onClick(View v){
                    startActivity(new Intent(MainActivity.this,Type3Adapter.class));
                    finish();
                }

        });

    }



    public static Date  getDateWithoutTimeUsingCalendar(long timestampLong) {
        Date d = new Date(timestampLong * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static String getDateFormat(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int d = calendar.get(Calendar.DATE);
        return year + "/"+ (month + 1) + "/" + d;
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private static Map<Date, Type2Model> sortByComparator(Map<Date, Type2Model> unsortMap, final boolean order)
    {

        List<Map.Entry<Date, Type2Model>> list = new LinkedList<Map.Entry<Date, Type2Model>>(unsortMap.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Map.Entry<Date, Type2Model>>() {
            public int compare(Map.Entry<Date, Type2Model> o1, Map.Entry<Date, Type2Model> o2) {
                if (order) {
                    return o1.getValue().avg_amount.compareTo(o2.getValue().avg_amount);
                }
                else {
                    return o2.getValue().avg_amount.compareTo(o1.getValue().avg_amount);
                }
            }
        });

        // Maintaining insertion order with the help of LinkedList
        Map<Date, Type2Model> sortedMap = new LinkedHashMap<Date, Type2Model>();
        for (Map.Entry<Date, Type2Model> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }


    @Override
    public void onBackPressed() {
        if(isClicked != 0){
            mainBinding.rvTransaction.setVisibility(View.GONE);
            mainBinding.buttonType1.setVisibility(View.VISIBLE);
            mainBinding.buttonType2.setVisibility(View.VISIBLE);
            button_type3.setVisibility(View.VISIBLE);
            isClicked = 0;
        }else{
            super.onBackPressed();
        }
    }
}

