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
@Table(name="products")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int product_id;

    @Column(name="name")
    private String product_name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private double price;

    @Column(name="stock_quantity")
    private int stock_quantity;

    @Column(name="manufacturer")
    private String manufacturer;

    @Column(name="production_date")
    private Date production_date;

    @Transient
    private SimpleDateFormat simpleDateFormat;

    public ProductModel(String product_name, String description, double price, int stock_quantity, String manufacturer, String production_date) throws ParseException {
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.stock_quantity = stock_quantity;
        this.manufacturer = manufacturer;
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.production_date = simpleDateFormat.parse(production_date);
    }
}
