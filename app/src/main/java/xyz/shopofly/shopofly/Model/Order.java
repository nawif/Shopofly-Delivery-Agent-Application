package xyz.shopofly.shopofly.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {
    private String code;
    private Customer customer;
    private ArrayList<Listing> listings;
    private double subtotal;
    private double vat;
    private double total;



    public Order(String code, Customer customer, double subtotal) {
        this.code = code;
        this.customer = customer;
        this.subtotal = subtotal;
        this.vat = subtotal*0.05;
        this.total = subtotal+vat;
        listings = new ArrayList<>();
    }

    public Order(String code, Customer customer, ArrayList<Listing> listings, double subtotal, double vat, double total) {
        this(code, customer, subtotal);
        this.listings = listings;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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


    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
