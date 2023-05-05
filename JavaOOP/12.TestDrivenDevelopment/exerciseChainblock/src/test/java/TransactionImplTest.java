import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TransactionImplTest {

    private TransactionImpl t1;
    private TransactionImpl t2;
    private TransactionImpl t3;

    @Before
    public void initializeObjectsForTest(){
        this.t1 = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Pesho", "Jojo", 10.50);
        this.t2 = new TransactionImpl(6, TransactionStatus.UNAUTHORIZED, "Kosio", "Pesho", 10.50);
        this.t3 = new TransactionImpl(7, TransactionStatus.FAILED, "Pesho", "Jojo", 11.0);
    }

    @Test
    public void test_compareTo_shouldReturnEqualTransactions(){

        int resultEquals = this.t1.compareTo(t2);

        assertEquals(0, resultEquals);

    }

    @Test
    public void test_compareTo_shouldReturnFirstIsSmaller(){

        int resultDifferent = t1.compareTo(t3);

        assertEquals(-1, resultDifferent);

    }

    @Test
    public void test_compareTo_shouldReturnFirstIsBigger(){

        int resultDifferent = t3.compareTo(t2);

        assertEquals(1, resultDifferent);

    }

}