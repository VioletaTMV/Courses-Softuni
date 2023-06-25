package exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class _11_FindEmployeesByFirstName {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();

        public static void runExercise(EntityManager em, Scanner scanner) {

        em.getTransaction().begin();

        System.out.print("Enter pattern: ");
        String pattern = scanner.nextLine();
        pattern = pattern.toLowerCase();

        em.createQuery("FROM Employee WHERE lower(firstName) like :patternForStartOfFN", Employee.class)
                .setParameter("patternForStartOfFN", pattern.toLowerCase().concat("%"))
                .getResultList()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        em.getTransaction().commit();
    }

}
