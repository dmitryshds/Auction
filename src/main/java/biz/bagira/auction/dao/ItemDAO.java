package biz.bagira.auction.dao;

import biz.bagira.auction.entities.Item;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
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

    public void indexItems() throws InterruptedException {
        FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
        fullTextSession.createIndexer().startAndWait();
        logger.info("Hibernate search indexed ");
    }

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
        SQLQuery query = currentSession.createSQLQuery("select i.* from items AS i  where i.state='Active' AND i.category_id=:id ");
        query.addEntity(Item.class);
        query.setParameter("id", categoryId);
        return (List<Item>) query.list();
    }
    public List<Item> getLimitItemsByCategoryId(Integer categoryId, Integer from, Integer rows) {
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("select i.* from items AS i where i.state='Active' AND i.category_id=:id ORDER BY i.ID_ITEMS");
        query.addEntity(Item.class);
        query.setParameter("id", categoryId);
        query.setFirstResult(from);
        query.setMaxResults(rows);

        return (List<Item>) query.list();
    }

    public Integer getCountItemsByCategory(Integer categoryId){
        Session currentSession = sessionFactory.getCurrentSession();
        SQLQuery query = currentSession.createSQLQuery("select COUNT(i.ID_ITEMS)  from  items AS i where i.state='Active' AND i.category_id=:id ");
        query.setParameter("id", categoryId);
        return ((BigInteger) query.uniqueResult()).intValue();
    }

    public List<Item> searchForItems(String searchText,Integer from, Integer rows) {
//        try {
//            indexItems();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        Session currentSession = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(currentSession);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("name","description").matching(searchText).createQuery();
        logger.info("ItemDAO luceneQuery = "+luceneQuery);
        // wrap Lucene query in a javax.persistence.Query
        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Item.class);
        logger.info("ItemDAO fullTextQuery = "+fullTextQuery);
        fullTextQuery.setFirstResult(from); //start from
        fullTextQuery.setMaxResults(rows); //return  elements
        return fullTextQuery.list();
    }
    public Integer getCountItemsBySearch(String searchText){
        Session currentSession = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = Search.getFullTextSession(currentSession);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword().onFields("name","description").matching(searchText).createQuery();
        org.hibernate.Query fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery, Item.class);
        return fullTextQuery.list().size();
    }
}
