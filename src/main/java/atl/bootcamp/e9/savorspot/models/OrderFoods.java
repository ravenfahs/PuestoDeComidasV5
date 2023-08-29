package atl.bootcamp.e9.savorspot.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orderfoods")
public class OrderFoods {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Foods food;

    @Column(name = "cantidad")
    private int quantity;

}
