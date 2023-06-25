package exercises;

import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


public class _02_ChangeCasing {

//    public static void main(String[] args) {

//        EntityManagerFactory factory =
//                Persistence.createEntityManagerFactory("soft_uni");
//
//        EntityManager em = factory.createEntityManager();

    public static void runExercise(EntityManager em){
        em.getTransaction().begin();

        List<Town> allTowns =
                em.createQuery("FROM Town", Town.class).getResultList();

        allTowns.stream()
                .filter(t -> t.getName().length() > 5)
                .forEach(em::detach);

        allTowns.forEach(t ->
        {
            t.setName(t.getName().toUpperCase());
            em.flush();
        });

        em.getTransaction().commit();
    }
}
