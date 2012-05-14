/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.CustomerBiz;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entity.Customer;
import entity.ProductReport;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author HieuDQ_B00385
 */
@ManagedBean
@RequestScoped
public class CustomerBean {

    /** Creates a new instance of CustomerBean */
    Customer c;
    CustomerBiz cBiz = new CustomerBiz();
    List<Customer> listC;
    List<Customer> listSearch;
    List<Customer> listCAdmin;
    List<ProductReport> listOrder;

    public CustomerBean() {
        c = new Customer();
        listCAdmin = cBiz.adminDisplayCustomer();

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean") != null) {
            LoginBean login = (LoginBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loginBean");
            String usernameLogin = login.getC().getUsername();
            listC = cBiz.displayCustomer(usernameLogin);
        }
        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username") != null) {

            String usernameGet = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("username");
            listOrder = cBiz.viewOrderCustomer(usernameGet);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", usernameGet);
        }

    }

    public String register() {
        if (!cBiz.checkUsernameExist(username)) {
            try {
                if (pass.equals(confirmPass)) {
                    int ageInt = Integer.parseInt(age);
                    int phoneInt = Integer.parseInt(phone);
                    if (cBiz.register(username, pass, fullname, ageInt, email, address, phoneInt)) {
                        return "registerdone.xhtml";
                    }
                }
            } catch (Exception e) {
                return "registerfaild.xhtml";
            }
        }
        return "registerfaild.xhtml";
    }

    public String deleteCustomer(String username) {
        cBiz.deleteCustomer(username);
        return "customer.xhtml";
    }

    public String deleteOrderByCustomer(int orderId) {
        cBiz.deleteOrderOfCustomer(orderId);
        String us = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username");
        return "viewOrderCustomer.xhtml?username='" + us + "'";
    }

    public String searchCustomer() {
        listSearch = cBiz.searchCustomer(usernameS);
        return "searchCustomer.xhtml";
    }
    String username;
    String pass;
    String fullname;
    String address;
    String email;
    String phone;
    String age;
    String confirmPass;
    String usernameS;

    public String getUsernameS() {
        return usernameS;
    }

    public void setUsernameS(String usernameS) {
        this.usernameS = usernameS;
    }

    public List<ProductReport> getListOrder() {
        return listOrder;
    }

    public List<Customer> getListSearch() {
        return listSearch;
    }

    public void setListSearch(List<Customer> listSearch) {
        this.listSearch = listSearch;
    }

    public void setListOrder(List<ProductReport> listOrder) {
        this.listOrder = listOrder;
    }

    public List<Customer> getListCAdmin() {
        return listCAdmin;
    }

    public void setListCAdmin(List<Customer> listCAdmin) {
        this.listCAdmin = listCAdmin;
    }

    public List<Customer> getListC() {
        return listC;
    }

    public void setListC(List<Customer> listC) {
        this.listC = listC;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
