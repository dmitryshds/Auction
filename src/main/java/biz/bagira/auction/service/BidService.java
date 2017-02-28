package biz.bagira.auction.service;

import biz.bagira.auction.dao.BidDAO;
import biz.bagira.auction.entities.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dmitriy on 25.01.2017.
 */
@Service("bidService")
@Transactional
public class BidService {
    @Autowired
    @Qualifier(value = "bidDAO")
    private BidDAO bidDAO;


    public void create(Bid entity) {
        bidDAO.create(entity);
    }

    public void delete(Bid entity) {
        bidDAO.delete(entity);
    }

    public void delete(Integer userId) {
        bidDAO.delete(userId);
    }

    public void edit(Bid entity) {
        bidDAO.edit(entity);
    }

    public Bid getById(Integer id) {
        return bidDAO.getById(id);
    }

    public List<Bid> getAll() {
        return bidDAO.getAll();
    }

}
