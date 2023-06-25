package exercises;

import entities.Employee;

import javax.persistence.EntityManager;

public class _05_EmployeesFromDepartment {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();

    public static void runExercise(EntityManager em){
        em.getTransaction().begin();

        em.createQuery("FROM Employee WHERE department.name = :placeholderDName ORDER BY salary ASC, id ASC", Employee.class)
                .setParameter("placeholderDName", "Research and Development")
                .getResultList()
                .forEach(e -> System.out.println(e.printFormatEmployeeDepartmentSalary()));

        em.getTransaction().commit();

    }
}
