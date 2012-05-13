/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;

/**
 *
 * @author Sky
 */
public class Order {

    int orderId;
    String userName;
    String orderDate;
    float totalPrice;
    float totalPriceAfterCheck;
    String status;
    String statusDetail;
    int totalProduct;
    List<OrderDetail> listOrderDetail;
    int countSoldOut;
    int countDelivered;
    int countChecking;

    public Order() {
    }

    public int getCountChecking() {
        return countChecking;
    }

    public void setCountChecking(int countChecking) {
        this.countChecking = countChecking;
    }

    public int getCountDelivered() {
        return countDelivered;
    }

    public void setCountDelivered(int countDelivered) {
        this.countDelivered = countDelivered;
    }

    public int getCountSoldOut() {
        return countSoldOut;
    }

    public void setCountSoldOut(int countSoldOut) {
        this.countSoldOut = countSoldOut;
    }

    public List<OrderDetail> getListOrderDetail() {
        return listOrderDetail;
    }

    public void setListOrderDetail(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDetail() {
        return statusDetail;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

    public float getTotalPriceAfterCheck() {
        return totalPriceAfterCheck;
    }

    public void setTotalPriceAfterCheck(float totalPriceAfterCheck) {
        this.totalPriceAfterCheck = totalPriceAfterCheck;
    }
}
