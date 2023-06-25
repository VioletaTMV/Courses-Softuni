package exercises;

import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

public class _09_FindTheLatest10Projects {

//    public static void main(String[] args) {
//
//        EntityManager em = Utils.getEntityManager();

    public static void runExercise(EntityManager em){

        em.getTransaction().begin();

        em.createQuery("FROM Project ORDER BY startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p -> System.out.println(p.stringFormatProjectWithNameDescriptionStartAndEndDates()));

        em.getTransaction().commit();
    }
}
