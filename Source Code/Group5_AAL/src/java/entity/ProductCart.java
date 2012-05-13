/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author HieuDQ_B00385
 */
public class ProductCart {
    int productId;
    String productName;
    float productPrice;
    String warehouseOfProduct;
    String productImage;
    int productQuantity;

    public ProductCart() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getWarehouseOfProduct() {
        return warehouseOfProduct;
    }

    public void setWarehouseOfProduct(String warehouseOfProduct) {
        this.warehouseOfProduct = warehouseOfProduct;
    }

    public ProductCart(int productId, String productName, float productPrice, String warehouseOfProduct, String productImage, int productQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.warehouseOfProduct = warehouseOfProduct;
        this.productImage = productImage;
        this.productQuantity = productQuantity;
    }
}
