import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ChainblockImpl implements Chainblock {

    Map<Integer, Transaction> chainblock;

    public ChainblockImpl() {
        this.chainblock = new LinkedHashMap<>();
    }


    public int getCount() {
        return chainblock.size();
    }

    public void add(Transaction transaction) {

        chainblock.putIfAbsent(transaction.getID(), transaction);
    }

    public boolean contains(Transaction transaction) {

        return chainblock.containsValue(transaction);
    }

    public boolean contains(int id) {

        return chainblock.containsKey(id);
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {

        Transaction transactionByID = getById(id);

        transactionByID.setTransactionStatus(newStatus);
    }

    public void removeTransactionById(int id) {

        checkForTransactionByID(id);
        chainblock.remove(id);
    }

    public Transaction getById(int id) {

        checkForTransactionByID(id);
        return chainblock.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {

        Set<Transaction> transactionSetWithStatus = chainblock.values().stream()
                .filter(transaction -> transaction.getTransactionStatus().equals(status))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        if (transactionSetWithStatus.isEmpty()) {
            throw new IllegalArgumentException("No transactions exist with specified status");
        }
        return transactionSetWithStatus;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {

        Iterable<Transaction> byTransactionStatus = getByTransactionStatus(status);

        return chainblock.values().stream()
                .filter(t -> t.getTransactionStatus().equals(status))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .map(Transaction::getSender)
                .collect(Collectors.toCollection(LinkedHashSet::new));

    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {

        long countOfTransactionsWithStatus = chainblock.values().stream()
                .filter(t -> t.getTransactionStatus().equals(status))
                .count();

        if (countOfTransactionsWithStatus == 0) {
            throw new IllegalArgumentException("No transactions exist with this status " + status);
        }

        return chainblock.values().stream()
                .filter(t -> t.getTransactionStatus().equals(status))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .map(Transaction::getReceiver)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return chainblock.values().stream()
                .sorted(Comparator.comparing(Transaction::getAmount).thenComparing(Transaction::getID))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {

        List<Transaction> senderTransactions = chainblock.values().stream()
                .filter(t -> t.getSender().equals(sender))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (senderTransactions.size() == 0) {
            throw new IllegalArgumentException();
        }
        return senderTransactions;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {

        List<Transaction> byReceiverOrderedByAmountThenByID = chainblock.values().stream()
                .filter(t -> t.getReceiver().equals(receiver))
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getID))
                .collect(Collectors.toList());

        if (byReceiverOrderedByAmountThenByID.size() == 0) {
            throw new IllegalArgumentException();
        }

        return byReceiverOrderedByAmountThenByID;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {

        return chainblock.values().stream()
                .filter(t -> t.getTransactionStatus().equals(TransactionStatus.SUCCESSFUL))
                .filter(t -> t.getAmount() <= amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {

        List<Transaction> transactionsBySenderAndMinAmount = chainblock.values().stream()
                .filter(t -> t.getSender().equals(sender))
                .filter(t -> t.getAmount() > amount)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if (transactionsBySenderAndMinAmount.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return transactionsBySenderAndMinAmount;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {

        List<Transaction> byReceiverAndAmountRangeList = chainblock.values().stream()
                .filter(t -> t.getReceiver().equals(receiver))
                .filter(t -> t.getAmount() >= lo && t.getAmount() < hi)
                .sorted(Comparator.comparing(Transaction::getAmount).reversed().thenComparing(Transaction::getID))
                .collect(Collectors.toList());

        if (byReceiverAndAmountRangeList.size() == 0) {
            throw new IllegalArgumentException();
        }

        return byReceiverAndAmountRangeList;

    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {

        return chainblock.values().stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {

        return new ChainblockIterator() {
        };
    }

    private class ChainblockIterator implements Iterator<Transaction> {

        private int counter = 0;
        int[] chainblockKeys = chainblock.keySet().stream().mapToInt(Integer::intValue).toArray();


        @Override
        public boolean hasNext() {
            return this.counter < chainblock.size();
        }

        @Override
        public Transaction next() {
            return chainblock.get(chainblockKeys[counter++]);
        }
    }

    private void checkForTransactionByID(int id) {
        if (!chainblock.containsKey(id)) {
            throw new IllegalArgumentException("Transaction with ID " + id + " does not exist");
        }
    }


}
