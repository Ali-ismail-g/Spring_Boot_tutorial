# Admin_Panel_App

## Project Overview
The Admin Panel App is a comprehensive management tool designed to streamline administrative tasks and enhance the control and efficiency of various operations within your system. Tailored for administrative users, the application provides a centralized hub to manage and monitor crucial aspects of your business or platform.
by using Spring MVC , Hibernate , Maven and MYSQL DB

## Key Features

### 1. User Management

Effortlessly manage user accounts, permissions, and access levels. The Admin Panel allows administrators to add, modify, or deactivate user accounts, ensuring secure and controlled access to the system.

### 2. Product Management

Take control of your product catalog with intuitive features for adding, updating, and removing products. The application supports seamless integration with databases, such as MySQL, and utilizes Hibernate for efficient data handling.

 
## CRUD Operations

The application facilitates the following CRUD operations for managing product details:

### View homePage
1. The home page is reached by going to the link: http://localhost:8080/
![GitHub Logo](/images/list_of_products.png)
in backend the request send to the method in controller and get response in list of product page

### Create (Add)

To add a new product to the system:

1. Open your web browser and go to the application URL :  http://localhost:8080/addProduct  
in backend : after clicking the button add product the request send to the controller which handle bussiness logic for insertion on product and go to dao layer and save product into database
![GitHub Logo](/images/addProduct.png)

### Read (View)

To view details of a product:
   The home page is reached by going to the link: http://localhost:8080/viewDetails
 ![GitHub Logo](/images/viewDetails.png)
 

  
### Update

To update the details of an existing product:

1. The home page is reached by going to the link: http://localhost:8080/updateProduct
![GitHub Logo](/images/updateProducts.png)

### Delete

To delete a product from the system:

1. clicking into button delete 
 in the backend : the request is send to method delete product in controller and this method handle operation that can delete on database and return the products after deleting 


These operations are implemented using Spring MVC and Hibernate, ensuring a seamless and efficient management of product data within the Admin_Panel_App.

## Technologies Used

- Spring MVC
- Hibernate
- Maven
- MYSQL DB

## IDE

This project was developed using IntelliJ IDEA 2020. 

 

## Database Schema

If applicable, the project uses the following database schema for CRUD operations on products:

 hib is the schema of myDataBase

 
## Contact Information

For questions or collaboration opportunities, feel free to reach out:

- Name : Abdullah hamada Abd ElSalam
- email : Abdullahhamada100@gmail.com
-  phone : +20 0106-734-3329
 
 


  