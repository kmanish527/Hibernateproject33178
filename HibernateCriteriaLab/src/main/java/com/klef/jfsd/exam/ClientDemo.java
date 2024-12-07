package com.klef.jfsd.exam;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(Customer.class);

        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        // Insert Records
        Customer customer1 = new Customer();
        customer1.setName("John Doe");
        customer1.setEmail("john.doe@example.com");
        customer1.setAge(25);
        customer1.setLocation("New York");

        Customer customer2 = new Customer();
        customer2.setName("Jane Smith");
        customer2.setEmail("jane.smith@example.com");
        customer2.setAge(30);
        customer2.setLocation("Los Angeles");   session.save(customer1);
        session.save(customer2);

        transaction.commit();

        // Using Criteria Queries
        Criteria criteria = session.createCriteria(Customer.class);

        // Equal restriction
        criteria.add(Restrictions.eq("location", "New York"));
        List<Customer> customers = criteria.list();
        for (Customer c : customers) {
            System.out.println("Customer: " + c.getName());
        }

        // Like restriction
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.like("name", "%Jane%"));
        customers = criteria.list();
        for (Customer c : customers) {
            System.out.println("Customer: " + c.getName());
        }

        // Between restriction
        criteria = session.createCriteria(Customer.class);
        criteria.add(Restrictions.between("age", 20, 30));
        customers = criteria.list();
        for (Customer c : customers) {
            System.out.println("Customer: " + c.getName());
        }

        session.close();
        sessionFactory.close();  
        }
}
