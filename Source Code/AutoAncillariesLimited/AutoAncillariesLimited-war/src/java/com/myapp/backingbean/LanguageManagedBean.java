package com.myapp.backingbean;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean
@SessionScoped
public class LanguageManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String localeCode;
    private static Map<String, Object> countries;

    static {
        countries = new LinkedHashMap<String, Object>();
        countries.put("English", Locale.ENGLISH); //label, value
        countries.put("Chinese", Locale.SIMPLIFIED_CHINESE);
        countries.put("Vietnamese", new Locale("vi"));
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public String changeLocale() {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map map = externalContext.getRequestParameterMap();
        String localeCode = (String) map.get("localeCode");
        System.out.println("changeLocale : "+localeCode);
        Locale newLocale = new Locale(localeCode);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(newLocale);
        return null;
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e) {
        
        String newLocaleValue = e.getNewValue().toString();

        //loop a map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if (entry.getValue().toString().equals(newLocaleValue)) {

                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());

            }
        }

    }
}
