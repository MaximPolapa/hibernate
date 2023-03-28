package hibernate;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeveloperRunner {
    private static SessionFactory sessionFactory;

    public static void main(String[] args){
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        DeveloperRunner developerRunner = new DeveloperRunner();
        developerRunner.addEmployee("ottbkmh","ortkok","ortko",18);
    }

    public String addEmployee(String fname, String lname, String specialty, int experience){
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Integer employeeID = null;
        try {
            tx = session.beginTransaction();
            Developer employee = new Developer(fname, lname, specialty, experience);
            employeeID = (Integer) session.save(employee);
            tx.commit();
            return "all good";
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            return "ALL BAAAAAD";
        } finally {
            session.close();
        }
    }

}
