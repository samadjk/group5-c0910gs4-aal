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
 * @author Admin
 */
@ManagedBean
@RequestScoped
public class ProductBean {

    /** Creates a new instance of ProductBean */
    ProductBiz pBiz = new ProductBiz();
    List<Product> listP;
    List<Product> listByCate;
    List<Product> listByHouse;
    List<Product> listDetail;
    List<Product> listSearch;
    List<Product> bestSeller;
    List<Product> productHot;
    List<Product> productSpecial;
    List<Integer> listProductCount;
    List<Product> listSameHouse;
    List<Product> listPrice;
    List<Integer> listI;
    int count;
    int count1;
    int count2;
    String txtSearch;
    int countProduct;
    int id1;
    int id2;
    int id3;

    public ProductBean() {
        listP = pBiz.topProduct();
        bestSeller = pBiz.bestSeller();
        productHot = pBiz.productHot();
        productSpecial = pBiz.productSpecial();
        count = pBiz.countProductByPrice(100, 200);
        count1 = pBiz.countProductByPrice(200, 300);
        count2 = pBiz.countProductByPrice(300, 400);
        //listByCate = pBiz.productByCategory(2);

        // All RequestParameter
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idC") != null) {
            String idC = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idC");
            int idCate = Integer.parseInt(idC);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssIdCategory", idCate);
            listByCate = pBiz.productByCategory(idCate);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idH") != null) {
            String idH = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idH");
            int idHouse = Integer.parseInt(idH);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssIdHouse", idHouse);
            listByHouse = pBiz.productByHouse(idHouse);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id") != null) {
            String idP = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            int idPro = Integer.parseInt(idP);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssIdDetail", idPro);
            listDetail = pBiz.productDetail(idPro);
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("priceMin") != null) {
            if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("priceMax") != null) {
                String min = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("priceMin");
                String max = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("priceMax");
                float minP = Float.parseFloat(min);
                float maxP = Float.parseFloat(max);
                listPrice = pBiz.productByPrice(minP, maxP);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssPriceMin", minP);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssPriceMax", maxP);
            }
        }

        // All SessionParameter
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdCategory") != null) {
            String idC = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdCategory").toString();
            int idCate = Integer.parseInt(idC);
            listByCate = pBiz.productByCategory(idCate);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdHouse") != null) {
            String idH = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdHouse").toString();
            int idHouse = Integer.parseInt(idH);
            listByHouse = pBiz.productByHouse(idHouse);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdDetail") != null) {
            String idP = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdDetail").toString();
            int idPro = Integer.parseInt(idP);
            listDetail = pBiz.productDetail(idPro);
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssPriceMin") != null) {
            Float min = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssPriceMin");
            Float max = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssPriceMax");

            listPrice = pBiz.productByPrice(min, max);
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListSearch") != null) {
            listSearch = (List<Product>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListSearch");
        }


    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public String searchProduct() {
        listSearch = pBiz.searchProduct(txtSearch);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssListSearch", listSearch);
        return "./searchResult.xhtml";
    }

    public List<Product> getProductSpecial() {
        return productSpecial;
    }

    public void setProductSpecial(List<Product> productSpecial) {
        this.productSpecial = productSpecial;
    }

    public int getCount1() {
        return count1;
    }

    public void setCount1(int count1) {
        this.count1 = count1;
    }

    public int getCount2() {
        return count2;
    }

    public void setCount2(int count2) {
        this.count2 = count2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getListPrice() {
        return listPrice;
    }

    public void setListPrice(List<Product> listPrice) {
        this.listPrice = listPrice;
    }

    public List<Product> getListDetail() {
        return listDetail;
    }

    public void setListDetail(List<Product> listDetail) {
        this.listDetail = listDetail;
    }

    public List<Product> getListP() {
        return listP;
    }

    public void setListP(List<Product> listP) {
        this.listP = listP;
    }

    public List<Product> getListByCate() {
        return listByCate;
    }

    public void setListByCate(List<Product> listByCate) {
        this.listByCate = listByCate;
    }

    public List<Product> getListByHouse() {
        return listByHouse;
    }

    public void setListByHouse(List<Product> listByHouse) {
        this.listByHouse = listByHouse;
    }

    public List<Product> getListSearch() {
        return listSearch;
    }

    public void setListSearch(List<Product> listSearch) {
        this.listSearch = listSearch;
    }

    public String getTxtSearch() {
        return txtSearch;
    }

    public void setTxtSearch(String txtSearch) {
        this.txtSearch = txtSearch;
    }

    public List<Product> getListSameHouse() {
        return listSameHouse;
    }

    public void setListSameHouse(List<Product> listSameHouse) {
        this.listSameHouse = listSameHouse;
    }

    public List<Product> getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(List<Product> bestSeller) {
        this.bestSeller = bestSeller;
    }

    public List<Integer> getListProductCount() {
        return listProductCount;
    }

    public void setListProductCount(List<Integer> listProductCount) {
        this.listProductCount = listProductCount;
    }

    public List<Product> getProductHot() {
        return productHot;
    }

    public void setProductHot(List<Product> productHot) {
        this.productHot = productHot;
    }

    public List<Integer> getListI() {
        return listI;
    }

    public void setListI(List<Integer> listI) {
        this.listI = listI;
    }
}
