package pe.edu.upc.appparkingreservation.model;

/**
 * Created by Heavyarms on 27/03/2016.
 */
public class Parking {
    String nameParking;
    String address;
    String phone;
    String openTime;
    String closeTime;
    double rate;
    String status;
    String logoUrl;
    double totalPrice;

    public Parking(String nameParking, double rate, String status, String address, String phone, String openTime, String closeTime, String logoUrl){
        this.nameParking = nameParking;
        this.rate = rate;
        this.status = status;
        this.address = address;
        this.phone = phone;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.logoUrl = logoUrl;
    }

    /*public String getNameParking() {
        return nameParking;
    }

    public void setNameParking(String nameParking) {
        this.nameParking = nameParking;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }*/
}
