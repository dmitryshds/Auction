package biz.bagira.auction.dao;

import biz.bagira.auction.entities.Category;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dmitriy on 24.01.2017.
 */

@Repository("categoryDAO")
public class CategoryDAO implements AbstractDAO<Category> {

    private static final Logger logger = LoggerFactory.getLogger(CategoryDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Integer create(Category entity) {
        Integer id = (Integer)sessionFactory.getCurrentSession().save(entity);
        logger.info("Category add successfully : " + entity);
        return id;
    }

    public void delete(Category entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        Category category = (Category) currentSession.load(Category.class, entity.getIdCategory());
        if (category != null) {
            currentSession.delete(category);
            logger.info("Category delete successfully : " + entity);
        }
    }

    public void delete(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Category category = (Category) currentSession.load(Category.class, id);
        logger.info("Class category : "+category.getClass());
        if (category != null) {
            currentSession.delete(category);

            logger.info("Category delete successfully : " + id);
        }
    }

    public void edit(Category entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        logger.info("Category edit successfully  : " + entity);
    }

    public Category getById(Integer id) {
        return (Category) sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Category> getAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("SELECT ID_CATEGORY, TYPE FROM CATEGORY ORDER BY TYPE");
        query.addEntity(Category.class);
        return  (List<Category>)query.list();
    }

    public Category getByName(String name) {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("SELECT * FROM category c WHERE c.type = :name ");
        query.addEntity(Category.class);
        query.setParameter("name", name);
        Category category = (Category) query.uniqueResult();
        logger.info("Category getByName : " + category);
        return category;
    }

}
