/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aal.entitybean;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Sky
 */
@Entity
@Table(name = "BrandVehicle")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BrandVehicle.findAll", query = "SELECT b FROM BrandVehicle b"),
    @NamedQuery(name = "BrandVehicle.findByBrandID", query = "SELECT b FROM BrandVehicle b WHERE b.brandID = :brandID"),
    @NamedQuery(name = "BrandVehicle.findByBrandVehiclename", query = "SELECT b FROM BrandVehicle b WHERE b.brandVehiclename = :brandVehiclename")})
public class BrandVehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "BrandID")
    private String brandID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "BrandVehiclename")
    private String brandVehiclename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "brandID")
    private Collection<Products> productsCollection;

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

    @XmlTransient
    public Collection<Products> getProductsCollection() {
        return productsCollection;
    }

    public void setProductsCollection(Collection<Products> productsCollection) {
        this.productsCollection = productsCollection;
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
        return "aal.entitybean.BrandVehicle[ brandID=" + brandID + " ]";
    }
    
}
