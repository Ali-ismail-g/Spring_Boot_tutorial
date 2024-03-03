import com.dummyApp.model.ProductDetails;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class ProductTest {
    @Test
    public void testGetProductById(){
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/mvc/products/productDetails?productId=1";

        ResponseEntity<ProductDetails> response = restTemplate.getForEntity(url,ProductDetails.class);

        String productName = "Milk shake Chocolate";

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(productName, response.getBody().getName());
    }

    @Test
    public void testAddProductDetails() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/mvc/products";

        ProductDetails productDetails = new ProductDetails("Pepsi",simpleDateFormat.parse("2024-04-22"),"Pepsico Corp",18888.0,true);

        ResponseEntity<ProductDetails> response = restTemplate.postForEntity(url,productDetails, ProductDetails.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testUpdateProductDetails() throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD");
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8080/mvc/products";

        ProductDetails productDetails = new ProductDetails(4,"Biscuits",simpleDateFormat.parse("2024-04-22"),"Corona Corp",999.0,true);

        restTemplate.put(url,productDetails);

        ResponseEntity<ProductDetails> response = restTemplate.getForEntity("http://localhost:8080/mvc/products/productDetails?productId=4",ProductDetails.class);

        String productName = "Biscuits";

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(productName, response.getBody().getName());
    }

    @Test
    public void testDeleteProductDetails(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange("http://localhost:8080/mvc/products?productId=21", HttpMethod.DELETE,null, Void.class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}
