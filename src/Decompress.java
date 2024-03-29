/*
 * Name: Andrew Mokhtarzadeh
 * PID: A15557088
 */

import java.io.*;

/**
 * Decompress the first given file to the second given file using Huffman coding
 *
 * @author Andrew Mokhtarzadeh
 * @since 06/02/20
 */
public class Decompress {
    private static final int EXP_ARG = 2; // number of expected arguments

    public static void main(String[] args) throws IOException {

        // Check if the number of arguments is correct
        if (args.length != EXP_ARG) {
            System.out.println("Invalid number of arguments.\n"
                + "Usage: ./decompress <infile outfile>.\n");
            return;
        }

        FileInputStream inFile = new FileInputStream(args[0]);
        DataInputStream in = new DataInputStream(inFile);
        BitInputStream bitIn = new BitInputStream(in);

        FileOutputStream outFile = new FileOutputStream(args[1]);
        DataOutputStream out = new DataOutputStream(outFile);

        // read the number of byte from the file
        try {
            int byteCount = in.readInt();

            // decode and build the tree from the "header"
            HCTree huffman = new HCTree();
            huffman.decodeHCTree(bitIn);

            // decode the file and write the results
            for (int i = 0; i < byteCount; i++)
                out.writeByte(huffman.decode(bitIn));

            inFile.close();
            in.close();
            outFile.close();
            out.close();
            return;
        } catch (IOException e) {
            return;
        }

    }
}
