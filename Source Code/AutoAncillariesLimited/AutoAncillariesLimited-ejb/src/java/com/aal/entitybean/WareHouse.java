/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aal.entitybean;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Windows7
 */
@Entity
@Table(name = "WareHouse", catalog = "AutoAncillariesLimited", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "WareHouse.findAll", query = "SELECT w FROM WareHouse w"),
    @NamedQuery(name = "WareHouse.findByWareHouseID", query = "SELECT w FROM WareHouse w WHERE w.wareHouseID = :wareHouseID"),
    @NamedQuery(name = "WareHouse.findByWareHouseName", query = "SELECT w FROM WareHouse w WHERE w.wareHouseName = :wareHouseName"),
    @NamedQuery(name = "WareHouse.findByLocation", query = "SELECT w FROM WareHouse w WHERE w.location = :location")})
public class WareHouse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WareHouseID", nullable = false, length = 20)
    private String wareHouseID;
    @Basic(optional = false)
    @Column(name = "WareHouseName", nullable = false, length = 50)
    private String wareHouseName;
    @Basic(optional = false)
    @Column(name = "Location", nullable = false, length = 50)
    private String location;
    @OneToMany(mappedBy = "wareHouse")
    private List<Category> categoryList;
    @JoinColumn(name = "FactoryID", referencedColumnName = "FactoryID", nullable = false)
    @ManyToOne(optional = false)
    private Factory factory;

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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
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
        return "com.aal.entitybean.WareHouse[wareHouseID=" + wareHouseID + "]";
    }

}
