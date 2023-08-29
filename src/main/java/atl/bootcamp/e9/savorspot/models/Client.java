package atl.bootcamp.e9.savorspot.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "client_id")
public class Client extends User{

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "phone", nullable = false)
    private String phone;
}
