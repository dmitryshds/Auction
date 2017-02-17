package biz.bagira.auction.service;

import biz.bagira.auction.dao.ItemDAO;
import biz.bagira.auction.entities.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Dmitriy on 24.01.2017.
 */

@Service("itemService")
@Transactional
public class ItemService {

    @Autowired
    @Qualifier(value = "itemDAO")
    private ItemDAO itemDAO;


    public void create(Item entity) { itemDAO.create(entity); }

    public void delete(Item entity) {
        itemDAO.delete(entity);
    }

    public void delete(Integer userId) {
        itemDAO.delete(userId);
    }

    public void edit(Item entity) {
        itemDAO.edit(entity);
    }

    public Item getById(Integer id) {
        return itemDAO.getById(id);
    }

    public Set<Item> getAll() {
        return itemDAO.getAll();
    }

    public List<Item> getItemsByCategory(Integer categoryId){ return itemDAO.getItemsByCategoryId(categoryId);}

    public List<Item> getLimitItemsByCategoryId(Integer categoryId, Integer from, Integer rows){return itemDAO.getLimitItemsByCategoryId(categoryId,from,rows);}

    public Integer getCountItemsByCategory(Integer categoryId){ return itemDAO.getCountItemsByCategory(categoryId);}

    public ItemDAO getItemDAO() {
        return itemDAO;
    }


}
