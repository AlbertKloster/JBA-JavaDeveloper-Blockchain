package blockchain;

public class BlockMiner extends Thread {

    private final String minerName;

    private Block lastBlock;
    private Block newBlock;
    private String message;

    public BlockMiner(String minerName, String message) {
        this.minerName = minerName;
        this.message = message;
    }

    public Block getNewBlock() {
        return newBlock;
    }
    public String getMinerName() {
        return minerName;
    }

    public void setLastBlock(Block lastBlock) {
        this.lastBlock = lastBlock;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public void run() {
        newBlock = new Block(minerName, lastBlock, message);
    }
}
