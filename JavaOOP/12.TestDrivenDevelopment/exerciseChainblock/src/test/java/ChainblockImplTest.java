import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static org.junit.Assert.*;

public class ChainblockImplTest {

    private Chainblock chainblock;
    private static final int idSuccess = 10;
    private static final int idAborted = 20;
    private static final int idUnauthorized = 30;
    private static final int idFailed = 40;
    private static final int idFailed2 = 50;
    private static final int idSuccess2 = 60;
    private static final int idSuccess3 = 70;
    private static final int idSuccess4 = 80;
    private static final int nonExistentID = 100;
    private static final Transaction successful = new TransactionImpl(idSuccess, TransactionStatus.SUCCESSFUL, "Joro", "Tosho", 100.0);
    private static final Transaction aborted = new TransactionImpl(idAborted, TransactionStatus.ABORTED, "Gosho", "Alan", 200.0);
    private static final Transaction unauthorized = new TransactionImpl(idUnauthorized, TransactionStatus.UNAUTHORIZED, "Tosho", "Misho", 300.0);
    private static final Transaction failed = new TransactionImpl(idFailed, TransactionStatus.FAILED, "Pesho", "Roki", 400.0);
    private static final Transaction duplicatedByID = new TransactionImpl(idAborted, TransactionStatus.SUCCESSFUL, "Gosho", "Alan", 200.0);
    private static final Transaction failed2 = new TransactionImpl(idFailed2, TransactionStatus.FAILED, "John", "Sam", 500.0);
    private static final Transaction success2 = new TransactionImpl(idSuccess2, TransactionStatus.SUCCESSFUL, "Kiro", "Gogo", 100);
    private static final Transaction success3SameSenderAndReceiverAsAborted = new TransactionImpl(idSuccess3, TransactionStatus.SUCCESSFUL, "Gosho", "Alan", 50.0);
    private static final Transaction success4SameReceiverAsAbortedBiggerIDSameAmount = new TransactionImpl(idSuccess4, TransactionStatus.SUCCESSFUL, "Hristo", "Alan", 200.0);



    @Before
    public void prepareObjectsForTest() {
        this.chainblock = new ChainblockImpl();
        chainblock.add(successful);

    }

    private void addToChainblockTwoFailedAndOneAbortedTransactions() {
        chainblock.add(failed);
        chainblock.add(aborted);
        chainblock.add(failed2);
    }

    @Test
    public void test_containsByID_shouldReturnFalseWhenNoSuchTransaction() {
        boolean chainblockContainsNonExistentTransactionID = chainblock.contains(nonExistentID);

        assertFalse(chainblockContainsNonExistentTransactionID);
    }

    @Test
    public void test_containsByID_shouldReturnTrueWhenChainblockContainsTransactionWithSpecifiedID() {

        boolean successfullTransactionIDContainedInChainblock = chainblock.contains(idSuccess);

        assertTrue(successfullTransactionIDContainedInChainblock);
    }

    @Test
    public void test_containsByTransaction_shouldReturnFalseWhenChainblockDoesNotContainSuchTransaction() {
        boolean chainblockContainsTransaction = chainblock.contains(aborted);

        assertFalse(chainblockContainsTransaction);
    }

    @Test
    public void test_containsByTransaction_shouldReturnTrueWhenChainblockContainsTransaction() {
        boolean successfullTransactionContainedInChainblock = chainblock.contains(successful);

        assertTrue(successfullTransactionContainedInChainblock);
    }

    @Test
    public void test_add_shouldAddAtTheEndTheNewTransaction() {
        chainblock.add(failed);

        assertTrue(chainblock.contains(idFailed));
        assertTrue(chainblock.contains(failed));
        assertEquals(2, chainblock.getCount());

        chainblock.add(aborted);
        assertEquals(3, chainblock.getCount());

    }

    @Test
    public void test_add_shouldNotAddDuplicateTransaction() {
        chainblock.add(aborted);
        assertEquals(2, chainblock.getCount());

        chainblock.add(success2);
        chainblock.add(aborted);
        assertEquals(3, chainblock.getCount());

        Transaction lastTransactionInChainblock = null;
        for (Transaction transaction : chainblock) {
            lastTransactionInChainblock = transaction;
        }
        assertEquals(success2, lastTransactionInChainblock);
    }

