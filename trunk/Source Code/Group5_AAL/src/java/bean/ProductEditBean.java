/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.ProductBiz;
import entity.Product;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class ProductEditBean {

    ProductBiz pBiz = new ProductBiz();
    Product product;
    String idCate;
    String idHouse;

    /** Creates a new instance of ProductEditBean */
    public ProductEditBean() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idP") != null) {
            String idPro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idP");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idProduct", idPro);
            int idProduct = Integer.parseInt(idPro);
            product = pBiz.productDetailSingle(idProduct);
        } else {
            String idPro = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idProduct");
            int idProduct = Integer.parseInt(idPro);
            product = pBiz.productDetailSingle(idProduct);
        }
    }

    public String editProduct(String image) {
        try {
            String images = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionImage");
            if (images == null) {
                product.setImage(image);
            } else {
                product.setImage(images);
            }
            pBiz.editProduct(product);
            return "./productPage.xhtml";
        } catch (Exception e) {
            return "./error.xhtml";
        }
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }

    public String getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(String idHouse) {
        this.idHouse = idHouse;
    }
}
