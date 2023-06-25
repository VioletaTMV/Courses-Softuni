import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utils {

    public static EntityManager getEntityManager() {

        EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("soft_uni");

        return factory.createEntityManager();

    }


}
