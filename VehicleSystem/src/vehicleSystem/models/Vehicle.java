package vehicleSystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component("vehicle")
@Entity
@Table(name="vehicle")
public interface Vehicle {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //it will handle auto-increment for given ID
    @Column(name="id")
    int Id = 0;

    @Column(name="brand")
    String brand = null;

    @Column(name="type")
    Type type = null;
    public void setId(int id);
    public int getId();
    public String getBrand();
    public void setBrand(String brand);
    public Type getType();
    public void setType(Type type);
}
