/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.OrderBiz;
import entity.Admin;
import entity.Order;
import entity.OrderDetail;
import entity.ProductCart;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Sky
 */
@ManagedBean
@RequestScoped
public class OrderBean {

    float percentDiscount;
    float totalPriceInAllOrder;
    OrderBiz orderBiz;
    int levelOfCustomer;
    List<Order> listOrderByUser;
    List<OrderDetail> listOrderDetailByOrderId;
    List<OrderDetail> listOrderDetailByOrderIdSession;
    List<Order> listOrderOfWarehouse;
    int index;

    /** Creates a new instance of OrderBean */
    public OrderBean() {
        orderBiz = new OrderBiz();
        percentDiscount = 0;
        index = 0;
        listOrderByUser = new ArrayList<Order>();
        listOrderOfWarehouse = new ArrayList<Order>();
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount") != null) {
            String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount");
            listOrderByUser = orderBiz.getAllOrderByUser2(userName);
            totalPriceInAllOrder = orderBiz.getSumTotalPriceOfOrder(userName);
            percentDiscount = orderBiz.getPercentOfBenefitFromLevel(userName);
            levelOfCustomer = orderBiz.getBenefitIdFromLevel(userName);
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin") != null) {
            Admin newAdmin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin");
            int permission = newAdmin.getPermission();
            if (permission == 0) {
                listOrderOfWarehouse = orderBiz.getAllOrderOfAdmin();
            } else {
                listOrderOfWarehouse = orderBiz.getAllOrderOfWarehouse(permission);
            }
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("orderId") != null) {
            int orderId = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("orderId").toString());
            index = 1;
            listOrderDetailByOrderId = orderBiz.getAllOrderDetailByOrderId(orderId);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssListOrderDetail", listOrderDetailByOrderId);
            listOrderDetailByOrderIdSession = (List<OrderDetail>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListOrderDetail");
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListOrderDetail") != null) {
            listOrderDetailByOrderIdSession = (List<OrderDetail>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListOrderDetail");
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<OrderDetail> getListOrderDetailByOrderIdSession() {
        return listOrderDetailByOrderIdSession;
    }

    public void setListOrderDetailByOrderIdSession(List<OrderDetail> listOrderDetailByOrderIdSession) {
        this.listOrderDetailByOrderIdSession = listOrderDetailByOrderIdSession;
    }

    public float getPercentDiscount() {
        return percentDiscount;
    }

    public void setPercentDiscount(float percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public float getTotalPriceInAllOrder() {
        return totalPriceInAllOrder;
    }

    public void setTotalPriceInAllOrder(float totalPriceInAllOrder) {
        this.totalPriceInAllOrder = totalPriceInAllOrder;
    }

    public int getLevelOfCustomer() {
        return levelOfCustomer;
    }

    public void setLevelOfCustomer(int levelOfCustomer) {
        this.levelOfCustomer = levelOfCustomer;
    }

    public List<Order> getListOrderByUser() {
        return listOrderByUser;
    }

    public void setListOrderByUser(List<Order> listOrderByUser) {
        this.listOrderByUser = listOrderByUser;
    }

    public List<OrderDetail> getListOrderDetailByOrderId() {
        return listOrderDetailByOrderId;
    }

    public void setListOrderDetailByOrderId(List<OrderDetail> listOrderDetailByOrderId) {
        this.listOrderDetailByOrderId = listOrderDetailByOrderId;
    }

    public List<Order> getListOrderOfWarehouse() {
        return listOrderOfWarehouse;
    }

    public void setListOrderOfWarehouse(List<Order> listOrderOfWarehouse) {
        this.listOrderOfWarehouse = listOrderOfWarehouse;
    }

    public String checkOutCart() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount") == null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssCheckout", true);
            return "loginpage.xhtml?faces-redirect=true";
        } else {
            return "checkoutCart.xhtml?faces-redirect=true";
        }
    }

    public String checkOutCart2() {
        return "loginpage.xhtml?faces-redirect=true";
    }
    public String continueCart() {
        return "viewproduct.xhtml?faces-redirect=true";
    }

    public String makePayment() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount") == null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssCheckout", true);
            return "loginpage.xhtml?faces-redirect=true";
        } else {
            String userName = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccount").toString();
            float totalPrice = 0;
            List<ProductCart> listProductCart = null;
            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cartProduct") != null) {
                listProductCart = (List<ProductCart>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("cartProduct");
                for (ProductCart productCart : listProductCart) {
                    totalPrice = totalPrice + (productCart.getProductQuantity() * productCart.getProductPrice());
                    if (percentDiscount != 0) {
                        totalPrice = totalPrice - (totalPrice * percentDiscount / 100);
                    }
                }
            }
            String statusOrder = "Paid";
            int orderId = orderBiz.insertIntoOrderAndGetOrderId(userName, levelOfCustomer, totalPrice, statusOrder);
            for (ProductCart productCart : listProductCart) {
                int productId = productCart.getProductId();
                int quantity = productCart.getProductQuantity();
                orderBiz.insertIntoOrderDetail(orderId, productId, quantity);
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("cartProduct");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssCheckout");
            return "checkoutSuccess.xhtml?faces-redirect=true";
        }
    }
}
