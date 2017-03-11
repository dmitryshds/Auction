package biz.bagira.auction.test;

import biz.bagira.auction.configuration.ApplicationConfig;
import biz.bagira.auction.configuration.HibernateConfiguration;
import biz.bagira.auction.configuration.SecurityConfiguration;
import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.User;
import biz.bagira.auction.service.*;
import biz.bagira.auction.util.ImageUtil;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

/**
 * Created by Dmitriy on 20.01.2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, HibernateConfiguration.class, SecurityConfiguration.class})
@WebAppConfiguration
public class UserServiceTest {
    @Autowired
    @Qualifier(value = "userService")
    UserService userService;

    @Autowired
    @Qualifier(value = "itemService")
    ItemService itemService;

    @Autowired
    @Qualifier(value = "categoryService")
    CategoryService categoryService;

    @Autowired
    @Qualifier(value = "orderService")
    OrderService orderService;

    @Autowired
    @Qualifier(value = "bidService")
    BidService bidService;

    @Autowired
    ImageUtil imageUtil;
//    @Autowired
//    @Qualifier("tokenDAO")
//    TokenDAO tokenDAO;

    @Autowired
     private SessionFactory sessionFactory;


//    @Autowired
//    CustomUserDetailsService customUserDetailsService;

    @org.junit.Test
//    @Transactional
    public void createTest() {

//        TEST USER

//        User user = new User();
//        user.setLogin("test1");
//        user.setPassword("test");
//        user.setFirstName("test");
//        user.setLastName("test");
//        user.setEmail("test1@gmail.com");
//        user.setPswSalt(new byte[]{1,2,3,5});

//        TEST ADDRESS

//        Address address = new Address();
//        address.setCountry("test1");
//        address.setCity("test211");


//        user.setAddress(address);
//        address.setUser(user);


//        User owner = userService.getById(50);
//
//        item.setOwner(owner);
////
//         Category category = categoryService.getById(1);
////          System.out.println("CAtegory "+category);
//        System.out.println("ID " + category.getIdCategory());
//
//        item.setName("EUR2");
//        item.setInitialPrice(new BigDecimal(10));
//        item.setDateStart(new Timestamp(System.currentTimeMillis()));
//        item.setDateFinish(new Timestamp(System.currentTimeMillis() + 1000000000));
//        item.addCategory(category);
//

//        Item item = itemService.getById(4);
//        Set<Category> categorySet = item.getCategorySet();
//        for (Category category : categorySet) {
//            System.out.println(category.getType());
//
//        }

//        Add Items


//        Item item = new Item();
//
//        Category category = categoryService.getById(6);
//        System.out.println(category);
//
//
//        User user48 = userService.getById(50);
//        System.out.println(user48);
//
//        item.addCategory(category);
//        item.setName("MARKA 10");
//        item.setInitialPrice(new BigDecimal(10));
//        item.setDateStart(new Timestamp(System.currentTimeMillis()));
//        item.setDateFinish(new Timestamp(System.currentTimeMillis() + 1000000000));
//        item.setOwner(user48);
//
//        category.addItem(item);
//        user48.addItem(item);
//        System.out.println("================================");
//        itemService.create(item);

        //        Add Items END




//        User user50 = userService.getById(50);
//        System.out.println(user50);
//        System.out.println("==============================");
//
//        Item item1 = itemService.getById(1);
//        System.out.println(item1);
//
//        Bid bid50 = new Bid();
//        bid50.setItem(item1);
//        bid50.setUserBidder(user50);
//        bid50.setBid(new BigDecimal(20));
//        bid50.setBidDate(new Timestamp(System.currentTimeMillis()));
//
//       // bidService.create(bid50);
//
//
//        Order order = new Order();
//        order.setWinner(user50);
//        order.setItem(item1);
//
//        orderService.create(order);
//           MANY TO MANY
//        List<Item> itemsByCategory = itemService.getItemsByCategory(2);
//
//        for (Item item : itemsByCategory) {
//            System.out.println(item.getName()+": :"+item.getCategoryList().get(0).getIdCategory());
//
//        }
//        List<Item> limitItemsByCategoryId = itemService.getLimitItemsByCategoryId(6, 0, 5);
//
//        for (Item item : limitItemsByCategoryId) {
//            System.out.println(item.getIdItems());
//        }

//        Integer countItemsByCategory = itemService.getCountItemsByCategory(5);
//        System.out.println(countItemsByCategory);

//        List<Category> all = categoryService.getAll();
//        for (Category category : all) {
//            System.out.println(category.getType());

//        }

//        User user = userService.getByName("login9121");
////        User user = userService.getById(47);
//        System.out.println(user);


//        User user91 = userService.getById(93);
//          user91.setTitle(Title.valueOf("Mr"));
////        user91.setPicture("gghhghggh");
//        System.out.println(user91);
//        System.out.println(user91.getTitle());
//
//        System.out.println(Title.valueOf(Title.class,"Mrs".toUpperCase()));



//        userService.edit(user91);
        //userService.edit(byId);
//        addressService.edit(byId.getAddress());
//        User byId2 = userService.getById(93);
//        System.out.println(byId2.getPicture());
//        Address address = addressService.getById(92);
//        System.out.println(user91.getAddress());
//        addressService.delete(91);
//        Set<Item> all = itemService.getAll();
//        ("select i.* from items AS i, category_item AS ci, category AS c, pictures AS p where i.id_items=ci.item_id AND ci.category_id=c.id_category AND i.id_items=p.id_item AND c.id_category=:id ORDER BY i.ID_ITEMS");
//        List<Item> all = itemService.getLimitItemsByCategoryId(9,0,10);
//        System.out.println("ALL size = "+all.size());
//        for (Item item : all) {
//            System.out.println(item);
//        }

//        for (Item item : all) {
//            System.out.println(item.getPictures());
//        }
//        Integer countItemsByCategory = itemService.getCountItemsByCategory(9);
//        List<Item> countItemsByCategory2 = itemService.getLimitItemsByCategoryId(9,0,10);
//        System.out.println(countItemsByCategory);
//        System.out.println(countItemsByCategory2);
//        List<Item> itemsByCategory = itemService.getItemsByCategory(9);
//        System.out.println(itemsByCategory);
        User user = userService.getById(144);
        List<Item> itemList = user.getItemList();
        System.out.println(itemList.size());

    }

}
