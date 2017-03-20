package biz.bagira.auction.service;

import biz.bagira.auction.dao.UserDAO;
import biz.bagira.auction.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by Dmitriy on 20.01.2017.
 */
@Service ("userService")
@Transactional
public class UserService  {


    @Autowired
    @Qualifier(value = "userDAO")
     private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void create(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        userDAO.create(entity);

    }

    public void delete(User entity) {
        userDAO.delete(entity);
    }

    public void delete(Integer userId) {
        userDAO.delete(userId);
    }

    public void edit(User entity) {
        User user = userDAO.getById(entity.getIdUsers());
        if (user != null) {
            if (entity.getIdUsers() != null) {
                user.setIdUsers(entity.getIdUsers());
            }
            if (!entity.getPassword().equals(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(entity.getPassword()));
            }
            user.setEmail(entity.getEmail());
            user.setFirstName(entity.getFirstName());
            user.setLastName(entity.getLastName());
            user.setValidateEmail(entity.getValidateEmail());
            user.setPicture(entity.getPicture());
            user.setCountry(entity.getCountry());
            user.setState(entity.getState());
            user.setCity(entity.getCity());
            user.setZip(entity.getZip());
            user.setStreet(entity.getStreet());
            user.setHomeNumber(entity.getHomeNumber());
            user.setTitle(entity.getTitle());
            userDAO.edit(user);
        }
    }

    public User getById(Integer id) {
        return userDAO.getById(id);
    }

    public Set<User> getAll() {
        return userDAO.getAll();
    }


    public User getByName(String name){
        return userDAO.getByName(name);
    }

}
