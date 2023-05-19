import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.LinkedList;

public class Hash {

    
    private static final int blockByteSize = 1024;

    
    public static LinkedList<byte[]> breakBlock(byte[] source) {
        LinkedList<byte[]> list = new LinkedList<byte[]>();

        for (int i = 0, chunck = 0; i < source.length; i += blockByteSize) {
            chunck = Math.min(blockByteSize, source.length - i);
            byte[] block = new byte[chunck];

            System.arraycopy(source, i, block, 0, chunck);
            list.add(block);
        }

        return list;
    }

    
    public static byte[] digestMessage(byte[] input)
            throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("SHA-256").digest(input);
    }

    
    public static byte[] concatenateBlocks(byte[] head, byte[] tail) {
        byte[] concatenation = new byte[head.length + tail.length];

        System.arraycopy(head, 0, concatenation, 0, head.length);
        System.arraycopy(tail, 0, concatenation, head.length, tail.length);
        return concatenation;
    }

    
    public static String hexadecimalToString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x ", b));
        }
        return sb.toString();
    }

}
