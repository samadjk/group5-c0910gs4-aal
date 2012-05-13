/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import biz.OrderBiz;
import entity.Order;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author HieuDQ_B00385
 */
@ManagedBean
@RequestScoped
public class RevenueStatisticBean {

    List<Order> listRevenueStatistic;
    List<Order> listRevenueStatisticSession;
    OrderBiz orderBiz;
    float subPrice;
    private static final long serialVersionUID = 1L;
    private static Map<String, Integer> values;
    private int dayCode = 1; //default value
    public String fromDate;
    public String toDate;

    static {
        values = new LinkedHashMap<String, Integer>();
        values.put("1 day ago", 1); //label, value
        values.put("2 days ago", 2);
        values.put("1 week ago", 8);
        values.put("1 month ago", 31);
        values.put("3 month ago", 93);
        values.put("1 year ago", 366);
    }

    /** Creates a new instance of RevenueStatisticBean */
    public RevenueStatisticBean() {
        listRevenueStatistic = new ArrayList<Order>();
        orderBiz = new OrderBiz();
        //subPrice = orderBiz.getSubPriceByDay(10);
        //listRevenueStatistic = orderBiz.getDetailSubPriceByDay(1000);
        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListRevenueStatistic") != null) {
            listRevenueStatisticSession = (List<Order>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssListRevenueStatistic");
        }

        if (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssSubPrice") != null) {
            subPrice = (Float) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("ssSubPrice");
        }
    }

    public void dayCodeChanged(ValueChangeEvent e) {
        //assign new value to dayCode
        //listRevenueStatistic.clear();
        dayCode = (Integer) e.getNewValue();
        listRevenueStatistic = orderBiz.getDetailSubPriceByDay(dayCode);
        float subPrice_ = orderBiz.getSubPriceByDay(dayCode);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssListRevenueStatistic");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("ssSubPrice");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssListRevenueStatistic", listRevenueStatistic);
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssSubPrice", subPrice_);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.add(Calendar.DATE, -dayCode);	//Adding 1 day to current date
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ssDateCheck", "true");
        fromDate = dateformat.format(cal.getTime());
        toDate = dateformat.format(cal2.getTime());
    }

    public Map<String, Integer> getCountryInMap() {
        return this.values;
    }

    public List<Order> getListRevenueStatistic() {
        return listRevenueStatistic;
    }

    public void setListRevenueStatistic(List<Order> listRevenueStatistic) {
        this.listRevenueStatistic = listRevenueStatistic;
    }

    public float getSubPrice() {
        return subPrice;
    }

    public void setSubPrice(float subPrice) {
        this.subPrice = subPrice;
    }

    public List<Order> getListRevenueStatisticSession() {
        return listRevenueStatisticSession;
    }

    public void setListRevenueStatisticSession(List<Order> listRevenueStatisticSession) {
        this.listRevenueStatisticSession = listRevenueStatisticSession;
    }

    public int getDayCode() {
        return dayCode;
    }

    public void setDayCode(int dayCode) {
        this.dayCode = dayCode;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
