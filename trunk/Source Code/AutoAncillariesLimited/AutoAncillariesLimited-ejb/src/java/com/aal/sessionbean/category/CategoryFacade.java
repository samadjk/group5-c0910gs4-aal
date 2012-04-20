/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aal.sessionbean.category;

import com.aal.entitybean.Category;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author thaoct_b00423
 */
@Stateless
public class CategoryFacade extends AbstractFacade<Category> implements CategoryFacadeLocal, CategoryFacadeRemote {
    @PersistenceContext(unitName = "AutoAncillariesLimited-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }

    @Override
    public int count() {
        return super.count();
    }

    @Override
    public void create(Category entity) {
        super.create(entity);
    }

    @Override
    public void edit(Category entity) {
        super.edit(entity);
    }

    @Override
    public Category find(Object id) {
        return super.find(id);
    }

    @Override
    public List<Category> findAll() {
        return super.findAll();
    }

    @Override
    public List<Category> findRange(int[] range) {
        return super.findRange(range);
    }

    @Override
    public void remove(Category entity) {
        super.remove(entity);
    }


}
