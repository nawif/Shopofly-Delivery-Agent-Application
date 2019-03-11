package xyz.shopofly.shopofly.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String code;
    private String address;
    private Customer customer;
    private ArrayList<Listing> listings;


    public Order(String code, String address, Customer customer) {
        this.code = code;
        this.address = address;
        this.customer = customer;
        listings = new ArrayList<>();
    }

    public Order(String code, String address, Customer customer, ArrayList<Listing> listings) {
        this(code, address, customer);
        this.listings = listings;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Listing> getListings() {
        return listings;
    }

    public void setListings(ArrayList<Listing> listings) {
        this.listings = listings;
    }

    public void addListing(Listing listing) {
        listings.add(listing);
    }
}
