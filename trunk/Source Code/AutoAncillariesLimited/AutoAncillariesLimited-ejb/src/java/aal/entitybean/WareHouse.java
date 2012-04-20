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
@Table(name = "WareHouse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WareHouse.findAll", query = "SELECT w FROM WareHouse w"),
    @NamedQuery(name = "WareHouse.findByWareHouseID", query = "SELECT w FROM WareHouse w WHERE w.wareHouseID = :wareHouseID"),
    @NamedQuery(name = "WareHouse.findByWareHouseName", query = "SELECT w FROM WareHouse w WHERE w.wareHouseName = :wareHouseName"),
    @NamedQuery(name = "WareHouse.findByLocation", query = "SELECT w FROM WareHouse w WHERE w.location = :location")})
public class WareHouse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "WareHouseID")
    private String wareHouseID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "WareHouseName")
    private String wareHouseName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Location")
    private String location;
    @OneToMany(mappedBy = "wareHouseID")
    private Collection<Category> categoryCollection;
    @JoinColumn(name = "FactoryID", referencedColumnName = "FactoryID")
    @ManyToOne(optional = false)
    private Factory factoryID;

    public WareHouse() {
    }

    public WareHouse(String wareHouseID) {
        this.wareHouseID = wareHouseID;
    }

    public WareHouse(String wareHouseID, String wareHouseName, String location) {
        this.wareHouseID = wareHouseID;
        this.wareHouseName = wareHouseName;
        this.location = location;
    }

    public String getWareHouseID() {
        return wareHouseID;
    }

    public void setWareHouseID(String wareHouseID) {
        this.wareHouseID = wareHouseID;
    }

    public String getWareHouseName() {
        return wareHouseName;
    }

    public void setWareHouseName(String wareHouseName) {
        this.wareHouseName = wareHouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlTransient
    public Collection<Category> getCategoryCollection() {
        return categoryCollection;
    }

    public void setCategoryCollection(Collection<Category> categoryCollection) {
        this.categoryCollection = categoryCollection;
    }

    public Factory getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(Factory factoryID) {
        this.factoryID = factoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wareHouseID != null ? wareHouseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WareHouse)) {
            return false;
        }
        WareHouse other = (WareHouse) object;
        if ((this.wareHouseID == null && other.wareHouseID != null) || (this.wareHouseID != null && !this.wareHouseID.equals(other.wareHouseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aal.entitybean.WareHouse[ wareHouseID=" + wareHouseID + " ]";
    }
    
}
