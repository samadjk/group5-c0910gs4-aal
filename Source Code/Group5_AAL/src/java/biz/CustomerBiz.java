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
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Customer;
import entity.ProductReport;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pe Yeu
 */
public class CustomerBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public boolean checkLogin(Customer c) {
        try {
            ResultSet rsCheckLogin;
            String sql = "{call checkLogin(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, c.getUsername());
            cs.setString(2, c.getPass());
            rsCheckLogin = cs.executeQuery();
            if (rsCheckLogin.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    

    public boolean checkUsernameExist(String username) {
        try {
            ResultSet rs;
            String sql = "{call checkUsernameExist(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, username);
            rs = cs.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean register(String username, String pass, String fullname, int age, String email, String address, int phone) {
        try {
//            String sqlRegister = "insert into Customer (Username,Password,Fullname,age,email,address,phone,status) values('" + username + "','" + pass + "','" + fullname + "'," + age + ",'" + email + "','" + address + "'," + phone + ",'True'" + ")";

            String sql = "{call register(?,?,?,?,?,?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, username);
            cs.setString(2, pass);
            cs.setString(3, fullname);
            cs.setInt(4, age);
            cs.setString(5, email);
            cs.setString(6, address);
            cs.setInt(7, phone);
            int register = cs.executeUpdate();
            if (register > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Customer> displayCustomer(String username) {
        List<Customer> listC = new ArrayList<Customer>();
        try {
            Customer c;
            ResultSet rsGetCustomer;
            String sql = "{call displayCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, username);
            rsGetCustomer = cs.executeQuery();
            while (rsGetCustomer.next()) {
                c = new Customer();
                c.setUsername(rsGetCustomer.getString("Username"));
                c.setFullname(rsGetCustomer.getString("Fullname"));
                c.setAddress(rsGetCustomer.getString("Address"));
                c.setAge(rsGetCustomer.getInt("Age"));
                c.setEmail(rsGetCustomer.getString("Email"));
                c.setPhone(rsGetCustomer.getString("Phone"));
                c.setLevel(rsGetCustomer.getInt("LEVEL"));
                listC.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Customer> searchCustomer(String username) {
        List<Customer> listC = new ArrayList<Customer>();
        try {
            Customer c;
            ResultSet rsGetCustomer;
            String sql = "{call searchCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, username);
            rsGetCustomer = cs.executeQuery();
            while (rsGetCustomer.next()) {
                c = new Customer();
                c.setUsername(rsGetCustomer.getString("Username"));
                c.setFullname(rsGetCustomer.getString("Fullname"));
                c.setAddress(rsGetCustomer.getString("Address"));
                c.setAge(rsGetCustomer.getInt("Age"));
                c.setEmail(rsGetCustomer.getString("Email"));
                c.setPhone(rsGetCustomer.getString("Phone"));
                listC.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Customer> adminDisplayCustomer() {
        List<Customer> listC = new ArrayList<Customer>();
        try {
            Customer c;
            ResultSet rsGetCustomer;
            String sql = "{call adminViewCustomer()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCustomer = cs.executeQuery();
            while (rsGetCustomer.next()) {
                c = new Customer();
                c.setUsername(rsGetCustomer.getString("Username"));
                c.setFullname(rsGetCustomer.getString("Fullname"));
                c.setAddress(rsGetCustomer.getString("Address"));
                c.setAge(rsGetCustomer.getInt("Age"));
                c.setEmail(rsGetCustomer.getString("Email"));
                c.setPhone(rsGetCustomer.getString("Phone"));
                listC.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<ProductReport> viewOrderCustomer(String username) {
        List<ProductReport> listC = new ArrayList<ProductReport>();
        try {
            ResultSet rs;
            ProductReport pr;
            String sql = "{call viewOrderCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, username);
            rs = cs.executeQuery();
            while (rs.next()) {
                pr = new ProductReport();
                pr.setOrderId(rs.getInt("ORDER_ID"));
                pr.setIdPro(rs.getInt("PRODUCT_ID"));
                pr.setName(rs.getString("PRODUCT_NAME"));
                pr.setHouse(rs.getString("WAREHOUSE_NAME"));
                pr.setFactory(rs.getString("FACTORY_NAME"));
                pr.setPrice(rs.getFloat("PRICE"));
                pr.setDes(rs.getString(8));
                pr.setImage(rs.getString("IMAGES"));
                pr.setIdHouse(rs.getInt("WAREHOUSE_ID"));
                pr.setUsername(rs.getString("USERNAME"));
                pr.setDate(rs.getDate("ORDER_DATE"));
                listC.add(pr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public boolean deleteCustomer(String username) {
        try {
            ResultSet rs;
            String sql = "{call deleteCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, username);
            int delete = cs.executeUpdate();
            if (delete > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteOrderOfCustomer(int orderID) {
        try {
            ResultSet rs;
            String sql = "{call deleteOrderOfCustomer(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, orderID);
            int delete = cs.executeUpdate();
            if (delete > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
