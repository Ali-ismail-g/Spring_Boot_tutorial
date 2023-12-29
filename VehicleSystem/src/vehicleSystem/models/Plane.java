package vehicleSystem.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component
@Scope("prototype")
@Setter
@Getter
public class Plane implements Vehicle{
    private int Id;

    private String brand;

    private Type type;
}
