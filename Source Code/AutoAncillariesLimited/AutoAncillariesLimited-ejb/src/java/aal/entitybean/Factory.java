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
@Table(name = "Factory")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Factory.findAll", query = "SELECT f FROM Factory f"),
    @NamedQuery(name = "Factory.findByFactoryID", query = "SELECT f FROM Factory f WHERE f.factoryID = :factoryID"),
    @NamedQuery(name = "Factory.findByFactoryName", query = "SELECT f FROM Factory f WHERE f.factoryName = :factoryName"),
    @NamedQuery(name = "Factory.findByDescription", query = "SELECT f FROM Factory f WHERE f.description = :description")})
public class Factory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "FactoryID")
    private String factoryID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "FactoryName")
    private String factoryName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "CompanyID", referencedColumnName = "CompanyID")
    @ManyToOne(optional = false)
    private Company companyID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "factoryID")
    private Collection<WareHouse> wareHouseCollection;

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

    public Company getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Company companyID) {
        this.companyID = companyID;
    }

    @XmlTransient
    public Collection<WareHouse> getWareHouseCollection() {
        return wareHouseCollection;
    }

    public void setWareHouseCollection(Collection<WareHouse> wareHouseCollection) {
        this.wareHouseCollection = wareHouseCollection;
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
        return "aal.entitybean.Factory[ factoryID=" + factoryID + " ]";
    }
    
}
