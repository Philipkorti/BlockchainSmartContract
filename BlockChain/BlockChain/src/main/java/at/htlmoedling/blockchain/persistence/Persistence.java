package at.htlmoedling.blockchain.persistence;


import at.htlmoedling.blockchain.Classes.Block;
import at.htlmoedling.blockchain.Classes.Chain;
import at.htlmoedling.blockchain.HelperClasses.SHA3Helper;
import com.google.gson.Gson;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Persistence {
    private Charset encoding = StandardCharsets.UTF_8;

    private String path = "chains/";

    public void writeChain(Chain chain) throws IOException{
        for (Block block : chain.getBlocks()){
            String id = SHA3Helper.digestToHex(block.getBlockHash());
            writeBlock(block,chain.getNetworkId(),id);
        }
    }

    private void writeBlock(Block block, int networkId, String id){
        File file = new File(getPathToBlock(networkId, id));
        try (OutputStreamWriter outputStream = new OutputStreamWriter( new FileOutputStream( file ), encoding ) )
        {
            Gson gson = new Gson( );
            outputStream.write(gson.toJson(block));
        }
        catch (IOException e)
        {
            e.printStackTrace( );
        }
    }

    private Block readBlock(File file){
        Block block = null;
        try(InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file), encoding))
        {
            Gson gson = new Gson();
            return gson.fromJson(inputStream, Block.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return block;
    }

    private String getPathToBlock( int networkId, String blockId )
    {
        return path + networkId + "/" + blockId + ".json";
    }
}
