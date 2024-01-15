package main.com.myApp.controller.model;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="guests")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class GuestModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;

    @Column(name="username")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    public GuestModel(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
