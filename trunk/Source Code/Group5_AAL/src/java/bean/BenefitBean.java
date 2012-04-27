/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.BenefitBiz;
import biz.OrderBiz;
import entity.Benefit;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pe Yeu
 */
@ManagedBean
@RequestScoped
public class BenefitBean {

    List<Benefit> listB;
    BenefitBiz bBiz = new BenefitBiz();
    float minCost;
    float maxCost;
    float percent;
    float oldPercent;
    int levelUp;
    float subPriceOfCustomer;
    OrderBiz orderBiz;

    /** Creates a new instance of BenefitBean */
    public BenefitBean() {
        listB = bBiz.allBenefit();
        orderBiz = new OrderBiz();
        minCost = bBiz.getMaxCostBeforeAddNew();
        oldPercent = bBiz.gePercentBeforeAddNew();
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount") != null) {
            String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount");
            levelUp = bBiz.checkLevelUpOfCustomer(userName);
            subPriceOfCustomer = orderBiz.getAllPriceOfCustomer(userName);
        }
    }

    public float getSubPriceOfCustomer() {
        return subPriceOfCustomer;
    }

    public void setSubPriceOfCustomer(float subPriceOfCustomer) {
        this.subPriceOfCustomer = subPriceOfCustomer;
    }

    public int getLevelUp() {
        return levelUp;
    }

    public void setLevelUp(int levelUp) {
        this.levelUp = levelUp;
    }

    public float getOldPercent() {
        return oldPercent;
    }

    public void setOldPercent(float oldPercent) {
        this.oldPercent = oldPercent;
    }

    public float getMaxCost() {
        return maxCost;
    }

    public void setMaxCost(float maxCost) {
        this.maxCost = maxCost;
    }

    public float getMinCost() {
        return minCost;
    }

    public void setMinCost(float minCost) {
        this.minCost = minCost;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public List<Benefit> getListB() {
        return listB;
    }

    public void setListB(List<Benefit> listB) {
        this.listB = listB;
    }

    public String delete(int id) {
        bBiz.delete(id);
        return "benefitPage.xhtml";
    }

    public String addNewBenefit() {
        if (percent < oldPercent) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssPercent", 1);
            return "benefitAdd.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssPercent");
            bBiz.addNewBenefit(minCost, maxCost, percent);
            return "benefitPage.xhtml?faces-redirect=true";
        }
    }
}
