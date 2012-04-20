/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aal.entitybean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Windows7
 */
@Entity
@Table(name = "Products", catalog = "AutoAncillariesLimited", schema = "dbo")
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByProductID", query = "SELECT p FROM Products p WHERE p.productID = :productID"),
    @NamedQuery(name = "Products.findByProductName", query = "SELECT p FROM Products p WHERE p.productName = :productName"),
    @NamedQuery(name = "Products.findByImage", query = "SELECT p FROM Products p WHERE p.image = :image"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description"),
    @NamedQuery(name = "Products.findByQuantity", query = "SELECT p FROM Products p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Products.findByDateOfManufacture", query = "SELECT p FROM Products p WHERE p.dateOfManufacture = :dateOfManufacture"),
    @NamedQuery(name = "Products.findByUnitPrice", query = "SELECT p FROM Products p WHERE p.unitPrice = :unitPrice"),
    @NamedQuery(name = "Products.findByRating", query = "SELECT p FROM Products p WHERE p.rating = :rating")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ProductID", nullable = false, length = 20)
    private String productID;
    @Basic(optional = false)
    @Column(name = "ProductName", nullable = false, length = 50)
    private String productName;
    @Basic(optional = false)
    @Column(name = "Image", nullable = false, length = 100)
    private String image;
    @Basic(optional = false)
    @Column(name = "Description", nullable = false, length = 500)
    private String description;
    @Basic(optional = false)
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "DateOfManufacture", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfManufacture;
    @Basic(optional = false)
    @Column(name = "UnitPrice", nullable = false)
    private double unitPrice;
    @Column(name = "Rating", precision = 15)
    private Double rating;
    @Lob
    @Column(name = "DescriptionBrief", length = 1073741823)
    private String descriptionBrief;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID", nullable = false)
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "BrandID", referencedColumnName = "BrandID", nullable = false)
    @ManyToOne(optional = false)
    private BrandVehicle brandVehicle;

    public Products() {
    }

    public Products(String productID) {
        this.productID = productID;
    }

    public Products(String productID, String productName, String image, String description, int quantity, Date dateOfManufacture, double unitPrice) {
        this.productID = productID;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.dateOfManufacture = dateOfManufacture;
        this.unitPrice = unitPrice;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getDescriptionBrief() {
        return descriptionBrief;
    }

    public void setDescriptionBrief(String descriptionBrief) {
        this.descriptionBrief = descriptionBrief;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BrandVehicle getBrandVehicle() {
        return brandVehicle;
    }

    public void setBrandVehicle(BrandVehicle brandVehicle) {
        this.brandVehicle = brandVehicle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productID == null && other.productID != null) || (this.productID != null && !this.productID.equals(other.productID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aal.entitybean.Products[productID=" + productID + "]";
    }

}
