package biz.bagira.auction.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitriy on 18.01.2017.
 */
@Entity
@Table(name = "CATEGORY")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORY")
    private Integer categoryId;

    @Column(name = "TYPE")
    private String type;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="ITEMS", joinColumns = @JoinColumn(name="CATEGORY_ID"))
//    private Set<Item> itemList;


//    public void addItem(Item item) {
//        if (itemList == null) {
//            itemList = new HashSet<Item>();
//        }
//        itemList.add(item);
//    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

//    public Set<Item> getItemList() {
//        return itemList;
//    }
//
//    public void setItemList(Set<Item> itemList) {
//        this.itemList = itemList;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != null ? !categoryId.equals(category.categoryId) : category.categoryId != null) return false;
        return type != null ? type.equals(category.type) : category.type == null;

    }

    @Override
    public int hashCode() {
        int result = categoryId != null ? categoryId.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
