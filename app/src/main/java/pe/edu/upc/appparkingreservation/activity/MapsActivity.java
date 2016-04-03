package pe.edu.upc.appparkingreservation.activity;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.appparkingreservation.R;
import pe.edu.upc.appparkingreservation.model.ParkingPlace;
import pe.edu.upc.appparkingreservation.service.ParkingService;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,LocationListener {

    private GoogleMap mMap;
    private LocationManager locManager;

    double latitud ;
    double longitud;

    ParkingService parkingService = new ParkingService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Hide the status bar.

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //ActionBar actionBar = getActionBar();
        //if (actionBar!= null)
        //actionBar.hide();

        //parkingService
        //boton de UBICACION

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {

//                getUserLocation();

//            }
//        });


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
    public void onMapReady(GoogleMap googleMap) {

        Log.d("hello","hola");
       // LocationManager locManager = (LocationManager)getSystemService(LOCATION_SERVICE);
       // List<String> listaProviders = locManager.getAllProviders();


        mMap = googleMap;

        //Obtenemos una referencia al LocationManager
        locManager =
                (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Log.d("hello 2","hola");
        //Obtenemos la última posición conocida


        //Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        Log.d("hello3","hola");
        //Mostramos la última posición conocida
        //mostrarPosicion(loc);


        //lista los estacionamientos



        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION);


        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            //Execute location service call if user has explicitly granted ACCESS_FINE_LOCATION..
            //locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, locationListener);
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
            Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if(loc == null){
                Log.d("hello 5","locManager es null");
                loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            }

            getUserLocation(loc);


            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }

        }


        listPark();


    }

    private void getUserLocation(Location location) {


        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Toast.makeText(getBaseContext(), "GPS IS DISSABLE", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getBaseContext(), "GPS  ENABLE", Toast.LENGTH_LONG).show();
            if(location != null)
            {
                Toast.makeText(getBaseContext(), "latitud : " + latitud + ", Longitud : " + longitud, Toast.LENGTH_LONG).show();
                latitud = location.getLatitude();
                longitud =location.getLatitude();

                LatLng myLatLng = new LatLng (latitud, longitud);

                mMap.addMarker(new MarkerOptions().position(myLatLng).title("you are here!"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,16));

            }else{

                Toast.makeText(getBaseContext(), "LOCATION IS NULL", Toast.LENGTH_LONG).show();
            }
        }
    }



    public void onLocationChanged(Location location) {
        Toast.makeText(getBaseContext(), "On location Change", Toast.LENGTH_LONG).show();
        //getUserLocation(location);

    }

    public void onProviderDisabled(String provider) {
        //lblEstado.setText("Provider OFF");
        Toast.makeText(getBaseContext(), "Provider OFF", Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String provider) {
        //lblEstado.setText("Provider ON");
        Toast.makeText(getBaseContext(), "Provider ON", Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        //lblEstado.setText("Provider Status: " + status);
        Toast.makeText(getBaseContext(), "Provider status"  + status, Toast.LENGTH_LONG).show();
    }



    /**
     * Obtiene la lista de los estacionamientos cercanos
     *
     */
    private void listPark() {

/*
        LatLng parking = new LatLng(-12.046884, -77.042783);
        //BitmapDescriptor bmd = new BitmapDescriptor();

        mMap.addMarker(new MarkerOptions().position(parking)
                .title("parking here")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking))
        );

        LatLng parking2 = new LatLng(-12.047884, -77.042183);
        //BitmapDescriptor bmd = new BitmapDescriptor();

        mMap.addMarker(new MarkerOptions().position(parking2)
                .title("parking here")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking))
        );

        LatLng parking3 = new LatLng(-12.106113, -76.964483 );
                mMap.addMarker(new MarkerOptions().position(parking3)
                        .title("parking here")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking))
        );
*/


        ArrayList<ParkingPlace> listParkingPlace = parkingService.getParkingPlaceMock();

        Log.d("hello","Ingresa a listar los estacionamientos ");
        for(ParkingPlace parkingPlace: listParkingPlace){

            Log.d("hello","crea 1 estacionamiento");
            LatLng latLngParkingPlace = new LatLng(parkingPlace.getLatitude(), parkingPlace.getLongitude());
            Log.d("hello","parkingPlace.getLatitude()" + parkingPlace.getLatitude() + ": parkingPlace.getLongitude:" + parkingPlace.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLngParkingPlace)
                    .title(parkingPlace.getName())
                    .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking))
            );

        }



    }


    private void getUserLocation() {

        //latitud = -12.046374;
        //longitud =-77.042793;

        latitud =-12.104183;
        longitud =-76.963529;

        Toast.makeText(getBaseContext(), "latitud : " + latitud + "," + longitud, Toast.LENGTH_LONG).show();
        // Add a marker in Sydney and move the camera
        LatLng myLatLng = new LatLng (latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(myLatLng).title("you are here!"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,17));

    }
}
