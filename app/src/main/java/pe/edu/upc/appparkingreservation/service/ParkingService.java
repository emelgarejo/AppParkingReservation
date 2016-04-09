package pe.edu.upc.appparkingreservation.service;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.upc.appparkingreservation.backend.BackEndRequest;
import pe.edu.upc.appparkingreservation.model.ParkingLot;
import pe.edu.upc.appparkingreservation.model.ParkingPlace;

/**
 * Created by ronald on 26/03/16.
 */
public class ParkingService {

    private Context context;

    private static final String URl_PARKING = "http://rnld1503-001-site1.btempurl.com/Parking.svc/";

    public ParkingService(Context context) {
        this.context = context;
    }


    public ArrayList<ParkingLot> getParkingLots() {

        Log.d("Hello", "ingreso a getParkingLots");

        ArrayList<ParkingLot> list = null;
        try {

            String methot = URl_PARKING + "getParkingLots/";
            //methot = String.format(methot, mEmail, mPassword);
            Log.d("URL ParkingLots: ", methot);
            BackEndRequest jsonObjReq = new BackEndRequest(this.context, methot);

            JSONArray result = jsonObjReq.getListResult();

            Log.d("Hello", "result :" + result);
            Log.d("Hello", "result.length() :" + result.length());

            if (result != null && result.length() > 0) {
                list = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jresponse = result.getJSONObject(i);
                    ParkingLot lots = adapterParkingLot(jresponse);
                    list.add(lots);
                    Log.d("Hello", "añande el elemento :" + i);
                }
            }

            Log.d("Hello", "fin llenado datos :" );


        } catch (Exception e) {
            Log.d("Hello", " entro al catch:");
            e.printStackTrace();
        }

        return list;
    }


    /**
     * metodo debe devolver los datos de un parking y si está reservado o no.
     *
     * @param id
     * @return
     */

    public ParkingLot getParkingLot(int id) {

        ParkingLot pp = null;

        try {

            Log.d("Hello","ingresa a getParkingLot");

            String methot = URl_PARKING + "getParkingLot/%s";
            methot = String.format(methot, id);
            Log.d("Hello: ", "method: " + methot);
            BackEndRequest jsonObjReq = new BackEndRequest(this.context, methot);
            JSONObject result = jsonObjReq.getSingleResult();
            Log.d("Hello: ", "result: " + result);
            if (result != null) {
                pp = adapterParkingLot(result);
                Log.d("Hello: ", "method: " + methot);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return pp;
    }

    public ArrayList<ParkingPlace> getParkingPlaceByLot(int parkingLotId) {
        ArrayList<ParkingPlace> list = null;
        try {

            String methot = URl_PARKING + "getParkingPlaceByLot/%s";
            methot = String.format(methot, parkingLotId);
            Log.d("URL ParkingLots: ", methot);
            BackEndRequest jsonObjReq = new BackEndRequest(this.context, methot);
            JSONArray result = jsonObjReq.getListResult();

            if (result != null && result.length() > 0) {
                list = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jresponse = result.getJSONObject(i);
                    ParkingPlace place = new ParkingPlace();
                    place.setParkingLotID(jresponse.getInt("parkingLotID"));
                    place.setParkingSpaceID(jresponse.getInt("parkingSpaceID"));
                    place.setShortName(jresponse.getString("shortName"));
                    place.setStatus(jresponse.getString("status"));
                    list.add(place);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public ArrayList<ParkingLot> getParkingPlaceMock() {

        ArrayList<ParkingLot> listParkingLot = new ArrayList<>();

        //Cesar Vallejo
        listParkingLot.add(new ParkingLot("Cesar Vallejo", 1, "Cesar Vallejo", -12.014693, -77.084373, 5.0));
        //Cesar Vallejo 3554
        listParkingLot.add(new ParkingLot("Cesar Vallejo 3554", 2, "Av. Cesar Vallejo 3554", -12.015464, -77.083622, 5.0));
        //Tomas Valle 18-89
        listParkingLot.add(new ParkingLot("Cesar Vallejo 3", 3, "Av Tomás marzano 1889", -12.013181, -77.083837, 5.0));

        //surco
        listParkingLot.add(new ParkingLot("Cesar Vallejo 35", 4, "Av Tomás facundo 1889", -12.047884, -77.042183, 5.0));

        //surco
        listParkingLot.add(new ParkingLot("Cesar Vallejo 355423", 5, "Av Tomás el terco 18", -12.106113, -76.964483, 5.0));

        return listParkingLot;
    }

    private ParkingLot adapterParkingLot(JSONObject jresponse) throws JSONException {
        ParkingLot lots = new ParkingLot();
        lots.setName(jresponse.getString("name"));
        lots.setAddress(jresponse.getString("address"));
        lots.setCloseTime(jresponse.getString("closeTime"));
        lots.setUrlPicture(jresponse.getString("urlPicture"));
        lots.setOpenTime(jresponse.getString("openTime"));
        lots.setLocalPhone(jresponse.getString("LocalPhone"));
        //lots.setRate(jresponse.getString("LocalPhone"));priceHour
        lots.setPriceHour(jresponse.getDouble("priceHour"));
        lots.setStatus(jresponse.getString("status"));
        lots.setDescription(jresponse.getString("description"));
        lots.setParkingLotID(jresponse.getInt("parkingLotID"));
        lots.setProviderID(jresponse.getInt("providerID"));
        lots.setDistrictId(jresponse.getInt("districtId"));
        lots.setLatitude(jresponse.getDouble("latitude"));
        lots.setLongitude(jresponse.getDouble("longitud"));
        return lots;
    }
}
