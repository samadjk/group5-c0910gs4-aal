/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import entity.Product;
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
public class CompareProductBean {

    Product productCompare;
    List<Product> listProductCompare;
    int countListProductCompare;
    float percentWidth;
    String productNameCompare;
    List<Product> listShowProductCompare;
    Product productDeleteCompare;

    /** Creates a new instance of CompareProductBean */
    public CompareProductBean() {
        listProductCompare = new ArrayList<Product>();
        countListProductCompare = 0;
        productCompare = null;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListCompareProduct") != null) {
            listShowProductCompare = (List<Product>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListCompareProduct");
            countListProductCompare = listShowProductCompare.size();
            percentWidth = 99 / countListProductCompare;
        }
    }

    public float getPercentWidth() {
        return percentWidth;
    }

    public void setPercentWidth(float percentWidth) {
        this.percentWidth = percentWidth;
    }

    public Product getProductCompare() {
        return productCompare;
    }

    public List<Product> getListProductCompare() {
        return listProductCompare;
    }

    public void setListProductCompare(List<Product> listProductCompare) {
        this.listProductCompare = listProductCompare;
    }

    public void setProductCompare(Product productCompare) {
        this.productCompare = productCompare;
    }

    public String getProductNameCompare() {
        return productNameCompare;
    }

    public void setProductNameCompare(String productNameCompare) {
        this.productNameCompare = productNameCompare;
    }

    public List<Product> getListShowProductCompare() {
        return listShowProductCompare;
    }

    public void setListShowProductCompare(List<Product> listShowProductCompare) {
        this.listShowProductCompare = listShowProductCompare;
    }

    public Product getProductDeleteCompare() {
        return productDeleteCompare;
    }

    public void setProductDeleteCompare(Product productDeleteCompare) {
        this.productDeleteCompare = productDeleteCompare;
    }

    public int getCountListProductCompare() {
        return countListProductCompare;
    }

    public void setCountListProductCompare(int countListProductCompare) {
        this.countListProductCompare = countListProductCompare;
    }

    public String compareProductAddList() {
        Product newProduct = productCompare;

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListCompareProduct") == null) {
            listProductCompare.add(newProduct);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssListCompareProduct", listProductCompare);
        } else {
            List<Product> listOldProductCompare = (List<Product>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListCompareProduct");
            int idProductCompare = newProduct.getIdPro();
            for (int i = 0; i < listOldProductCompare.size(); i++) {
                Product productDeleteDetail = listOldProductCompare.get(i);
                if (productDeleteDetail.getIdPro() != idProductCompare) {
                    listOldProductCompare.add(newProduct);
                    break;
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("rqExistProduct", newProduct.getName());
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssListCompareProduct", listOldProductCompare);
        }

        FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("rqProductName", newProduct.getName());
        return "viewproduct.xhtml?faces-redirect=true";
    }

    public String deleteProductInCompareList() {
        int id = productDeleteCompare.getIdPro();
        List<Product> oldListProduct = new ArrayList<Product>();
        oldListProduct = (List<Product>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListCompareProduct");
        for (int i = 0; i < oldListProduct.size(); i++) {
            Product productDeleteDetail = oldListProduct.get(i);
            if (productDeleteDetail.getIdPro() == id) {
                oldListProduct.remove(i);
            }
        }
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssListCompareProduct", oldListProduct);
        if (oldListProduct.isEmpty()) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssListCompareProduct");
        }
        return "viewproduct.xhtml?faces-redirect=true";
    }

    public String clearAllProductInCompareList() {
        List<Product> oldListProduct = new ArrayList<Product>();
        oldListProduct = (List<Product>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListCompareProduct");
        oldListProduct.clear();
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssListCompareProduct");
        return "viewproduct.xhtml?faces-redirect=true";
    }
}
