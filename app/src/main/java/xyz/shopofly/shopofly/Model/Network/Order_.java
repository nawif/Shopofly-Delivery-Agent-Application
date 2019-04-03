
package xyz.shopofly.shopofly.Model.Network;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order_ implements Serializable
{

    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    @SerializedName("total")
    @Expose
    private Total total;
    @SerializedName("address")
    @Expose
    private Address address;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    private final static long serialVersionUID = -4620496365853181329L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Order_() {
    }

    /**
     * 
     * @param total
     * @param address
     * @param items
     * @param customer
     */
    public Order_(List<Item> items, Total total, Address address, Customer customer) {
        super();
        this.items = items;
        this.total = total;
        this.address = address;
        this.customer = customer;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Total getTotal() {
        return total;
    }

    public void setTotal(Total total) {
        this.total = total;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
