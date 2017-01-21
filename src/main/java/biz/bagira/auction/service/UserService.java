package biz.bagira.auction.service;

import biz.bagira.auction.dao.UserDAO;
import biz.bagira.auction.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Dmitriy on 20.01.2017.
 */
//@Service
public class UserService implements DAOService<User> {
    final UserDAO userDAO;

   // @Autowired
    public UserService(@Qualifier(value = "userDAO") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void create(User entity) {
        userDAO.create(entity);

    }

    @Transactional
    public void delete(User entity) {
        userDAO.delete(entity);
    }

    @Transactional
    public void delete(Integer userId) {
        userDAO.delete(userId);
    }

    @Transactional
    public void edit(User entity) {
      userDAO.edit(entity);
    }

    @Transactional
    public User getById(Integer id) {
        return userDAO.getById(id);
    }

    @Transactional
    public Set<User> getAll() {
        return userDAO.getAll();
    }
}
