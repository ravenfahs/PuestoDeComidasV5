package atl.bootcamp.e9.savorspot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "foodStall_id")
    private FoodStall foodStall;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Foods food;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client client;

    @Column(name = "commentary")
    private String commentary;

    @Column(name = "rating")
    private int rating;

    @Column(name = "date_time_reviews")
    private LocalDateTime dateTimeReviews;

}