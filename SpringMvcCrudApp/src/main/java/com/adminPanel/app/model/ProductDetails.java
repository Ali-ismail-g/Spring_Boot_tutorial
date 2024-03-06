package com.adminPanel.app.model;


import com.adminPanel.app.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.text.ParseException;
import java.util.Date;

@Entity
@Table(name = "product_details")
@Setter
@Getter
@NoArgsConstructor
public class ProductDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "price")
    private Double price;

    @Column(name = "available")
    private Boolean available;

    @OneToOne(mappedBy = "productDetails" , cascade = CascadeType.ALL)
    private Product product;
}
