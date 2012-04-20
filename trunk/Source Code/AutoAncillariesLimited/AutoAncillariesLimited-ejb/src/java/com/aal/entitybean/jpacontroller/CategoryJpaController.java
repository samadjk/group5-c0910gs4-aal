/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aal.entitybean.jpacontroller;

import com.aal.entitybean.Category;
import com.aal.entitybean.jpacontroller.exceptions.IllegalOrphanException;
import com.aal.entitybean.jpacontroller.exceptions.NonexistentEntityException;
import com.aal.entitybean.jpacontroller.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.aal.entitybean.Products;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author thaoct_b00423
 */
public class CategoryJpaController {

    public CategoryJpaController() {
        emf = Persistence.createEntityManagerFactory("AutoAncillariesLimited-ejbPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Category category) throws PreexistingEntityException, Exception {
        if (category.getProductsList() == null) {
            category.setProductsList(new ArrayList<Products>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Products> attachedProductsList = new ArrayList<Products>();
            for (Products productsListProductsToAttach : category.getProductsList()) {
                productsListProductsToAttach = em.getReference(productsListProductsToAttach.getClass(), productsListProductsToAttach.getProductID());
                attachedProductsList.add(productsListProductsToAttach);
            }
            category.setProductsList(attachedProductsList);
            em.persist(category);
            for (Products productsListProducts : category.getProductsList()) {
                Category oldCategoryOfProductsListProducts = productsListProducts.getCategory();
                productsListProducts.setCategory(category);
                productsListProducts = em.merge(productsListProducts);
                if (oldCategoryOfProductsListProducts != null) {
                    oldCategoryOfProductsListProducts.getProductsList().remove(productsListProducts);
                    oldCategoryOfProductsListProducts = em.merge(oldCategoryOfProductsListProducts);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCategory(category.getCategoryID()) != null) {
                throw new PreexistingEntityException("Category " + category + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Category category) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category persistentCategory = em.find(Category.class, category.getCategoryID());
            List<Products> productsListOld = persistentCategory.getProductsList();
            List<Products> productsListNew = category.getProductsList();
            List<String> illegalOrphanMessages = null;
            for (Products productsListOldProducts : productsListOld) {
                if (!productsListNew.contains(productsListOldProducts)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Products " + productsListOldProducts + " since its category field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Products> attachedProductsListNew = new ArrayList<Products>();
            for (Products productsListNewProductsToAttach : productsListNew) {
                productsListNewProductsToAttach = em.getReference(productsListNewProductsToAttach.getClass(), productsListNewProductsToAttach.getProductID());
                attachedProductsListNew.add(productsListNewProductsToAttach);
            }
            productsListNew = attachedProductsListNew;
            category.setProductsList(productsListNew);
            category = em.merge(category);
            for (Products productsListNewProducts : productsListNew) {
                if (!productsListOld.contains(productsListNewProducts)) {
                    Category oldCategoryOfProductsListNewProducts = productsListNewProducts.getCategory();
                    productsListNewProducts.setCategory(category);
                    productsListNewProducts = em.merge(productsListNewProducts);
                    if (oldCategoryOfProductsListNewProducts != null && !oldCategoryOfProductsListNewProducts.equals(category)) {
                        oldCategoryOfProductsListNewProducts.getProductsList().remove(productsListNewProducts);
                        oldCategoryOfProductsListNewProducts = em.merge(oldCategoryOfProductsListNewProducts);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = category.getCategoryID();
                if (findCategory(id) == null) {
                    throw new NonexistentEntityException("The category with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Category category;
            try {
                category = em.getReference(Category.class, id);
                category.getCategoryID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The category with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Products> productsListOrphanCheck = category.getProductsList();
            for (Products productsListOrphanCheckProducts : productsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Category (" + category + ") cannot be destroyed since the Products " + productsListOrphanCheckProducts + " in its productsList field has a non-nullable category field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(category);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Category> findCategoryEntities() {
        return findCategoryEntities(true, -1, -1);
    }

    public List<Category> findCategoryEntities(int maxResults, int firstResult) {
        return findCategoryEntities(false, maxResults, firstResult);
    }

    private List<Category> findCategoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Category.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Category findCategory(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Category> rt = cq.from(Category.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
