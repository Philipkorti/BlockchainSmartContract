
import java.io.Serializable;
import java.nio.charset.Charset;

public class Transaction implements Serializable {
    private transient byte[] txId;

    private byte[] sender;
    private byte[] receiver;
    private double amount;
    private int nonce;
    private double transactionFeeBasePrice;
    private double transectionFeeLimit;

    private transient long receivedTimeStamp;
    private transient double transactionFee;

    private transient int sizeInByte;

    private transient byte[] blockId;

    public Transaction(String sender, String receiver, double amount, int nonce, double transectionFeeBasePrice, double transectionFeeLimit) {
        this.sender = sender.getBytes(Charset.forName("utf8"));
        this.receiver = receiver.getBytes(Charset.forName("utf8"));
        this.amount = amount;
        this.nonce = nonce;
        this.transactionFeeBasePrice = transectionFeeBasePrice;
        this.transectionFeeLimit = transectionFeeLimit;

        this.blockId = SHA3Helper.hash256(this);
    }

    public byte[] GetSender(){
        return this.sender;
    }
    public void SetSender(byte[] sender){
        this.sender = sender;
    }
    public byte[] GetReceiver(){
        return this.receiver;
    }
    public void SetReceiver(byte[] receiver){
        this.receiver = receiver;
    }
    public double GetAmout(){
        return amount;
    }
    public void SetAmout(double amount){
        this.amount = amount;
    }
    public double GetTransactionFeeBasePrice(){
        return transactionFeeBasePrice;
    }
    public void SetTransactionFeeBasePrice(double transactionFeeBasePrice){
        this.transactionFeeBasePrice = transactionFeeBasePrice;
    }

}
