package biz.bagira.auction.controller;

import biz.bagira.auction.entities.User;
import biz.bagira.auction.service.CategoryService;
import biz.bagira.auction.service.ItemService;
import biz.bagira.auction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Dmitriy on 09.03.2017.
 */
@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model){
      model.addAttribute("users",userService.getAll());
      model.addAttribute("category",categoryService.getAll());
      return "/admin/admin";
    }


    @RequestMapping(value = "/admin/edit/user/{idUsers}", method = RequestMethod.GET)
    public String editUser(@PathVariable("idUsers")Integer idUsers, ModelMap modelMap){

        modelMap.addAttribute("user",userService.getById(idUsers));
        return "/admin/editUser";
    }

    @RequestMapping(value = "/admin/editUser", method = RequestMethod.POST)
    public String saveChanges(@RequestParam("idUsers")Integer idUsers,
                              @RequestParam(name = "titles") String titles,
                              ModelMap modelMap){
        User user = userService.getById(idUsers);
        user.setState(titles.toUpperCase());
        userService.edit(user);
        modelMap.addAttribute("user", user);
        return "redirect: /admin/edit/user/"+user.getIdUsers();
    }
}
