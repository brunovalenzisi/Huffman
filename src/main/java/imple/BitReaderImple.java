package imple;

import java.io.IOException;
import java.io.InputStream;
import huffman.def.BitReader;

public class BitReaderImple implements BitReader {
    private InputStream is;
    private int currentByte = 0; 
    private int bitCount = 0; 
    @Override
    public void using(InputStream is) {
        this.is = is;
        this.currentByte = 0;
        this.bitCount = 0; 
    }

    @Override
    public int readBit() throws IOException {
       
        if (bitCount == 0) {
            flush();
            if (currentByte == -1) {
                return -1;
            }
        }

        
        int bit = (currentByte & 1); 
        currentByte >>= 1; 
        bitCount--;

        return bit; 
    }

    @Override
    public void flush() throws IOException {
        currentByte = is.read(); // retorna el proximo byte o -1 si no hay mas
        bitCount = 8; 
    }
}
