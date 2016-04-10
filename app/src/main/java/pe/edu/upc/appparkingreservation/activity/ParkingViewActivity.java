package pe.edu.upc.appparkingreservation.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.upc.appparkingreservation.R;
import pe.edu.upc.appparkingreservation.model.ParkingAdapter;
import pe.edu.upc.appparkingreservation.model.Parking;
import pe.edu.upc.appparkingreservation.model.ParkingLot;
import pe.edu.upc.appparkingreservation.model.Reservation;
import pe.edu.upc.appparkingreservation.service.AccountService;
import pe.edu.upc.appparkingreservation.service.ParkingService;

public class ParkingViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private ArrayList<ParkingLot> parking;
    private RecyclerView mParkingRecyclerView;
    private RecyclerView.Adapter mParkingAdapter;
    private RecyclerView.LayoutManager mParkingLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_view);

        parking = new ArrayList<>();
        if (AccountService.CURRENT_USER != null) {
            ArrayList<Reservation> rest = AccountService.CURRENT_USER.getMyReservation();
            if(rest!=null){

                ArrayList<ParkingLot> currentLots = new ArrayList<>();
                for (int i = 0; i < rest.size(); i++) {
                    for (int y = 0; y < ParkingService.PARKING_LOTS.size(); y++) {
                        ParkingLot lotPAr = ParkingService.PARKING_LOTS.get(y);
                        for (int z = 0; z < lotPAr.getParkingSpace().size(); z++) {
                            if (lotPAr.getParkingSpace().get(z).getParkingSpaceID() == rest.get(i).getparkingSpaceID()) {
                                currentLots.add(lotPAr);
                            }
                        }
                    }
                }
                parking=currentLots;
            }
        }
        //initializeData();
        mParkingRecyclerView = (RecyclerView) findViewById(R.id.parkingRecyclerView);
        mParkingRecyclerView.setHasFixedSize(true);
        mParkingLayoutManager = new LinearLayoutManager(this);
        mParkingRecyclerView.setLayoutManager(mParkingLayoutManager);
        mParkingAdapter = new ParkingAdapter(parking);
        mParkingRecyclerView.setAdapter(mParkingAdapter);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_reservation);
        navigationView.setNavigationItemSelectedListener(this);
        String name = AccountService.CURRENT_USER.getName() + " " + AccountService.CURRENT_USER.getLastName();

        TextView txtNameProfile = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txtNameProfile);
        if (txtNameProfile != null) {
            txtNameProfile.setText(name.toUpperCase());
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_parkingfind) {
            startActivity(new Intent(ParkingViewActivity.this, MapsActivity.class));
        } else if (id == R.id.nav_list_parkinglot) {
            startActivity(new Intent(ParkingViewActivity.this, ParkingViewActivity.class));
        } else if (id == R.id.nav_logout) {
            startActivity(new Intent(ParkingViewActivity.this, LoginActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
   /* public void initializeData() {
        parking = new ArrayList<>();
        parking.add(new Parking("Los Portales S.A.", 11.00, "Activo","Av. República de Panamá- San Isidro","412-6489","10:30 a.m.","18:30 p.m.",Integer.toString(R.mipmap.ic_logo_los_portales)));
        parking.add(new Parking("Los Frutales", 8.50, "Inactivo","Av. Rosa Toro 465 - San Luis","474-6489","08:30 p.m.","09:30 a.m.",Integer.toString(R.mipmap.ic_logo_empty)));
    }*/

}

/*Version que se intento hacer consumiendo el servicio REST*/

//package pe.edu.upc.appparkingreservation.activity;
//
//        import android.os.AsyncTask;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.support.v7.widget.LinearLayoutManager;
//        import android.support.v7.widget.RecyclerView;
//        import android.util.Log;
//
//        import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//        import com.google.android.gms.maps.model.LatLng;
//        import com.google.android.gms.maps.model.MarkerOptions;
//
//        import java.util.ArrayList;
//
//        import pe.edu.upc.appparkingreservation.R;
//        import pe.edu.upc.appparkingreservation.model.ParkingAdapter;
//        import pe.edu.upc.appparkingreservation.model.Parking;
//        import pe.edu.upc.appparkingreservation.model.ParkingLot;
//        import pe.edu.upc.appparkingreservation.service.ParkingService;
//
//public class ParkingViewActivity extends AppCompatActivity {
//    private ArrayList<Parking> parking;
//    private RecyclerView mParkingRecyclerView;
//    private RecyclerView.Adapter mParkingAdapter;
//    private RecyclerView.LayoutManager mParkingLayoutManager;
//
//    private GetParkingTask mLotTask = null;
//
//    ArrayList<ParkingLot> listParkingLotGeneral;
//
//    //Services
//    ParkingService parkingService = new ParkingService(this);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_parking_view);
//
//        parking = new ArrayList<>();
//        initializeData();
//    }
//
//    public void initializeData() {
//
//        /*parking = new ArrayList<>();
//        parking.add(new Parking("Los Portales S.A.", 11.00, "Activo", "Av. República de Panamá- San Isidro", "412-6489", "10:30 a.m.", "18:30 p.m.", Integer.toString(R.mipmap.ic_logo_los_portales)));
//        parking.add(new Parking("Los Frutales", 8.50, "Inactivo", "Av. Rosa Toro 465 - San Luis", "474-6489", "08:30 p.m.", "09:30 a.m.", Integer.toString(R.mipmap.ic_logo_empty)));
//        */
//        addParkingLotView();
//
//        Log.d("CROM Hello: ", "addParkingLotView despues de cargar" );
//        mParkingRecyclerView = (RecyclerView) findViewById(R.id.parkingRecyclerView);
//        mParkingRecyclerView.setHasFixedSize(true);
//        mParkingLayoutManager = new LinearLayoutManager(this);
//        mParkingRecyclerView.setLayoutManager(mParkingLayoutManager);
//        mParkingAdapter = new ParkingAdapter(parking);
//        mParkingRecyclerView.setAdapter(mParkingAdapter);
//    }
//
//    /**
//     * coloca los Makers en el mapa de los estacionamientos
//     *
//     */
//    private void addParkingLotView() {
//
//        mLotTask = new GetParkingTask();
//        mLotTask.execute((Void) null);
//    }
//
//    /**
//     * permite trabajar el hilos la consulta de
//     */
//    public class GetParkingTask extends AsyncTask<Void, Void, Boolean> {
//
//        GetParkingTask() {
//        }
//
//        @Override
//        protected Boolean doInBackground(Void... params) {
//
//            listParkingLotGeneral = parkingService.getParkingLots();
//            return true;
//
//        }
//
//        @Override
//        protected void onPostExecute(final Boolean success) {
//
//            if (success) {
//                putParkingInCard(listParkingLotGeneral);
//            }
//        }
//
//        @Override
//        protected void onCancelled() {
//            mLotTask = null;
//        }
//
//        private void putParkingInCard(ArrayList<ParkingLot> listParkingLot) {
//            Log.d("CROM Hello: ", "listParkingLot.size(): " + listParkingLot.size());
//            parking = new ArrayList<>();
//            for (ParkingLot parkingLot : listParkingLot) {
//                Log.d("CROM getName: ",parkingLot.getName());
//                Log.d("CROM getAddress: ", parkingLot.getAddress());
//                parking.add(new Parking(parkingLot.getName(),
//                        11.00,
//                        "Activo",
//                        parkingLot.getAddress(),
//                        parkingLot.getLocalPhone(),
//                        parkingLot.getOpenTime() + " a.m.",
//                        parkingLot.getCloseTime() + " p.m.",
//                        Integer.toString(R.mipmap.ic_logo_los_portales)));
//            }
//            Log.d("CROM Hello: ", "FIN listParkingLot.size(): " + listParkingLot.size());
//        }
//    }
//
//
//}
