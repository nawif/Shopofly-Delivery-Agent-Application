package xyz.shopofly.shopofly.Model.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;

    @SerializedName("password")
    @Expose
    private String password;


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String mobileNumber, String password) {
        this.mobileNumber = mobileNumber;
        this.password = password;
    }



}
