package biz.bagira.auction.entities;

import javax.persistence.*;

/**
 * Created by Dmitriy on 25.01.2017.
 */
@Entity
@Table(name = "ORDERS")
public class Order {
    private Integer orderId;
    private Boolean paid;
    private User winner;
    private Item item;

    public Order() {
        paid = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Column(name = "PAID")
    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
    @JoinColumn(name = "WINNER_ID")
    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (paid != null ? !paid.equals(order.paid) : order.paid != null) return false;
        return item != null ? item.equals(order.item) : order.item == null;

    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        result = 31 * result + (item != null ? item.hashCode() : 0);
        return result;
    }
}
