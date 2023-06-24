package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.foodorderapp.Adapters.OrderAdapter;
import com.example.foodorderapp.Models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        recyclerview=findViewById(R.id.recyclerView);

        getSupportActionBar().setTitle("Orders");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DBeHelper dBeHelper=new DBeHelper(this);

       List<OrderModel>modelList= dBeHelper.getOrders();


       OrderAdapter orderAdapter=new OrderAdapter(this,modelList);
       recyclerview.setAdapter(orderAdapter);

       LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

       recyclerview.setLayoutManager(linearLayoutManager);







    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}