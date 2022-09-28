package blockchain;

public class Transaction {
    private byte[] data;
    private byte[] signature;

    public Transaction(byte[] data, byte[] signature) {
        this.data = data;
        this.signature = signature;
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getSignature() {
        return signature;
    }
}
