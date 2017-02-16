package biz.bagira.auction.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dmitriy on 18.01.2017.
 */
//@Embeddable
@Entity
@Table(name = "BIDS")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BID")
    private Integer bidId;

    @Column(name = "ITEM_ID")
//    @ManyToOne
//    @JoinTable(name = "ITEMS", joinColumns = @JoinColumn(name = "ID_ITEMS"))
    private Integer itemId;

    @Column(name = "USER_ID")
    //@ManyToOne
//    @JoinTable(name = "USERS", joinColumns = @JoinColumn(name = "ID_USERS"))
    private Integer bidderId;

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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer item) {
        this.itemId = item;
    }

    public Integer getBidderId() {
        return bidderId;
    }

    public void setBidderId(Integer bidder) {
        this.bidderId = bidder;
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
        return itemId != null ? itemId.equals(bid.itemId) : bid.itemId == null;

    }

    @Override
    public int hashCode() {
        int result = bidId != null ? bidId.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        return result;
    }
}
