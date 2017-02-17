package biz.bagira.auction.dao;

import biz.bagira.auction.entities.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Dmitriy on 19.01.2017.
 */

@Repository("userDAO")
public class UserDAO implements AbstractDAO<User> {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
   	private PasswordEncoder encoder;


    public void create(User entity) {

        entity.setPassword(encoder.encode(entity.getPassword()));
        sessionFactory.getCurrentSession().save(entity);
        logger.info("User add successfully : " + entity);
    }

    public void delete(User entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = (User) currentSession.load(User.class, entity.getIdUsers());
        if (user != null) {
            currentSession.delete(user);
            logger.info("User delete successfully : " + entity);
        }

    }

    public void delete(Integer userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        User user = (User) currentSession.load(User.class, userId);
        if (user != null) {
            currentSession.delete(user);
            logger.info("User delete successfully userId : " + userId);
        }

    }


    public void edit(User entity) {

        sessionFactory.getCurrentSession().update(entity);
        logger.info("User edit successfully  : " + entity);
    }

    public User getById(Integer id) {

        return (User) sessionFactory.getCurrentSession().get(User.class, id);
    }

    public User getByName(String name){
        Session currentSession = sessionFactory.getCurrentSession();
         SQLQuery query = currentSession.createSQLQuery("SELECT * FROM users u WHERE u.login = :name ");
         query.addEntity(User.class);
         query.setParameter("name", name);
        User user = (User) query.uniqueResult();
        logger.info("User getByName : "+user);
        return user;
    }

    public Set<User> getAll() {
        List list = sessionFactory.getCurrentSession().createQuery("from User").list();
        return new HashSet<User>(list);
    }

}
