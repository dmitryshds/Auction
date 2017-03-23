package biz.bagira.auction.dao;

import biz.bagira.auction.entities.Item;
import org.dbunit.dataset.CompositeDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Dmitriy on 21.03.2017.
 */
public class ItemDAOTest extends AbstractEntityDaoTest {

    @Autowired
    ItemDAO itemDAO;

    @Override
    protected IDataSet getDataSet() throws Exception {
        IDataSet[] datasets = new IDataSet[]{
                        new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("category.xml")),
                        new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("items.xml")),
                        new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("users.xml"))

                };
                return new CompositeDataSet(datasets);
    }

    @Test
    public void searchForItemsTest(){
//         String search = "for old";
//         String search = "ite";
        try {
            itemDAO.indexItems();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String search = "users";
//        Set<Item> items = itemDAO.searchForItems(search);
        List<Item> items = itemDAO.searchForItems(search,0,100);
//        System.out.println(">>>>>>>>>>>>>>>>>>> "+items);
        for (Item item : items) {
            System.out.println("<<<<<<<<<<<<<< "+item);
        }
//        Set<Item> all = itemDAO.getAll();
//        for (Item item : all) {
////            System.out.println("SET ITEMS<><><><?> "+item);
//        }
    }
}
