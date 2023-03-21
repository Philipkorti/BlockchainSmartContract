package at.htlmoedling.blockchain.logic;



import at.htlmoedling.blockchain.Classes.Block;
import at.htlmoedling.blockchain.Classes.Transaction;
import at.htlmoedling.blockchain.threads.SizeHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PendingTransaction {
    private PriorityQueue<Transaction> pendingTransactions;

    public PendingTransaction( )
    {
        Comparator<Transaction> comparator = new TransactionComparatorByFee( );
        pendingTransactions = new PriorityQueue<>( 11, comparator );
    }

    public PendingTransaction( Comparator<Transaction> comparator )
    {
        pendingTransactions = new PriorityQueue<>( 11, comparator );
    }
    public void ClearPendingTransaction(Block block){
        for (Transaction transaction : block.getTransactions()){
            pendingTransactions.remove(transaction);
        }
    }
    public List<Transaction> getTransactionsForNextBlock(){
        List<Transaction> nextTransactions = new ArrayList<>();
        int transactionCapacity = SizeHelper.calculateTransactionCapacity();

        PriorityQueue<Transaction> tmp = new PriorityQueue<>(pendingTransactions);

        while(transactionCapacity > 0 && !tmp.isEmpty()){
            nextTransactions.add(tmp.poll());
            transactionCapacity--;
        }
        return nextTransactions;
    }
    public void addPendingTransaction( Transaction transaction )
    {
        pendingTransactions.add( transaction );
    }
    public void clearPendingTransactions( Block block )
    {
        clearPendingTransactions( block.getTransactions() );
    }
    public void clearPendingTransactions( List<Transaction> transactions )
    {
        for ( Transaction transaction : transactions )
        {
            pendingTransactions.remove( transaction );
        }
    }
    public boolean pendingTransactionsAvailable( )
    {
        return !pendingTransactions.isEmpty();
    }
    public void clearPendingTransaction( Transaction transaction )
    {
        pendingTransactions.remove( transaction );
    }
}
