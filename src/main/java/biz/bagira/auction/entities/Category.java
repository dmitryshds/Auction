package biz.bagira.auction.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitriy on 23.01.2017.
 */
@Entity
public class Category {
    private Integer idCategory;
    private String type;
    private List<Item> itemList = new ArrayList<Item>(0);

    @Id
    @Column(name = "ID_CATEGORY")
    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    @Column(name = "TYPE")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "category")
//    public List<Item> getItemList() {
//        return itemList;
//    }
//
//    public void setItemList(List<Item> itemList) {
//        this.itemList = itemList;
//    }

    public void addItem(Item item)
    {

        itemList.add(item);
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "CATEGORY_ITEM", joinColumns = @JoinColumn(name = "CATEGORY_ID"), inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
   // @OrderBy("name")
    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (idCategory != category.idCategory) return false;
        if (type != null ? !type.equals(category.type) : category.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCategory;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
