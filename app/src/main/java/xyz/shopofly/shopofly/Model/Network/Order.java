
package xyz.shopofly.shopofly.Model.Network;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order implements Serializable
{

    @SerializedName("order")
    @Expose
    private Order_ order;
    private final static long serialVersionUID = -5296690169080293914L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Order() {
    }

    /**
     * 
     * @param order
     */
    public Order(Order_ order) {
        super();
        this.order = order;
    }

    public Order_ getOrder() {
        return order;
    }

    public void setOrder(Order_ order) {
        this.order = order;
    }

}
