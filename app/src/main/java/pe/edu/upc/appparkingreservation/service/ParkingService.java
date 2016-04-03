package pe.edu.upc.appparkingreservation.service;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import pe.edu.upc.appparkingreservation.R;
import pe.edu.upc.appparkingreservation.model.ParkingPlace;

/**
 * Created by ronald on 26/03/16.
 */
public class ParkingService {

    public ArrayList<ParkingPlace> getParkingPlace(){
        //TODO : completar el servicio de obtener parking places.
        return null;
    }

    public ArrayList<ParkingPlace> getParkingPlaceMock(){

        ArrayList<ParkingPlace> listParkingPlace = new ArrayList<>();

        //Cesar Vallejo
        listParkingPlace.add(new ParkingPlace("Cesar Vallejo",1,"Cesar Vallejo",-12.014693,-77.084373,5.0));
        //Cesar Vallejo 3554
        listParkingPlace.add(new ParkingPlace("Cesar Vallejo 3554",1,"Av. Cesar Vallejo 3554",-12.015464,-77.083622,5.0));
        //Tomas Valle 18-89
        listParkingPlace.add(new ParkingPlace("Cesar Vallejo 3554",1,"Av Tomás Valle 1889",-12.013181,-77.083837,5.0));


        //surco
        listParkingPlace.add(new ParkingPlace("Cesar Vallejo 3554",1,"Av Tomás Valle 1889",-12.047884,-77.042183,5.0));

        //surco
        listParkingPlace.add(new ParkingPlace("Cesar Vallejo 3554",1,"Av Tomás Valle 1889",-12.106113,-76.964483,5.0));

        return listParkingPlace;
    }
}
