import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factorySales = Persistence.createEntityManagerFactory("sales");
        EntityManager emSales = factorySales.createEntityManager();

    }
}
