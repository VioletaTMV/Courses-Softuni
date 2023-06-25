package exercises;

import entities.Employee;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class _03_ContainsEmployee {

 //   public static void main(String[] args) {
// EntityManagerFactory factory =
//         Persistence.createEntityManagerFactory("soft_uni");
//
//    EntityManager em = factory.createEntityManager();

    public static void runExercise(EntityManager em, Scanner scanner){

        em.getTransaction().begin();

        System.out.print("Enter employee name: ");
        String employeeToFind = scanner.nextLine();


        boolean isEmployeeMissingFromDB = em.createQuery("FROM Employee e WHERE concat_ws(' ', e.firstName, e.lastName) = :placeholderForFullName", Employee.class)
                .setParameter("placeholderForFullName", employeeToFind)
                .getResultList()
                .isEmpty();

        System.out.println(isEmployeeMissingFromDB ? "No" : "Yes");

        em.getTransaction().commit();

    }


}
