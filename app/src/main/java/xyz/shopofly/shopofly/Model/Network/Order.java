
package xyz.shopofly.shopofly.Model.Network;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Order implements Serializable
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
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("transaction")
    @Expose
    private Transaction transaction;
    private final static long serialVersionUID = -3264761101413436169L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Order() {
    }

    /**
     *  @param items
     * @param total
     * @param address
     * @param customer
     * @param orderId
     * @param transaction
     */
    public Order(List<Item> items, Total total, Address address, Customer customer, Integer orderId, Transaction transaction) {
        super();
        this.items = items;
        this.total = total;
        this.address = address;
        this.customer = customer;
        this.orderId = orderId;
        this.transaction = transaction;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
