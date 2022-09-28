package blockchain;

import java.util.Date;
import java.util.Random;

public class Block {
    private int zeros;
    private String miner;
    private long id;
    private long timeStamp = new Date().getTime();
    private Block previousBlock;
    private long magicNumber;
    private String previousHash;
    private String hash;
    private long generationTime;
    private String message;

    public Block() {}

    public Block(String miner, Block previousBlock, String message) {
        this.miner = miner;
        this.message = message;
        if (previousBlock == null) {
            this.id = 1;
            this.previousBlock = null;
            this.previousHash = "0";
            this.zeros = 0;
        } else {
            this.id = previousBlock.getId() + 1;
            this.previousBlock = previousBlock;
            this.previousHash = previousBlock.getHash();
            this.zeros = updateZeros(previousBlock);
        }
        setHash(zeros);
    }

    private int updateZeros(Block previousBlock) {
        if (previousBlock == null)
            return 0;
        return previousBlock.getZeros() + getDeltaZeros();
    }

    public int getZeros() {
        return zeros;
    }
    public void setZeros(int zeros) {
        this.zeros = zeros;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public long getMagicNumber() {
        return magicNumber;
    }
    public void setMagicNumber(long magicNumber) {
        this.magicNumber = magicNumber;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }
    public void setHash(String hash) {
        this.hash = hash;
    }

    public long getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(long generationTime) {
        this.generationTime = generationTime;
    }

    private void setHash(int zeros) {
        long startTime = new Date().getTime();
        while (true) {
            Random random = new Random();
            this.magicNumber = random.nextInt(100_000_000);
            String hash = Sha256.applySha256(this.toString());
            String regex = "0".repeat(zeros) + ".*";
            if (hash.matches(regex)) {
                this.hash = hash;
                this.generationTime = new Date().getTime() - startTime;
                return;
            }
        }
    }

    private int getDeltaZeros() {
        long previousGenerationTime = previousBlock == null ? 0 : previousBlock.getGenerationTime();
        return computeDeltaZeros(previousGenerationTime);
    }

    public int computeDeltaZeros(long generationTime) {
        if (generationTime < 10)
            return 1;
        if (generationTime > 100)
            return -1;
        return 0;
    }

    public Block getPreviousBlock() {
        return previousBlock;
    }

    public void setPreviousBlock(Block previousBlock) {
        this.previousBlock = previousBlock;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "" + id + timeStamp + (previousBlock == null ? 0 : previousBlock.hash) + magicNumber + message;
    }
}
