package pe.edu.upc.appparkingreservation.model;

import java.util.ArrayList;

/**
 * Created by Edgar Melgarejo on 25/03/2016.
 */
public class Person {
    private String name;
    private String lastName;
    private String userName;
    private String password;

    private ArrayList<Reservation> myReservation;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Reservation> getMyReservation() {
        return myReservation;
    }

    public void setMyReservation(ArrayList<Reservation> myReservation) {
        this.myReservation = myReservation;
    }
}
