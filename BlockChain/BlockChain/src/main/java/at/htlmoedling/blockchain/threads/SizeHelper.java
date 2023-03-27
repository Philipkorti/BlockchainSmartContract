package at.htlmoedling.blockchain.threads;



import at.htlmoedling.blockchain.Classes.Block;
import at.htlmoedling.blockchain.Classes.Transaction;
import at.htlmoedling.blockchain.logic.Blockchain;

import java.util.List;

public class SizeHelper {
    public final static int TRANSACTION_SIZE_IN_BYTES = 128;

    public final static int BLOCK_HEADER_SIZE_IN_BYTES = 80;

    public final static int BLOCK_META_DATA_SIZE_IN_BYTES = 12;

    public static int calculateBlockSize( Block block )
    {
        return BLOCK_HEADER_SIZE_IN_BYTES + block.getTransactions( ).size( ) * TRANSACTION_SIZE_IN_BYTES +
                BLOCK_META_DATA_SIZE_IN_BYTES;
    }

    public static int calculateTransactionListSize( List<Transaction> transactions )
    {
        return transactions.size( ) * TRANSACTION_SIZE_IN_BYTES;
    }

    public static int calculateTransactionCapacity( )
    {
        return ( Blockchain.MAX_BLOCK_SIZE_IN_BYTES - SizeHelper.BLOCK_META_DATA_SIZE_IN_BYTES -
                SizeHelper.BLOCK_HEADER_SIZE_IN_BYTES ) / SizeHelper.TRANSACTION_SIZE_IN_BYTES;
    }
}
