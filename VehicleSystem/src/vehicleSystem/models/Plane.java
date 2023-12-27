package vehicleSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component
@Scope("prototype")
@Entity
@Table(name="plane")
@Setter
@Getter
public class Plane implements Vehicle{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //it will handle auto-increment for given ID
    @Column(name="id")
    private int Id;

    @Column(name="brand")
    private String brand;

    @Column(name="type")
    private Type type;

}
