package biz.bagira.auction.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by Dmitriy on 23.01.2017.
 */

@Entity
@Table(name = "ITEMS")
public class Item {

    @JsonProperty("idItems")
    private Integer idItems;

    @JsonIgnore
    private User owner;

    @JsonIgnore
//    private List<Category> categoryList = new ArrayList<Category>();
    private Category category;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("initialPrice")
    private BigDecimal initialPrice;

    @JsonProperty("buynowPrice")
    private BigDecimal buynowPrice;

    @JsonProperty("dateStart")
    private Timestamp dateStart;

    @JsonProperty("pictures")
    private List<String> pictures = new ArrayList<>(0);

    @JsonProperty("dateFinish")
    private Timestamp dateFinish;

    private String state;

    @JsonIgnore
    private Set<Bid> bidSet = new ConcurrentSkipListSet<Bid>();

    @JsonIgnore
    private Order order;

    public Item() {
        state = State.ACTIVE.getState();
    }

    @Id
    @Column(name = "ID_ITEMS")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdItems() {
        return idItems;
    }

    public void setIdItems(Integer idItems) {
        this.idItems = idItems;
    }

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "OWNER_ID")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    @ManyToOne(cascade = { CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    @NotNull
//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
//    @JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "ITEM_ID"), inverseJoinColumns = @JoinColumn(name = "CATEGORY_ID"))
//    //@OrderBy("type")
//    public Category getCategory() {
//        return category;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }

//    public void addCategory(Category category)
//    {
//        categoryList.add(category);
//
//    }
    public void addBid(Bid bid) {
        bidSet.add(bid);
    }

    @NotNull
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Column(name = "INITIAL_PRICE")
    public BigDecimal getInitialPrice() {
        return initialPrice;
    }

    public void setInitialPrice(BigDecimal initialPrice) {
        this.initialPrice = initialPrice;
    }

    @Column(name = "BUYNOW_PRICE")
    public BigDecimal getBuynowPrice() {
        return buynowPrice;
    }

    public void setBuynowPrice(BigDecimal buynowPrice) {
        this.buynowPrice = buynowPrice;
    }

    @NotNull
    @Column(name = "DATE_START")
    public Timestamp getDateStart() {
        return dateStart;
    }

    public void setDateStart(Timestamp dateStart) {
        this.dateStart = dateStart;
    }

    @ElementCollection()
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(name="PICTURES", joinColumns=@JoinColumn(name="ID_ITEM"))
    @Column(name="PATH")
    public List<String> getPictures() {
        return pictures;
    }

    public void addPicture(String path){
          pictures.add(path);
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    @NotNull
    @Column(name = "DATE_FINISH")
    public Timestamp getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(Timestamp dateFinish) {
        this.dateFinish = dateFinish;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "item")
    @OrderBy("BID")
    public Set<Bid> getBidSet() {
        return bidSet;
    }

    public void setBidSet(Set<Bid> bidSet) {
        this.bidSet = bidSet;
    }
    ////???
    @OneToOne(mappedBy = "item" , fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    @Column(name="STATE")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (idItems != null ? !idItems.equals(item.idItems) : item.idItems != null) return false;
        if (owner != null ? !owner.equals(item.owner) : item.owner != null) return false;
        if (name != null ? !name.equals(item.name) : item.name != null) return false;
        return initialPrice != null ? initialPrice.equals(item.initialPrice) : item.initialPrice == null;

    }

    @Override
    public int hashCode() {
        int result = idItems != null ? idItems.hashCode() : 0;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (initialPrice != null ? initialPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItems=" + idItems +


                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", initialPrice=" + initialPrice +
                ", buynowPrice=" + buynowPrice +
                ", dateStart=" + dateStart +
                ", pictures='" + pictures + '\'' +
                ", dateFinish=" + dateFinish +

                '}';
    }
}
