package biz.bagira.auction.service;

import biz.bagira.auction.dao.ProfileDAO;
import biz.bagira.auction.entities.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dmitriy on 13.02.2017.
 */

@Service ("profileService")
@Transactional
public class ProfileService {

    @Autowired
    @Qualifier(value = "profileDAO")
    private ProfileDAO profileDAO;

    public void create(UserProfile entity) {
        profileDAO.create(entity);

    }

    public void delete(UserProfile entity) {
        profileDAO.delete(entity);
    }

    public void delete(Integer id) {
        profileDAO.delete(id);
    }

    public void edit(UserProfile entity) {
        profileDAO.edit(entity);
    }

    public UserProfile getById(Integer id) {
        return profileDAO.getById(id);
    }

    public List<UserProfile> getAll() {
        return profileDAO.getAll();
    }


}
