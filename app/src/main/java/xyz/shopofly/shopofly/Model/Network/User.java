package xyz.shopofly.shopofly.Model.Network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("access_token")
    @Expose
    private String token;

    @SerializedName("token_type")
    @Expose
    private String tokenType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public User(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }
}
