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
        return getTokenType()+" "+token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String getTokenType() {
        if (tokenType == null || tokenType.length() == 0) {
            return tokenType;
        }
        return tokenType.substring(0, 1).toUpperCase() + tokenType.substring(1);
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public User(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }
}
