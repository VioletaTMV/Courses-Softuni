import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory billsPaymentFactory = Persistence.createEntityManagerFactory("bills_payment");
        EntityManager em = billsPaymentFactory.createEntityManager();


    }
}
