/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import connection.GetConnection;
import entity.Category;
import entity.Warehouse;
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
 * @author Admin
 */
public class CategoryBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public List<Category> allCategory() {
        List<Category> listC = new ArrayList<Category>();
        try {
            Category c;
            ResultSet rsGetCategory;
            String sql = "{call allCategory()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                c = new Category();
                c.setId(rsGetCategory.getInt("CATEGORY_ID"));
                c.setName(rsGetCategory.getString("CATEGORY_NAME"));
                c.setDes(rsGetCategory.getString("DESCRIPTION"));
                listC.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Warehouse> allWarehouse() {
        List<Warehouse> listC = new ArrayList<Warehouse>();
        try {
            Warehouse w;
            ResultSet rsGetCategory;
            String sql = "{call allWarehouse()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                w = new Warehouse();
                w.setId(rsGetCategory.getInt("WAREHOUSE_ID"));
                w.setIdF(rsGetCategory.getInt("FACTORY_ID"));
                w.setName(rsGetCategory.getString("WAREHOUSE_NAME"));
                w.setLocal(rsGetCategory.getString("LOCATION"));
                listC.add(w);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }
}
