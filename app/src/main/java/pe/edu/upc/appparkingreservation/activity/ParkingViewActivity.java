package pe.edu.upc.appparkingreservation.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.upc.appparkingreservation.R;
import pe.edu.upc.appparkingreservation.model.ParkingAdapter;
import pe.edu.upc.appparkingreservation.model.Parking;
import pe.edu.upc.appparkingreservation.model.ParkingLot;
import pe.edu.upc.appparkingreservation.model.Reservation;
import pe.edu.upc.appparkingreservation.service.AccountService;
import pe.edu.upc.appparkingreservation.service.ParkingService;

public class ParkingViewActivity extends AppCompatActivity {
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
