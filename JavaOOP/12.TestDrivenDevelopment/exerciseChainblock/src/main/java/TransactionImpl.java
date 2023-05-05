public class TransactionImpl implements Comparable<TransactionImpl>, Transaction {

    private int id;
    private TransactionStatus status;
    private String from;
    private String to;
    private double amount;

    public TransactionImpl(int id, TransactionStatus status, String from, String to, double amount) {
        this.id = id;
        this.status = status;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public int compareTo(TransactionImpl o) {

        if (this.getAmount() > o.getAmount()) {
            return 1;
        } else if (this.getAmount() < o.getAmount()) {
            return -1;
        }
        return 0;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public TransactionStatus getTransactionStatus() {
        return this.status;
    }

    @Override
    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.status = transactionStatus;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getSender() {
        return from;
    }

    @Override
    public String getReceiver() {
        return to;
    }


}