    @Test
    public void test_add_shouldNotAddTransactionWithSameIDevenIfStatusIsDifferent() {

        chainblock.add(aborted);
        chainblock.add(duplicatedByID);

        assertEquals(2, chainblock.getCount());
        assertEquals(aborted, chainblock.getById(idAborted));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getByID_shouldThrowIllegalArgExceptionWhenNoSuchIDinChainblock() {

        Transaction transaction = chainblock.getById(nonExistentID);
    }

    @Test
    public void test_getByID_shouldReturnTransactionWithRequestedID() {
        Transaction transaction = chainblock.getById(idSuccess);

        assertEquals(successful, transaction);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_changeTransactionStatus_ShouldThrowIllArgExWhenNonExistingID() {

        chainblock.changeTransactionStatus(nonExistentID, TransactionStatus.FAILED);
    }

    @Test
    public void test_changeTransactionStatus_ShouldChangeStatusOfTransactionWithSpecifiedID() {
        chainblock.changeTransactionStatus(idSuccess, TransactionStatus.FAILED);

        assertEquals(TransactionStatus.FAILED, chainblock.getById(idSuccess).getTransactionStatus());
    }

    @Test
    public void test_removeTransactionByID_ShouldRemoveTheTransactionWithSpecifiedID() {
        chainblock.removeTransactionById(idSuccess);

        assertFalse(chainblock.contains(idSuccess));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_removeTransactionByID_ShouldThrowIllegalArgExWhenSpecifiedIDNonExistent() {
        chainblock.removeTransactionById(nonExistentID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getByTransactionStatus_ShouldThrowIllegalArgExWhenNoTransactionExistsWithSpecifiedStatus() {
        chainblock.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void test_getByTransactionStatus_ShouldReturnIterableOfTransactionsOnlyWithSpecifiedStatus() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        Iterable<Transaction> expected = Arrays.asList(failed, failed2);
        Iterable<Transaction> result = chainblock.getByTransactionStatus(TransactionStatus.FAILED);

        for (Transaction transaction : result) {
            assertEquals(transaction.getTransactionStatus(), TransactionStatus.FAILED);
        }

    }

    @Test
    public void test_getByTransactionStatus_ShouldReturnIterableOrderedByAmountDescending() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        Iterable<Transaction> expected = new LinkedHashSet<>(Arrays.asList(failed2, failed));
        Iterable<Transaction> result = chainblock.getByTransactionStatus(TransactionStatus.FAILED);

        assertEquals(expected, result);

//        double amount = Double.MAX_VALUE;
//
//        for (Transaction currentTransaction : result) {
//            assertTrue(amount >= currentTransaction.getAmount());
//            amount = currentTransaction.getAmount();
//        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getByTransactionStatus_shouldThrowIllegalArgExWhenRequestedStatusNonExistentInChainblockTransactions() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        Iterable<Transaction> result = chainblock.getByTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void test_getAllSendersWithTransactionStatus_shouldReturnIterableOfSendersWithSpecifiedStatus() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        Iterable<String> allSendersWithTransactionStatus = chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);

        for (String sendersWithTransactionStatus : allSendersWithTransactionStatus) {
            assertTrue(sendersWithTransactionStatus.equals("Pesho") || sendersWithTransactionStatus.equals("John"));
        }
    }

    @Test
    public void test_getAllSendersWithTransactionStatus_shouldReturnSendersWithSpecifiedStatusOrderedByAmount() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        Iterable<String> expected = new LinkedHashSet<>(Arrays.asList("John", "Pesho"));
        Iterable<String> resultAllSendersWithTransactionStatus = chainblock.getAllSendersWithTransactionStatus(TransactionStatus.FAILED);

        assertEquals(expected, resultAllSendersWithTransactionStatus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getAllSendersWithTransactionStatus_shouldThrowIllegalArgExWhenNoTransactionsWithSpecifiedStatusExistInChainblock() {

        chainblock.getAllSendersWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void test_getAllReceiversWithTransactionStatus_shouldReturnIterableOfReceiversWithSpecifiedStatus() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        //  Iterable<String> expected = new LinkedHashSet<>(Arrays.asList("Roki", "Sam"));
        Iterable<String> resultAllReceiversWithTransactionStatus = chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);

        for (String receiversWithTransactionStatus : resultAllReceiversWithTransactionStatus) {
            assertTrue(receiversWithTransactionStatus.equals("Roki") || receiversWithTransactionStatus.equals("Sam"));
        }
    }

    @Test
    public void test_getAllReceiversWithTransactionStatus_shouldReturnReceiversWithSpecifiedStatusOrderedByAmount() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        Iterable<String> expected = new LinkedHashSet<>(Arrays.asList("Roki", "Sam"));
        Iterable<String> allReceiversWithTransactionStatus = chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.FAILED);

        assertEquals(expected, allReceiversWithTransactionStatus);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getAllReceiversWithTransactionStatus_shouldThrowIllegalArgExWhenNoTransactionsWithSpecifiedStatusExistInChainblock() {

        chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.UNAUTHORIZED);
    }

    @Test
    public void test_getAllOrderedByAmountDescendingThenById_shouldReturnTransactionsOrderedByAmountDescendingThenByIDdescending() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success2);

        Iterable<Transaction> expected = new LinkedHashSet<>(Arrays.asList(failed2, failed, aborted,
                success2, successful));
        Iterable<Transaction> result = chainblock.getAllOrderedByAmountDescendingThenById();

        assertEquals(expected, result);
    }

