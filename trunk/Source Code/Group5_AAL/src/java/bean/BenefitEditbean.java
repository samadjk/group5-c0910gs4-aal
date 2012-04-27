/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.BenefitBiz;
import entity.Benefit;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pe Yeu
 */
@ManagedBean
@RequestScoped
public class BenefitEditbean {

    Benefit b;
    BenefitBiz bBiz = new BenefitBiz();

    /** Creates a new instance of BenefitEditbean */
    public BenefitEditbean() {
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idB") != null) {
            String idPro = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("idB");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idB", idPro);
            int idB = Integer.parseInt(idPro);
            b = bBiz.detailBenefit(idB);
        } else {
            String idPro = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idB");
            int idProduct = Integer.parseInt(idPro);
            b = bBiz.detailBenefit(idProduct);
        }
    }

    public Benefit getB() {
        return b;
    }

    public void setB(Benefit b) {
        this.b = b;
    }
}
