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
@Table(name="passport")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="person_id",referencedColumnName = "id")
    private Person person;

    @Column(name="passport_no")
    private String passport_no;

    @Column(name="expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiry_date;

    @Transient
    private SimpleDateFormat simpleDateFormat;

    public Passport(String passport_no, String expiry_date) throws ParseException {
        this.passport_no = passport_no;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.expiry_date = simpleDateFormat.parse(expiry_date);
    }
}
