package mvc.entity;

import javax.persistence.*;


@Entity
@Table(name="orderDetails")
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="quantity")
    private int quantity;

    @ManyToOne
    @JoinColumn(name="orderId")
    private OrdersEntity order;

    @ManyToOne
    @JoinColumn(name="productId")
    private ProductEntity product;

    public OrderDetailEntity(){}

    public int getId() {
        return id;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrdersEntity getOrder() {
        return order;
    }

    public void setOrder(OrdersEntity order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderDetails: {" +
                "id=" + id +
                ", productId='" + product.getId() + '\'' +
                ", productName='" + product.getName() + '\'' +
                ", quantity=" + quantity +
                ", unit price=" + product.getUnitPrice() +
                '}';
    }
}