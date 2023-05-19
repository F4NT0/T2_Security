import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {

    
    private static final String fileName = "src/FuncoesResumo - SHA1.mp4";

    public static void main(String args[]) throws NoSuchAlgorithmException, IOException {
        
        String file = "";
        if (args.length > 0)
            file = args[0];
        else
            file = fileName;

        byte[] rawFile = Files.readAllBytes(Paths.get(file));

        LinkedList<byte[]> blocks = Hash.breakBlock(rawFile);

        byte[] bn1, h0;
        bn1 = h0 = Hash.digestMessage(blocks.pollLast());

        do {
            h0 = Hash.concatenateBlocks(blocks.pollLast(), h0);
            h0 = Hash.digestMessage(h0);
        } while (blocks.size() > 0);

        System.out.println("Ultimo bloco Hash: " + Hash.hexadecimalToString(bn1));
        System.out.println("Primeiro bloco Hash: " + Hash.hexadecimalToString(h0));
    }

}