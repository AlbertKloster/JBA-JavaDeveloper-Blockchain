package blockchain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blockchain {
    private final Random random = new Random();

    private final List<String> messagesWinner = List.of(
            "sent 30 VC to miner2",
            "sent 30 VC to miner3",
            "sent 30 VC to miner4",
            "sent 30 VC to miner5",
            "sent 30 VC to miner6"
    );
    private final List<String> messagesLoser = List.of(
            "sent 1 VC to ShoesShop",
            "sent 3 VC to FastFood",
            "sent 5 VC to CarShop",
            "sent 4 VC to ShoesShop",
            "sent 15 VC to BookShop"
    );
    private Block lastBlock;
    private List<BlockMiner> miners = new ArrayList<>();
    private List<String> messages = new ArrayList<>();
    private String winner;

    public Block addBlock(Block block) {

        String message = messages.stream().reduce("", (sub, s) -> sub + "\n" + s);
        messages.clear();
        startMining(block, message);
        boolean hasFound = false;
        while (!hasFound) {
            for (BlockMiner miner : miners) {
                Block minedBlock = miner.getNewBlock();
                if (minedBlock != null) {
                    lastBlock = minedBlock;
                    winner = miner.getMinerName();
                    messages.add(winner + messagesWinner.get(random.nextInt(messagesWinner.size())));
                    hasFound = true;
                    stopMining();
                    break;
                }
            }
        }
        return lastBlock;
    }

    private void startMining(Block block, String message) {
        miners = List.of(
                new BlockMiner("miner1", message),
                new BlockMiner("miner2", message),
                new BlockMiner("miner3", message),
                new BlockMiner("miner4", message),
                new BlockMiner("miner5", message)
        );
        miners.forEach(miner -> {
            miner.setLastBlock(block);
            miner.start();
        });
    }

    private void stopMining() {
        miners.forEach(miner -> {
            if (!miner.getMinerName().equals(winner))
                messages.add(miner.getMinerName() + messagesLoser.get(random.nextInt(messagesLoser.size())));
        });
    }

    public Block getLastBlock() {
        return lastBlock;
    }

    public void setLastBlock(Block lastBlock) {
        this.lastBlock = lastBlock;
    }

}
