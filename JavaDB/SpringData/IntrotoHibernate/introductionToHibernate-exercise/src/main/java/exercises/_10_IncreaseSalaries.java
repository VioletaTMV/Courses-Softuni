package exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class _10_IncreaseSalaries {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();

    public static void runExercise(EntityManager em){

        em.getTransaction().begin();

        em.createQuery("FROM Employee WHERE department.name IN (:placeholderSetOfDptNames )", Employee.class)
                .setParameter("placeholderSetOfDptNames", Set.of("Engineering", "Tool Design", "Marketing", "Information Services"))
                .getResultList()
                .forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    em.flush();
                    System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary());
                });

        em.getTransaction().commit();
    }
}
