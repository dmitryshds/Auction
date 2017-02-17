package biz.bagira.auction.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Dmitriy on 01.02.2017.
 */
public class JSONItems {

    @JsonProperty("count")
    private Integer countItemsByCategory;
    @JsonProperty("start")
    private Integer start;
    @JsonProperty("quantity")
    private Integer quantity;
    private List<Item> itemList;

    public Integer getCountItemsByCategory() {
        return countItemsByCategory;
    }

    public void setCountItemsByCategory(Integer countItemsByCategory) {
        this.countItemsByCategory = countItemsByCategory;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
