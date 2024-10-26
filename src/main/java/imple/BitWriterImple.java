package imple;

import java.io.OutputStream;
import java.io.IOException; // Importa IOException
import huffman.def.BitWriter;

public class BitWriterImple implements BitWriter {
    private OutputStream os;
    private int currentByte = 0;
    private int bitCount = 0;

    @Override
    public void using(OutputStream os) {
        this.os = os; 

    @Override
    public void writeBit(int bit) throws IOException { 
        currentByte = (currentByte << 1) | (bit & 1);
        bitCount++;
        if (bitCount == 8) {
            flush(); 
        }
    }

    @Override
    public void flush() throws IOException { 
        if (bitCount > 0) {
            currentByte <<= (8 - bitCount); 
            os.write((byte) currentByte);   
            bitCount = 0;                    
            currentByte = 0;                 
        }
        os.flush(); 
    }
}