/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package aal.entitybean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
@Table(name = "Products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByProductID", query = "SELECT p FROM Products p WHERE p.productID = :productID"),
    @NamedQuery(name = "Products.findByProductName", query = "SELECT p FROM Products p WHERE p.productName = :productName"),
    @NamedQuery(name = "Products.findByImage", query = "SELECT p FROM Products p WHERE p.image = :image"),
    @NamedQuery(name = "Products.findByDescription", query = "SELECT p FROM Products p WHERE p.description = :description"),
    @NamedQuery(name = "Products.findByQuantity", query = "SELECT p FROM Products p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "Products.findByDateOfManufacture", query = "SELECT p FROM Products p WHERE p.dateOfManufacture = :dateOfManufacture"),
    @NamedQuery(name = "Products.findByUnitPrice", query = "SELECT p FROM Products p WHERE p.unitPrice = :unitPrice"),
    @NamedQuery(name = "Products.findByRating", query = "SELECT p FROM Products p WHERE p.rating = :rating"),
    @NamedQuery(name = "Products.findByDescriptionBrief", query = "SELECT p FROM Products p WHERE p.descriptionBrief = :descriptionBrief")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ProductID")
    private String productID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ProductName")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Image")
    private String image;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "Description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DateOfManufacture")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfManufacture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UnitPrice")
    private double unitPrice;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Rating")
    private Double rating;
    @Size(max = 1073741823)
    @Column(name = "DescriptionBrief")
    private String descriptionBrief;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false)
    private Category categoryID;
    @JoinColumn(name = "BrandID", referencedColumnName = "BrandID")
    @ManyToOne(optional = false)
    private BrandVehicle brandID;
    @OneToMany(mappedBy = "productId")
    private Collection<OrderDetail> orderDetailCollection;

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

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public BrandVehicle getBrandID() {
        return brandID;
    }

    public void setBrandID(BrandVehicle brandID) {
        this.brandID = brandID;
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
        return "aal.entitybean.Products[ productID=" + productID + " ]";
    }
    
}
