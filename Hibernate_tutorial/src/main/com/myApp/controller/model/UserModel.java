package main.com.myApp.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Column(name="birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birth_date;

    @Transient
    private SimpleDateFormat simpleDateFormat;

    public UserModel(String username, String password, Date birth_date) throws ParseException {
        this.username = username;
        this.password = password;
        this.birth_date = birth_date;
        //simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //this.birth_date = simpleDateFormat.parse(birth_date);
        //Timestamp timestamp = new Timestamp(birth_date.getTime());
    }
}
