package biz.bagira.auction.dao;

import biz.bagira.auction.entities.Item;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitriy on 24.01.2017.
 */
@Repository("itemDAO")
public class ItemDAO implements AbstractDAO<Item> {

    private static final Logger logger = LoggerFactory.getLogger(ItemDAO.class);
    @Autowired
    private SessionFactory sessionFactory;

    public Integer create(Item entity) {
        Integer id = (Integer) sessionFactory.getCurrentSession().save(entity);
        logger.info("Item add successfully : " + entity);
        return id;
    }

    public void delete(Item entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        Item item = (Item) currentSession.load(Item.class, entity.getIdItems());
        if (item != null) {
            currentSession.delete(item);
            logger.info("Item delete successfully : " + entity);
        }
    }

    public void delete(Integer entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        Item item = (Item) currentSession.load(Item.class, entity);
        logger.info("Class item : " + item.getClass());

        if (item != null) {
            currentSession.delete(item);
            logger.info("Item delete successfully : " + entity);
        }
    }

    public void edit(Item entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        logger.info("Item edit successfully  : " + entity);
    }

    public Item getById(Integer id) {
        return (Item) sessionFactory.getCurrentSession().get(Item.class, id);
    }

    public Set<Item> getAll() {
        List list = sessionFactory.getCurrentSession().createQuery("from Item").list();
        return new HashSet<Item>(list);
    }

    public List<Item> getItemsByCategoryId(Integer categoryId) {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("select i.* from items AS i, category_item AS ci, category AS c where i.id_items=ci.item_id AND ci.category_id=c.id_category AND c.id_category=:id");
        query.addEntity(Item.class);
        query.setParameter("id", categoryId);
        return (List<Item>) query.list();
    }
    public List<Item> getLimitItemsByCategoryId(Integer categoryId, Integer from, Integer rows) {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("select i.* from items AS i, category_item AS ci, category AS c where i.id_items=ci.item_id AND ci.category_id=c.id_category AND c.id_category=:id ORDER BY i.ID_ITEMS");
        query.addEntity(Item.class);
        query.setParameter("id", categoryId);
        query.setFirstResult(from);
        query.setMaxResults(rows);
        return (List<Item>) query.list();
    }

    public Integer getCountItemsByCategory(Integer categoryId){
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("select COUNT(ci.ITEM_ID )  from  category_item AS ci where ci.category_id=:id ");
        query.setParameter("id", categoryId);
        return ((BigInteger) query.uniqueResult()).intValue();
    }


}
