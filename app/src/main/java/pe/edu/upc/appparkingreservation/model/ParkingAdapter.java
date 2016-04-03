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
        TextView nameTextViewCard;
        CardView parkingCard;

        public ViewHolder(View itemView) {
            super(itemView);
            parkingCard = (CardView) itemView.findViewById(R.id.parking_card);
            nameTextViewCard = (TextView) itemView.findViewById(R.id.nameTextViewCard);
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
        holder.nameTextViewCard.setText(parking.get(position).nameParking);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.printf("Selected position: %d%n", position);
                Intent itemIntent = new Intent(view.getContext(), ItemParkingViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameParking", parking.get(position).nameParking);
                bundle.putDouble("rate", parking.get(position).rate);
                bundle.putString("status", parking.get(position).status);
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
