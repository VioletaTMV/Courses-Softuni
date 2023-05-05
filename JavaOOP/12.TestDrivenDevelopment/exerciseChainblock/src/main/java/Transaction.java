public interface Transaction {

    int getID();

    TransactionStatus getTransactionStatus();

    void setTransactionStatus(TransactionStatus transactionStatus);

    double getAmount();

    String getSender();

    String getReceiver();

}
