/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package biz;

import connection.GetConnection;
import entity.Product;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ProductBiz {

    GetConnection getCon = new GetConnection();
    Connection con = getCon.getConnec();

    public List<Product> productByPrice(float min, float max) {
        List<Product> listP = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetPrice;
            String sql = "{call productByPrice(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setFloat(1, min);
            cs.setFloat(2, max);
            rsGetPrice = cs.executeQuery();
            while (rsGetPrice.next()) {
                p = new Product();
                p.setIdPro(rsGetPrice.getInt("PRODUCT_ID"));
                p.setName(rsGetPrice.getString("PRODUCT_NAME"));
                p.setHouse(rsGetPrice.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetPrice.getString("FACTORY_NAME"));
                p.setPrice(rsGetPrice.getFloat("PRICE"));
                p.setDes(rsGetPrice.getString(8));
                p.setImage(rsGetPrice.getString("IMAGES"));
                p.setIdHouse(rsGetPrice.getInt("WAREHOUSE_ID"));
                listP.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listP;
    }

    public int countProductByPrice(float min, float max) {
        int count = 0;
        try {

            ResultSet rsGetPrice;
            String sql = "{call countProductByPrice(?,?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setFloat(1, min);
            cs.setFloat(2, max);
            rsGetPrice = cs.executeQuery();
            if (rsGetPrice.next()) {
                count = rsGetPrice.getInt("COUNT_PID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public List<Product> topProduct() {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call topProduct()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                p.setCategory(rsGetCategory.getString("CATEGORY_NAME"));
                p.setIdCate(rsGetCategory.getInt("CATEGORY_ID"));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Product> productByCategory(int id) {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call productByCategory(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, id);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                p.setCategory(rsGetCategory.getString("CATEGORY_NAME"));
                p.setIdCate(rsGetCategory.getInt("CATEGORY_ID"));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Product> productByHouse(int id) {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call productByHouse(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, id);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                p.setCategory(rsGetCategory.getString("CATEGORY_NAME"));
                p.setIdCate(rsGetCategory.getInt("CATEGORY_ID"));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Product> productDetail(int id) {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call productDetail(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, id);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                p.setCategory(rsGetCategory.getString("CATEGORY_NAME"));
                p.setIdCate(rsGetCategory.getInt("CATEGORY_ID"));
                p.setSell(rsGetCategory.getFloat("PRICE") - ((rsGetCategory.getFloat("PRICE") * rsGetCategory.getFloat("SELL_OFF")) / 100));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Product> searchProduct(String text) {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call searchProduct(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setString(1, text);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                p.setCategory(rsGetCategory.getString("CATEGORY_NAME"));
                p.setIdCate(rsGetCategory.getInt("CATEGORY_ID"));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Product> bestSeller() {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call productBestSeller()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Product> productSpecial() {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call productSpecial()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                p.setSell(rsGetCategory.getFloat("PRICE") - ((rsGetCategory.getFloat("PRICE") * rsGetCategory.getFloat("SELL_OFF")) / 100));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public List<Product> productHot() {
        List<Product> listC = new ArrayList<Product>();
        try {
            Product p;
            ResultSet rsGetCategory;
            String sql = "{call productHot()}";
            CallableStatement cs = con.prepareCall(sql);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p = new Product();
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setImage(rsGetCategory.getString("IMAGES"));
                listC.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listC;
    }

    public boolean addProduct(int cate, String name, float price, String des, String image) {
        try {
            Statement strAdd;
            String sql = "INSERT INTO PRODUCT(CATEGORY_ID,PRODUCT_NAME,PRICE,DESCRIPTION,IMAGES,STATUS) VALUES(" + cate + ",'" + name + "'," + price + ",'" + des + "','" + image + "','TRUE')";
            strAdd = con.createStatement();
            int add = strAdd.executeUpdate(sql);
            if (add > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean addProductHouse(int house, int quantity, int quantityO, float sale, int rate) {
        try {
            Statement strAdd;
            String sql = "INSERT INTO PRODUCT_WAREHOUSE(PRODUCT_ID,WAREHOUSE_ID,QUANTIY,QUANTITY_IN_ORDER,SELL_OFF,DATE,RATING,STATUS)" + ""
                    + "" + " VALUES((SELECT MAX(PRODUCT_ID) as 'MAX_CONTRACT_ID' FROM PRODUCT)," + house + "," + quantity + "," + quantityO + "," + sale + ",GETDATE()," + rate + ",'true')";
            strAdd = con.createStatement();
            int add = strAdd.executeUpdate(sql);
            if (add > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean delProduct(int id) {
        try {
            Statement strAdd;
            String sql = "UPDATE PRODUCT SET STATUS = 'false' WHERE PRODUCT_ID = " + id + "";
            strAdd = con.createStatement();
            int add = strAdd.executeUpdate(sql);
            if (add > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean editProduct(Product p) {
        try {
            Statement strAdd;
            String sql = "UPDATE PRODUCT SET CATEGORY_ID = " + p.getIdCate() + ",PRODUCT_NAME = '" + p.getName() + "', PRICE = " + p.getPrice() + ",DESCRIPTION = '" + p.getDes() + "',IMAGES = '" + p.getImage() + "' WHERE PRODUCT_ID = " + p.getIdPro() + "";
            strAdd = con.createStatement();
            int add = strAdd.executeUpdate(sql);
            if (add > 0) {
                Statement strAdd2;
                String sql2 = "UPDATE PRODUCT_WAREHOUSE SET WAREHOUSE_ID = " + p.getIdHouse() + ",QUANTIY = " + p.getQuantity() + ", QUANTITY_IN_ORDER = " + p.getQuantityO() + ",SELL_OFF = " + p.getSaleOff() + ",RATING = " + p.getRate() + " WHERE PRODUCT_ID = " + p.getIdPro() + "";
                strAdd2 = con.createStatement();
                int add2 = strAdd2.executeUpdate(sql2);
                if (add2 > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Product productDetailSingle(int id) {
        Product p = new Product();
        try {
            ResultSet rsGetCategory;
            String sql = "{call productDetail(?)}";
            CallableStatement cs = con.prepareCall(sql);
            cs.setInt(1, id);
            rsGetCategory = cs.executeQuery();
            while (rsGetCategory.next()) {
                p.setIdPro(rsGetCategory.getInt("PRODUCT_ID"));
                p.setName(rsGetCategory.getString("PRODUCT_NAME"));
                p.setHouse(rsGetCategory.getString("WAREHOUSE_NAME"));
                p.setFactory(rsGetCategory.getString("FACTORY_NAME"));
                p.setPrice(rsGetCategory.getFloat("PRICE"));
                p.setDes(rsGetCategory.getString(8));
                p.setImage(rsGetCategory.getString("IMAGES"));
                p.setIdHouse(rsGetCategory.getInt("WAREHOUSE_ID"));
                p.setSaleOff(rsGetCategory.getFloat("SELL_OFF"));
                p.setCategory(rsGetCategory.getString("CATEGORY_NAME"));
                p.setIdCate(rsGetCategory.getInt("CATEGORY_ID"));
                p.setQuantity(rsGetCategory.getInt("QUANTIY"));
                p.setQuantityO(rsGetCategory.getInt("QUANTITY_IN_ORDER"));
                p.setRate(rsGetCategory.getInt("RATING"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductBiz.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }
}
