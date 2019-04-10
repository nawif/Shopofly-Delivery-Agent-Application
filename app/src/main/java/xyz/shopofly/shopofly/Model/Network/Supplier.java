
package xyz.shopofly.shopofly.Model.Network;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Supplier implements Serializable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("supplierName")
    @Expose
    private String supplierName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    private final static long serialVersionUID = -8093873427214733688L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Supplier() {
    }

    /**
     * 
     * @param id
     * @param supplierName
     * @param createdAt
     * @param description
     */
    public Supplier(Integer id, String supplierName, String description, String createdAt) {
        super();
        this.id = id;
        this.supplierName = supplierName;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
