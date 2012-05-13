/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author HieuDQ_B00385
 */
public class Customer {

    private String username;
    private String pass;
    private String fullname;
    private int age;
    private String email;
    private String address;
    private String phone;
    private int level;

    public Customer() {
    }

    public Customer(String username, String pass, String fullname, int age, String email, String address, String phone, int level) {
        this.username = username;
        this.pass = pass;
        this.fullname = fullname;
        this.age = age;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.level = level;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
