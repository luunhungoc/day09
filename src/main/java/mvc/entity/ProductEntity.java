package mvc.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="unitPrice")
    private double unitPrice;

    @OneToMany(mappedBy = "product",fetch = FetchType.EAGER)
    private List<OrderDetailEntity> orderDetailEntityList;
    public ProductEntity(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrderDetailEntity> getOrderDetailEntityList() {
        return orderDetailEntityList;
    }

    public void setOrderDetailEntityList(List<OrderDetailEntity> orderDetailEntityList) {
        this.orderDetailEntityList = orderDetailEntityList;
    }

    @Override
    public String toString() {
        return "{" +
                "Id='" + id + '\'' +
               "Name='" + name + '\'' +
                ", description='" + description + '\'' +
                "unit price='" + unitPrice + '\'' +
                '}';
    }
}