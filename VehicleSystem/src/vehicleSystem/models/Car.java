package vehicleSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@Entity
@Table(name="car")
@Setter
@Getter
public class Car implements Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //it will handle auto-increment for given ID
    @Column(name="id")
    private int Id;

    @Column(name="brand")
    private String brand;

    @Column(name="type")
    private Type type;
}
