/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.RateBiz;
import entity.Rate;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author KuBk
 */
@ManagedBean
@RequestScoped
public class ReviewBean {

    String valueRate;
    RateBiz rateBiz;
    List<Rate> listRateByUserForProduct;
    boolean checkReview;

    /** Creates a new instance of ReviewBean */
    public ReviewBean() {
        rateBiz = new RateBiz();
        checkReview = false;
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id") != null && FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount") != null) {
            String parameterId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            int productId = Integer.parseInt(parameterId);
            String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount").toString();
            listRateByUserForProduct = rateBiz.getRateByUserForProduct(productId, userName);
            if (listRateByUserForProduct.size() > 0) {
                checkReview = true;
            } else {
                checkReview = false;
            }
        }
    }

    public boolean isCheckReview() {
        return checkReview;
    }

    public void setCheckReview(boolean checkReview) {
        this.checkReview = checkReview;
    }

    public String getValueRate() {
        return valueRate;
    }

    public void setValueRate(String valueRate) {
        this.valueRate = valueRate;
    }

    public List<Rate> getListRateByUserForProduct() {
        return listRateByUserForProduct;
    }

    public void setListRateByUserForProduct(List<Rate> listRateByUserForProduct) {
        this.listRateByUserForProduct = listRateByUserForProduct;
    }

    public String submitReviewProduct() {
        int productId = 0;
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id") != null) {
            String idP = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
            productId = Integer.parseInt(idP);
        } else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdDetail") != null) {
            String idP = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssIdDetail").toString();
            productId = Integer.parseInt(idP);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount") == null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssRateProduct", productId);
            return "loginpage.xhtml?faces-redirect=true";
        } else {
            int value = Integer.parseInt(valueRate);
            String userNameMoment = "";
            userNameMoment = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount").toString();
            rateBiz.insertIntoRate(productId, userNameMoment, value);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssRateProduct");
            return "viewDetail.xhtml?id=" + productId + "&faces-redirect=true";
        }

    }
}
