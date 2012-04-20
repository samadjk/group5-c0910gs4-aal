/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aal.entitybean;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Sky
 */
@Entity
@Table(name = "OrderDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderDetail.findAll", query = "SELECT o FROM OrderDetail o"),
    @NamedQuery(name = "OrderDetail.findByOrderId", query = "SELECT o FROM OrderDetail o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrderDetail.findByTotal", query = "SELECT o FROM OrderDetail o WHERE o.total = :total")})
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderId")
    private Integer orderId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Total")
    private BigDecimal total;
    @JoinColumn(name = "ShipId", referencedColumnName = "ShipId")
    @ManyToOne
    private Ship shipId;
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductID")
    @ManyToOne
    private Products productId;
    @JoinColumn(name = "CustomerId", referencedColumnName = "CustomerId")
    @ManyToOne
    private Customer customerId;

    public OrderDetail() {
    }

    public OrderDetail(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Ship getShipId() {
        return shipId;
    }

    public void setShipId(Ship shipId) {
        this.shipId = shipId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aal.entitybean.OrderDetail[ orderId=" + orderId + " ]";
    }
    
}
