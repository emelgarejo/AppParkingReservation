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
    public static ArrayList<ParkingLot> PARKING_LOTS;
    private static final String URl_PARKING = "http://rnld1503-001-site1.btempurl.com/Parking.svc/";

    public ParkingService(Context context) {
        this.context = context;
    }


    public ArrayList<ParkingLot> getParkingLots() {

        Log.d("Hello", "ingreso a getParkingLots");

        ArrayList<ParkingLot> list = null;
        try {

            String methot = URl_PARKING + "getParkingLots/";

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
                    ParkingService.PARKING_LOTS = list;
                }
            }

            Log.d("Hello", "fin llenado datos :");


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


    private ParkingLot adapterParkingLot(JSONObject jresponse) throws JSONException {
        ParkingLot lots = new ParkingLot();
        lots.setName(jresponse.getString("name"));
        lots.setAddress(jresponse.getString("address"));
        lots.setCloseTime(jresponse.getString("closeTime"));
        lots.setUrlPicture(jresponse.getString("urlPicture"));
        lots.setOpenTime(jresponse.getString("openTime"));
        lots.setLocalPhone(jresponse.getString("LocalPhone"));
        lots.setPriceHour(jresponse.getDouble("priceHour"));
        lots.setStatus("No Disponible");
        if(jresponse.getString("status").toLowerCase()=="true"){

            lots.setStatus("Disponible");
        }
        lots.setDescription(jresponse.getString("description"));
        lots.setParkingLotID(jresponse.getInt("parkingLotID"));
        lots.setProviderID(jresponse.getInt("providerID"));
        lots.setDistrictId(jresponse.getInt("districtId"));
        lots.setLatitude(jresponse.getDouble("latitude"));
        lots.setLongitude(jresponse.getDouble("longitud"));

        JSONArray array = jresponse.getJSONArray("lstParkingSpace");
        if (array != null && array.length() > 0) {
            lots.setParkingSpace(new ArrayList<ParkingPlace>());
            for(int i=0;i<array.length();i++){
                JSONObject obj = array.getJSONObject(i);
                ParkingPlace place = new ParkingPlace();
                place.setParkingSpaceID(obj.getInt("parkingSpaceID"));
                lots.getParkingSpace().add(place);
            }
        }
        return lots;
    }
}
