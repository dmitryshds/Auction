package biz.bagira.auction.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dmitriy on 18.01.2017.
 */
@Embeddable
@Entity
@Table(name = "BIDS")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BID")
    private Integer bidId;

    @Column(name = "ITEM_ID")
    @ManyToOne
    @JoinTable(name = "ITEMS")
    //@JoinTable(name = "ITEMS", joinColumns = @JoinColumn(name = ""))
    private Item item;

    @Column(name = "USER_ID")
    private User bidder;

    @Column(name = "BID")
    private BigDecimal bidValue;

    @Column(name = "BID_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Integer getBidId() {
        return bidId;
    }

    public void setBidId(Integer bidId) {
        this.bidId = bidId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getBidder() {
        return bidder;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }

    public BigDecimal getBidValue() {
        return bidValue;
    }

    public void setBidValue(BigDecimal bidValue) {
        this.bidValue = bidValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (bidId != null ? !bidId.equals(bid.bidId) : bid.bidId != null) return false;
        return item != null ? item.equals(bid.item) : bid.item == null;

    }

    @Override
    public int hashCode() {
        int result = bidId != null ? bidId.hashCode() : 0;
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}
