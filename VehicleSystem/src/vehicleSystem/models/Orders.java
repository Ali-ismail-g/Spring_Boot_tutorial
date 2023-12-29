package vehicleSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name="orders")
@AllArgsConstructor
@Setter
@Getter
public class Orders {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //it will handle auto-increment for given ID
    @Column(name="id")
    private int Id;

    @Column(name="orderDate")
    private String orderDate;

    @Column(name="price")
    private String price;

    @Column(name="vehicleId")
    private int vehicleId;

}
