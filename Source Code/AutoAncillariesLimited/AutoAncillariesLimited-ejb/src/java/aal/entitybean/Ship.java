/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aal.entitybean;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "Ship")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ship.findAll", query = "SELECT s FROM Ship s"),
    @NamedQuery(name = "Ship.findByShipId", query = "SELECT s FROM Ship s WHERE s.shipId = :shipId"),
    @NamedQuery(name = "Ship.findByShipVia", query = "SELECT s FROM Ship s WHERE s.shipVia = :shipVia"),
    @NamedQuery(name = "Ship.findByShipPrice", query = "SELECT s FROM Ship s WHERE s.shipPrice = :shipPrice")})
public class Ship implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ShipId")
    private Integer shipId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "ShipVia")
    private String shipVia;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ShipPrice")
    private BigDecimal shipPrice;
    @OneToMany(mappedBy = "shipId")
    private Collection<OrderDetail> orderDetailCollection;

    public Ship() {
    }

    public Ship(Integer shipId) {
        this.shipId = shipId;
    }

    public Ship(Integer shipId, String shipVia) {
        this.shipId = shipId;
        this.shipVia = shipVia;
    }

    public Integer getShipId() {
        return shipId;
    }

    public void setShipId(Integer shipId) {
        this.shipId = shipId;
    }

    public String getShipVia() {
        return shipVia;
    }

    public void setShipVia(String shipVia) {
        this.shipVia = shipVia;
    }

    public BigDecimal getShipPrice() {
        return shipPrice;
    }

    public void setShipPrice(BigDecimal shipPrice) {
        this.shipPrice = shipPrice;
    }

    @XmlTransient
    public Collection<OrderDetail> getOrderDetailCollection() {
        return orderDetailCollection;
    }

    public void setOrderDetailCollection(Collection<OrderDetail> orderDetailCollection) {
        this.orderDetailCollection = orderDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipId != null ? shipId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ship)) {
            return false;
        }
        Ship other = (Ship) object;
        if ((this.shipId == null && other.shipId != null) || (this.shipId != null && !this.shipId.equals(other.shipId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aal.entitybean.Ship[ shipId=" + shipId + " ]";
    }
    
}
