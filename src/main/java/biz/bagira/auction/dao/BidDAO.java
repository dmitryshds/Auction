package biz.bagira.auction.dao;

import biz.bagira.auction.entities.Bid;
import biz.bagira.auction.entities.Item;
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

@Repository("bidDAO")
public class BidDAO implements AbstractDAO<Bid> {

    private static final Logger logger = LoggerFactory.getLogger(BidDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    public Integer create(Bid entity) {
        Integer id = (Integer) sessionFactory.getCurrentSession().save(entity);
        logger.info("Bid add successfully : " + entity);
        return id;
    }

    public void delete(Bid entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        Item item = (Item) currentSession.load(Item.class, entity.getIdBid());
        if (item != null) {
            currentSession.delete(item);
            logger.info("Bid delete successfully : " + entity);
        }
    }

    public void delete(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Item item = (Item) currentSession.load(Item.class, id);
        if (item != null) {
            currentSession.delete(item);
            logger.info("Bid delete successfully : " + id);
        }
    }


    public void edit(Bid entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        logger.info("Bid edit successfully  : " + entity);
    }

    public Bid getById(Integer id) {
        return (Bid) sessionFactory.getCurrentSession().get(Bid.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Bid> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Bid").list();
    }
}
