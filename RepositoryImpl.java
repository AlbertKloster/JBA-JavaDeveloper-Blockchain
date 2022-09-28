package blockchain;

public class RepositoryImpl implements Repository {

    private volatile static RepositoryImpl instance;
    private final String FILE = "blockchain.txt";
    private final Blockchain blockchain = new Blockchain();

    private final FileHandler fileHandler = new FileHandler();

    private RepositoryImpl() { }

    public static RepositoryImpl getInstance() {
        if (instance == null)
            synchronized (RepositoryImpl.class) {
                if (instance == null)
                    instance = new RepositoryImpl();
            }
        return instance;
    }

    public Block addBlock(Block block) {
        return blockchain.addBlock(block);
    }

    public Block getLastBlock() {
        return blockchain.getLastBlock();
    }

    public void setLastBlock(Block lastBlock) {
        blockchain.setLastBlock(lastBlock);
    }

    @Override
    public void save(Block lastBlock) {
        fileHandler.save(FILE, lastBlock);
    }

    @Override
    public Block load() {
        return fileHandler.load(FILE);
    }
}
