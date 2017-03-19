package biz.bagira.auction.service;

import biz.bagira.auction.entities.Item;
import biz.bagira.auction.entities.Order;
import biz.bagira.auction.entities.State;
import biz.bagira.auction.entities.User;
import biz.bagira.auction.util.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Timestamp;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by Dmitriy on 16.03.2017.
 */
public class SheduledTasks {
    private static final Logger logger = LoggerFactory.getLogger(SheduledTasks.class);

    private static final long DAY = 1000 * 60 * 60 * 24;
    @Autowired
    ItemService itemService;
    @Autowired
    MailUtil mailUtil;
    @Autowired
    OrderService orderService;

    private Set<Item> oneDayLeftItems = new ConcurrentSkipListSet<>();


    @Scheduled(fixedRate = SheduledTasks.DAY)
    public void fillSetItemOneDayLeft() {
        oneDayLeftItems.clear();
        Set<Item> all = itemService.getAll();
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        Timestamp oneDayLeft = new Timestamp(currentDate.getTime() + DAY);
        for (Item item : all) {
            Timestamp dateFinish = item.getDateFinish();
            if (dateFinish.before(oneDayLeft)) {
                oneDayLeftItems.add(item);
            }

        }
        logger.info("OneDayLeftItems Set FILLED");
    }

    @Scheduled(fixedRate = 1000*60*5)
    public void checkItem() {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        for (Item item : oneDayLeftItems) {

            Timestamp dateFinish = item.getDateFinish();
            if (dateFinish.before(currentDate)){

              //items date finish some logic here
                item.setState(State.INACTIVE.getState());
                itemService.edit(item);
                //get bidder, create order and send e-mail
                User winner = mailUtil.sendMessagesWhenItemsDateOff(item);
                Order order = new Order();
                order.setItem(item);
                order.setWinner(winner);
                orderService.create(order);
               logger.info("Item id = "+item.getIdItems() + " was sold");
            }

        }

    }


}