    @Test
    public void test_getAllOrderedByAmountDescendingThenById_shouldReturnEmptyArrayWhenChainblockEmpty() {
        chainblock.removeTransactionById(idSuccess);

        Iterable<Transaction> result = chainblock.getAllOrderedByAmountDescendingThenById();

        int resultLength = 0;
        for (Transaction transaction : result) {
            resultLength += 1;
        }

        assertEquals(0, resultLength);
    }

    @Test
    public void test_getBySenderOrderedByAmountDescending_shouldReturnSendersOrdered() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success3SameSenderAndReceiverAsAborted);

        Iterable<Transaction> expected = new ArrayList<>(Arrays.asList(aborted, success3SameSenderAndReceiverAsAborted));
        Iterable<Transaction> result = chainblock.getBySenderOrderedByAmountDescending("Gosho");

        assertEquals(expected, result);

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getBySenderOrderedByAmountDescending_shouldThrowWhenNoSuchTransactions() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        chainblock.getBySenderOrderedByAmountDescending("Tosho");
    }

    @Test
    public void test_getByReceiverOrderedByAmountThenById_shouldReturnTransactionsOfSpecifiedReceiverByAmountDescendingThenByIDascending() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success3SameSenderAndReceiverAsAborted);
        chainblock.add(success4SameReceiverAsAbortedBiggerIDSameAmount);

        Iterable<Transaction> expected = new ArrayList<>(Arrays.asList(aborted,
                success4SameReceiverAsAbortedBiggerIDSameAmount,
                success3SameSenderAndReceiverAsAborted));
        Iterable<Transaction> result = chainblock.getByReceiverOrderedByAmountThenById("Alan");

        assertEquals(expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getByReceiverOrderedByAmountThenById_shouldThrowWhenNoSuchTransaction() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        chainblock.getByReceiverOrderedByAmountThenById("Misho");
    }

    @Test
    public void test_getByTransactionStatusAndMaximumAmount_shouldReturnAllTransactionsWithStatusUpToAmountOrderedByAmountDescending() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success3SameSenderAndReceiverAsAborted);
        chainblock.add(success2);

        Iterable<Transaction> expected = new ArrayList<>(Arrays.asList(successful, success2,
                success3SameSenderAndReceiverAsAborted));
        Iterable<Transaction> result = chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.SUCCESSFUL, 100.0);

        assertEquals(expected, result);
    }

    @Test
    public void test_getByTransactionStatusAndMaximumAmount_shouldReturnEmptyCollectionWhenNoSuchTransaction() {
        addToChainblockTwoFailedAndOneAbortedTransactions();

        Iterable<Transaction> result = chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.FAILED, 3);

        int countTransactionsInResult = 0;

        for (Transaction transaction : result) {
            countTransactionsInResult = countTransactionsInResult + 1;
        }
        assertEquals(0, countTransactionsInResult);
    }

    @Test
    public void test_getBySenderAndMinimumAmountDescending_shouldReturnTransactionsOfSenderWithAmountBiggerThanSpecifiedOrderedByAmountDescending() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success3SameSenderAndReceiverAsAborted);

        Iterable<Transaction> expected = new ArrayList<>(Arrays.asList(aborted,
                success3SameSenderAndReceiverAsAborted));
        Iterable<Transaction> result = chainblock.getBySenderAndMinimumAmountDescending("Gosho", 40);
        assertEquals(expected, result);

        Iterable<Transaction> expectedForEdgeAmount = new ArrayList<>(Arrays.asList(aborted));
        Iterable<Transaction> resultForEdgeAmount = chainblock.getBySenderAndMinimumAmountDescending("Gosho", 50);
        assertEquals(expectedForEdgeAmount, resultForEdgeAmount);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getBySenderAndMinimumAmountDescending_shouldThrowWhenNoSuchTransactions() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success3SameSenderAndReceiverAsAborted);

        Iterable<Transaction> resultForEdgeAmount = chainblock.getBySenderAndMinimumAmountDescending("Gosho", 200);
    }

    @Test
    public void test_getByReceiverAndAmountRange_shouldReturnTransactionsOfSpecifiedReceiverWithAmountBetweenLowLimitInclusiveAndUpLimitExclusiveOrderedByAmountDescendingThenByIDascending() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success3SameSenderAndReceiverAsAborted);
        chainblock.add(success4SameReceiverAsAbortedBiggerIDSameAmount);

        Iterable<Transaction> expectedWithinLimits = new ArrayList<>(Arrays.asList(aborted,
                success4SameReceiverAsAbortedBiggerIDSameAmount,
                success3SameSenderAndReceiverAsAborted));
        Iterable<Transaction> resultWithinLimits = chainblock.getByReceiverAndAmountRange("Alan", 50, 201);
        assertEquals(expectedWithinLimits, resultWithinLimits);

        Iterable<Transaction> expectedWhenUpperLimitExcluded = new ArrayList<>(Arrays.asList(success3SameSenderAndReceiverAsAborted));
        Iterable<Transaction> resultWhenUpperLimitExcluded = chainblock.getByReceiverAndAmountRange("Alan", 50, 200);
        assertEquals(expectedWhenUpperLimitExcluded, resultWhenUpperLimitExcluded);

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getByReceiverAndAmountRange_shouldThrowWhenNoSuchTransaction() {
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success3SameSenderAndReceiverAsAborted);
        chainblock.add(success4SameReceiverAsAbortedBiggerIDSameAmount);

        Iterable<Transaction> resultWhenBoundariesOut = chainblock.getByReceiverAndAmountRange("Alan", 51, 200);
    }

    @Test
    public void test_getAllInAmountRange_shouldReturnTransactionsWithinRangInclusiveByOrderOfInsertion(){
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success2);

        Iterable<Transaction> expectedInclusive = new ArrayList<>(Arrays.asList(successful, failed, aborted,
                failed2, success2));
        Iterable<Transaction> resultInclusive = chainblock.getAllInAmountRange(100, 500);
        assertEquals(expectedInclusive, resultInclusive);

        Iterable<Transaction> expectedExclusive = new ArrayList<>(Arrays.asList(failed, aborted));
        Iterable<Transaction> resultExclusive = chainblock.getAllInAmountRange(101, 499);
        assertEquals(expectedExclusive, resultExclusive);
    }

    @Test
    public void test_getAllInAmountRange_shouldReturnEmptyCollectionIfNoTransactionFound(){
        addToChainblockTwoFailedAndOneAbortedTransactions();
        chainblock.add(success2);

        Iterable<Transaction> expectedEmpty = new ArrayList<>();
        Iterable<Transaction> resultAmountRangeNonExisting = chainblock.getAllInAmountRange(301, 399);

        assertEquals(expectedEmpty, resultAmountRangeNonExisting);

    }
}