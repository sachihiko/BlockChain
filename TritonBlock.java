/***
 * This class defines the Blocks to be used in TritonBlockChain.
 */
public class TritonBlock {
	/*Class variables, all the attributes of the block*/
    private int index;
    private long timestamp;
    private TritonData data;
    private String prev_hash;
    private String self_hash;

    /*Constructor, builds a block with passed in variables, then creates a hash for curr block*/
    public TritonBlock(int index, long timestamp, TritonData data,
                       String prev_hash){
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.prev_hash = prev_hash;
        this.self_hash = hashBlock();
    }
    /**
     * Hashes data in block
     *
     * @return Hash value
     */
    private String hashBlock(){

        StringBuilder sb = new StringBuilder();
        char[] charArray; // holds each char of string
        int sum = 0; // sum of ASCII values

        charArray = data.toString().toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            sum += charArray[i];
        }

        return Integer.toString(sum * (sum + 3));
    }

    /*Get index*/
    public int getIndex(){
        return index;
    }

    /*Get timestamp*/
    public long getTimestamp(){
        return timestamp;
    }

    /*Get data block*/
    public TritonData getData(){
        return data;
    }

    /*Get previous hash*/
    public String getPrev_hash(){
        return prev_hash;
    }

    /*Get current hash*/
    public String getSelf_hash(){
        return self_hash;
    }

    /*Print the block*/
    public String toString(){

        StringBuilder sb = new StringBuilder();

        sb.append("TritonBlock " + getIndex()     + "\n");
        sb.append("Index: "      + getIndex()     + "\n");
        sb.append("Timestamp: "    + getTimestamp() + "\n");
        sb.append("Prev Hash: "  + getPrev_hash() + "\n");
        sb.append("Hash: "       + getSelf_hash() + "\n");

        sb.append(data.toString());

        return sb.toString();
    }
}
