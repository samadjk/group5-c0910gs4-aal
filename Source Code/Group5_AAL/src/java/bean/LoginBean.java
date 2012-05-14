/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.AdminBiz;
import biz.CustomerBiz;
import entity.Admin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import entity.Customer;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sky
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    /** Creates a new instance of LoginBean */
    Customer c;
    Admin admin;
    CustomerBiz cBiz = new CustomerBiz();
    AdminBiz adminBiz = new AdminBiz();
    String warehouseName;
    String factoryName;

    public LoginBean() {
        c = new Customer();
        admin = new Admin();
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin") != null) {
            Admin newAdmin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin");
            int permission = newAdmin.getPermission();
            warehouseName = adminBiz.getWarehouseNameByFactoryId(permission);
            factoryName = adminBiz.getFactoryNameByFactoryId(permission);
        }
    }

    public Customer getC() {
        return c;
    }

    public void setC(Customer c) {
        this.c = c;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String login() {
        if (cBiz.checkLogin(c)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssAccount", c.getUsername());
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssCheckout") != null) {
                return "checkoutCart.xhtml?faces-redirect=true";
            } else if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssRateProduct") != null) {
                String parameterIdProduct = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssRateProduct").toString();
                return "viewDetail.xhtml?id=" + parameterIdProduct + "&faces-redirect=true";
            } else {
                return "logindone.xhtml?faces-redirect=true";
            }
        }
        return "loginfail.xhtml?faces-redirect=true";
    }

    public String loginAdmin() {
        if (adminBiz.checkLoginAdmin(admin)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssAccountAdmin", admin);
            return "homePage.xhtml?faces-redirect=true";
        }
        return "loginPage.xhtml?faces-redirect=true";
    }
    public String logoutHome() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("ssAccount");
        return "loginpage.xhtml?faces-redirect=true";
    }
    public String logoutAdmin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.removeAttribute("ssAccountAdmin");
        return "loginPage.xhtml?faces-redirect=true";
    } 
 
}
