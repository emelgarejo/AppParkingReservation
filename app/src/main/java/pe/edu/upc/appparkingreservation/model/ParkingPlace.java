package pe.edu.upc.appparkingreservation.model;

/**
 * Created by Edgar Melgarejo on 07/04/2016.
 */
public class ParkingPlace {

    private  int  parkingLotID;
    private  int parkingSpaceID;
    private String shortName;
    private String status;

    public int getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(int parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public int getParkingSpaceID() {
        return parkingSpaceID;
    }

    public void setParkingSpaceID(int parkingSpaceID) {
        this.parkingSpaceID = parkingSpaceID;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ParkingPlace() {
    }

    public ParkingPlace(int  parkingLotID, int parkingSpaceID, String shortName) {
        this.parkingLotID = parkingLotID;
        this.parkingSpaceID = parkingSpaceID;
        this.shortName = shortName;
    }
}
