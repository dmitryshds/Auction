package biz.bagira.auction.service;

import biz.bagira.auction.dao.UserDAO;
import biz.bagira.auction.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Dmitriy on 20.01.2017.
 */
@Service
@Qualifier(value = "userService")
@Transactional
public class UserService  {

    @Autowired
     private UserDAO userDAO;


    public void create(User entity) {
        userDAO.create(entity);

    }

    public void delete(User entity) {
        userDAO.delete(entity);
    }

    public void delete(Integer userId) {
        userDAO.delete(userId);
    }

    public void edit(User entity) {
        userDAO.edit(entity);
    }

    public User getById(Integer id) {
        return userDAO.getById(id);
    }

    public Set<User> getAll() {
        return userDAO.getAll();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

}
