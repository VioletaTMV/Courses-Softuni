package exercises;

import antlr.debug.SemanticPredicateListener;
import entities.Employee;

import javax.persistence.EntityManager;

public class _04_EmployeeswithaSalaryOver50K {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();
public static void runExercise(EntityManager em){
        em.getTransaction().begin();

        em.createQuery("SELECT firstName FROM Employee WHERE salary > 50000", String.class)
                .getResultList()
                .forEach(System.out::println);

        //долното също работи, но прави излишно голяма заявка към базата
//        em.createQuery("FROM Employee WHERE salary > 50000", Employee.class)
//                .getResultList()
//                .forEach(e -> System.out.println(e.getFirstName()));

        em.getTransaction().commit();
    }
}
