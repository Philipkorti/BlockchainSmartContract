package at.htlmoedling.blockchain.Classes;

public class GenesisBlock extends Block{
    public static byte[] ZERO_HASH = new byte[32];

    public GenesisBlock(){
        super(ZERO_HASH);
    }
}
