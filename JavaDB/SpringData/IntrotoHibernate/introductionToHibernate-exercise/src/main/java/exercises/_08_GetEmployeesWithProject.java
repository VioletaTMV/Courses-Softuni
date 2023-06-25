package exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class _08_GetEmployeesWithProject {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();

    public static void runExercise(EntityManager em, Scanner scanner){

        em.getTransaction().begin();

        System.out.print("Enter employee ID: ");
        int eID = Integer.parseInt(scanner.nextLine());

        System.out.println(em.createQuery("FROM Employee WHERE id = :placeholderForID", Employee.class)
                .setParameter("placeholderForID", eID)
                .getSingleResult()
                .printFormatEmployeeNamesWithJobTitleAndProjects());

        em.getTransaction().commit();
    }
}
