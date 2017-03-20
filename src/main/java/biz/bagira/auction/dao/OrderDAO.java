package biz.bagira.auction.dao;

import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dmitriy on 25.01.2017.
 */
@Repository("orderDAO")
public class OrderDAO implements AbstractDAO<Order> {

    private static final Logger logger = LoggerFactory.getLogger(OrderDAO.class);
    @Autowired
    private SessionFactory sessionFactory;

    public Integer create(Order entity) {
        Integer id = (Integer) sessionFactory.getCurrentSession().save(entity);
        logger.info("Order add successfully : " + entity);
        return id;
    }

    public void delete(Order entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        Item item = (Item) currentSession.load(Order.class, entity.getOrderId());
        if (item != null) {
            currentSession.delete(item);
            logger.info("Order delete successfully : " + entity);
        }
    }

     public void delete(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Item item = (Item) currentSession.load(Order.class, id);
        if (item != null) {
            currentSession.delete(item);
            logger.info("Order delete successfully : " + id);
        }
    }



    public void edit(Order entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
                logger.info("Order edit successfully  : " + entity);
    }

    public Order getById(Integer id) {
        return (Order) sessionFactory.getCurrentSession().get(Order.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Order> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Order").list();
    }
}
