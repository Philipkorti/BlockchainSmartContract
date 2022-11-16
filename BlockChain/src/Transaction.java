
import java.io.Serializable;
import java.nio.charset.Charset;

public class Transaction implements Serializable {
private byte[] sender;
private byte[] receiver;
private double amount;
private int nonce;
private double transactionFeeBasePrice;
private double transectionFeeLimit;
private transient byte[] txId;

public Transaction(String sender, String receiver, double amount, int nonce, double transectionFeeBasePrice, double transectionFeeLimit){
    this.sender = sender.getBytes(Charset.forName("utf8"));
    this.receiver = receiver.getBytes(Charset.forName("utf8"));
    this.amount = amount;
    this.nonce = nonce;
    this.transactionFeeBasePrice = transectionFeeBasePrice;
    this.transectionFeeLimit = transectionFeeLimit;

    this.txId = SHA3Helper.hash256(this);
}
}
