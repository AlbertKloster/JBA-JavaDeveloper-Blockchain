package blockchain;

public interface Repository {
    void save(Block lastBlock);

    Block load();
}
