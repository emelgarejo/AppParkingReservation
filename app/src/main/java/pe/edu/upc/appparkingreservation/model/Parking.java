package pe.edu.upc.appparkingreservation.model;

/**
 * Created by Heavyarms on 27/03/2016.
 */
public class Parking {
    String name;
    String description;
    String address;
    String phone;
    String openTime;
    String closeTime;
    double rate;
    String status;
    String logoUrl;
    double totalPrice;

    public Parking() {
    }

    public Parking(String nameParking, double rate, String status, String address, String phone, String openTime, String closeTime, String logoUrl) {
        this.name = nameParking;
        this.rate = rate;
        this.status = status;
        this.address = address;
        this.phone = phone;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.logoUrl = logoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameParking) {
        this.name = nameParking;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
