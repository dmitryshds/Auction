package biz.bagira.auction.controller;

import biz.bagira.auction.configuration.ApplicationConfig;
import biz.bagira.auction.configuration.HibernateConfiguration;
import biz.bagira.auction.configuration.SecurityConfiguration;
import biz.bagira.auction.entities.Item;
import biz.bagira.auction.service.CategoryService;
import biz.bagira.auction.service.ItemService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by Dmitriy on 22.03.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, HibernateConfiguration.class, SecurityConfiguration.class})
@WebAppConfiguration
public class Test2 {

    @Autowired
          ItemService itemService;
   @Autowired
    CategoryService categoryService;

    @org.junit.Test
       public void selectTest(){
        itemService.index();
           String search = "item";
           String search2 = "Acces";
//                  Set<Item> items = itemDAO.searchForItems(search);
                  List<Item> items = itemService.search(search2,0,100);
//                  Set<Item> items = itemService.getAll();
//               List<Category> items = categoryService.getAll();
//                  System.out.println(">>>>>>>>>>>>>>>>>>> "+itemService);
//                  System.out.println(">>>>>>>>>>>>>>>>>>> "+items);
                  for (Item item : items) {
                      System.out.println("<<<<<<<<<<<<<< "+item);
                  }
}           }
