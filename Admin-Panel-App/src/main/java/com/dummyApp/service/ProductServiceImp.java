package com.dummyApp.service;

import com.dummyApp.dao.ProductDAO;
import com.dummyApp.model.Product;
import com.dummyApp.model.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductServiceImp  implements  ProductService{

    // inject productDAO
    @Autowired
    private   ProductDAO productDAO;


    @Transactional
    public ProductDetails insert(ProductDetails productDetails) {

          // do some validation on data before go on dataAccess Layer
       productDAO.insert(productDetails);
        return productDetails;
    }


    @Transactional
    public Product findById(int id) {

        Product product= productDAO.findById(id);
        return product;
    }

     @Transactional
    public void deleteById(int id) {

        productDAO.deleteById(id);
    }

    @Transactional
    public ProductDetails update(ProductDetails productDetails) {

           // search for related product exist or not
          Product product= productDAO.findByProductDetails(productDetails);


                productDAO.update(productDetails);




        return productDetails;
    }

  @Transactional
    public List<Product> getAllProducts() {

        return   productDAO.getAllProducts();
    }

    @Transactional
    public List<Product> search(String name) {
       List<Product>  products= productDAO.search(name);
        return products;
    }


    // make method for validation
}
