package at.htlmoedling.blockchain.logic;


import at.htlmoedling.blockchain.threads.Miner;

public class DependencyManager {
    private static PendingTransaction pendingTransactions;

    public static PendingTransaction getPendingTransactions( )
    {
        if ( pendingTransactions == null )
        {
            pendingTransactions = new PendingTransaction( );
        }

        return pendingTransactions;
    }

    public static void injectPendingTransactions( PendingTransaction pendingTransactions )
    {
        DependencyManager.pendingTransactions = pendingTransactions;
    }

    private static Blockchain blockchain;

    public static Blockchain getBlockchain( )
    {
        if ( blockchain == null )
        {
            blockchain = new Blockchain( );
        }

        return blockchain;
    }

    public static void injectBlockchain( Blockchain blockchain )
    {
        DependencyManager.blockchain = blockchain;
    }

    private static Miner miner;

    public static Miner getMiner( )
    {
        if ( miner == null )
        {
            miner = new Miner( );
        }

        return miner;
    }
}
