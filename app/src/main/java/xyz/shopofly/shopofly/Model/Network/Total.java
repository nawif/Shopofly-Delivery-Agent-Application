
package xyz.shopofly.shopofly.Model.Network;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Total implements Serializable
{

    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("vat")
    @Expose
    private Double vat;
    @SerializedName("total_with_vat")
    @Expose
    private Double totalWithVat;
    private final static long serialVersionUID = 6949631045724061955L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Total() {
    }

    /**
     * 
     * @param total
     * @param vat
     * @param totalWithVat
     */
    public Total(Double total, Double vat, Double totalWithVat) {
        super();
        this.total = total;
        this.vat = vat;
        this.totalWithVat = totalWithVat;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTotalWithVat() {
        return totalWithVat;
    }

    public void setTotalWithVat(Double totalWithVat) {
        this.totalWithVat = totalWithVat;
    }

}
