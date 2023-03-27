package at.htlmoedling.blockchain.threads;

import at.htlmoedling.blockchain.Classes.Block;
import at.htlmoedling.blockchain.Classes.Transaction;
import at.htlmoedling.blockchain.logic.Blockchain;
import at.htlmoedling.blockchain.logic.DependencyManager;
import at.htlmoedling.blockchain.logic.PendingTransaction;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.List;

public class Miner implements Runnable {
    private boolean mining = true;
    private List<MinerListener> listeners = new ArrayList<>( );
    private boolean cancelBlock = false;

    private Block block;
   @Override public void run( ){
        while(isMining()){
            this.block = getNewBlockForMining();
            while(!cancelBlock && doesNotFulfillDifficulty(block.getBlockHash())){
                block.incrementNonce();
            }
            blockMined(block);
        }
    }
    private boolean doesNotFulfillDifficulty( byte[] digest )
    {
        Blockchain blockchain = DependencyManager.getBlockchain( );
        return !blockchain.fulfillsDifficulty( digest );
    }
    private void blockMined(Block block){
        if(block.getTransactions().size() > 0){
            for (Transaction transaction : block.getTransactions()){
                transaction.setBlockId(block.getBlockHash());
            }
        }
        DependencyManager.getBlockchain( ).addBlock( block );
        DependencyManager.getPendingTransactions().clearPendingTransactions( block );

        for ( MinerListener listener : listeners )
        {
            listener.notifyNewBlock( block );
        }
    }
    public boolean isMining( )
    {
        return mining;
    }

    private Block getNewBlockForMining( )
    {
        PendingTransaction pendingTransactions = DependencyManager.getPendingTransactions( );
        Blockchain blockchain = DependencyManager.getBlockchain( );
        List<Transaction> transactions = pendingTransactions.getTransactionsForNextBlock( );

        return new Block( transactions, blockchain.getPreviousHash( ) );
    }
    public void registerListener( MinerListener listener )
    {
        listeners.add( listener );
    }
    public void stopMining( )
    {

        this.mining = false;
    }
}
