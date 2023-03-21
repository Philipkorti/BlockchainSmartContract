package at.htlmoedling.blockchain.Classes;

import at.htlmoedling.blockchain.HelperClasses.SHA3Helper;
import at.htlmoedling.blockchain.threads.SizeHelper;
import org.bouncycastle.util.Arrays;

import java.util.ArrayList;
import java.util.List;


public class Block {

    //region Fields
    private int macicNumber = 0xD9B4BEF9;
    private int blockSize;

    private List<Transaction> transactions;

    private int transactionCount;

    private BlockHeader blockHeader;
    //endregion

    //region Properties
    /**
     * Getter for the BlockHeader
     * @return blockHeader
     */
    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    /**
     * Setter Method for a new BlockHeader.
     * @param blockHeader
     */
    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }

    /**
     * Getter method for the BlockSize.
     * @return blockSize
     */
    public int getBlockSize() {
        return blockSize;
    }

    /**
     * Setter method for the blockSize.
     * @param blockSize
     */
    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    /**
     * Getter Method for macicNumber.
     * @return macicNumber
     */
    public int getMacicNumber() {
        return macicNumber;
    }

    /**
     * Setter method for the macicNumber.
     * @param macicNumber
     */
    public void setMacicNumber(int macicNumber) {
        this.macicNumber = macicNumber;
    }

    /**
     * Getter method for the Transactions.
     * @return transactions
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Setter Method for Transactions.
     * @param transactions
     */
    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Getter method for the TransactionCount.
     * @return transactionCount
     */
    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * Setter Method for the TransactionCount.
     * @param transactionCoubt
     */
    public void setTransactionCount(int transactionCoubt) {
        this.transactionCount = transactionCoubt;
    }

    /**
     * Getter Method for the TransactonHash.
     * @return transactionsInBytes
     */
    public byte[] getTransactonHash(){
        byte[] transactionsInBytes = new byte[0];
        for (Transaction transaction : transactions){
            transactionsInBytes = Arrays.concatenate(transactionsInBytes,transaction.getTxId());
        }
        return transactionsInBytes;
    }

    public void incrementNonce( ) throws ArithmeticException
    {
        this.blockHeader.incrementNonce( );
    }
    /**
     * Getter Method or BlockHash
     * @return the Hashcode in SHA-3
     */
    public byte[] getBlockHash(){
        return this.blockHeader.asHash();
    }
    //endregion

    //region Constructors
    /**
     * Method to create a new block with the previousHash.
     * @param previousHash
     */
    public Block(byte[] previousHash){
        this.blockSize = 92;
        this.transactions = new ArrayList<>();
        this.transactionCount = 0;
        this.blockHeader = new BlockHeader(System.currentTimeMillis(), previousHash, getTransactonHash());
    }
    //endregion

    //region Methods
    /**
     * Method for add Transaction in the Transaction Class.
     * @param transaction
     */
    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
        this.transactionCount++;
        this.blockHeader.setTransaktionsListeHash(getTransactonHash());
        this.blockSize = SizeHelper.calculateBlockSize(this);
    }

    private byte[] getTransactionHash( )
    {
        byte[] transactionsInBytes = new byte[ 0 ];

        for ( Transaction transaction : transactions )
        {
            transactionsInBytes = Arrays.concatenate( transactionsInBytes, transaction.getTxId( ) );
        }

        return SHA3Helper.hash256( transactionsInBytes );
    }
    //endregion
    public Block( List<Transaction> transactions, byte[] previousHash )
    {
        this.transactions = transactions;
        this.transactionCount = transactions.size( );
        this.blockSize = SizeHelper.calculateBlockSize( this );
        this.blockHeader = new BlockHeader( System.currentTimeMillis( ), previousHash, getTransactionHash( ) );
    }

    public int getNonce(){return this.blockHeader.getNonce();}

    public void setNonce(int nonce){this.blockHeader.setNonce(nonce);}


}
