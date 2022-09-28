package blockchain;

public class BlockchainService {

    private final RepositoryImpl repository = RepositoryImpl.getInstance();

    public void loadBlockchain() {
        Block lastBlock = repository.load();
        if (isValid(lastBlock))
            repository.setLastBlock(lastBlock);
    }

    public boolean isValid(Block lastBlock) {
        while (lastBlock != null) {
            if (!getHash(lastBlock).equals(lastBlock.getHash()))
                return false;
            lastBlock = lastBlock.getPreviousBlock();
        }
        return true;
    }

    public String getHash(Block block) {
        return Sha256.applySha256(block.toString());
    }

    public void addBlock() {
        repository.save(repository.addBlock(repository.getLastBlock()));
    }

    public Block getLastBlock() {
        return repository.getLastBlock();
    }

    public void printBlock(Block block) {
        System.out.printf("""
                Block:
                Created by: %s
                %s gets 100 VC
                Id: %d
                Timestamp: %d
                Magic number: %d
                Hash of the previous block:
                %s
                Hash of the block:
                %s
                Block data: %s
                Block was generating for %d seconds
                %s
                
                """,
                block.getMiner(),
                block.getMiner(),
                block.getId(),
                block.getTimeStamp(),
                block.getMagicNumber(),
                block.getPreviousHash(),
                block.getHash(),
                block.getMessage().isBlank() ? "no messages" : block.getMessage(),
                block.getGenerationTime() / 1000,
                getZerosChangeMessage(block)
        );
    }

    private String getZerosChangeMessage(Block block) {
        int deltaZeros = block.computeDeltaZeros(block.getGenerationTime());
        if (deltaZeros > 0)
            return String.format("N was increased to %d", block.getZeros() + deltaZeros);
        if (deltaZeros < 0)
            return String.format("N was decreased by %d", Math.abs(deltaZeros));
        return "N stays the same";
    }
}
