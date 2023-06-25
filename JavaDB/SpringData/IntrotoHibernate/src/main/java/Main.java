import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory =
                cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

// Your Code Here

//        Student student = new Student("Pesho");
//        session.save(student);

//        Student studentRetrieve = session.get(Student.class, 1L);
//        System.out.println(studentRetrieve);

        List<Student> studentListRetrieve = session.createQuery("FROM Student", Student.class).list();

        for (Student s: studentListRetrieve ) {
            System.out.println(s);
        }

        session.getTransaction().commit();
        session.close();


    }
}
