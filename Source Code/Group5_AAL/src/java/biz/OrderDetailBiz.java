/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import connection.GetConnection;
import entity.Order;
import entity.OrderDetail;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author KuBk
 */
public class OrderDetailBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public boolean updateStatusForOrderDetail(String status, int quantityAccept, int orderId, int productId) {
        try {
            String sql = "{call updateStatusForOrderDetail(?,?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, status);
            cs.setInt(2, quantityAccept);
            cs.setInt(3, orderId);
            cs.setInt(4, productId);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean setQuantityForProductWareHouse(int quantityAccept, int permission, int productId) {
        try {
            String sql = "{call setQuantityForProductWareHouse(?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, quantityAccept);
            cs.setInt(2, permission);
            cs.setInt(3, productId);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean divPriceInOrder(float priceDiv, int orderId) {
        try {
            String sql = "{call divPriceInOrder(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setFloat(1, priceDiv);
            cs.setInt(2, orderId);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean getTotalAllPriceAndSetLevelForCustomer(String userName) {
        try {
            String sql = "{call getTotalAllPriceAndSetLevelForCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addPriceInOrder(float priceDiv, int orderId) {
        try {
            String sql = "{call addPriceInOrder(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setFloat(1, priceDiv);
            cs.setInt(2, orderId);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean divAndAddPriceInOrder(float price, int orderId) {
        try {
            String sql = "{call divAndAddPriceInOrder(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setFloat(1, price);
            cs.setInt(2, orderId);
            cs.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getStatusOfOrderDetail(int orderId, int productId) {
        try {
            String status = "";
            ResultSet rsGetStatusOfOrderDetail;
            String sql = "{call getOrderDetailByOrderIdProductId(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            cs.setInt(2, productId);
            rsGetStatusOfOrderDetail = cs.executeQuery();
            while (rsGetStatusOfOrderDetail.next()) {
                status = rsGetStatusOfOrderDetail.getString("STATUS");
            }
            return status;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public int countStatusSoldOutByOrderId(int orderId) {
        try {
            int countStatusSoldOut = 0;
            ResultSet rsCountStatusSoldOutByOrderId;
            String sql = "{call countStatusSoldOutByOrderId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsCountStatusSoldOutByOrderId = cs.executeQuery();
            while (rsCountStatusSoldOutByOrderId.next()) {
                countStatusSoldOut = rsCountStatusSoldOutByOrderId.getInt("COUNT_SOLD_OUT");
            }
            return countStatusSoldOut;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int countStatusDeliveredByOrderId(int orderId) {
        try {
            int countStatusSoldOut = 0;
            ResultSet rsCountStatusDeliveredByOrderId;
            String sql = "{call countStatusDeliveredByOrderId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsCountStatusDeliveredByOrderId = cs.executeQuery();
            while (rsCountStatusDeliveredByOrderId.next()) {
                countStatusSoldOut = rsCountStatusDeliveredByOrderId.getInt("COUNT_DELIVERED");
            }
            return countStatusSoldOut;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getCheckingOfOrderDetail(int orderId) {
        try {
            int countStatusSoldOut = 0;
            ResultSet rsGetCheckingOfOrderDetail;
            String sql = "{call getCheckingOfOrderDetail(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsGetCheckingOfOrderDetail = cs.executeQuery();
            while (rsGetCheckingOfOrderDetail.next()) {
                countStatusSoldOut = rsGetCheckingOfOrderDetail.getInt("COUNT_CHECKING");
            }
            return countStatusSoldOut;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public String checkDeleviredStatus(int orderId) {
        try {
            String strTest = "";
            ResultSet rsCheckDeleviredStatus;
            String sql = "{call checkDeleviredStatus(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsCheckDeleviredStatus = cs.executeQuery();
            while (rsCheckDeleviredStatus.next()) {
                strTest = rsCheckDeleviredStatus.getString("TEST");
            }
            return strTest;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
