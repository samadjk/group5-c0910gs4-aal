/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aal.sessionbean.product;

import com.aal.entitybean.Products;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Local;

/**
 *
 * @author thaoct_b00423
 */
@Local
public interface ProductsFacadeLocal {

    void create(Products products);

    void edit(Products products);

    void remove(Products products);

    Products find(Object id);

    List<Products> findAll();

    List<Products> findRange(int[] range);

    int count();

    List<Products> findByCategoryId(String categoryId) throws EJBException;

     List<Products> findByCategoryId(String categoryId, int pageSize, int currentPage) throws EJBException;

}
