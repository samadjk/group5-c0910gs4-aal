/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Product;
import entity.ProductCart;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HieuDQ_B00385
 */
@ManagedBean
@RequestScoped
public class CartBean {

    Product productCart;
    List<ProductCart> listProductCart;
    String productNameCart;
    ProductCart productDelete;

    /** Creates a new instance of CartBean */
    public CartBean() {
        listProductCart = new ArrayList<ProductCart>();
        productNameCart = null;
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productName") != null) {
            productNameCart = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("productName").toString();
        }
    }

    public Product getProductCart() {
        return productCart;
    }

    public void setProductCart(Product productCart) {
        this.productCart = productCart;
    }

    public String getProductNameCart() {
        return productNameCart;
    }

    public void setProductNameCart(String productNameCart) {
        this.productNameCart = productNameCart;
    }

    public ProductCart getProductDelete() {
        return productDelete;
    }

    public void setProductDelete(ProductCart productDelete) {
        this.productDelete = productDelete;
    }

    public String addToCart() {
        ProductCart newProductCart = new ProductCart();
        newProductCart.setProductId(productCart.getIdPro());
        newProductCart.setProductName(productCart.getName());
        newProductCart.setProductPrice(productCart.getPrice());
        newProductCart.setProductImage(productCart.getImage());
        newProductCart.setWarehouseOfProduct(productCart.getHouse());

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cartProduct") == null) {
            newProductCart.setProductQuantity(1);
            listProductCart.add(newProductCart);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cartProduct", listProductCart);
            return "yourcart.xhtml?productName=" + newProductCart.getProductName() + "&faces-redirect=true";
        } else {
            List<ProductCart> oldListProduct = new ArrayList<ProductCart>();
            oldListProduct = (List<ProductCart>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cartProduct");
            int index = 0;
            for (int i = 0; i < oldListProduct.size(); i++) {
                ProductCart productDup = oldListProduct.get(i);
                if (newProductCart.getProductId() == productDup.getProductId()) {
                    index = 1;
                    productDup.setProductQuantity(productDup.getProductQuantity() + 1);
                    oldListProduct.set(i, productDup);
                    break;
                }
            }
            if (index == 0) {
                newProductCart.setProductQuantity(1);
                oldListProduct.add(newProductCart);
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cartProduct", oldListProduct);
            return "yourcart.xhtml?productName=" + newProductCart.getProductName() + "&faces-redirect=true";
        }
    }

    public String deleteProductInCart() {
        Map<String, String> params =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String action = params.get("deleteFromRightMenu");
        int id = productDelete.getProductId();
        List<ProductCart> oldListProduct = new ArrayList<ProductCart>();
        oldListProduct = (List<ProductCart>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cartProduct");
        for (int i = 0; i < oldListProduct.size(); i++) {
            ProductCart productDeleteDetail = oldListProduct.get(i);
            if (productDeleteDetail.getProductId() == id) {
                oldListProduct.remove(i);
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cartProduct", oldListProduct);
        if (action == null) {
            return "yourcart.xhtml?faces-redirect=true";
        } else {
            return "viewproduct.xhtml?faces-redirect=true";
        }
    }
}
