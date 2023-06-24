package com.example.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

 ImageView detailImage,minus,plus;
 TextView foodName,count,description,priceOrder;
 EditText yourName,phone;

 Button orderNow;
    int countNo=1;

    int countUpdate=1;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setTitle("Oredr Now");

        progressDialog=new ProgressDialog(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        detailImage=findViewById(R.id.detailImage);
        foodName=findViewById(R.id.foodName);
        count=findViewById(R.id.count);
        description=findViewById(R.id.description);
        priceOrder=findViewById(R.id.priceorder);
        yourName=findViewById(R.id.yourName);
        phone=findViewById(R.id.phone);
        orderNow=findViewById(R.id.orderNow);
        minus=findViewById(R.id.minus);
        plus=findViewById(R.id.plus);

     final int image=getIntent().getIntExtra("image",R.drawable.burger);
         String price= getIntent().getStringExtra("price");
          String desc= getIntent().getStringExtra("desc");
          String name= getIntent().getStringExtra("name");


          detailImage.setImageResource(image);
          foodName.setText(name);
          description.setText(desc);
          priceOrder.setText(price);


          plus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  countNo++;

                  count.setText(countNo+"");

                  int b=Integer.parseInt(price);
                  int p=countNo*b;
                  priceOrder.setText(p+"");


              }
          });

          minus.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (countNo!=0)
                  {
                      countNo--;
                      count.setText(countNo+"");

                      int b=Integer.parseInt(price);
                      int p=countNo*b;
                      priceOrder.setText(p+"");


                  }
              }
          });

          DBeHelper dBeHelper=new DBeHelper(this);

          if (getIntent().getIntExtra("type",0)==1)
          {
              progressDialog.setTitle("Order.");
              progressDialog.setMessage("creating order...");

              orderNow.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (!yourName.getText().toString().equals("") && !phone.getText().toString().equals(""))
                      {
                          String name=yourName.getText().toString();
                          String yourPhone=phone.getText().toString();
                          int orderprice=Integer.parseInt(priceOrder.getText().toString());

                          String desc=description.getText().toString();
                          String foodname=foodName.getText().toString();

                          progressDialog.show();

                          boolean isInserted= dBeHelper.insertOrder(name,yourPhone,orderprice,image,countNo,desc,foodname);

                          progressDialog.dismiss();
                          if (isInserted)
                          {
                              Toast.makeText(DetailActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                              Intent intent=new Intent(DetailActivity.this,MainActivity.class);
                              startActivity(intent);
                              finish();
                          }
                          else
                          {
                              progressDialog.show();
                              yourName.setError("Field is empty");
                              phone.setError("field is empty");

                              Toast.makeText(DetailActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                              progressDialog.dismiss();

                          }


                      }
                      else
                      {
                          Toast.makeText(DetailActivity.this, "Fields Are empty", Toast.LENGTH_SHORT).show();

                      }

                  }
              });

          }
          else
          {

              progressDialog.setTitle("update");
              progressDialog.setMessage("Order Updating..");



              orderNow.setText("Update Order");

              int id=Integer.parseInt(getIntent().getStringExtra("id"));

             Cursor cursor= dBeHelper.getOrderById(id);

             detailImage.setImageResource(R.drawable.burger);
             int i=cursor.getInt(4);

             int p=cursor.getInt(3);
             String pp=p+"";
             priceOrder.setText(pp);
             yourName.setText(cursor.getString(1));
             description.setText(cursor.getString(6));
             int c=cursor.getInt(5);
             String cc=c+"";
             count.setText(cc);
             phone.setText(cursor.getString(2));
             foodName.setText(cursor.getString(7));

             countUpdate=c;



             plus.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     countUpdate++;
                     count.setText(countUpdate+"");

                 }
             });
             minus.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (countNo!=0)
                     {
                        countUpdate--;
                        count.setText(countUpdate+"");


                     }
                 }
             });

             orderNow.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         if (yourName.getText().toString().equals("")&& phone.getText().toString().equals(""))
                         {
                             Toast.makeText(DetailActivity.this, "Fields Are Empty !", Toast.LENGTH_SHORT).show();

                         }
                         else
                         {

                             progressDialog.show();

                             boolean isUpdated= dBeHelper.updateOrder(yourName.getText().toString(),phone.getText().toString(),Integer.parseInt(priceOrder.getText().toString()),i,
                                     Integer.parseInt(count.getText().toString()),id,description.getText().toString(),foodName.getText().toString());
                             progressDialog.dismiss();


                             if (isUpdated)
                             {
                                 Toast.makeText(DetailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                                 Intent intent=new Intent(DetailActivity.this,OrderActivity.class);
                                 startActivity(intent);
                                 finish();
                             }
                         }
                     }
                 });
             }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
