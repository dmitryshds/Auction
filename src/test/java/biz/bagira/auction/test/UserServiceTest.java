package biz.bagira.auction.test;

import biz.bagira.auction.configuration.ApplicationConfig;
import biz.bagira.auction.configuration.HibernateConfiguration;
import biz.bagira.auction.configuration.SecurityConfiguration;
import biz.bagira.auction.service.*;
import biz.bagira.auction.util.ImageUtil;
import biz.bagira.auction.util.MailUtil;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
    MailUtil mailUtil;

    @Autowired
     private SessionFactory sessionFactory;


//    @Autowired
//    CustomUserDetailsService customUserDetailsService;

    @org.junit.Test
//    @Transactional
    public void createTest() {

//
//        Integer countItemsByCategory = itemService.getCountItemsByCategory(9);
//        List<Item> countItemsByCategory2 = itemService.getLimitItemsByCategoryId(9,0,10);
//        System.out.println(countItemsByCategory);
//        System.out.println(countItemsByCategory2);
//        List<Item> itemsByCategory = itemService.getItemsByCategory(9);
//        System.out.println(itemsByCategory);
//        User user = userService.getById(144);
//        Set<Item> itemList = user.getItemList();
//        System.out.println(itemList.size());

//        String path = new File("").getAbsolutePath();
//                   String name = "Simpson.jpg";
//                   File avat = new File(path+File.separator+"src"+File.separator+"main"+File.separator+"webapp"+File.separator+"resources"+File.separator+"img"+File.separator+"avat"+File.separator+name);
//
//        System.out.println(avat.getAbsolutePath());
//        System.out.println(avat.exists());
//          String to ="0675009780@ukr.net";
//          String subject ="Hello";
//          File file = new File("C:\\Users\\Dmitriy\\Pictures\\Simpson.jpg");
//
////          mailUtil.sendMessageWithAttach(to,subject,subject,file);
//        mailUtil.sendMail(to,subject);
    }

}
