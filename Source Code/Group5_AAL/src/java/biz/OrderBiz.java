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
 * @author HieuDQ_B00385
 */
public class OrderBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public float getSumTotalPriceOfOrder(String userName) {
        try {
            float totalPriceOrder = 0;
            ResultSet rsSumTotalPriceOfOrder;
            String sql = "{call sumTotalPriceOfOrder(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            rsSumTotalPriceOfOrder = cs.executeQuery();
            while (rsSumTotalPriceOfOrder.next()) {
                totalPriceOrder = rsSumTotalPriceOfOrder.getFloat("TOTAL_ALL_PRICE");
            }
            return totalPriceOrder;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public float getPercentOfBenefit(float sumTotalPrice) {
        try {
            float percentDiscount = 0;
            ResultSet rsPercentOfBenefit;
            String sql = "{call getPercentOfBenefit(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setFloat(1, sumTotalPrice);
            rsPercentOfBenefit = cs.executeQuery();
            while (rsPercentOfBenefit.next()) {
                percentDiscount = rsPercentOfBenefit.getFloat("PERCENT");
            }
            return percentDiscount;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public float getPercentOfBenefitFromLevel(String userName) {
        try {
            float percentDiscount = 0;
            ResultSet rsPercentOfBenefitFromLevel;
            String sql = "{call getPercentOfBenefitFromLevel(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            rsPercentOfBenefitFromLevel = cs.executeQuery();
            while (rsPercentOfBenefitFromLevel.next()) {
                percentDiscount = rsPercentOfBenefitFromLevel.getFloat("PERCENT");
            }
            return percentDiscount;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int getBenefitIdFromLevel(String userName) {
        try {
            int benefitId = 0;
            ResultSet rsPercentOfBenefitFromLevel;
            String sql = "{call getPercentOfBenefitFromLevel(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            rsPercentOfBenefitFromLevel = cs.executeQuery();
            while (rsPercentOfBenefitFromLevel.next()) {
                benefitId = rsPercentOfBenefitFromLevel.getInt("BENEFIT_ID");
            }
            return benefitId;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public float getPercentByLevelOfCustomer(int level) {
        try {
            float percent = 0;
            ResultSet rsGetPercentByLevelOfCustomer;
            String sql = "{call getAllBenefitByLevelOfCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, level);
            rsGetPercentByLevelOfCustomer = cs.executeQuery();
            while (rsGetPercentByLevelOfCustomer.next()) {
                percent = rsGetPercentByLevelOfCustomer.getFloat("PERCENT");
            }
            return percent;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int insertIntoOrderAndGetOrderId(String userName, int recentLevel, float totalPrice, String status) {
        try {
            int orderId = 0;
            String sql = "{call insertIntoOrder(?,?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            cs.setInt(2, recentLevel);
            cs.setFloat(3, totalPrice);
            cs.setString(4, status);
            if (cs.executeUpdate() > 0) {
                ResultSet rsGetOrderIdNew;
                String sql2 = "{call getOrderIdNew()}";
                CallableStatement cs2 = con.prepareCall(sql2);
                rsGetOrderIdNew = cs2.executeQuery();
                while (rsGetOrderIdNew.next()) {
                    orderId = rsGetOrderIdNew.getInt("ORDER_ID_NEW");
                }
            }
            return orderId;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public void insertIntoOrderDetail(int orderId, int productId, int quantity) {
        try {
            String sql = "{call insertIntoOrderDetail(?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            cs.setInt(2, productId);
            cs.setInt(3, quantity);
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Order> getAllOrderByUser(String userName) {
        List<Order> listGetAllOrderByUser = new ArrayList<Order>();
        try {
            Order o;
            ResultSet rsGetCategory;
            String sql = "{call getAllOrderByUser(?)}";
            DateFormat dfDateUp = new SimpleDateFormat("dd-MM-yyyy");
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                o = new Order();
                o.setOrderId(rsGetCategory.getInt("ORDER_ID"));
                o.setTotalPrice(rsGetCategory.getFloat("TOTAL_PRICE"));
                o.setTotalProduct(rsGetCategory.getInt("NUM_OF_PRODUCT"));
                o.setStatus(rsGetCategory.getString("STATUS"));
                o.setOrderDate(dfDateUp.format(rsGetCategory.getDate("ORDER_DATE")));

                listGetAllOrderByUser.add(o);
            }
            return listGetAllOrderByUser;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public float getTotalPriceOfOrder(int orderId) {
        try {
            float totalPrice = 0;
            ResultSet rsGetTotalPriceOfOrder;
            String sql = "{call getOrderByOrderId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsGetTotalPriceOfOrder = cs.executeQuery();
            while (rsGetTotalPriceOfOrder.next()) {
                totalPrice = rsGetTotalPriceOfOrder.getFloat("TOTAL_PRICE");
            }
            return totalPrice;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public String getUserNameByOrderId(int orderId) {
        try {
            String userName = "";
            ResultSet rsGetTotalPriceOfOrder;
            String sql = "{call getOrderByOrderId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsGetTotalPriceOfOrder = cs.executeQuery();
            while (rsGetTotalPriceOfOrder.next()) {
                userName = rsGetTotalPriceOfOrder.getString("USERNAME");
            }
            return userName;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public int getRecentLevelOfCustomerByOrderId(int orderId) {
        try {
            int level = 0;
            ResultSet rsGetTotalPriceOfOrder;
            String sql = "{call getOrderByOrderId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsGetTotalPriceOfOrder = cs.executeQuery();
            while (rsGetTotalPriceOfOrder.next()) {
                level = rsGetTotalPriceOfOrder.getInt("RECENT_LEVEL");
            }
            return level;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public float getTotalPriceAfterCheckOfOrder(int orderId) {
        try {
            float totalPrice = 0;
            ResultSet rsGetTotalPriceOfOrder;
            String sql = "{call getOrderByOrderId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsGetTotalPriceOfOrder = cs.executeQuery();
            while (rsGetTotalPriceOfOrder.next()) {
                totalPrice = rsGetTotalPriceOfOrder.getFloat("TOTAL_PRICE_AFTER_CHECK");
            }
            return totalPrice;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public List<Order> getAllOrderByUser2(String userName) {
        List<Order> listGetAllOrderByUser = new ArrayList<Order>();

        try {
            Order o;
            ResultSet rsGetCategory;
            String sql = "{call getAllOrderByUser(?)}";
            DateFormat dfDateUp = new SimpleDateFormat("dd-MM-yyyy");
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                o = new Order();
                o.setOrderId(rsGetCategory.getInt("ORDER_ID"));
                o.setTotalPrice(rsGetCategory.getFloat("TOTAL_PRICE"));
                o.setTotalPriceAfterCheck(rsGetCategory.getFloat("TOTAL_PRICE_AFTER_CHECK"));
                o.setTotalProduct(rsGetCategory.getInt("NUM_OF_PRODUCT"));
                o.setStatus(rsGetCategory.getString("STATUS"));
                o.setOrderDate(dfDateUp.format(rsGetCategory.getDate("ORDER_DATE")));

                //OderDetail

                OrderDetail od;
                ResultSet rsGetCategory2;
                String sql2 = "{call getAllOrderDetailByOrderId(?)}";
                CallableStatement cs2 = con.prepareCall(sql2);
                cs2.setInt(1, rsGetCategory.getInt("ORDER_ID"));
                rsGetCategory2 = cs2.executeQuery();
                List<OrderDetail> listGetAllOrderDetailByOrderId = new ArrayList<OrderDetail>();
                while (rsGetCategory2.next()) {
                    od = new OrderDetail();
                    od.setOrderId(rsGetCategory2.getInt("ORDER_ID"));
                    od.setProductId(rsGetCategory2.getInt("PRODUCT_ID"));
                    od.setProductImage(rsGetCategory2.getString("IMAGES"));
                    od.setProductName(rsGetCategory2.getString("PRODUCT_NAME"));
                    od.setProductPrice(rsGetCategory2.getFloat("PRICE"));
                    od.setQuantity(rsGetCategory2.getInt("QUANTITY"));
                    od.setStatus(rsGetCategory2.getString("STATUS"));
                    od.setQuantityAccept(rsGetCategory2.getInt("QUANTITY_ACCEPT"));
                    listGetAllOrderDetailByOrderId.add(od);
                }
                o.setListOrderDetail(listGetAllOrderDetailByOrderId);
                listGetAllOrderByUser.add(o);
            }
            return listGetAllOrderByUser;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Order> getAllOrderOfWarehouse(int factoryId) {
        List<Order> listGetAllOrderByUser = new ArrayList<Order>();
        OrderDetailBiz orderDetailBiz = new OrderDetailBiz();
        try {
            ResultSet rsGetCategory;
            String sql = "{call checkProductOfOrderByFactoryId(?)}";
            DateFormat dfDateUp = new SimpleDateFormat("dd-MM-yyyy");
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, factoryId);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                mtResultSetOfOrder(rsGetCategory, dfDateUp, orderDetailBiz, listGetAllOrderByUser);
            }
            return listGetAllOrderByUser;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Order> getAllOrderOfAdmin() {
        List<Order> listGetAllOrderByUser = new ArrayList<Order>();
        OrderDetailBiz orderDetailBiz = new OrderDetailBiz();
        try {
            Order o;
            ResultSet rsGetCategory;
            String sql = "{call getAllOrderForAdmin()}";
            DateFormat dfDateUp = new SimpleDateFormat("dd-MM-yyyy");
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                mtResultSetOfOrder(rsGetCategory, dfDateUp, orderDetailBiz, listGetAllOrderByUser);
            }
            return listGetAllOrderByUser;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<OrderDetail> getAllOrderDetailByOrderId(int orderId) {
        List<OrderDetail> listGetAllOrderDetailByOrderId = new ArrayList<OrderDetail>();
        try {
            OrderDetail o;
            ResultSet rsGetCategory;
            String sql = "{call getAllOrderDetailByOrderId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderId);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                o = new OrderDetail();
                o.setOrderId(rsGetCategory.getInt("ORDER_ID"));
                o.setProductId(rsGetCategory.getInt("PRODUCT_ID"));
                o.setProductImage(rsGetCategory.getString("IMAGES"));
                o.setProductName(rsGetCategory.getString("PRODUCT_NAME"));
                o.setProductPrice(rsGetCategory.getFloat("PRICE"));
                o.setQuantity(rsGetCategory.getInt("QUANTITY"));
                o.setQuantityAccept(rsGetCategory.getInt("QUANTITY_ACCEPT"));
                o.setStatus(rsGetCategory.getString("STATUS"));
                listGetAllOrderDetailByOrderId.add(o);
            }
            return listGetAllOrderDetailByOrderId;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public float getSubPriceByDay(int day) {
        try {
            float subPrice = 0;
            ResultSet rsGetSubPriceByDay;
            String sql = "{call getSubPriceByDay(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, day);
            rsGetSubPriceByDay = cs.executeQuery();
            while (rsGetSubPriceByDay.next()) {
                subPrice = rsGetSubPriceByDay.getFloat("SUB_PRICE");
            }
            return subPrice;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public List<Order> getDetailSubPriceByDay(int day) {
        List<Order> listGetAllOrderByUser = new ArrayList<Order>();
        OrderDetailBiz orderDetailBiz = new OrderDetailBiz();
        Order o = null;
        try {
            ResultSet rsGetDetailSubPriceByDay;
            String sql = "{call getDetailSubPriceByDay(?)}";
            DateFormat dfDateUp = new SimpleDateFormat("dd-MM-yyyy");
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, day);
            rsGetDetailSubPriceByDay = cs.executeQuery();
            while (rsGetDetailSubPriceByDay.next()) {
                o = new Order();
                o.setOrderId(rsGetDetailSubPriceByDay.getInt("ORDER_ID"));
                o.setUserName(rsGetDetailSubPriceByDay.getString("USERNAME"));
                o.setTotalPrice(rsGetDetailSubPriceByDay.getFloat("TOTAL_PRICE"));
                o.setTotalPriceAfterCheck(rsGetDetailSubPriceByDay.getFloat("TOTAL_PRICE_AFTER_CHECK"));
                o.setStatus(rsGetDetailSubPriceByDay.getString("STATUS"));
                o.setOrderDate(dfDateUp.format(rsGetDetailSubPriceByDay.getDate("ORDER_DATE")));
                o.setCountSoldOut(orderDetailBiz.countStatusSoldOutByOrderId(o.getOrderId()));
                o.setCountDelivered(orderDetailBiz.countStatusDeliveredByOrderId(o.getOrderId()));
                o.setCountChecking(orderDetailBiz.getCheckingOfOrderDetail(o.getOrderId()));
                listGetAllOrderByUser.add(o);
            }
            return listGetAllOrderByUser;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void mtResultSetOfOrder(ResultSet rsGetCategory, DateFormat dfDateUp, OrderDetailBiz orderDetailBiz, List<Order> listGetAllOrderByUser) throws SQLException {
        Order o = new Order();
        o.setOrderId(rsGetCategory.getInt("ORDER_ID"));
        o.setUserName(rsGetCategory.getString("USERNAME"));
        o.setTotalPrice(rsGetCategory.getFloat("TOTAL_PRICE"));
        o.setTotalPriceAfterCheck(rsGetCategory.getFloat("TOTAL_PRICE_AFTER_CHECK"));
        o.setTotalProduct(rsGetCategory.getInt("NUM_OF_PRODUCT"));
        o.setStatus(rsGetCategory.getString("STATUS"));
        o.setOrderDate(dfDateUp.format(rsGetCategory.getDate("ORDER_DATE")));
        o.setCountSoldOut(orderDetailBiz.countStatusSoldOutByOrderId(o.getOrderId()));
        o.setCountDelivered(orderDetailBiz.countStatusDeliveredByOrderId(o.getOrderId()));
        o.setCountChecking(orderDetailBiz.getCheckingOfOrderDetail(o.getOrderId()));
        //OderDetail
        OrderDetail od;
        ResultSet rsGetCategory2;
        String sql2 = "{call getAllOrderDetailByOrderId(?)}";
        CallableStatement cs2 = con.prepareCall(sql2);
        cs2.setInt(1, rsGetCategory.getInt("ORDER_ID"));
        rsGetCategory2 = cs2.executeQuery();
        List<OrderDetail> listGetAllOrderDetailByOrderId = new ArrayList<OrderDetail>();
        while (rsGetCategory2.next()) {
            od = new OrderDetail();
            od.setOrderId(rsGetCategory2.getInt("ORDER_ID"));
            od.setProductId(rsGetCategory2.getInt("PRODUCT_ID"));
            od.setProductImage(rsGetCategory2.getString("IMAGES"));
            od.setProductName(rsGetCategory2.getString("PRODUCT_NAME"));
            od.setProductPrice(rsGetCategory2.getFloat("PRICE"));
            od.setQuantity(rsGetCategory2.getInt("QUANTITY"));
            od.setQuantityAccept(rsGetCategory2.getInt("QUANTITY_ACCEPT"));
            od.setStatus(rsGetCategory2.getString("STATUS"));
            listGetAllOrderDetailByOrderId.add(od);
        }
        o.setListOrderDetail(listGetAllOrderDetailByOrderId);
        listGetAllOrderByUser.add(o);
    }

    public float getAllPriceOfCustomer(String userName) {
        try {
            float subPrice = 0;
            ResultSet rsGetSubPriceByDay;
            String sql = "{call getAllPriceOfCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            rsGetSubPriceByDay = cs.executeQuery();
            while (rsGetSubPriceByDay.next()) {
                subPrice = rsGetSubPriceByDay.getFloat("TOTAL_PRICE_CUSTOMER");
            }
            return subPrice;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
