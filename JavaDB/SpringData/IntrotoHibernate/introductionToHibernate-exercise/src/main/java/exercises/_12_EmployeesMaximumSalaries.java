package exercises;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class _12_EmployeesMaximumSalaries {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();

    public static void runExercise(EntityManager em) {

        em.getTransaction().begin();

        em.createQuery(
                "SELECT d.name, max(e.salary) " +
                        "FROM Department d " +
                        "JOIN d.employees e " +
                        "GROUP BY d.id " +
                        "HAVING max(e.salary) < 30000 OR max(e.salary) > 70000", Object[].class)
                .getResultList()
                .forEach(o -> System.out.println(o[0] + "   " + o[1].toString()));

        em.getTransaction().commit();
    }
}
