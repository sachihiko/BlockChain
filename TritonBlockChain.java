/***
 * NAME : Sachihiko Kanda
 * ID   : A14782640
 * EMAIL: sakanda@ucsd.edu
 * DATE: 12 November 2018
 *
 * This class implements a BlockChain
 */
import java.util.*;

public class TritonBlockChain {

	private static final String MINE_REWARD = "1";
    /*Blockchain clas variable*/
    private List<TritonBlock> blockchain;

    /*Constructor, takes in genesis block data to start the blockchain*/
    public TritonBlockChain(int index, long timestamp, TritonData data, String prev_hash) {
        blockchain = new ArrayList<>();
        blockchain.add(new TritonBlock(index, timestamp, data, prev_hash));
    }

    /*Makes the next block after the proof of work from mining is finished*/
    public TritonBlock makeNewBlock(TritonBlock lastBlock, TritonData newData) {
        //initializes new block
        TritonBlock block = new TritonBlock(lastBlock.getIndex() + 1,
                System.currentTimeMillis(), newData, lastBlock.getSelf_hash());

        return block;
    }

    /*Mines the transaction and creates the block to add to the blockchain*/
    public boolean beginMine(List<String> curTransactions) {

        if (curTransactions.isEmpty())
            return false;

        int proofId = proofOfWork();
        TritonBlock lastBlock = blockchain.get(blockchain.size() - 1);
        TritonData newData = new TritonData(proofId, curTransactions);

        curTransactions.add("Triton coin earned: " + MINE_REWARD);

        //uses latest block as reference to make new block
        TritonBlock block = makeNewBlock(lastBlock, newData);
        blockchain.add(block);

        return true;
    }

    /*Simple proof of work algorithm to prove cpu usage was used to mine block*/
    public int proofOfWork() {
        int num = 13;
        int prevProofId = blockchain.get(blockchain.size() - 1).getData().getProofId();
        prevProofId++;

        return lcm(prevProofId, num);
    }

    //helper method for finding lcm and proof of work
    public static int gcd(int a, int b) {
        if (a == 0 || b == 0)
            return 0;
        //base case
        if (a == b)
            return a;
        //recursively call until gcd found
        if (a > b)
            return gcd(a - b, b);
        return gcd(a, b - a);
    }
    //helper method for finding proof of work
    public static int lcm(int a, int b){
        return (a * b) / gcd(a, b);
    }

    /*Prints current blockchain*/
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                "TRITON BLOCKCHAIN\n" +
                "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");

        for(TritonBlock block : blockchain) sb.append("\n" + block.toString());

        return sb.toString();
    }

    /*Validates each block in the chain looking for any hash pointer descrepancies, which can point to a tampering problem*/
    public boolean validateChain() {

        for (int i = 0; i < blockchain.size(); i++) {
            if (i > 0)
                if (blockchain.get(i).getPrev_hash() != blockchain.get(i - 1).getSelf_hash())
                    return false;
        }
        return true;
    }
    /*Get blockchain*/
    public List<TritonBlock> getBlockchain() {
        return blockchain;
    }
}
