/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.CategoryBiz;
import entity.Category;
import entity.Warehouse;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author HieuDQ_B00385
 */
@ManagedBean
@RequestScoped
public class CategoryBean {

    /** Creates a new instance of CategoryBean */
    CategoryBiz cBiz = new CategoryBiz();
    List<Category> listC;
    List<Warehouse> listH;

    public CategoryBean() {
        listC = cBiz.allCategory();
        listH = cBiz.allWarehouse();
    }

    public List<Category> getListC() {
        return listC;
    }

    public void setListC(List<Category> listC) {
        this.listC = listC;
    }

    public List<Warehouse> getListH() {
        return listH;
    }

    public void setListH(List<Warehouse> listH) {
        this.listH = listH;
    }
}
