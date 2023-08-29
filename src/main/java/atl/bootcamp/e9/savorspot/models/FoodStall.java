package atl.bootcamp.e9.savorspot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "foodStall_id")
public class FoodStall extends User {

    @Column(name = "name", nullable = false, unique = true)
    private String foodStallName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "type_cuisine", nullable = false)
    private String typeCuisine;

    @Column(name = "attention_schedules")
    private String attentionSchedules;

    @Column(name = "phone")
    private String phone;

    @Column(name = "facade_image_url")
    private String facadeImageUrl;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
