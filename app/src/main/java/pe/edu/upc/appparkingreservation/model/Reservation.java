package pe.edu.upc.appparkingreservation.model;

import java.util.Date;

/**
 * Created by CROMSYSTEM on 06/04/2016.
 */
public class Reservation {
    private int reservationID;
    private int parkingSpaceID;
    private int userID;
    private String dateReservation;
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

    public String getdateReservation() {
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

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public void setParkingSpaceID(int parkingSpaceID) {
        this.parkingSpaceID = parkingSpaceID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public void setStartParking(String startParking) {
        this.startParking = startParking;
    }

    public void setFinishParking(String finishParking) {
        this.finishParking = finishParking;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
