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
@Table(name = "Factory", catalog = "AutoAncillariesLimited", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Factory.findAll", query = "SELECT f FROM Factory f"),
    @NamedQuery(name = "Factory.findByFactoryID", query = "SELECT f FROM Factory f WHERE f.factoryID = :factoryID"),
    @NamedQuery(name = "Factory.findByFactoryName", query = "SELECT f FROM Factory f WHERE f.factoryName = :factoryName"),
    @NamedQuery(name = "Factory.findByDescription", query = "SELECT f FROM Factory f WHERE f.description = :description")})
public class Factory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FactoryID", nullable = false, length = 20)
    private String factoryID;
    @Basic(optional = false)
    @Column(name = "FactoryName", nullable = false, length = 50)
    private String factoryName;
    @Basic(optional = false)
    @Column(name = "Description", nullable = false, length = 500)
    private String description;
    @JoinColumn(name = "CompanyID", referencedColumnName = "CompanyID", nullable = false)
    @ManyToOne(optional = false)
    private Company company;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factory")
    private List<WareHouse> wareHouseList;

    public Factory() {
    }

    public Factory(String factoryID) {
        this.factoryID = factoryID;
    }

    public Factory(String factoryID, String factoryName, String description) {
        this.factoryID = factoryID;
        this.factoryName = factoryName;
        this.description = description;
    }

    public String getFactoryID() {
        return factoryID;
    }

    public void setFactoryID(String factoryID) {
        this.factoryID = factoryID;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<WareHouse> getWareHouseList() {
        return wareHouseList;
    }

    public void setWareHouseList(List<WareHouse> wareHouseList) {
        this.wareHouseList = wareHouseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (factoryID != null ? factoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factory)) {
            return false;
        }
        Factory other = (Factory) object;
        if ((this.factoryID == null && other.factoryID != null) || (this.factoryID != null && !this.factoryID.equals(other.factoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aal.entitybean.Factory[factoryID=" + factoryID + "]";
    }

}
