package exercises;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class _07_AddressesWithEmployeeCount {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();

    public static void runExercise(EntityManager em) {

        em.getTransaction().begin();

        em.createQuery("FROM Address a ORDER BY size(a.employees) DESC", Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(a -> System.out.println(a.stringFormatForAddressAndEmployeeCount()));

        em.getTransaction().commit();

    }
}
