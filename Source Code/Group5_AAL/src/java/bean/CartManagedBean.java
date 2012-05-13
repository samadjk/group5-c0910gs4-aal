/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.ProductCart;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author HieuDQ_B00385
 */
@ManagedBean
@RequestScoped
public class CartManagedBean {

    List<ProductCart> listProductCart;
    int countCart;
    double totalPrice;

    public int getCountCart() {
        return countCart;
    }

    public void setCountCart(int countCart) {
        this.countCart = countCart;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ProductCart> getListProductCart() {
        return listProductCart;
    }

    public void setListProductCart(List<ProductCart> listProductCart) {
        this.listProductCart = listProductCart;
    }

    /** Creates a new instance of CartManagedBean */
    public CartManagedBean() {
        countCart = 0;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cartProduct") != null) {
            listProductCart = (List<ProductCart>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cartProduct");
            countCart = listProductCart.size();
            for (ProductCart productCart : listProductCart) {
                totalPrice = totalPrice + (productCart.getProductQuantity() * productCart.getProductPrice());
            }
        }
    }
}
