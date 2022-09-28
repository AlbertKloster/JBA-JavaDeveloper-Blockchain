package blockchain;

public class Main {
    public static void main(String[] args) {

        BlockchainService service = new BlockchainService();
        service.loadBlockchain();
        for (int i = 0; i < 15; i++) {
            service.addBlock();
            service.printBlock(service.getLastBlock());
        }
    }
}
