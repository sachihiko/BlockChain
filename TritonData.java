/***
 * This class defines the data to be used in the TritonBlock class
 */
import java.util.ArrayList;
import java.util.List;

public class TritonData {

    private List<String> transactions;
    private int proofId;

    /**
     * Triton Data Constructor
     *
     * @param None
     */
    public TritonData() {
        proofId = 0;
        transactions = new ArrayList<>();
    }

    /*Constructor if specific values are specified*/
    public TritonData(int proofId, List<String> transactions) {
        this.proofId = proofId;
        this.transactions = transactions;
    }

    /*Get transactions*/
    public List<String> getTransactions() {
        return transactions;
    }

    /*Get proofId*/
    public int getProofId() {
        return proofId;
    }

    /*Print the data block*/
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("DATA Start--------------------------------\n"
                + "Proof of work: " + getProofId() + "\n");

        for (int i = 0; i < transactions.size(); i++) {
            sb.append("Transaction " + i + "\n"
                    + "Transaction Content: " + getTransactions().get(i) + "\n");
        }
        //sb.append("Transaction Content: Triton Coin earned: 1\n");
        sb.append("DATA End  --------------------------------\n");

        return sb.toString();
    }

}
