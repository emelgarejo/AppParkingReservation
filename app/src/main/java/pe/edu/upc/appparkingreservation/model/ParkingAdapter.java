package pe.edu.upc.appparkingreservation.model;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import pe.edu.upc.appparkingreservation.activity.ItemParkingViewActivity;
import pe.edu.upc.appparkingreservation.model.Parking;
import pe.edu.upc.appparkingreservation.R;
//import pe.edu.upc.appparkingreservation.activities.CatalogActivity;
//import pe.edu.upc.appparkingreservation.activities.ItemActivity;

import java.util.ArrayList;

/**
 * Created by Heavyarms on 28/03/2016.
 */
public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ViewHolder> {

    private ArrayList<Parking> parking;

    public ParkingAdapter(ArrayList<Parking> parking) {
        this.parking = parking;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView addressTextView;
        TextView statusTextView;
        ImageView logoImageView;
        CardView parkingCard;

        public ViewHolder(View itemView) {
            super(itemView);
            parkingCard = (CardView) itemView.findViewById(R.id.parking_card);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            addressTextView = (TextView) itemView.findViewById(R.id.addressTextView);
            statusTextView = (TextView) itemView.findViewById(R.id.statusTextView);
            logoImageView = (ImageView) itemView.findViewById(R.id.logoImageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parking_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nameTextView.setText(parking.get(position).nameParking);
        holder.addressTextView.setText(parking.get(position).address);
        holder.statusTextView.setText(parking.get(position).status);
        holder.logoImageView.setImageResource(Integer.parseInt(parking.get(position).logoUrl));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.printf("Selected position: %d%n", position);
                Intent itemIntent = new Intent(view.getContext(), ItemParkingViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameParking", parking.get(position).nameParking);
                bundle.putDouble("rate", parking.get(position).rate);
                bundle.putString("status", parking.get(position).status);
                bundle.putString("address", parking.get(position).address);
                bundle.putString("phone", parking.get(position).phone);
                bundle.putString("openTime", parking.get(position).openTime);
                bundle.putString("closeTime", parking.get(position).closeTime);
                bundle.putString("logoUrl", parking.get(position).logoUrl);
                itemIntent.putExtras(bundle);
                view.getContext().startActivity(itemIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return parking.size();
    } /*Por que?*/

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
