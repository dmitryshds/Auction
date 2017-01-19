package biz.bagira.auction.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

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
    private User owner;

    @ManyToMany(mappedBy = "itemList")
    @Column(name = "CATEGORY_ID")
    private Category category;

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

    @OneToMany(mappedBy = "item")
    private Set<Bid> bidList;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Set<Bid> getBidList() {
        return bidList;
    }

    public void setBidList(Set<Bid> bidList) {
        this.bidList = bidList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (itemId != null ? !itemId.equals(item.itemId) : item.itemId != null) return false;
        if (owner != null ? !owner.equals(item.owner) : item.owner != null) return false;
        return category != null ? category.equals(item.category) : item.category == null;

    }

    @Override
    public int hashCode() {
        int result = itemId != null ? itemId.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
