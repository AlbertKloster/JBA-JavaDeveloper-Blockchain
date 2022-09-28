package blockchain;

import java.io.*;

public class FileHandler {

    BlockSerializer serializer = new BlockSerializer();
    public Block load(String fileName) {
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            reader.close();
            return serializer.parseToBlock(builder.toString());

        } catch (Exception e) {
            return null;
        }
    }

    public void save(String fileName, Block lastBlock) {
        try {
            FileWriter writer = new FileWriter(fileName, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(serializer.toString(lastBlock));
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
