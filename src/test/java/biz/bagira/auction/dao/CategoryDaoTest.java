package biz.bagira.auction.dao;

import biz.bagira.auction.entities.Category;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Dmitriy on 19.03.2017.
 */
public class CategoryDaoTest extends AbstractEntityDaoTest {

   @Autowired
   private CategoryDAO categoryDAO;


    protected IDataSet getDataSet() throws Exception {
        IDataSet[] datasets = new IDataSet[]{
                new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("category.xml"))

        };
        return new CompositeDataSet(datasets);
    }

    @Test
    public void getById() {
        Assert.assertNotNull(categoryDAO.getById(1));
        Assert.assertNull(categoryDAO.getById(3));
    }

    @Test
    public void getAll() {
        List<Category> all = categoryDAO.getAll();
        when(mock(categoryDAO.getClass()).getAll()).thenReturn(all);
        Assert.assertEquals(all.size(), 2);
    }

    @Test
    public void create() {
        categoryDAO.create(getNewCategory());
        Assert.assertEquals(categoryDAO.getAll().size(), 3);
    }

    @Test
    public void update() {
        Category byId = categoryDAO.getById(2);
        byId.setType("NEW_TYPE");
        categoryDAO.edit(byId);
        Assert.assertEquals(categoryDAO.getById(2).getType(), "NEW_TYPE");

    }

    @Test
    public void delete() {
        Integer categoryId = categoryDAO.create(getNewCategory());
        Category category = categoryDAO.getById(categoryId);
        Assert.assertEquals(categoryDAO.getAll().size(), 3);
        categoryDAO.delete(categoryId);
        Assert.assertEquals(categoryDAO.getAll().size(), 2);

    }



    private Category getNewCategory() {
        Category category = new Category();
        category.setType("NEW_CATEGORY");
        return category;
    }
}
