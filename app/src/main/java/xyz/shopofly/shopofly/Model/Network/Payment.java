package xyz.shopofly.shopofly.Model.Network;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payment implements Serializable
{

    @SerializedName("card_hash")
    @Expose
    private String cardHash;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    private final static long serialVersionUID = 675404861636093633L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Payment() {
    }

    /**
     *
     * @param cardHash
     * @param orderId
     */
    public Payment(String cardHash, Integer orderId) {
        super();
        this.cardHash = cardHash;
        this.orderId = orderId;
    }

    public String getCardHash() {
        return cardHash;
    }

    public void setCardHash(String cardHash) {
        this.cardHash = cardHash;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}