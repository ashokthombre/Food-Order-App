package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrderModel;

import java.util.ArrayList;
import java.util.List;

public class DBeHelper extends SQLiteOpenHelper {

    public final static String DB_NAME="mydatabase.db";

    public final static int VERSION_ID=1;
    public DBeHelper(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION_ID);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*

       int id=0
        name=1
        phone=2
        int price=3
       int image=4
        int quantity=5
        desc=6
        foodname=7
         */


         db.execSQL(
                 "create table orders" +
                         "(id int primary key autoincrement," +
                         "name text," +
                         "phone text," +
                         "price int," +
                         "image int," +
                         "quantity int," +
                         "description text," +
                         "foodname text)"
         );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP table if exists oredrs");
        onCreate(db);
    }

    public boolean insertOrder(String name,String phone,int price,int image,int quantity,String desc,String foodname)
    {
        SQLiteDatabase database=getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("description",desc);
        values.put("foodname",foodname);

        long id=database.insert("orders",null,values);


        if (id<=0)
        {
            return false;
        }
        return true;

    }

    public boolean updateOrder(String name,String phone,int price,int image,int quantity,int id,String desc,String foodname)
    {
        SQLiteDatabase database=getReadableDatabase();

        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("quantity",quantity);
        values.put("description",desc);
        values.put("foodname",foodname);

        long row=database.update("orders",values,"id=" +id,null);


        if (row<=0)
        {
            return false;
        }
        return true;

    }

public List<OrderModel> getOrders()
{
    List<OrderModel> orders=new ArrayList<>();

    SQLiteDatabase database=getWritableDatabase();
    Cursor cursor=database.rawQuery("select id,foodname,image,price from orders",null);

    if (cursor.moveToFirst())
    {
        while (cursor.moveToNext())
        {
            OrderModel model=new OrderModel();

            model.setOrderNumber(cursor.getInt(0)+"");
            model.setSoldItemName(cursor.getString(1));
             model.setOrderImage(R.drawable.frenchfry);
            model.setPrice(cursor.getInt(3)+"");
            orders.add(model);

        }

    }

    return orders;


}


public Cursor getOrderById(int id)
{

    SQLiteDatabase database=getWritableDatabase();

    Cursor cursor=database.rawQuery("select * from orders where id="+id,null);

    if (cursor!=null)
    {
        cursor.moveToNext();
    }

    return cursor;


}
public void deleteOrder(int id)

{

    SQLiteDatabase database=getWritableDatabase();

    int row=database.delete("orders","id="+id,null);



}

}
