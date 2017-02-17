package biz.bagira.auction.test;

import biz.bagira.auction.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by Dmitriy on 20.01.2017.
 */


//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration( classes = {ApplicationInitializer.class,ApplicationConfig.class, HibernateConfiguration.class})
//@ContextConfiguration(locations = {ApplicationConfig.class})
//@ContextConfiguration(locations = {"classpath: biz/bagira/auction/test/config/applicationContextTest.xml", "classpath:biz/bagira/auction/test/config/dispatcher-servletTest.xml", "classpath:biz/bagira/auction/test/config/security-configTest.xml", "classpath:hibernate.cfg.xml"})
//@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
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

//    @Autowired
//    CustomUserDetailsService customUserDetailsService;

//  @org.junit.Test
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
    }

}
