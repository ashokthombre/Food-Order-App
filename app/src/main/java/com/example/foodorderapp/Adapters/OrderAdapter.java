package com.example.foodorderapp.Adapters;

import static android.content.Intent.getIntent;
import static androidx.core.content.ContextCompat.startActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DBeHelper;
import com.example.foodorderapp.DetailActivity;
import com.example.foodorderapp.Models.OrderModel;
import com.example.foodorderapp.OrderActivity;
import com.example.foodorderapp.R;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
Context context;
List<OrderModel>orderModels;

ProgressDialog progressDialog;

    public OrderAdapter(Context context, List<OrderModel> orderModels) {
        this.context = context;
        this.orderModels = orderModels;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_sample,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {

         final OrderModel model=orderModels.get(holder.getAdapterPosition());

         holder.foodname.setText(model.getSoldItemName());
         holder.orderPrice.setText(model.getPrice());
         holder.orderNo.setText(model.getOrderNumber());
         holder.orderImage.setImageResource(model.getOrderImage());

         holder.itemView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent intent=new Intent(context, DetailActivity.class);

                 intent.putExtra("id",model.getOrderNumber());
                 intent.putExtra("type",2);

                 context.startActivity(intent);


             }
         });

         holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View v) {

                 progressDialog=new ProgressDialog(context);
                 progressDialog.setTitle("Delete.");
                 progressDialog.setMessage("Deleting..");

                 AlertDialog.Builder builder=new AlertDialog.Builder(context);

                 builder.setTitle("Delete");
                 builder.setMessage("You Want to delete ?");
                 builder.setIcon(R.drawable.baseline_delete_24);

                 builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                            progressDialog.show();
                         DBeHelper dBeHelper=new DBeHelper(context);

                         dBeHelper.deleteOrder(Integer.parseInt(model.getOrderNumber()));
                         Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                         progressDialog.dismiss();
                         orderModels.remove(holder.getAdapterPosition());
                         notifyItemRemoved(holder.getAdapterPosition());








                     }
                 });
                 builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                         dialog.cancel();
                     }
                 });

                 builder.create();
                 builder.show();


                 return true;
             }
         });
    }

    @Override
    public int getItemCount() {
        return orderModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView orderImage;
        TextView foodname,orderNo,orderPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderImage=itemView.findViewById(R.id.orderFoodImage);
            foodname=itemView.findViewById(R.id.foodname);
            orderNo=itemView.findViewById(R.id.orderNo);
            orderPrice=itemView.findViewById(R.id.orderPrice);
        }
    }
}
