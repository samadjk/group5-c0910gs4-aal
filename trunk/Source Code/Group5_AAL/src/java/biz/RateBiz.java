/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import connection.GetConnection;
import entity.Order;
import entity.OrderDetail;
import entity.Rate;
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
public class RateBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public RateBiz() {
    }

    public void insertIntoRate(int productId, String userName, int rateValue) {
        try {
            String sql = "{call insertIntoRate(?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, productId);
            cs.setString(2, userName);
            cs.setInt(3, rateValue);
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Rate> getRateByUserForProduct(int productId, String userName) {
        List<Rate> listRateByUserForProduct = new ArrayList<Rate>();
        try {
            Rate rate;
            ResultSet rs;
            String sql = "{call getRateByUserForProduct(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, productId);
            cs.setString(2, userName);
            rs = cs.executeQuery();
            while (rs.next()) {
                rate = new Rate();
                rate.setProductId(rs.getInt("PRODUCT_ID"));
                rate.setUserName(rs.getString("USERNAME"));
                rate.setRateValue(rs.getInt("RATE"));
                listRateByUserForProduct.add(rate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRateByUserForProduct;
    }
}
