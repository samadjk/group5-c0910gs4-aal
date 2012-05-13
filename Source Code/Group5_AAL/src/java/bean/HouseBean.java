/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.ProductBiz;
import entity.Product;
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
public class HouseBean {

    ProductBiz pBiz = new ProductBiz();
    List<Product> listSameHouse;
    String idCate;
    String name;
    String image;
    String price;
    String des;
    String idHouse;
    String quantity;
    String quantityO;
    String sale;
    String rate;
    Product product;

    /** Creates a new instance of HouseBean */
    public HouseBean() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idS") != null) {
            String idS = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idS");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idSa", idS);
            int idSame = Integer.parseInt(idS);
            listSameHouse = pBiz.productByHouse(idSame);
        }
    }

    public String addProduct() {
        try {
            int idC = Integer.parseInt(idCate);
            float prices = Float.parseFloat(price);
            image = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionImage");
            pBiz.addProduct(idC, name, prices, des, image);
            return "./productAddHouse.xhtml";
        } catch (Exception e) {
            return "./error.xhtml";
        }
    }

    public String addProductHouse() {
        try {
            int idC = Integer.parseInt(idCate);
            float prices = Float.parseFloat(price);
            image = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionImage");
            int idH = Integer.parseInt(idHouse);
            int qua = Integer.parseInt(quantity);
            int quaO = Integer.parseInt(quantityO);
            int rating = Integer.parseInt(rate);
            float saleOff = Float.parseFloat(sale);
            pBiz.addProduct(idC, name, prices, des, image);
            pBiz.addProductHouse(idH, qua, quaO, saleOff, rating);
            return "./productPage.xhtml";
        } catch (Exception e) {
            return "./error.xhtml";
        }
    }

    public String delProduct(int id) {
        try {
            pBiz.delProduct(id);
            return "./productPage.xhtml";
        } catch (Exception e) {
            return "./error.xhtml";
        }
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getIdCate() {
        return idCate;
    }

    public void setIdCate(String idCate) {
        this.idCate = idCate;
    }

    public List<Product> getListSameHouse() {
        return listSameHouse;
    }

    public void setListSameHouse(List<Product> listSameHouse) {
        this.listSameHouse = listSameHouse;
    }

    public String getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(String idHouse) {
        this.idHouse = idHouse;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getQuantityO() {
        return quantityO;
    }

    public void setQuantityO(String quantityO) {
        this.quantityO = quantityO;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
