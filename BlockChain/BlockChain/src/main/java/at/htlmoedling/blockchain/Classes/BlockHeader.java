package at.htlmoedling.blockchain.Classes;

import at.htlmoedling.blockchain.HelperClasses.SHA3Helper;

import java.io.Serializable;
import java.util.Date;
import java.io.Serializable;

public class BlockHeader implements Serializable {

    private int versionsNumber;

    private long tinmeStamp;

    private byte[]  previousHash;

    private byte[] transaktionsListeHash;

    private int nonce = 0;
    public BlockHeader(){

    }

    public BlockHeader(long timestamp, byte[] previousHash, byte[] transaktionsListeHash){
        this.tinmeStamp = timestamp;
        this.versionsNumber = 1;
        this.previousHash = previousHash;
        this.transaktionsListeHash = transaktionsListeHash;
    }

    public int getVersionNumber() {
        return this.versionsNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionsNumber = versionNumber;
    }

    public long getTimeStamp() {
        return this.tinmeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.tinmeStamp = timeStamp;
    }

    public byte[] getBlockChainID() {
        return this.previousHash;
    }

    public void setBlockChainID(byte[] previousHash) {
        this.previousHash = previousHash;
    }

    public byte[] getTransaktionsListe() {
        return this.transaktionsListeHash;
    }

    public void setTransaktionsListeHash(byte[] transaktionsListe){
        this .transaktionsListeHash = transaktionsListe;
    }

    public int getNonce(){
        return this.nonce;
    }

    public void setNonce(int nonce){
        this.nonce = nonce;
    }

    public void IncremantNonce() throws ArithmeticException{
        if (this.nonce == Integer.MAX_VALUE){
            throw new ArithmeticException("The integer is to heigh!");
        }
        this.nonce++;
    }
    public byte[] asHash( )
    {
        return SHA3Helper.hash256( this);
    }

    public void incrementNonce( ) throws ArithmeticException
    {
        if ( this.nonce == Integer.MAX_VALUE )
        {
            throw new ArithmeticException( "nonce to high" );
        }

        this.nonce++;
    }
}
