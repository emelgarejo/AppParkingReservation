package pe.edu.upc.appparkingreservation.model;

/**
 * Created by ronald on 26/03/16.
 */
public class ParkingPlace {

    private int parkingLotID;
    private int providerID;
    private String name ;
    private String address ;
    private int districtId ;
    private String department ;
    private String description;
    private String urlPicture;
    private double longitude;
    private double latitude;
    private String LocalPhone;
    private String openTime;
    private String closeTime;
    private double priceHour;
    private String status;


    public ParkingPlace(String name, int parkingLotID, String address,  double latitude, double longitude, double priceHour) {
        this.name = name;
        this.parkingLotID = parkingLotID;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.priceHour = priceHour;
    }

    public int getParkingLotID() {
        return parkingLotID;
    }

    public void setParkingLotID(int parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public int getProviderID() {
        return providerID;
    }

    public void setProviderID(int providerID) {
        this.providerID = providerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(String urlPicture) {
        this.urlPicture = urlPicture;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getLocalPhone() {
        return LocalPhone;
    }

    public void setLocalPhone(String localPhone) {
        LocalPhone = localPhone;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public double getPriceHour() {
        return priceHour;
    }

    public void setPriceHour(double priceHour) {
        this.priceHour = priceHour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
