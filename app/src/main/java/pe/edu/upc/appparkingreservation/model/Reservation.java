package pe.edu.upc.appparkingreservation.model;

import java.util.Date;

/**
 * Created by CROMSYSTEM on 06/04/2016.
 */
public class Reservation {
    private int reservationID;
    private int parkingSpaceID;
    private int userID;
    private Date dateReservation;
    private String startParking;
    private String finishParking;
    private Boolean status;

    public int getReservationID() {
        return reservationID;
    }

    public int getparkingSpaceID() {
        return parkingSpaceID;
    }

    public int getuserID() {
        return userID;
    }

    public Date getdateReservation() {
        return dateReservation;
    }

    public String getstartParking() {
        return startParking;
    }

    public String getfinishParking() {
        return finishParking;
    }

    public Boolean getstatus() {
        return status;
    }

}
