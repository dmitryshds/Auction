package biz.bagira.auction.service;

import biz.bagira.auction.dao.CategoryDAO;
import biz.bagira.auction.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dmitriy on 24.01.2017.
 */

@Service ("categoryService")
@Transactional
public class CategoryService {
    @Autowired
    @Qualifier(value = "categoryDAO")
    private CategoryDAO categoryDAO;


    public void create(Category entity) {
        categoryDAO.create(entity);
    }

    public void delete(Category entity) {
        categoryDAO.delete(entity);
    }

    public void delete(Integer userId) {
        categoryDAO.delete(userId);
    }

    public void edit(Category entity) {
        categoryDAO.edit(entity);
    }

    public Category getById(Integer id) {
        return categoryDAO.getById(id);
    }

    public List<Category> getAll() {
        return categoryDAO.getAll();
    }

    public CategoryDAO getItemDAO() {
        return categoryDAO;
    }


}
