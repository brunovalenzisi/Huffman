package imple;

import java.io.IOException;
import java.io.InputStream;
import huffman.def.BitReader;

public class BitReaderImple implements BitReader {
    private InputStream is;
    private int currentByte; 
    private int bitCount; 

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

   
    int bit = (currentByte >> (bitCount - 1)) & 1; 
    bitCount--; 

 
    if (bitCount == 0) {
        currentByte = is.read(); 
        bitCount = (currentByte == -1) ? 0 : 8; 
    }

    return bit; 
}

    @Override
    public void flush() throws IOException {
        currentByte = is.read(); 
        bitCount = (currentByte == -1) ? 0 : 8; 
    }
}