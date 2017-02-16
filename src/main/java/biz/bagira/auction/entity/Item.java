package biz.bagira.auction.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Dmitriy on 18.01.2017.
 */

@Entity
@Table(name = "ITEMS")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ITEMS")
    private Integer itemId;

    @Column(name = "OWNER_ID")
//    @ManyToOne
//    @JoinTable(name = "USERS", joinColumns = @JoinColumn(name = "ID_USERS"))
    private Integer ownerId;

//    @ManyToOne
    @Column(name = "CATEGORY_ID")
//    @JoinTable(name = "CATEGORY", joinColumns = @JoinColumn(name = "ID_CATEGORY"))
    private Integer categoryId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "INITIAL_PRICE")
    private BigDecimal price;

    @Column(name = "BUYNOW_PRICE")
    private BigDecimal buyNowPrice;

    @Column(name = "DATE_START")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "DATE_FINISH")
    @Temporal(TemporalType.TIMESTAMP)
    private Date finishDate;

    @Column(name="PICTURES")
    private String pathToImages;

//    @OneToMany(mappedBy = "item")
//    private Set<Bid> bidList;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer owner) {
        this.ownerId = owner;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer category) {
        this.categoryId = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getBuyNowPrice() {
        return buyNowPrice;
    }

    public void setBuyNowPrice(BigDecimal buyNowPrice) {
        this.buyNowPrice = buyNowPrice;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public String getPathToImages() {
        return pathToImages;
    }

    public void setPathToImages(String pathToImages) {
        this.pathToImages = pathToImages;
    }

//    public Set<Bid> getBidList() {
//        return bidList;
//    }
//
//    public void setBidList(Set<Bid> bidList) {
//        this.bidList = bidList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemId != null ? !itemId.equals(item.itemId) : item.itemId != null) return false;
        if (ownerId != null ? !ownerId.equals(item.ownerId) : item.ownerId != null) return false;
        return categoryId != null ? categoryId.equals(item.categoryId) : item.categoryId == null;

    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }
}
