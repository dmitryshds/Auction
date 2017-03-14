package biz.bagira.auction.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;

/**
 * Created by Dmitriy on 23.01.2017.
 */
@Entity
@Table(name = "BIDS")
public class Bid implements Comparator<Bid>{
    private Integer idBid;
    private Item item;
    private User userBidder;
    private BigDecimal bid;
    private Timestamp bidDate;

    public Bid() {
        bidDate = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BID")
    public Integer getIdBid() {
        return idBid;
    }

    public void setIdBid(Integer idBid) {
        this.idBid = idBid;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "ITEM_ID")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    public User getUserBidder() {
        return userBidder;
    }

    public void setUserBidder(User userBidder) {
        this.userBidder = userBidder;
    }




    @Column(name = "BID")
    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    @Column(name = "BID_DATE")
    public Timestamp getBidDate() {
        return bidDate;
    }

    public void setBidDate(Timestamp bidDate) {
        this.bidDate = bidDate;
    }



    @Override
    public int compare(Bid o1, Bid o2) {
        return o1.getBid().compareTo(o2.getBid());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid1 = (Bid) o;

        if (idBid != bid1.idBid) return false;
        if (bid != null ? !bid.equals(bid1.bid) : bid1.bid != null) return false;
        return bidDate != null ? bidDate.equals(bid1.bidDate) : bid1.bidDate == null;

    }

    @Override
    public int hashCode() {
        int result = idBid;
        result = 31 * result + (bid != null ? bid.hashCode() : 0);
        result = 31 * result + (bidDate != null ? bidDate.hashCode() : 0);
        return result;
    }
}
