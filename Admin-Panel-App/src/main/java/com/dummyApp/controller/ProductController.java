package com.dummyApp.controller;

import com.dummyApp.model.Product;
import com.dummyApp.model.ProductDetails;
import com.dummyApp.service.ProductService;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import com.dummyApp.util.ProductErrorResponse;
import com.dummyApp.util.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    // inject product service
    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ProductDetails insertProduct(@RequestBody ProductDetails productDetails){
        return productService.insert(productDetails);
    }

    @PutMapping("/products")
    public ProductDetails updateProduct (@RequestBody ProductDetails productDetails){

       return productService.update(productDetails);
    }

    @DeleteMapping("/products")
    public void deleteProduct(@RequestParam("productId") int id){
        productService.deleteById(id);

    }

    @GetMapping("/products/productDetails")
    public ProductDetails getProductsDetailsByID(@RequestParam("productId") int id)
    {
        List<Product> products=productService.getAllProducts();
        if(id < 0 || id>products.size()){
            throw new ProductNotFoundException("product is not found at "+id+" !!");
        }
        Product product = productService.findById(id);

        return product.getProductDetails();
    }

    @GetMapping("/product")
    public Product getProductByID(@RequestParam("productId") int id)
    {
        return productService.findById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts()
    {
        return  productService.getAllProducts();
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exception){

        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception exception){

        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value()); //400 error code to handle if client entered string instead of int for id in params which cause mismatch
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

//---------------------------------------------------------------------------
// get all products to show in showpage
//    @GetMapping("/")
//    public String showAll(Model model){
//
//        // get list of product
//        List<Product> products=  productService.getAllProducts();
//        // add all to model and send into to homepage to show result;
//        model.addAttribute("products",products);
//        return "homePage";
//    }
//
//    // get all products to show in showpage
//    @GetMapping("/showDetails")
//    public String showDetails(@RequestParam("id") int productId,Model model){
//
//        // get object by product id
//        Product product=productService.findById(productId);
//        ProductDetails productDetails= product.getProductDetails();
//        // throw product details into model
//        model.addAttribute("productDetails",productDetails);
//        return "viewDetails";
//    }
//// to update products
//    @GetMapping("/updateDetails")
//    public String  update(@RequestParam("id") int id,Model model){
//
//        // get object by product id
//        Product product=productService.findById(id);
//        ProductDetails productDetails= product.getProductDetails();
//        // throw product details into model
//        model.addAttribute("productDetails",productDetails);
//        return "updateProducts";
//    }
//
//
//    @PostMapping("/processUpdateDetails")
//    public String  update(@Valid @ModelAttribute("productDetails") ProductDetails productDetails, BindingResult bindingResult, Model model){
//
//        if(bindingResult.hasErrors()){
//            return "updateProducts";
//        }
//
//         // update product
//         productService.update(productDetails);
//        // get list of product
//        List<Product> products=  productService.getAllProducts();
//        // add all to model and send into to homepage to show result;
//        model.addAttribute("products",products);
//         // you should sent the details after updated into home page
//        return "redirect:/";
//    }
//
//    // add product
//    @GetMapping("/addProduct")
//    public String addProduct(Model model){
//
//        // send product details
//        ProductDetails productDetails=new ProductDetails();
//        model.addAttribute("productDetails",productDetails);
//
//        return "addProduct";
//    }
//
//    // process add product
//    @PostMapping("/processAddProduct")
//    public String processAddProduct(@Valid @ModelAttribute("productDetails") ProductDetails productDetails, BindingResult bindingResult, Model model){
//
//        if(bindingResult.hasErrors()){
//            return "addProduct";
//        }
//        // add product details into database
//        productService.insert(productDetails);
//        List<Product> products=productService.getAllProducts();
//        model.addAttribute("products",products);
//        return "redirect:/";
//    }
//
//    // delete products
//    @GetMapping("/deleteProduct")
//    public String processDeleteProduct(@RequestParam("id") int id){
//
//        // delete all products
//        productService.deleteById(id);
//
//        return "redirect:/";
//    }
//
//    // search by name
//    @GetMapping("/searchProduct")
//    public String getAlldetails(@RequestParam("name") String name, Model model){
//
//       List<Product> products=  productService.search(name);
//        model.addAttribute("products",products);
//
//        return "homePage";
//    }
//
//    // to control date before user check it
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
//    }
//
//
//    @InitBinder
//    public void removeWhiteSpaces(WebDataBinder dataBinder){
//
//        // define object of trimer
//        StringTrimmerEditor trimmerEditor= new StringTrimmerEditor(true);
//        dataBinder.registerCustomEditor(String.class,trimmerEditor);
//    }
}
