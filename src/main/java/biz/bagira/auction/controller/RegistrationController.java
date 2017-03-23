package biz.bagira.auction.controller;

import biz.bagira.auction.entities.Title;
import biz.bagira.auction.entities.User;
import biz.bagira.auction.service.ProfileService;
import biz.bagira.auction.service.UserService;
import biz.bagira.auction.util.ImageUtil;
import biz.bagira.auction.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.security.Principal;

/**
 * Created by Dmitriy on 21.02.2017.
 */
@Controller
public class RegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(RegistrationController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ProfileService profileService;
    @Autowired
    ImageUtil imageUtil;
    @Autowired
    MailUtil mailUtil;


    @RequestMapping(value = "/register", method = {RequestMethod.GET})
    public String register(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "register";
    }


    @RequestMapping(value = "/newuser", method = {RequestMethod.POST})
    public String newUser(@Valid User user,
                          @RequestParam(name = "titles") String titles,
                          BindingResult result, ModelMap model) {

        if (result.hasErrors()) {
            model.addAttribute("error", "Invalid parameter");
            return "register";
        }
        User byName = userService.getByName(user.getLogin());
        if (byName == null) {
            user.addUserProfile(profileService.getById(1));
            user.setTitle(Title.valueOf(titles.toUpperCase()));
            userService.create(user);


            logger.info("NEW USER CREATED : " + user.getLogin());
        }
        model.addAttribute("userId", user.getIdUsers());
        return "/stepTwo";
    }


    @RequestMapping(value = "/stepTwo", method = RequestMethod.POST)
    public String newUserStep3(@RequestParam Integer userId,
                               @RequestParam("file") MultipartFile file,
                               HttpServletRequest request) {
        String pathToFile = "";
        User user = userService.getById(userId);
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                pathToFile = imageUtil.saveAvatar(userId, bytes);

                if (ImageUtil.isValidString(pathToFile)) {
                    user.setPicture(pathToFile);
                }
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        if (user.getPicture() == null) {
            String path = imageUtil.getRootFolder() + File.separator + "adm"+File.separator;
            String name = "avat.jpg";
            File avat = new File(path + File.separator + name);
            if (avat.exists()) {
                byte[] bytes = imageUtil.downloadPicture(path, name);
                pathToFile = imageUtil.saveAvatar(userId, bytes);

                if (ImageUtil.isValidString(pathToFile)) {
                    user.setPicture(pathToFile);
                }
            }
        }
        userService.edit(user);
        mailUtil.sendConfirmMessage(user);
        request.getSession().setAttribute("loginName", user.getLogin());
        return "redirect:/login";
    }


    @RequestMapping(value = "/userlogin/{login}", method = RequestMethod.POST)
    public
    @ResponseBody
    Boolean getItemsByCategory(@PathVariable("login") String userLogin) {

        User byName = userService.getByName(userLogin);
        logger.info("BY NAME = " + byName);
        if (byName == null) {
            return true;
        }
        return false;
    }


    @RequestMapping(value = "/confirm/{name}/{code}", method = {RequestMethod.GET})
    public String confirmEmail(@NotNull @PathVariable("name") String
                                       userName, @NotNull @PathVariable("code") String code) {
        User byName = userService.getByName(userName);
        if (byName != null) {
            String randomCode = byName.getPassword().substring(7, 17).replace(".", "").replace("/", "");
            if (randomCode.equals(code)) {
                byName.setValidateEmail(true);
                userService.edit(byName);
                logger.info("confirmEmail SUCCESS");
                return "redirect:/index";
            }

        }
        logger.info("confirmEmail FAIL");
        return "error";
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String myAccount(ModelMap model, Principal principal) {
        User user = userService.getByName(principal.getName());
        String canonicalPath = user.getPicture();
        if (canonicalPath != null) {
            canonicalPath = canonicalPath.replace(imageUtil.getRootFolder(), "");
            canonicalPath = canonicalPath.replace('\\', '/').replace('\\', '/');
            user.setPicture(canonicalPath);
        }
        model.addAttribute("user", user);
        return "account";
    }


    @RequestMapping(value = "/re-confirmation", method = RequestMethod.POST)
    public String reConfirmEmail(@RequestParam("userId")Integer userId){
        logger.info("reConfirmEmail userId: "+userId);
        User user = userService.getById(userId);
        mailUtil.sendConfirmMessage(user);
        return "redirect: /account";
    }

}

