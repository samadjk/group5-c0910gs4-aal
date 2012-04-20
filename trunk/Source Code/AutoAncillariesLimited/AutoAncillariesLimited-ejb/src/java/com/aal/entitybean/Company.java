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
@Table(name = "Company", catalog = "AutoAncillariesLimited", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByCompanyID", query = "SELECT c FROM Company c WHERE c.companyID = :companyID"),
    @NamedQuery(name = "Company.findByCompanyName", query = "SELECT c FROM Company c WHERE c.companyName = :companyName"),
    @NamedQuery(name = "Company.findByDescription", query = "SELECT c FROM Company c WHERE c.description = :description"),
    @NamedQuery(name = "Company.findByAddress", query = "SELECT c FROM Company c WHERE c.address = :address"),
    @NamedQuery(name = "Company.findByPhone", query = "SELECT c FROM Company c WHERE c.phone = :phone")})
public class Company implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CompanyID", nullable = false, length = 20)
    private String companyID;
    @Basic(optional = false)
    @Column(name = "CompanyName", nullable = false, length = 50)
    private String companyName;
    @Basic(optional = false)
    @Column(name = "Description", nullable = false, length = 500)
    private String description;
    @Basic(optional = false)
    @Column(name = "Address", nullable = false, length = 50)
    private String address;
    @Basic(optional = false)
    @Column(name = "Phone", nullable = false, length = 20)
    private String phone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Factory> factoryList;

    public Company() {
    }

    public Company(String companyID) {
        this.companyID = companyID;
    }

    public Company(String companyID, String companyName, String description, String address, String phone) {
        this.companyID = companyID;
        this.companyName = companyName;
        this.description = description;
        this.address = address;
        this.phone = phone;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Factory> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<Factory> factoryList) {
        this.factoryList = factoryList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (companyID != null ? companyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Company)) {
            return false;
        }
        Company other = (Company) object;
        if ((this.companyID == null && other.companyID != null) || (this.companyID != null && !this.companyID.equals(other.companyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aal.entitybean.Company[companyID=" + companyID + "]";
    }

}
