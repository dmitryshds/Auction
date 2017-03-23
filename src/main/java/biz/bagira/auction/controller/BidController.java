package biz.bagira.auction.controller;

import biz.bagira.auction.entities.Bid;
import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.User;
import biz.bagira.auction.service.BidService;
import biz.bagira.auction.service.ItemService;
import biz.bagira.auction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.security.Principal;

/**
 * Created by Dmitriy on 07.03.2017.
 */
@Controller
public class BidController {

    private static final Logger logger = LoggerFactory.getLogger(BidController.class);


    @Autowired
    private UserService userService;
    @Autowired
    private BidService bidService;
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/newbid", method = RequestMethod.POST)
   public String addBid(@NotNull @RequestParam("bid")BigDecimal bid,
                        @NotNull @RequestParam("userName") String userName,
                        @NotNull @RequestParam("itemId") Integer itemId){

        User userBidder = userService.getByName(userName.trim());
        Item item = itemService.getById(itemId);
        Bid currentBid = new Bid();
        currentBid.setItem(item);
        currentBid.setUserBidder(userBidder);
        currentBid.setBid(bid);
        logger.info("userBidder = "+userBidder);
        bidService.create(currentBid);

        return "redirect:/showItem/"+itemId;
   }

   @RequestMapping(value = "/bids", method = RequestMethod.GET)
    public String showUserBids(ModelMap model, Principal principal){
       User user = userService.getByName(principal.getName());
       model.addAttribute("bids",user.getBidSet());
       return "bids";

   }

}
