/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import connection.GetConnection;
import entity.Benefit;
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
public class BenefitBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public List<Benefit> allBenefit() {
        List<Benefit> listP = new ArrayList<Benefit>();
        try {
            Benefit b;
            ResultSet rsGetPrice;
            String sql = "{call allBenifit()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetPrice = cs.executeQuery();
            while (rsGetPrice.next()) {
                b = new Benefit();
                b.setMinCost(rsGetPrice.getFloat("MIN_COST"));
                b.setMaxCost(rsGetPrice.getFloat("MAX_COST"));
                b.setPerCent(rsGetPrice.getFloat("PERCENT"));
                b.setId(rsGetPrice.getInt("BENEFIT_ID"));
                listP.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public boolean delete(int id) {
        try {
            Benefit b;
            ResultSet rsGetPrice;
            String sql = "{call deleteBenefit(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, id);
            int delete = cs.executeUpdate();
            if (delete > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Benefit detailBenefit(int id) {
        Benefit b = new Benefit();
        try {
            ResultSet rsGetCategory;
            String sql = "{call detail(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, id);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                b.setId(rsGetCategory.getInt("BENEFIT_ID"));
                b.setMinCost(rsGetCategory.getFloat("MIN_COST"));
                b.setMaxCost(rsGetCategory.getFloat("MAX_COST"));
                b.setPerCent(rsGetCategory.getFloat("PERCENT"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    public float getMaxCostBeforeAddNew() {
        try {
            float maxCost = 0;
            ResultSet rsGetCategory;
            String sql = "{call getMaxCostBeforeAddNew()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                maxCost = rsGetCategory.getFloat("MAX_COST");
            }
            return maxCost;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public float gePercentBeforeAddNew() {
        try {
            float percent = 0;
            ResultSet rsGetCategory;
            String sql = "{call getMaxCostBeforeAddNew()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                percent = rsGetCategory.getFloat("PERCENT");
            }
            return percent;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public void addNewBenefit(float minCost, float maxCost, float percent) {
        try {
            String sql = "{call addNewBenefit(?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setFloat(1, minCost);
            cs.setFloat(2, maxCost);
            cs.setFloat(3, percent);
            cs.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int checkLevelUpOfCustomer(String userName) {
        try {
            int levelUp = -1;
            ResultSet rsGetCategory;
            String sql = "{call checkLevelUpOfCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, userName);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                levelUp = rsGetCategory.getInt("LEVEL_UP");
            }
            return levelUp;
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
}
