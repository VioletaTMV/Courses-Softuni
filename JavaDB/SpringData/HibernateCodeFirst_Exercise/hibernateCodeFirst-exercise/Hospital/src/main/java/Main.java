import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory hospitalFactory = Persistence.createEntityManagerFactory("hospital");
        EntityManager em = hospitalFactory.createEntityManager();

    }
}
