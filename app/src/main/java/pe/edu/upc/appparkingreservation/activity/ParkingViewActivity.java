package pe.edu.upc.appparkingreservation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.upc.appparkingreservation.R;
import pe.edu.upc.appparkingreservation.model.ParkingAdapter;
import pe.edu.upc.appparkingreservation.model.Parking;

public class ParkingViewActivity extends AppCompatActivity {
    private ArrayList<Parking> parking;
    private RecyclerView mParkingRecyclerView;
    private RecyclerView.Adapter mParkingAdapter;
    private RecyclerView.LayoutManager mParkingLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_view);

        parking = new ArrayList<>();
        initializeData();
        mParkingRecyclerView = (RecyclerView) findViewById(R.id.parkingRecyclerView);
        mParkingRecyclerView.setHasFixedSize(true);
        mParkingLayoutManager = new LinearLayoutManager(this);
        mParkingRecyclerView.setLayoutManager(mParkingLayoutManager);
        mParkingAdapter = new ParkingAdapter(parking);
        mParkingRecyclerView.setAdapter(mParkingAdapter);
    }

    public void initializeData() {
        parking = new ArrayList<>();
        parking.add(new Parking("Los Portales S.A.", 11.00, "Activo","Av. República de Panamá- San Isidro","412-6489","10:30 a.m.","18:30 p.m.",Integer.toString(R.mipmap.ic_logo_los_portales)));
        parking.add(new Parking("Los Frutales", 8.50, "Inactivo","Av. Rosa Toro 465 - San Luis","474-6489","08:30 p.m.","09:30 a.m.",Integer.toString(R.mipmap.ic_logo_empty)));
    }

}
