package pe.edu.upc.appparkingreservation.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import pe.edu.upc.appparkingreservation.R;
import pe.edu.upc.appparkingreservation.model.ParkingPlace;
import pe.edu.upc.appparkingreservation.service.ParkingService;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap googleMap;
    private LocationManager locManager;

    CardView cardViewDetail;
    Toolbar toolbar;
    ImageView parkLotImageView;
    TextView addressDetailTextView;
    TextView pricexHourDetailTextView;

    double latitud ;
    double longitud;
    boolean firstTime;

    MarkerOptions parkingLotSelectedMaker;
    Marker markerSelectedParkingLog;

    //Services
    ParkingService parkingService = new ParkingService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        firstTime=true;


        cardViewDetail = (CardView) findViewById(R.id.cardViewDetail);
        toolbar = (Toolbar) findViewById(R.id.toolbarCard);
        parkLotImageView = (ImageView) findViewById(R.id.parkLotImageView);
        addressDetailTextView = (TextView) findViewById(R.id.addressDetailTextView);
        pricexHourDetailTextView = (TextView) findViewById(R.id.textViewPricexHourDetail);

        parkingLotSelectedMaker = new MarkerOptions();


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    public void onMapReady(GoogleMap gMap) {
        Log.d("myLog","");
        googleMap = gMap;

        if (googleMap != null) {

            Log.d("myLog","googleMap no es null");
            locManager = (LocationManager)getSystemService(LOCATION_SERVICE);

            int permissionCheck = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_FINE_LOCATION);
            Log.d("myLog","permissionCheck:" + permissionCheck);

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                //Execute location service call if user has explicitly granted ACCESS_FINE_LOCATION..
                locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 500, 0, this);
                Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                if(loc == null){
                    Log.d("myLog","locManager para NETWORK_PROVIDER es null");
                    loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if(loc == null){
                        Toast.makeText(getBaseContext(), "locManager para GPS_PROVIDER es null", Toast.LENGTH_LONG).show();
                    }
                }

                getUserLocation(loc);

                if (googleMap != null) {
                    googleMap.setMyLocationEnabled(true);
                    googleMap.getCameraPosition();


                    googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
                    {

                        @Override
                        public boolean onMarkerClick(Marker makerSelected) {

                            googleMap.moveCamera(CameraUpdateFactory.newLatLng(makerSelected.getPosition()));

                            if(markerSelectedParkingLog != null){
                                markerSelectedParkingLog.remove();
                            }

                            markerSelectedParkingLog = googleMap.addMarker(parkingLotSelectedMaker.position(makerSelected.getPosition()));

                            int IdParkingLot = Integer.valueOf(makerSelected.getTitle());

                            ParkingPlace myParkingPlace = parkingService.getParkingPlace(IdParkingLot);

                            toolbar.setTitle(myParkingPlace.getName());

                            parkLotImageView.setImageURI(Uri.parse("http://www.sanborja.com/fotos/distrito-de-san-borja.jpg"));

                            addressDetailTextView.setText( myParkingPlace.getAddress());

                            NumberFormat formatter = new DecimalFormat("#0.00");
                            pricexHourDetailTextView.setText( "S/".concat(String.valueOf(
                                    formatter.format(myParkingPlace.getPriceHour()))));

                            cardViewDetail.setVisibility(View.VISIBLE);

                            return true;
                        }

                    });
                }

                addParkingLotMakers();

            }else{
                Toast.makeText(getBaseContext(), "No se tiene permisos para acceder a la " +
                        "ubicación del dispositivo. Brindar los permisos a la aplicación.",
                        Toast.LENGTH_LONG).show();
            }

        } else{
            Toast.makeText(getBaseContext(), "No es posible Cargar el mapa.", Toast.LENGTH_LONG).show();
        }

        cardViewDetail.setVisibility(View.INVISIBLE);

    }

    private void getUserLocation(Location location) {

        Log.d("myLog","Ingresa a getUserLocation");
        if (!locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

            Toast.makeText(getBaseContext(), "Network Provider is Dissable", Toast.LENGTH_LONG).show();
        }else{
            Log.d("myLog","NETWORK PROVIDER IS ENABLE");

            if(location != null){
                latitud = location.getLatitude();
                longitud =location.getLongitude();
                LatLng myLatLng = new LatLng (latitud, longitud);

                if(firstTime){
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,15));
                    firstTime=false;
                }else{
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
                }
            }else{
                Toast.makeText(getBaseContext(), "No se puede obtener la localización", Toast.LENGTH_LONG).show();
            }
        }
    }


    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String provider) {
        Toast.makeText(getBaseContext(), "Location provider off. Please, enable your GPS.", Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Location Provider on.", Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }
    
    /**
     * coloca los Makers en el mapa de los estacionamientos
     *
     */
    private void addParkingLotMakers() {

        ArrayList<ParkingPlace> listParkingPlace = parkingService.getParkingPlaceMock();

        for(ParkingPlace parkingPlace: listParkingPlace){

            LatLng latLngParkingPlace = new LatLng(parkingPlace.getLatitude(), parkingPlace.getLongitude());
            googleMap.addMarker(new MarkerOptions().position(latLngParkingPlace)
                    .title(String.valueOf(parkingPlace.getParkingLotID()))
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking2))

            );

        }
    }
}
