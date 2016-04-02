package pe.edu.upc.appparkingreservation.activity;

import android.app.ActionBar;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
import pe.edu.upc.appparkingreservation.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    private LocationManager locManager;
    double latitud ;
    double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        //int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        //decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        //ActionBar actionBar = getActionBar();
        //if (actionBar!= null)
        //actionBar.hide();

        //boton de UBICACION

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();

                getUserLocation();

            }
        });
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
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        //Obtenemos una referencia al LocationManager
        locManager =
                (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        //Obtenemos la última posición conocida
        //Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        //Mostramos la última posición conocida
        //mostrarPosicion(loc);


        //lista los estacionamientos

        getUserLocation();
        listPark();

    }

    private void getUserLocation(Location location) {
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Toast.makeText(getBaseContext(), "GPS IS DISSABLE", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getBaseContext(), "GPS  ENABLE", Toast.LENGTH_LONG).show();
            if(location != null)
            {
                Toast.makeText(getBaseContext(), "latitud : " + latitud + "," + longitud, Toast.LENGTH_LONG).show();
                //latitud = location.getLatitude();
                //longitud =location.getLatitude();

                latitud = -12.046374;
                longitud =-77.042793;

                Toast.makeText(getBaseContext(), "latitud : " + latitud + "," + longitud, Toast.LENGTH_LONG).show();
                // Add a marker in Sydney and move the camera
                //LatLng myLatLng = new LatLng (-12.046374, -77.042793);
                LatLng myLatLng = new LatLng (latitud, longitud);
                mMap.addMarker(new MarkerOptions().position(myLatLng).title("you are here!"));
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,18));
            }else{

                Toast.makeText(getBaseContext(), "LOCATION IS NULL", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void onLocationChanged(Location location) {
        Toast.makeText(getBaseContext(), "On location Change", Toast.LENGTH_LONG).show();
        getUserLocation(location);

    }

    public void onProviderDisabled(String provider){
        //lblEstado.setText("Provider OFF");
        Toast.makeText(getBaseContext(), "Provider OFF", Toast.LENGTH_LONG).show();
    }

    public void onProviderEnabled(String provider){
        //lblEstado.setText("Provider ON");
        Toast.makeText(getBaseContext(), "Provider ON", Toast.LENGTH_LONG).show();
    }

    public void onStatusChanged(String provider, int status, Bundle extras){
        //lblEstado.setText("Provider Status: " + status);
        Toast.makeText(getBaseContext(), "Provider status", Toast.LENGTH_LONG).show();
    }


    /**
     * Obtiene la lista de los estacionamientos cercanos
     *
     */
    private void listPark() {
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

        //-12.106113, -76.964483
                LatLng parking3 = new LatLng(-12.106113, -76.964483 );
                mMap.addMarker(new MarkerOptions().position(parking3)
                        .title("parking here")
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking))
        );

        //-12.106037, -76.963202
        LatLng parking4 = new LatLng(-12.106037, -76.963202 );
        mMap.addMarker(new MarkerOptions().position(parking4)
                .title("parking here")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_parking))
        );
    }

    private void validaGPS(){
        if (!locManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            Toast.makeText(getBaseContext(), "GPS IS DISSABLE", Toast.LENGTH_LONG).show();
        }else{
            //Toast.makeText(getBaseContext(), "GPS IS ENABLE", Toast.LENGTH_LONG).show();

            LocationListener locListener = new LocationListener() {

                public void onLocationChanged(Location location) {

                    getUserLocation(location);

                }

                public void onProviderDisabled(String provider){
                    //lblEstado.setText("Provider OFF");
                }

                public void onProviderEnabled(String provider){
                    //lblEstado.setText("Provider ON");
                }

                public void onStatusChanged(String provider, int status, Bundle extras){
                    //lblEstado.setText("Provider Status: " + status);
                }
            };
        }
    }

    private void getUserLocation() {

        //latitud = -12.046374;
        //longitud =-77.042793;

        latitud =-12.104183;
        longitud =-76.963529;

        Toast.makeText(getBaseContext(), "latitud : " + latitud + "," + longitud, Toast.LENGTH_LONG).show();
        // Add a marker in Sydney and move the camera
        //LatLng myLatLng = new LatLng (-12.046374, -77.042793);
        LatLng myLatLng = new LatLng (latitud, longitud);
        mMap.addMarker(new MarkerOptions().position(myLatLng).title("you are here!"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLatLng,18));

    }
}
