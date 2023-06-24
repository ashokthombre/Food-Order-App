package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<MainModel>modelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);


        modelList.add(new MainModel(R.drawable.pizza,"Pizaa","10","delicious food"));
        modelList.add(new MainModel(R.drawable.burger,"Burer","5","Meaty portobello mushrooms make for the perfect vegetarian burger!"));
        modelList.add(new MainModel(R.drawable.frenchfry,"FrenchFry","7","Potato FrenchFry"));
        modelList.add(new MainModel(R.drawable.rice,"Rice","2","Jira Rice"));
        modelList.add(new MainModel(R.drawable.salad,"Salad","5","Healty salad"));
        modelList.add(new MainModel(R.drawable.pizza,"Pizaa","10","delicious food"));
        modelList.add(new MainModel(R.drawable.burger,"Burer","5","Non-neg Burger"));
        modelList.add(new MainModel(R.drawable.frenchfry,"FrenchFry","7","Potato FrenchFry"));
        modelList.add(new MainModel(R.drawable.rice,"Rice","2","Jira Rice"));
        modelList.add(new MainModel(R.drawable.salad,"Salad","5","Healty salad"));




        MainAdapter adapter=new MainAdapter(this,modelList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId()==R.id.my_order)
       {
           Intent intent=new Intent(MainActivity.this,OrderActivity.class);
           startActivity(intent);

       }

        return super.onOptionsItemSelected(item);
    }
}
