package biz.bagira.auction.dao;

import biz.bagira.auction.entities.UserProfile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Dmitriy on 13.02.2017.
 */
@Repository("profileDAO")
public class ProfileDAO implements AbstractDAO<UserProfile> {

    private static final Logger logger = LoggerFactory.getLogger(ProfileDAO.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void create(UserProfile entity) {
        sessionFactory.getCurrentSession().save(entity);
        logger.info("UserProfile add successfully : " + entity);
    }

    public void delete(UserProfile entity) {
        Session currentSession = sessionFactory.getCurrentSession();
        UserProfile profile = (UserProfile) currentSession.load(UserProfile.class, entity.getId());
        if (profile != null) {
            currentSession.delete(profile);
            logger.info("UserProfile delete successfully : " + entity);
        }

    }

    public void delete(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        UserProfile profile = (UserProfile) currentSession.load(UserProfile.class, id);
        if (profile != null) {
            currentSession.delete(profile);
            logger.info("UserProfile delete successfully : " + id);
        }
    }

    public void edit(UserProfile entity) {
        sessionFactory.getCurrentSession().update(entity);
         logger.info("UserProfile edit successfully  : " + entity);
    }

    public UserProfile getById(Integer id) {
        return (UserProfile) sessionFactory.getCurrentSession().get(UserProfile.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<UserProfile> getAll() {
        return  sessionFactory.getCurrentSession().createQuery("from User").list();
    }
}
