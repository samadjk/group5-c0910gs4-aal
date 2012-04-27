/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import connection.GetConnection;
import entity.ProductReport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pe Yeu
 */
public class ReportBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public List<ProductReport> displayStock() {
        List<ProductReport> listPR = new ArrayList<ProductReport>();
        try {

            ResultSet rs;
            ProductReport pr;
            String sql = "{call stock()}";
            CallableStatement cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                pr = new ProductReport();
                pr.setIdPro(rs.getInt("PRODUCT_ID"));
                pr.setName(rs.getString("PRODUCT_NAME"));
                pr.setHouse(rs.getString("WAREHOUSE_NAME"));
                pr.setFactory(rs.getString("FACTORY_NAME"));
                pr.setPrice(rs.getFloat("PRICE"));
                pr.setDes(rs.getString(8));
                pr.setImage(rs.getString("IMAGES"));
                pr.setIdHouse(rs.getInt("WAREHOUSE_ID"));
                pr.setCateName(rs.getString("CATEGORY_NAME"));
                pr.setQuantityStock(rs.getInt("QUANTIY") - rs.getInt("QUANTITY_IN_ORDER"));
                listPR.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPR;
    }

    public List<ProductReport> hotProduct() {
        List<ProductReport> listPR = new ArrayList<ProductReport>();
        try {

            ResultSet rs;
            ProductReport pr;
            String sql = "{call hotProduct()}";
            CallableStatement cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                pr = new ProductReport();
                pr.setIdPro(rs.getInt("PRODUCT_ID"));
                pr.setName(rs.getString("PRODUCT_NAME"));
                pr.setHouse(rs.getString("WAREHOUSE_NAME"));
                pr.setFactory(rs.getString("FACTORY_NAME"));
                pr.setPrice(rs.getFloat("PRICE"));
                pr.setDes(rs.getString(8));
                pr.setImage(rs.getString("IMAGES"));
                pr.setIdHouse(rs.getInt("WAREHOUSE_ID"));
                pr.setQuantityStock(rs.getInt("QUANTITY_IN_ORDER"));
                listPR.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPR;
    }

    public List<ProductReport> BestsellerProduct() {
        List<ProductReport> listPR = new ArrayList<ProductReport>();
        try {

            ResultSet rs;
            ProductReport pr;
            String sql = "{call BestsellerProduct()}";
            CallableStatement cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                pr = new ProductReport();
                pr.setIdPro(rs.getInt("PRODUCT_ID"));
                pr.setName(rs.getString("PRODUCT_NAME"));
                pr.setHouse(rs.getString("WAREHOUSE_NAME"));
                pr.setFactory(rs.getString("FACTORY_NAME"));
                pr.setPrice(rs.getFloat("PRICE"));
               // pr.setDes(rs.getString(8));
                pr.setImage(rs.getString("IMAGES"));
               // pr.setIdHouse(rs.getInt("WAREHOUSE_ID"));
                pr.setHot(rs.getInt("HOT"));
                listPR.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPR;
    }

    public List<ProductReport> displayRejected() {
        List<ProductReport> listJ = new ArrayList<ProductReport>();
        try {

            ResultSet rs;
            ProductReport pr;
            String sql = "{call rejectedMaterial()}";
            CallableStatement cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                pr = new ProductReport();
                pr.setIdPro(rs.getInt("PRODUCT_ID"));
                pr.setName(rs.getString("PRODUCT_NAME"));
                pr.setHouse(rs.getString("WAREHOUSE_NAME"));
                pr.setFactory(rs.getString("FACTORY_NAME"));
                pr.setPrice(rs.getFloat("PRICE"));
                pr.setDes(rs.getString(8));
                pr.setImage(rs.getString("IMAGES"));
                pr.setIdHouse(rs.getInt("WAREHOUSE_ID"));
                //pr.setCateName(rs.getString("CATEGORY_NAME"));
                pr.setUsername(rs.getString("USERNAME"));
                pr.setDate(rs.getDate("ORDER_DATE"));
                listJ.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listJ;
    }

    public int countStock() {
        int count = 0;
        try {
            ResultSet rs;
            ProductReport pr;
            String sql = "{call countStock()}";
            CallableStatement cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT_PID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int countRejected() {
        int count = 0;
        try {
            ResultSet rs;
            ProductReport pr;
            String sql = "{call countRejectedMaterial()}";
            CallableStatement cs = con.prepareCall(sql);
            rs = cs.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNT_PID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}
