package at.htlmoedling.blockchain.threads;

import at.htlmoedling.blockchain.Classes.Block;

public interface MinerListener {
    void notifyNewBlock(Block block);
}
