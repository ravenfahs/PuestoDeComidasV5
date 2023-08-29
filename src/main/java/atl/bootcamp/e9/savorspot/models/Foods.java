package atl.bootcamp.e9.savorspot.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "foods")
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name="food_drink")
    private String foodDrink;

    @Column(name = "image")
    private String image;

    // Relación muchos a uno con Restaurante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foodStall_id")
    private FoodStall foodStall;

}
