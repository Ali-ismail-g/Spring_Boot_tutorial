package main.com.myApp.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name="person")
@Setter
@Getter
//@ToString
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="birth_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birth_date;

    @OneToOne(mappedBy = "person")
    private Passport passport;

    @Transient
    private SimpleDateFormat simpleDateFormat;

    public Person(String first_name, String last_name, String birth_date) throws ParseException {
        this.first_name = first_name;
        this.last_name = last_name;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.birth_date = simpleDateFormat.parse(birth_date);
    }
}
