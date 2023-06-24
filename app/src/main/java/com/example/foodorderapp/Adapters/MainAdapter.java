package com.example.foodorderapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DetailActivity;
import com.example.foodorderapp.Models.MainModel;
import com.example.foodorderapp.OrderActivity;
import com.example.foodorderapp.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.Viewolder>
{
    Context context;
    List<MainModel>list;

    public MainAdapter(Context context, List<MainModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainAdapter.Viewolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.sample_main,parent,false);

        return new Viewolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.Viewolder holder, int position) {

        final  MainModel model=list.get(position);
        holder.foodImage.setImageResource(model.getImage());
        holder.price.setText(model.getPrice());
        holder.name.setText(model.getName());
        holder.desc.setText(model.getDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailActivity.class);

                intent.putExtra("image",model.getImage());
                intent.putExtra("price",model.getPrice());
                intent.putExtra("desc",model.getDesc());
                intent.putExtra("name",model.getName());
                intent.putExtra("type",1);
                context.startActivity(intent);

            }
        });





    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewolder extends RecyclerView.ViewHolder {
        ImageView foodImage;
        TextView name,price,desc;
        public Viewolder(@NonNull View itemView) {
            super(itemView);

            foodImage=itemView.findViewById(R.id.foodimage);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            desc=itemView.findViewById(R.id.desc);

        }
    }
}
