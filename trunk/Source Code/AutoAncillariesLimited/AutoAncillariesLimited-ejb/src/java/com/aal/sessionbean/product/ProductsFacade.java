/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aal.sessionbean.product;

import com.aal.entitybean.Products;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author thaoct_b00423
 */
@Stateless
public class ProductsFacade extends AbstractFacade<Products> implements ProductsFacadeLocal, ProductsFacadeRemote {

    @PersistenceContext(unitName = "AutoAncillariesLimited-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public void create(Products entity) {
        super.create(entity);
    }

    @Override
    public void edit(Products entity) {
        super.edit(entity);
    }

    @Override
    public Products find(Object id) {
        return super.find(id);
    }

    @Override
    public List<Products> findAll() {
        return super.findAll();
    }

    @Override
    public List<Products> findRange(int[] range) {
        return super.findRange(range);
    }

    @Override
    public void remove(Products entity) {
        super.remove(entity);
    }

    @Override
    public List<Products> findByCategoryId(String categoryId) throws EJBException {
        Query query = getEntityManager().createNamedQuery("sp_findProductsByCategoryID1");
        query.setParameter("categoryID", categoryId);
        List<Products> list = (List<Products>) query.getResultList();
        return list;

    }

    @Override
    public List<Products> findByCategoryId(String categoryId, int pageSize, int currentPage) throws EJBException {
        Query query = getEntityManager().createNamedQuery("sp_findProductsByCategoryID");
        query.setParameter("categoryID", categoryId);
        query.setParameter("pageSize", 2);
        query.setParameter("currentPage", 1);
        List<Products> list = (List<Products>) query.getResultList();
        return list;
    }
}
