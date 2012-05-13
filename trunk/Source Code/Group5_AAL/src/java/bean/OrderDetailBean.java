/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.OrderBiz;
import biz.OrderDetailBiz;
import entity.Admin;
import entity.OrderDetail;
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
public class OrderDetailBean {

    OrderBiz orderBiz = new OrderBiz();
    OrderDetailBiz orderDetailBiz = new OrderDetailBiz();
    List<OrderDetail> listOrderDetailByOrderId;
    int permissionAdmin;

    /** Creates a new instance of OrderDetailBean */
    public OrderDetailBean() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin") != null) {
            Admin admin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin");
            permissionAdmin = admin.getPermission();
        }
    }

    public List<OrderDetail> getListOrderDetailByOrderId() {
        return listOrderDetailByOrderId;
    }

    public void setListOrderDetailByOrderId(List<OrderDetail> listOrderDetailByOrderId) {
        this.listOrderDetailByOrderId = listOrderDetailByOrderId;
    }

    public int getPermissionAdmin() {
        return permissionAdmin;
    }

    public void setPermissionAdmin(int permissionAdmin) {
        this.permissionAdmin = permissionAdmin;
    }

    public String saveStatusOfOrderDetail() {
        int permission = -1;
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListOrderDetail") != null) {
            listOrderDetailByOrderId = (List<OrderDetail>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListOrderDetail");

            if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin") != null) {
                Admin admin = (Admin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssAccountAdmin");
                permission = admin.getPermission();

            }
            int orderIdReal = 0;
            float totalAllPriceOfOrder = 0;
            for (OrderDetail orderDetail : listOrderDetailByOrderId) {

                int orderId = orderDetail.getOrderId();
                orderIdReal = orderId;
                int productId = orderDetail.getProductId();
                int quantityAccept = orderDetail.getQuantityAccept();
                String status = orderDetail.getStatus();

                // Update New Status
                orderDetailBiz.updateStatusForOrderDetail(status, quantityAccept, orderId, productId);

                // Set Quantity in order For Warehouse
                orderDetailBiz.setQuantityForProductWareHouse(quantityAccept, permission, productId);

                int recentLevelOfCustomer = orderBiz.getRecentLevelOfCustomerByOrderId(orderId);
                float percentDiscount = orderBiz.getPercentByLevelOfCustomer(recentLevelOfCustomer);
                float priceDiv = 0;
                priceDiv = totalAllPriceOfOrder + orderDetail.getProductPrice() * quantityAccept;
                totalAllPriceOfOrder = priceDiv - (priceDiv * percentDiscount / 100);
                orderDetailBiz.divAndAddPriceInOrder(totalAllPriceOfOrder, orderId);

                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssVitualList", "false");
            }
            String userName = orderBiz.getUserNameByOrderId(orderIdReal);
            orderDetailBiz.getTotalAllPriceAndSetLevelForCustomer(userName);
            return "viewOrderCustomer.xhtml?saveStatus=true&faces-redirect=true";
        }
        return "error.xhtml?id=&faces-redirect=true";
    }

    public String updateStatusAndQuantity() {
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListOrderDetail") != null) {
            listOrderDetailByOrderId = (List<OrderDetail>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListOrderDetail");
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssVitualList", "true");
            for (OrderDetail orderDetail : listOrderDetailByOrderId) {
                if (orderDetail.getQuantity() < orderDetail.getQuantityAccept()) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssErrorQuantity", true);
                    return "viewOrderDetail.xhtml?orderId=" + orderDetail.getOrderId();
                } else if (orderDetail.getQuantity() == orderDetail.getQuantityAccept()) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssErrorQuantity");
                    orderDetail.setStatus("DELIVERED");
                } else {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssErrorQuantity");
                    orderDetail.setStatus("SOLD OUT");
                }
            }
        }
        return "viewOrderDetail.xhtml";
    }
}
