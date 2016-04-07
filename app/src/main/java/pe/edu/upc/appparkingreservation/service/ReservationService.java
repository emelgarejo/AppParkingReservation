package pe.edu.upc.appparkingreservation.service;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pe.edu.upc.appparkingreservation.backend.BackEndRequest;
import pe.edu.upc.appparkingreservation.model.Reservation;

/**
 * Created by Edgar Melgarejo on 07/04/2016.
 */
public class ReservationService {
    private Context context;

    private static final String URl_RESERVATION = "http://rnld1503-001-site1.btempurl.com/Parking.svc/";

    public ReservationService(Context context) {
        this.context = context;
    }

    public ArrayList<Reservation> getReservationByUser(String user) {
        ArrayList<Reservation> list = null;
        try {

            String methot = URl_RESERVATION + "getReservationByUser/%s";
            methot = String.format(methot, user);
            Log.d("URL ParkingLots: ", methot);
            BackEndRequest jsonObjReq = new BackEndRequest(this.context, methot);
            JSONArray result = jsonObjReq.getListResult();

            if (result != null && result.length() > 0) {
                list = new ArrayList<>();
                for (int i = 0; i < result.length(); i++) {
                    JSONObject jresponse = result.getJSONObject(i);
                    Reservation reser = new Reservation();
                    reser.setDateReservation(jresponse.getString("dateReservation"));
                    reser.setFinishParking(jresponse.getString("finishParking"));
                    reser.setParkingSpaceID(jresponse.getInt("parkingSpaceID"));
                    reser.setReservationID(jresponse.getInt("reservationID"));
                    reser.setStartParking(jresponse.getString("startParking"));
                    reser.setStatus(jresponse.getBoolean("status"));
                    list.add(reser);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    public boolean registerReservation(Reservation reservation) {

        try {
            String methot = URl_RESERVATION+ "Reservation";
            BackEndRequest jsonObjReq = new BackEndRequest(this.context, Request.Method.POST, methot);

            Map<String, String> params = new HashMap<String, String>();
            params.put("finishParking", reservation.getfinishParking());
            params.put("startParking", reservation.getstartParking());
            params.put("parkingSpaceID", Integer.toString(reservation.getparkingSpaceID()));
            params.put("userID",Integer.toString(reservation.getuserID()) );

            jsonObjReq.sendRequest(params);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
