package biz.bagira.auction.controller;

import biz.bagira.auction.entities.*;
import biz.bagira.auction.service.CategoryService;
import biz.bagira.auction.service.ItemService;
import biz.bagira.auction.service.ProfileService;
import biz.bagira.auction.service.UserService;
import biz.bagira.auction.util.ImageUtil;
import biz.bagira.auction.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Set;

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
    @Autowired
    private ProfileService profileService;
    @Autowired
    ImageUtil imageUtil;
    @Autowired
    MailUtil mailUtil;

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
    public String saveChangesUsers(@RequestParam("idUsers") Integer idUsers,
                                   @RequestParam(name = "titles") String titles,
                                   ModelMap modelMap) {

        User user = userService.getById(idUsers);
        user.setState(State.valueOf(titles.toUpperCase()).getState());
        if (titles.toUpperCase().equals("LOCKED")) {
            List<UserProfile> userProfiles = user.getUserProfiles();
            userProfiles.clear();
            userProfiles.add(profileService.getById(4));

        }
        if (titles.toUpperCase().equals("ACTIVE")) {
            List<UserProfile> userProfiles = user.getUserProfiles();
            userProfiles.clear();
            userProfiles.add(profileService.getById(0));

        }

        userService.edit(user);
        modelMap.addAttribute("user", user);
        return "redirect: /admin/edit/user/"+user.getIdUsers();
    }

    @RequestMapping(value = "/admin/editItem", method = RequestMethod.POST)
    public String saveChangesItems(@RequestParam("idItems") Integer idItems,
                                   @RequestParam(name = "itemTitles") String itemTitles,
                                   ModelMap modelMap) {
        Item item = itemService.getById(idItems);
        item.setState(itemTitles.toUpperCase());
        itemService.edit(item);
        User user = userService.getById(item.getOwner().getIdUsers());

        modelMap.addAttribute("user", user);
        return "redirect: /admin/edit/user/" + user.getIdUsers();
    }

    @RequestMapping(value = "/admin/edit/category", method = RequestMethod.POST)
    public String editCategory(@RequestParam("idCategory") Integer idCategory,
                               @RequestParam("editCategory") String editCategory,
                               ModelMap modelMap) {

        Category category = categoryService.getById(idCategory);
        category.setType(editCategory);
        categoryService.edit(category);

//        modelMap.addAttribute("users",userService.getAll());
//        modelMap.addAttribute("category",categoryService.getAll());
        return "redirect: /admin";
    }

    @RequestMapping(value = "/admin/newCategory", method = RequestMethod.POST)
    public String newCategory(@RequestParam("newCategory") String newCategory, ModelMap modelMap) {
        Category category = new Category();
        category.setType(newCategory);
        categoryService.create(category);
        return "redirect: /admin";
    }

    @RequestMapping(value = "/admin/userAvatar", method = RequestMethod.POST)
    public String userAvatar(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, ModelMap modelMap) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String pathToUserAvatarFolder = imageUtil.createUserAvatarFolder("adm");

                boolean writeImage = imageUtil.writeImage(bytes, "avat.jpg", pathToUserAvatarFolder);

                if (writeImage) {
                    redirectAttributes.addAttribute("success","You successfully uploaded image");
                } else{
                    redirectAttributes.addAttribute("error","Failed download");
                }
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }

        return "redirect: /admin";
    }

    @RequestMapping(value = "/admin/sendMessage", method = RequestMethod.POST)
    public String sendMessage(@RequestParam("address")String address,
                              @RequestParam("theme") String theme,
                              @RequestParam("select")String select,
                              @RequestParam("textarea")String textarea,
                              @RequestParam("file") MultipartFile file){
        logger.info("Multifile = "+file);


               if(select.equals("ALL_USERS")){
                   Set<User> all = userService.getAll();
                   for (User user : all) {
                       mailUtil.sendMessageWithAttach(user.getEmail(),textarea,user.getTitle().getTitle(),user.getLogin(),theme,file);
                   }
               }
               if (select.equals("ITEM_OWNERS"))
               {
                   Set<Item> all = itemService.getAll();
                   for (Item item : all) {
                       User owner = item.getOwner();
                       mailUtil.sendMessageWithAttach(owner.getEmail(),textarea,owner.getTitle().getTitle(),owner.getLogin(),theme,file);

                   }
               }

                  mailUtil.sendMessageWithAttach(address,textarea,"MR(MS)","USER",theme,file);

       return "redirect: /admin";
    }
}
