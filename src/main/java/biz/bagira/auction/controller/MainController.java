package biz.bagira.auction.controller;

import biz.bagira.auction.entities.Category;
import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.JSONItems;
import biz.bagira.auction.service.CategoryService;
import biz.bagira.auction.service.ItemService;
import biz.bagira.auction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Dmitriy on 22.01.2017.
 */
@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;


    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
//    @Autowired
//    AuthenticationTrustResolver authenticationTrustResolver;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public ModelAndView index() {
        List<Category> categories = categoryService.getAll();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categories);
//        modelAndView.addObject("user", new User());
//        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ROOT: "+Bid.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return modelAndView;
    }







    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public String accessDenied() {

        return "accessDenied";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }
        model.setViewName("login");

        return model;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        logger.info("<><><><><><>AUTH NAME: " + auth.getName());

        if (auth != null) {

            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);

        }
        return "redirect:/index";
    }

    @RequestMapping(value = "/cat/{id}/{startPos}/{quantity}", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONItems getItemsByCategory(@PathVariable("id") Integer categoryId,
                                 @PathVariable Integer startPos,
                                 @PathVariable Integer quantity) {
        List<Item> items = itemService.getLimitItemsByCategoryId(categoryId, startPos, quantity);
        Integer count = itemService.getCountItemsByCategory(categoryId);
        logger.info(items.toString());
        JSONItems jsonItems = new JSONItems();
        jsonItems.setCountItemsByCategory(count);
        jsonItems.setItemList(items);
        jsonItems.setStart(startPos);
        jsonItems.setQuantity(quantity);
        return jsonItems;
    }



    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    /**
     * This method returns true if users is already authenticated [logged-in], else false.
     */
//    private boolean isCurrentAuthenticationAnonymous() {
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        return authenticationTrustResolver.isAnonymous(authentication);
//    }

}
