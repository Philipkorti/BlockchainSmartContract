package at.htlmoedling.blockchain.Classes;


import at.htlmoedling.blockchain.HelperClasses.SHA3Helper;

import java.io.Serializable;
import java.nio.charset.Charset;

public class Transaction implements Serializable {


    //region Description
    private transient byte[] txId;

    private byte[] sender;

    private byte[] receiver;

    private double amount;

    private int nonce;
    private double transactionFeeLimit;

    private double transactionFeeBasePrice;

    private double transectionFeeLimit;


    private transient long receivedTimeStamp;

    private transient double transactionFee;
    //endregion

    /**
     * Getter method for TxID.
     * @return txId
     */
    public byte[] getTxId() {
        return txId;
    }

    /**
     * Setter method for TxId.
     * @param txId
     */
    public void setTxId(byte[] txId) {
        this.txId = txId;
    }
    public String getTxIdAsString( )
    {
        return SHA3Helper.hash256AsHex( this );
    }
    /**
     * Setter method for sizeInByte.
     * @param sizeInByte
     */
    public void setSizeInByte(int sizeInByte) {
        this.sizeInByte = sizeInByte;
    }

    private transient int sizeInByte;

    /**
     * Getter method for the BlockID.
     * @return blockID
     */
    public byte[] getBlockId() {
        return blockId;
    }
    public void setBlockId( byte[] blockId )
    {
        this.blockId = blockId;
    }
    private transient byte[] blockId;

    /**
     * Constructor for the Transaction Class.
     * @param sender
     * @param receiver
     * @param amount
     * @param nonce
     * @param transectionFeeBasePrice
     * @param transectionFeeLimit
     */
    public Transaction(String sender, String receiver, double amount, int nonce, double transectionFeeBasePrice, double transectionFeeLimit) {
        this.sender = sender.getBytes(Charset.forName("utf8"));
        this.receiver = receiver.getBytes(Charset.forName("utf8"));
        this.amount = amount;
        this.nonce = nonce;
        this.transactionFeeBasePrice = transectionFeeBasePrice;
        this.transectionFeeLimit = transectionFeeLimit;

        this.blockId = SHA3Helper.hash256(this);
    }
    public Transaction( byte[] sender, byte[] receiver, double amount, int nonce, double transactionFeeBasePrice,
                        double transactionFeeLimit )
    {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.nonce = nonce;
        this.transactionFeeBasePrice = transactionFeeBasePrice;
        this.transactionFeeLimit = transactionFeeLimit;

        initTxId( );
    }
    /**
     * Getter method for the Sender.
     * @return sender
     */
    public byte[] getSender(){
        return this.sender;
    }
    private void initTxId()
    {
        this.setTxId( SHA3Helper.hash256( this ) );
    }
    /**
     * Setter method for the Sender.
     * @param sender
     */
    public void setSender(byte[] sender){
        this.sender = sender;
    }

    /**
     * Getter method for the Receiver.
     * @return receiver
     */
    public byte[] getReceiver(){
        return this.receiver;
    }

    /**
     * Setter method for the Receiver.
     * @param receiver
     */
    public void setReceiver(byte[] receiver){
        this.receiver = receiver;
    }

    /**
     * Getter method for the Amount.
     * @return amount
     */
    public double getAmout(){
        return amount;
    }

    /**
     * Setter Method for the Amout.
     * @param amount
     */
    public void setAmout(double amount){
        this.amount = amount;
    }

    /**
     * Getter for the TransactionFeeBasePrice.
     * @return transactionFeeBasePrice
     */
    public double getTransactionFeeBasePrice(){
        return transactionFeeBasePrice;
    }

    /**
     * Setter Method for the transactionFeeBasePrice.
     * @param transactionFeeBasePrice
     */
    public void setTransactionFeeBasePrice(double transactionFeeBasePrice){
        this.transactionFeeBasePrice = transactionFeeBasePrice;
    }

    /**
     * Getter for the TransectionFeeLimit.
     * @return transectionFeeLimit
     */
    public double getTransectionFeeLimit(){
        return this.transectionFeeLimit;
    }

    /**
     * Setter method for the TTransactionFeeLimit.
     * @param transectionFeeLimit
     */
    public void setTransactionFeeLimit(double transectionFeeLimit){
        this.transectionFeeLimit = transectionFeeLimit;
    }

    /**
     * Getter for the receivedTimeStamp.
     * @return receivedTimeStamp
     */
    public long getReceivedTimeStamp(){
        return this.receivedTimeStamp;
    }

    /**
     * Setter for the ReceivedTimeStamp.
     * @param timestamp
     */
    public void setReceivedTimeStamp(long timestamp){
        this.receivedTimeStamp = timestamp;
    }

    /**
     * Getter for the transactionFee.
     * @return transactionFee.
     */
    public double getTransactionFee(){
        return this.transactionFee;
    }

    /**
     * Setter for the TransactionFee.
     * @param transactionFee
     */
    public void setTransactionFee(double transactionFee){
        this.transactionFee = transactionFee;
    }

    /**
     * Getter for the SizeInBit.
     * @return this.sizeInByte
     */
    public int getSizeInBit(){
        return this.sizeInByte;
    }

    /**
     * Setter method for the SizeInBit.
     * @param sizeInBit
     */
    public void setSizeInBit(int sizeInBit){
        this.sizeInByte = sizeInBit;
    }
}