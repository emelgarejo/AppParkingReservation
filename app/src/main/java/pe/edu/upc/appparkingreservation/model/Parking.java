package pe.edu.upc.appparkingreservation.model;

/**
 * Created by Heavyarms on 27/03/2016.
 */
public class Parking {
    String nameParking;
    double rate;
    String status;

    public Parking(String nameParking, double rate, String status){
        this.nameParking = nameParking;
        this.rate = rate;
        this.status = status;
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
