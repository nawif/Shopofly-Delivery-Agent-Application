
package xyz.shopofly.shopofly.Model.Network;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Address implements Serializable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("district")
    @Expose
    private String district;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("house_number")
    @Expose
    private String houseNumber;
    private final static long serialVersionUID = -1816263561576280999L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Address() {
    }

    /**
     * 
     * @param status
     * @param street
     * @param houseNumber
     * @param district
     * @param country
     * @param city
     */
    public Address(String status, String city, String country, String district, String street, String houseNumber) {
        super();
        this.status = status;
        this.city = city;
        this.country = country;
        this.district = district;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return district+" "+street+" "+houseNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

}
