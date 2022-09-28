package blockchain;

public class BlockSerializer {
    public String toString(Block block) {

        if (block == null)
            return "";

        return String.format("%d:%s:%d:%d:%d:%s:%s:%s:%d:\n%s",
                block.getZeros(),
                block.getMiner(),
                block.getId(),
                block.getTimeStamp(),
                block.getMagicNumber(),
                block.getPreviousHash(),
                block.getHash(),
                block.getMessage(),
                block.getGenerationTime(),
                toString(block.getPreviousBlock())
                );
    }

    public Block parseToBlock(String string) {
        String[] fields = string.split(":",10);
        Block block = new Block();
        block.setZeros(Integer.parseInt(fields[0]));
        block.setMiner(fields[1]);
        block.setId(Long.parseLong(fields[2]));
        block.setTimeStamp(Long.parseLong(fields[3]));
        block.setMagicNumber(Long.parseLong(fields[4]));
        block.setPreviousHash(fields[5]);
        block.setHash(fields[6]);
        block.setMessage(fields[7]);
        block.setGenerationTime(Long.parseLong(fields[8]));
        if (!fields[8].isEmpty())
            block.setPreviousBlock(parseToBlock(fields[9]));
        return block;
    }
}
