package at.htlmoedling.blockchain.logic;



import at.htlmoedling.blockchain.Classes.Block;
import at.htlmoedling.blockchain.Classes.Chain;
import at.htlmoedling.blockchain.Classes.Transaction;
import at.htlmoedling.blockchain.HelperClasses.SHA3Helper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Blockchain{
    public BigInteger difficulty;
    private Chain chain;
    private Map<String, Block> blockCache;
    private Map<String, Transaction> transactionCache;
    public final static int MAX_BLOCK_SIZE_IN_BYTES = 1120;
    public byte[] getPreviousHash( )
    {
        return chain.getLast( ).getBlockHash( );
    }

    public final static int NETWORK_ID = 1;
    public Blockchain(){
        this.chain = new Chain(NETWORK_ID);
        this.blockCache = new ConcurrentHashMap<>();
        this.transactionCache = new ConcurrentHashMap<>();
        this.difficulty = new BigInteger("-57895550000000000000000000000000000000000000000000000000000000000000000000000");
    }

    public void addBlock(Block block){
        chain.add(block);
        blockCache.put(SHA3Helper.digestToHex(block.getBlockHash()), block);

        for (Transaction transaction : block.getTransactions()){
            transactionCache.put(transaction.getTxIdAsString(), transaction);
        }
    }

    public boolean fulfillsDifficulty(byte[] digest){
        BigInteger temp = new BigInteger(digest);
        return temp.compareTo(difficulty) <= 0;
    }

}