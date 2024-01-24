package main;

import main.com.myApp.controller.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

//@EntityScan("main.com.myApp.controller.model")
public class Main
{
    public static void main(String[] args) {
        // define the Session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate-config.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(Passport.class)
                .buildSessionFactory();

        //define session
        Session session = sessionFactory.getCurrentSession();

        try {
            //---------GuestModel CRUD using Hibernate---------------------------------------------
            //Create guest object
            //GuestModel guest = new GuestModel("ali","email@gmail.com","password");

            //open the transaction
            session.beginTransaction();

            //save object into table
           // session.save(guest);

            //retrieve object from database
            //GuestModel guest = session.get(GuestModel.class , 1);
            //System.out.println(guest);

            //update the object
            //guest.setEmail("aliesmaiil94@gmail.com");

            //delete the object
            //session.delete(guest);



            //------------Products using ProductModel & HQL-------------------------------------------
            //Create Product object
            //UserModel user = new UserModel("sherif","password",new Date());
            //ProductModel product = new ProductModel("samsung s23","mobile_phone",24500.0,4,"china","2023-08-04");

            //save object into table
            //session.save(user);
            //System.out.println(user);
            //session.save(product);
            //System.out.println(product);

            //update query
            //Query query = session.createQuery("update ProductModel set product_name=:name where id=:i");

            //set the parameter
            //query.setParameter("name","samsung a54");
            //query.setParameter("i",31);
            //Execute query
            //int status = query.executeUpdate();
            //System.out.println(status);

            //aggregation function with query HQL
            //Query query = session.createQuery("select sum(price) from ProductModel");

            //excute query
            //List<Integer> priceResult = query.getResultList();
            //System.out.println("the total price of all products is:  "+priceResult.get(0));

            //get all products in a list with pagination
            //Query query = session.createQuery("from ProductModel");

            //set the start row
            //query.setFirstResult(21); //this number is not included in fetch data will start with the next/following number
            //set the total rows to fetch
            //query.setMaxResults(10);

            //list all product
            //List<ProductModel> productList = query.getResultList();
            //for(Object product : productList)
                //System.out.println(product);

            //----------OneToOne Relation-------------------------------------
            //Passport passport = new Passport("A340E39","2024-01-15");
            //Person person = new Person("Ali","ismail","2024-01-02");

            //passport.setPerson(person);

            //save object into table
            //session.save(passport);
            //System.out.println(passport);
            //System.out.println(person);

            //retrieve object from database
            //Passport passport1 = session.get(Passport.class , 1);
            //System.out.println(passport1);
            //System.out.println(passport1.getPerson());

            Person person1 = session.get(Person.class,1);
            System.out.println(person1.getFirst_name()+ "  " +person1.getLast_name());

            //print the associated passport
            Passport passport2 = person1.getPassport();
            System.out.println(passport2);

            //commit the changes
            session.getTransaction().commit();
        }catch (Exception exception)
        {
            exception.printStackTrace();
        }finally {
            session.close();
        }

    }
}
