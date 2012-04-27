/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.ReportBiz;
import entity.ProductReport;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Pe Yeu
 */
@ManagedBean
@RequestScoped
public class ReportBean {

    List<ProductReport> listPR;
    List<ProductReport> listPJ;
    List<ProductReport> listPH;
    List<ProductReport> listPB;
    ReportBiz rBiz = new ReportBiz();
    int countStock;
    int countRejected;

    /** Creates a new instance of ReportBean */
    public ReportBean() {
        listPR = rBiz.displayStock();
        listPJ = rBiz.displayRejected();
        countStock = rBiz.countStock();
        countRejected = rBiz.countRejected();
        listPH = rBiz.hotProduct();
        listPB = rBiz.BestsellerProduct();
    }

    public List<ProductReport> getListPB() {
        return listPB;
    }

    public void setListPB(List<ProductReport> listPB) {
        this.listPB = listPB;
    }

    public int getCountRejected() {
        return countRejected;
    }

    public void setCountRejected(int countRejected) {
        this.countRejected = countRejected;
    }

    public List<ProductReport> getListPH() {
        return listPH;
    }

    public void setListPH(List<ProductReport> listPH) {
        this.listPH = listPH;
    }

    public int getCountStock() {
        return countStock;
    }

    public void setCountStock(int countStock) {
        this.countStock = countStock;
    }

    public List<ProductReport> getListPR() {
        return listPR;
    }

    public void setListPR(List<ProductReport> listPR) {
        this.listPR = listPR;
    }

    public List<ProductReport> getListPJ() {
        return listPJ;
    }

    public void setListPJ(List<ProductReport> listPJ) {
        this.listPJ = listPJ;
    }
}
