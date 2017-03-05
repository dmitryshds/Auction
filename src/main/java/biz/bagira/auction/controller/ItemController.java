package biz.bagira.auction.controller;

import biz.bagira.auction.entities.Category;
import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.JSONItems;
import biz.bagira.auction.entities.User;
import biz.bagira.auction.service.CategoryService;
import biz.bagira.auction.service.ItemService;
import biz.bagira.auction.service.UserService;
import biz.bagira.auction.util.DateUtil;
import biz.bagira.auction.util.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Dmitriy on 01.03.2017.
 */

@Controller
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    ImageUtil imageUtil;
    @Autowired
    ItemService itemService;

    @RequestMapping(value = {"/additem", "/item"}, method = {RequestMethod.GET})
    public String register(ModelMap modelMap) {
        modelMap.addAttribute("category", categoryService.getAll());
        return "item";
    }

    @RequestMapping(value = "/newitem", method = {RequestMethod.POST})
    public String addItem(ModelMap model, RedirectAttributes redirectAttributes,
                          @RequestParam("files") MultipartFile[] files,
                          @RequestParam("userName") String userName,
                          @RequestParam("categoryType") String categoryType,
                          @RequestParam("name") String name,
                          @RequestParam("description") String description,
                          @RequestParam("initialPrice") BigDecimal initialPrice,
                          @RequestParam("buynowPrice") BigDecimal buynowPrice,
                          @RequestParam("dateFinish") String dateFinish) {
        User user = userService.getByName(userName.trim());
        Category category = categoryService.getByName(categoryType);
        Item item = new Item();
        item.setOwner(user);
        item.addCategory(category);
        item.setName(name);
        item.setDescription(description);
        item.setInitialPrice(initialPrice);
        item.setBuynowPrice(buynowPrice);
        item.setDateFinish(DateUtil.convert(dateFinish));
        Integer itemId = itemService.create(item);
        item.setIdItems(itemId);


        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isEmpty()) {
                continue;
            }
            try {
                String pathToImage = imageUtil.saveImage(user.getIdUsers(), itemId, files[i].getBytes(), i);
                sb.append(pathToImage + ";");
            } catch (IOException e) {
                logger.info(e.getMessage());
            }
        }
        item.setPictures(sb.toString().substring(0, sb.length() - 1));
        itemService.edit(item);
        logger.info("Item added = " + itemId);
        redirectAttributes.addFlashAttribute("message", "You successfully added item");

        return "redirect:/item";
    }


    @RequestMapping(value = "/cat/{id}/{startPos}/{quantity}", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONItems getItemsByCategory(@PathVariable("id") Integer categoryId,
                                 @PathVariable Integer startPos,
                                 @PathVariable Integer quantity) {
        List<Item> items = itemService.getLimitItemsByCategoryId(categoryId, startPos, quantity);
        if (items.size() > 0) {
            for (Item item : items) {
                String pictures = item.getPictures();
                if (pictures != null) {
                    String[] split = pictures.split(";");
                    item.setPictures(split[0].replace(imageUtil.getRootFolder(), "").replace('\\', '/').replace('\\', '/'));
                    logger.info("PICTURE ITEM = " + split[0].replace(imageUtil.getRootFolder(), "").replace('\\', '/').replace('\\', '/'));
                }
            }
        }
        Integer count = itemService.getCountItemsByCategory(categoryId);
        logger.info(items.toString());
        JSONItems jsonItems = new JSONItems();
        jsonItems.setCountItemsByCategory(count);
        jsonItems.setItemList(items);
        jsonItems.setStart(startPos);
        jsonItems.setQuantity(quantity);
        return jsonItems;
    }

    @RequestMapping(value = "/showItem/{itemId}", method = RequestMethod.GET)
    public String showItem(@PathVariable("itemId") Integer itemId, ModelMap modelMap){
        logger.info("Get Item  Id = "+itemId);
        Item item = itemService.getById(itemId);
        String pictures = item.getPictures();
        pictures = pictures.replace(imageUtil.getRootFolder(),"").replace('\\', '/').replace('\\', '/');;
        item.setPictures(pictures);
        modelMap.addAttribute("item",item);

        return "showItem";
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

}
