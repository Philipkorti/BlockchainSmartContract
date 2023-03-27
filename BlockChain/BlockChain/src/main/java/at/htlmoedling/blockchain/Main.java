package at.htlmoedling.blockchain;


import at.htlmoedling.blockchain.Classes.Block;
import at.htlmoedling.blockchain.Classes.Transaction;
import at.htlmoedling.blockchain.HelperClasses.SHA3Helper;
import at.htlmoedling.blockchain.logic.DependencyManager;
import at.htlmoedling.blockchain.logic.PendingTransaction;
import at.htlmoedling.blockchain.threads.Miner;
import at.htlmoedling.blockchain.threads.MinerListener;

public class Main implements MinerListener {
    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        PendingTransaction trx = DependencyManager.getPendingTransactions();
        for(int i = 0; i< 1000; i++){
            String sender = "testSender" + i;
            String receiver = "testReceiver" +i;
            double amount = i * 1.1;
            double transactionFee = 0.0000001 * i;
            trx.addPendingTransaction(new Transaction(sender.getBytes(), receiver.getBytes(), amount, 1, transactionFee, 10.0));
        }
        main.MinerTest();
    }
    public static void setUp(){
        PendingTransaction trx = DependencyManager.getPendingTransactions();
        for(int i = 0; i< 1000; i++){
            String sender = "testSender" + i;
            String receiver = "testReceiver" +i;
            double amount = i * 1.1;
            double transactionFee = 0.0000001 * i;
            trx.addPendingTransaction(new Transaction(sender.getBytes(), receiver.getBytes(), amount, 1, transactionFee, 10.0));
        }
    }
    public void MinerTest() throws InterruptedException {
        Miner miner = new Miner( );
        miner.registerListener( this );

        Thread thread = new Thread(miner);
        thread.start( );

        while (DependencyManager.getPendingTransactions().pendingTransactionsAvailable()){
            Thread.sleep(1000);
        }
        miner.stopMining();
    }
    @Override public void notifyNewBlock( Block block )
    {
        System.out.println( "new block mined" );

        System.out.println( SHA3Helper.digestToHex( block.getBlockHash( ) ) );
    }
}