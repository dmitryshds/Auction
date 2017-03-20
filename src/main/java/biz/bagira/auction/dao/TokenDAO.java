package biz.bagira.auction.dao;

import biz.bagira.auction.entities.PersistentLogin;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Dmitriy on 18.02.2017.
 */
@Repository("tokenDAO")
@Transactional
public class TokenDAO implements PersistentTokenRepository {

    private static final Logger logger = LoggerFactory.getLogger(TokenDAO.class);

    @Autowired
    private SessionFactory sessionFactory;


    public void createNewToken(PersistentRememberMeToken token) {
        PersistentLogin persistentLogin = new PersistentLogin();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        sessionFactory.getCurrentSession().save(persistentLogin);
        logger.info("creating token for user : " + token.getUsername());
    }

    public void updateToken(String series, String tokenValue, Date lastUsed) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("series", series));
        logger.info("updateToken");
        PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
        if (persistentLogin != null) {
            persistentLogin.setToken(tokenValue);
            persistentLogin.setLast_used(lastUsed);
            sessionFactory.getCurrentSession().update(persistentLogin);
            logger.info("updating token for series : " + series);

        }
    }

    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PersistentLogin.class);
            criteria.add(Restrictions.eq("series", seriesId));
            logger.info("getTokenForSeries");
            PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            logger.info("Token for series : " + seriesId + " not found...");
            return null;
        }
    }

    public void removeUserTokens(String username) {

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PersistentLogin.class);
        criteria.add(Restrictions.eq("username", username));
        logger.info("removeUserTokens");
        List<PersistentLogin> list = criteria.list();
               if (list.size() > 0) {
            for (PersistentLogin persistentLogin : list) {
                sessionFactory.getCurrentSession().delete(persistentLogin);
                logger.info("Token for series : " + username + " successful deleted");
            }
        }

    }


}
