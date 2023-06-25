package exercises;

import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class _06_AddingaNewAddressAndUpdatingTheEmployee {

    final private static String ADDRESS_NAME = "Vitoshka 15";

    //    public static void main(String[] args) {
//
//        final Scanner scanner = new Scanner(System.in);
//
//        final EntityManager em = Utils.getEntityManager();
    public static void runExercise(EntityManager em, Scanner scanner) {

        em.getTransaction().begin();

        Address address = new Address();
        address.setText(ADDRESS_NAME);

        em.persist(address);

        System.out.print("Enter last name of employee: ");
        final String lName = scanner.nextLine();

        em.createQuery("FROM Employee WHERE lastName = :placeholderLName", Employee.class)
                .setParameter("placeholderLName", lName)
                .getResultList()
                .forEach(e -> e.setAddress(address));

        //flush-ва автоматично при commit, видно в генерираната HQL заявка в конзолата при пускане на програмката
        em.getTransaction().commit();

    }
}
