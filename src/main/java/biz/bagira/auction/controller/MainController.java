package biz.bagira.auction.controller;

import biz.bagira.auction.entities.*;
import biz.bagira.auction.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private ProfileService profileService;
    @Autowired
    AddressService addressService;
    @Autowired
    @Qualifier("tokenDAO")
    private PersistentTokenRepository persistentTokenRepository;

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
        return modelAndView;
    }

    @RequestMapping(value = "/register", method = {RequestMethod.GET, RequestMethod.POST})
    public String register(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/newuser", method = {RequestMethod.POST})
    public String newUser(@Validated User user,
                          BindingResult result, ModelMap model) {

        logger.info("<><><><>NEW USER : " + user);
        if (result.hasErrors()) {
            return "register";
        }
        User byName = userService.getByName(user.getLogin());

        if (byName == null) {

            user.addUserProfile(profileService.getById(1));
            userService.create(user);
            logger.info("NEW USER CREATED : " +user.getLogin());
        }
        logger.info("userId" + user.getIdUsers());
        model.addAttribute("userId", user.getIdUsers());
        model.addAttribute("address", new Address());
//        return "redirect:/index";
        return "/stepTwo";
    }

    @RequestMapping(value = "/stepTwo", method = {RequestMethod.POST})
    public String newUserStep2(@Validated Address address,
                               @RequestParam("chosenSel")String chosenSel,
                               @RequestParam("firstName")String firstName,
                               @RequestParam("lastName")String lastName,
                               @RequestParam("postalCode")String postalCode,
                               ModelMap model){
           logger.info("adress : " + address);
            logger.info("chosenSel: "+ chosenSel+"firstName: "+ firstName +"lastName "+lastName+"postalCode "+postalCode);
        Integer userId = address.getUserId();
        User user = userService.getById(userId);
         user.setFirstName(chosenSel+" "+firstName);
        user.setLastName(lastName);
        address.setCity(postalCode+" "+address.getCity());
        user.setAddress(address);
        address.setUser(user);
        logger.info("USER BEFORE UPDATE : " +user);
//        userService.edit(user);
         addressService.create(address);
         model.addAttribute("userId",userId);
        return "/stepThree";
    }

    @RequestMapping(value = "/stepThree", method = RequestMethod.POST)
    public String newUserStep3(@RequestParam("userId")Integer userId, @RequestParam("file") MultipartFile file ) {
         logger.info("userId : "+userId);
        logger.info("file : "+file);
        if (file!=null)
        {
            try {
                byte[] bytes = file.getBytes();
                logger.info("file length: "+ bytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            // put to model username here
        return "redirect:/login";
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

    @RequestMapping(value = "/cat/{id}/{startPos}/{quantity}", method = RequestMethod.GET)
    public
    @ResponseBody
    JSONItems getItemsByCategory(@PathVariable("id") Integer categoryId, @PathVariable Integer startPos, @PathVariable Integer quantity) {
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
