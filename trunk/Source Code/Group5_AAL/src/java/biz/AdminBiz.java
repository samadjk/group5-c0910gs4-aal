/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import connection.GetConnection;
import entity.Admin;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.CallableStatement;
import java.util.List;

/**
 *
 * @author KuBk
 */
public class AdminBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public AdminBiz() {
    }

    public boolean checkLoginAdmin(Admin admin) {
        try {
            ResultSet rsCheckLogin;
            String sql = "{call getAccountAndPermission(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, admin.getUserName());
            cs.setString(2, admin.getPassword());
            rsCheckLogin = cs.executeQuery();
            if (rsCheckLogin.next()) {
                admin.setPermission(rsCheckLogin.getInt("PERMISSION"));
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public String getFactoryNameByFactoryId(int factoryId) {
        try {
            String factoryName = "";
            ResultSet rsGetFactoryName;
            String sql = "{call getFactoryAndWarehouseByFactoryId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, factoryId);
            rsGetFactoryName = cs.executeQuery();
            if (rsGetFactoryName.next()) {
                factoryName = rsGetFactoryName.getString("FACTORY_NAME");
            }
            return factoryName;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }

    public String getWarehouseNameByFactoryId(int factoryId) {
        try {
            String warehouseName = "";
            ResultSet rsGetWarehouseName;
            String sql = "{call getFactoryAndWarehouseByFactoryId(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, factoryId);
            rsGetWarehouseName = cs.executeQuery();
            if (rsGetWarehouseName.next()) {
                warehouseName = rsGetWarehouseName.getString("WAREHOUSE_NAME");
            }
            return warehouseName;
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
}
