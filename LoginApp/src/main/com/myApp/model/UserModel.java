package main.com.myApp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Setter
@Getter
public class UserModel
{
    private String username;
    private String password;
    private Double salary;
    private String country;
    private String programmingLanguage;
    private List<String> operatingSystem;
}
