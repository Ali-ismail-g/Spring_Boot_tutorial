package com.dummyApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "productdetail")
@Setter
@Getter
@NoArgsConstructor
public class ProductDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "this field cannot be null")
    @Size(min = 3,message = "the name should by greater than 3")
    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "this field cannot be null")
    @Column(name = "expirationDate")
    private Date expirationDate;

    @NotEmpty(message = "this field cannot be null")
    @Column(name = "manufacturer")
    private String manufacturer;

    @NotNull(message = "this field cannot be null")
    @Column(name = "price")
    private Double price;

    @NotNull(message = "this field cannot be null")
    @Column(name = "available")
    private Boolean  available;

    @JsonIgnore
    @OneToOne(mappedBy = "productDetails",cascade = CascadeType.ALL )
    private Product product;

    public ProductDetails(int id) {
        this.id = id;
    }
}
