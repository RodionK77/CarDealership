package com.example.cardealership.ui.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cardealership.R;
import com.squareup.picasso.Picasso;

import java.time.Instant;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.CarsViewHolder> {

    private List<CarCard> items;
    Context context;
    private static int viewHolderCount;

    public SearchAdapter(List<CarCard> items){
        this.items = items;
    }

    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        int layoutIdForListCars = R.layout.rv_card_car;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutIdForListCars, parent, false);
        CarsViewHolder viewHolder = new CarsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        CarCard item = items.get(position);
        holder.name.setText(item.getBrand() + " " + item.getName());
        holder.price.setText(String.valueOf(item.getPrice()) + " ₽");
        holder.infoLeft.setText(item.getEngine() +"\n" + String.valueOf(item.getCapacity()) + " л.\n" + String.valueOf(item.getPower()) + " л. с.");
        holder.infoRight.setText(item.getDrive() +"\n" + item.getTransmission() + "\n" + item.getBody());
        Picasso.get().load(item.getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class CarsViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView price;
        public TextView infoLeft;
        public TextView infoRight;
        public ImageView image;
        public ImageView mark;
        public CardView cv;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.rv_card_name);
            price = (TextView)itemView.findViewById(R.id.rv_card_price);
            infoLeft = (TextView)itemView.findViewById(R.id.rv_card_engine_capacity_power);
            infoRight = (TextView)itemView.findViewById(R.id.rv_card_drive_transmission_body);
            image = (ImageView) itemView.findViewById(R.id.iv_rv_card);
            mark = (ImageView) itemView.findViewById(R.id.iv_bookmark);
        }

    }
}
