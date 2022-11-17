import java.util.Date;

public class BlockHeader {
    private int versionsNumber;
    private long tinmeStamp;
    private byte[]  previousHash;
    private byte[] transaktionsListeHash;
    private int nonce;

    public BlockHeader(long timestamp, byte[] previousHash, byte[] transaktionsListeHash){
        this.tinmeStamp = timestamp;
        this.versionsNumber = 1;
        this.previousHash = previousHash;
        this.transaktionsListeHash = transaktionsListeHash;
    }
    public int GetVersionNumber() {
        return this.versionsNumber;
    }

    public void SetVersionNumber(int versionNumber) {
        this.versionsNumber = versionNumber;
    }

    public long GetTimeStamp() {
        return this.tinmeStamp;
    }

    public void SetTimeStamp(long timeStamp) {
        this.tinmeStamp = timeStamp;
    }

    public byte[] GetBlockChainID() {
        return this.previousHash;
    }

    public void SetBlockChainID(byte[] previousHash) {
        this.previousHash = previousHash;
    }

    public byte[] GetTransaktionsListe() {
        return this.transaktionsListeHash;
    }
    public void SetTransaktionsListe(byte[] transaktionsListe){
        this .transaktionsListeHash = transaktionsListe;
    }
    public int GetNonce(){
        return this.nonce;
    }
    public void SetNonce(int nonce){
        this.nonce = nonce;
    }
    public void IncremantNonce() throws ArithmeticException{
        if (this.nonce == Integer.MAX_VALUE){
            throw new ArithmeticException("The integer is to heigh!");
        }
        this.nonce++;
    }
}
