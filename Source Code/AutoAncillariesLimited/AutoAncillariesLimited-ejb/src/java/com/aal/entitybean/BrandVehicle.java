/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aal.entitybean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Windows7
 */
@Entity
@Table(name = "BrandVehicle", catalog = "AutoAncillariesLimited", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "BrandVehicle.findAll", query = "SELECT b FROM BrandVehicle b"),
    @NamedQuery(name = "BrandVehicle.findByBrandID", query = "SELECT b FROM BrandVehicle b WHERE b.brandID = :brandID"),
    @NamedQuery(name = "BrandVehicle.findByBrandVehiclename", query = "SELECT b FROM BrandVehicle b WHERE b.brandVehiclename = :brandVehiclename")})
public class BrandVehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BrandID", nullable = false, length = 20)
    private String brandID;
    @Basic(optional = false)
    @Column(name = "BrandVehiclename", nullable = false, length = 50)
    private String brandVehiclename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brandVehicle")
    private List<Products> productsList;

    public BrandVehicle() {
    }

    public BrandVehicle(String brandID) {
        this.brandID = brandID;
    }

    public BrandVehicle(String brandID, String brandVehiclename) {
        this.brandID = brandID;
        this.brandVehiclename = brandVehiclename;
    }

    public String getBrandID() {
        return brandID;
    }

    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }

    public String getBrandVehiclename() {
        return brandVehiclename;
    }

    public void setBrandVehiclename(String brandVehiclename) {
        this.brandVehiclename = brandVehiclename;
    }

    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (brandID != null ? brandID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BrandVehicle)) {
            return false;
        }
        BrandVehicle other = (BrandVehicle) object;
        if ((this.brandID == null && other.brandID != null) || (this.brandID != null && !this.brandID.equals(other.brandID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aal.entitybean.BrandVehicle[brandID=" + brandID + "]";
    }

}
