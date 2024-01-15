package main;

import main.com.myApp.controller.model.GuestModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main
{
    public static void main(String[] args) {
        // define the Session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate-config.xml")
                .addAnnotatedClass(GuestModel.class)
                .buildSessionFactory();

        //define session
        Session session = sessionFactory.getCurrentSession();

        try {
            //Create guest object
            GuestModel guest = new GuestModel("ali","email@gmail.com","password");

            //open the transaction
            session.beginTransaction();

            //save object into table
            session.save(guest);

            //retrieve object from database
            //GuestModel guest = session.get(GuestModel.class , 1);
            System.out.println(guest);

            //update the object
            //guest.setEmail("aliesmaiil94@gmail.com");

            //delete the object
            //session.delete(guest);

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
