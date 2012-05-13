/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author HieuDQ_B00385
 */
public class Product {

    private int idPro;
    private int idCate;
    private int idHouse;
    private String name;
    private float price;
    private String des;
    private String image;
    private String house;
    private String factory;
    private float saleOff;
    private float sell;
    private String category;
    private int quantity;
    private int quantityO;
    private int rate;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantityO() {
        return quantityO;
    }

    public void setQuantityO(int quantityO) {
        this.quantityO = quantityO;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public float getSell() {
        return sell;
    }

    public void setSell(float sell) {
        this.sell = sell;
    }

    public float getSaleOff() {
        return saleOff;
    }

    public void setSaleOff(float saleOff) {
        this.saleOff = saleOff;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Product(int idPro, int idCate, int idHouse, String name, float price, String des, String image, String house, String factory, float saleOff, float sell, String category, int quantity, int quantityO, int rate) {
        this.idPro = idPro;
        this.idCate = idCate;
        this.idHouse = idHouse;
        this.name = name;
        this.price = price;
        this.des = des;
        this.image = image;
        this.house = house;
        this.factory = factory;
        this.saleOff = saleOff;
        this.sell = sell;
        this.category = category;
        this.quantity = quantity;
        this.quantityO = quantityO;
        this.rate = rate;
    }

    public Product() {
    }
}
