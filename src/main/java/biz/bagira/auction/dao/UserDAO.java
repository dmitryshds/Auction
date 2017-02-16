package biz.bagira.auction.dao;

import biz.bagira.auction.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitriy on 19.01.2017.
 */

@Repository
//@Qualifier(value = "userDAO")
public class UserDAO implements AbstractDAO<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    @Autowired
    private SessionFactory sessionFactory;




    public void create(User entity) {

        sessionFactory.getCurrentSession().persist(entity);
        logger.info("User add succssfully : " + entity);
    }

    public void delete(User entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = (User) currentSession.load(User.class, entity.getUserId());
        if (user != null) {
            currentSession.delete(user);
        }
        logger.info("User delete succssfully : " + entity);
    }

    public void delete(int userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = (User) currentSession.load(User.class, userId);
        if (user != null) {
            currentSession.delete(user);
        }
        logger.info("User delete succssfully userId : " + userId);
    }


    public void edit(User entity) {

        sessionFactory.getCurrentSession().update(entity);
        logger.info("User edit succssfully  : " + entity);
    }

    public User getById(Integer id) {

        return (User) sessionFactory.getCurrentSession().load(User.class, id);
    }

    public Set<User> getAll() {
        List list = sessionFactory.getCurrentSession().createQuery("from users").list();
        return new HashSet<User>(list);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
           this.sessionFactory = sessionFactory;
       }
}
