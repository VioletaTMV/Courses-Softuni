package exercises;

import entities.Address;
import entities.Employee;
import entities.Town;
import org.hibernate.mapping.ForeignKey;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class _13_RemoveTowns {

    private static String STRING_FORMAT_NUMBER_OF_ADDRESSES_IN_TOWN_DELETED = "%d address in %s deleted";

    public static void runExercise(EntityManager em, Scanner scanner) {

        System.out.print("Enter town name to be deleted from DB: ");
        String townName = scanner.nextLine();

        int counter = 0;

        em.getTransaction().begin();

        List<Town> townList = em.createQuery("FROM Town WHERE name = :townNameHere", Town.class)
                .setParameter("townNameHere", townName)
                .getResultList();

        if (townList.size() > 0) {

            Town town = townList.get(0);

            List<Address> addressesInTown = em.createQuery("FROM Address WHERE town.name = :townNameHere", Address.class)
                    .setParameter("townNameHere", townName)
                    .getResultList();


            if (addressesInTown.size() > 0) {

                counter = addressesInTown.size();

                List<Employee> employeesWithAddressesInTown = em.createQuery("FROM Employee WHERE address IN (:addressRangeHere)", Employee.class)
                        .setParameter("addressRangeHere", addressesInTown)
                        .getResultList();

                employeesWithAddressesInTown.forEach(e -> e.setAddress(null));
                // em.flush();

                for (int i = 0; i < addressesInTown.size(); i++) {
                    em.remove(addressesInTown.get(i));
                }
                //em.flush();
            }

            em.remove(town);
            // em.flush();
        }


        em.getTransaction().commit();

        System.out.printf(STRING_FORMAT_NUMBER_OF_ADDRESSES_IN_TOWN_DELETED, counter, townName);

    }
}
