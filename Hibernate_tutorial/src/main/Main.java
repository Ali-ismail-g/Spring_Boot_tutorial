package main;

import main.com.myApp.controller.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

//@EntityScan("main.com.myApp.controller.model")
public class Main
{
    public static void main(String[] args) {
        // define the Session factory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate-config.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
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

            //Person person1 = session.get(Person.class,1);
            //System.out.println(person1.getFirst_name()+ "  " +person1.getLast_name());

            //print the associated passport
            //Passport passport2 = person1.getPassport();
            //System.out.println(passport2);

            //----------OneToMany Relation uni direction-------------------------------------
            //define Question object
            //Question question = new Question("Question 1");

            //define List of Answers
            //List<Answer> answers = new ArrayList<>();
            //add answers into the list
            //Collections.addAll(answers,
                    //new Answer("Question 1 first answer"),
                    //new Answer("Question 1 second answer"),
                    //new Answer("Question 1 third answer"));
            //question.setAnswers(answers);

            //save Question and its answers
            //session.save(question);

            //----------OneToMany Relation bi direction-------------------------------------
            //define Instructor object
            //Instructor instructor = new Instructor("Instructor Shohdy");
            //define objects of Courses
            //Course course1 = new Course("Course 1");
            //Course course2 = new Course("Course 2");
            //Course course3 = new Course("Course 3");

            //instructor.addCourse(course1);
            //instructor.addCourse(course2);
            //instructor.addCourse(course3);

            //save Instructor and its courses
            //session.persist(instructor);

            //get instructor object
            int id = 1;
            Instructor instructor = session.get(Instructor.class,id);
            System.out.println(instructor);

            //----------ManyToMany Relation-------------------------------------
            //define courses objects
            //Courses courses1 = new Courses("Math");
            //Courses courses2 = new Courses("CS");


            //define students objects
            //Student student1 = new Student("Ali");
            //Student student2 = new Student("Sherif");

            //add students to courses
            //courses1.addStudent(student1);
            //courses2.addStudent(student1);
            //courses2.addStudent(student2);

            //save the courses
            //session.persist(courses1);
            //session.persist(courses2);

            //get the student from the database
            //Student tempStudent = session.get(Student.class,1);

            //print the student data
            //System.out.println("student data: "+tempStudent.getStudentName());
            //System.out.println("courses related to student: "+tempStudent.getCourses());

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
