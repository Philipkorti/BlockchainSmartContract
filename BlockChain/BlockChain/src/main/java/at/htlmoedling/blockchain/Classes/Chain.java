package at.htlmoedling.blockchain.Classes;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Chain {
    private int networkId;
    private CopyOnWriteArrayList blocks;
    private List<Block> chain = new CopyOnWriteArrayList<>( );

    public Chain(int networkId) {
        this.networkId = networkId;
        chain.add( new GenesisBlock( ) );
    }
    public void add( Block block )
    {
        chain.add( block );
    }
    public int getNetworkId() {
        return networkId;
    }

    public void setNetworkId(int networkId) {
        this.networkId = networkId;
    }
    public Block getLast( )
    {
        return chain.get( chain.size( ) - 1 );
    }
    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(CopyOnWriteArrayList blocks) {
        this.blocks = blocks;
    }
}
