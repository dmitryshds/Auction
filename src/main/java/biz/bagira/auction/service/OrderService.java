package biz.bagira.auction.service;

import biz.bagira.auction.dao.OrderDAO;
import biz.bagira.auction.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dmitriy on 25.01.2017.
 */

@Service("orderService")
@Transactional
public class OrderService {

    @Autowired
    @Qualifier(value = "orderDAO")
    private OrderDAO orderDAO;


    public void create(Order entity) {
        orderDAO.create(entity);

    }

    public void delete(Order entity) {
        orderDAO.delete(entity);
    }

    public void delete(Integer userId) {
        orderDAO.delete(userId);
    }

    public void edit(Order entity) {
        orderDAO.edit(entity);
    }

    public Order getById(Integer id) {
        return orderDAO.getById(id);
    }

    public List<Order> getAll() {
        return orderDAO.getAll();
    }

    public OrderDAO getUserDAO() {
        return orderDAO;
    }

}
