/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Pe Yeu
 */
public class ProductReport {

    private int idPro;
    private int idCate;
    private int idHouse;
    private String name;
    private float price;
    private String des;
    private String image;
    private String house;
    private String factory;
    private String cateName;
    private int quantityStock;
    private String username;
    private Date date;
    private int orderId;
    private int hot;

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    
    public int getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public int getIdCate() {
        return idCate;
    }

    public void setIdCate(int idCate) {
        this.idCate = idCate;
    }

    public int getIdHouse() {
        return idHouse;
    }

    public void setIdHouse(int idHouse) {
        this.idHouse = idHouse;
    }

    public int getIdPro() {
        return idPro;
    }

    public void setIdPro(int idPro) {
        this.idPro = idPro;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ProductReport() {
    }

    public ProductReport(int idPro, int idCate, int idHouse, String name, float price, String des, String image, String house, String factory, String cateName, int quantityStock, String username, Date date, int orderId) {
        this.idPro = idPro;
        this.idCate = idCate;
        this.idHouse = idHouse;
        this.name = name;
        this.price = price;
        this.des = des;
        this.image = image;
        this.house = house;
        this.factory = factory;
        this.cateName = cateName;
        this.quantityStock = quantityStock;
        this.username = username;
        this.date = date;
        this.orderId = orderId;
    }

   
}
