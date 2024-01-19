package main.com.myApp.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="user")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    public UserModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
