package imple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import huffman.def.BitReader;
import huffman.def.Descompresor;
import huffman.def.HuffmanInfo;

public class DescompresorImple implements Descompresor {
    public int longOriginal;

    @Override
    public long recomponerArbol(String filename, HuffmanInfo arbol) {
        HuffmanInfo current = new HuffmanInfo();
        BitReader bitR = Factory.getBitReader();
        long longitud = 0;

        try {
            FileInputStream fIS = new FileInputStream(filename + ".huf");
            bitR.using(fIS);

            int cantHojas = fIS.read();
            if (cantHojas == 0) {
                cantHojas = 256;
            }
            longitud++;
            for (int i = 0; i < cantHojas; i++) {
                int code = fIS.read();
                int longCod = fIS.read();
                longitud += 2;
                longitud += longCod / 8;
                if (longCod % 8 != 0) {
                    longitud++;
                }
                bitR.flush();
                current = arbol;
                for (int bitPos = 0; bitPos < longCod; bitPos++) {
                    int bit = bitR.readBit();
                    if (bit == 1) {
                        if (current.getRight() == null) {
                            current.setRight(new HuffmanInfo());
                            current = current.getRight();
                        } else {
                            current = current.getRight();
                        }
                    } else {
                        if (current.getLeft() == null) {
                            current.setLeft(new HuffmanInfo());
                            current = current.getLeft();
                        } else {
                            current = current.getLeft();
                        }
                    }
                }
                current.setC(code);
            }

            byte[] bytes = new byte[4];
            fIS.read(bytes);
            longOriginal = ByteBuffer.wrap(bytes).getInt();
            fIS.close();
            longitud += 4;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return longitud;
    }

    @Override
    public void descomprimirArchivo(HuffmanInfo root, long n, String filename) {
        File fOut = new File(filename);
        try {
            FileOutputStream fOS = new FileOutputStream(fOut);
            FileInputStream fIS = new FileInputStream(filename + ".huf");
            HuffmanInfo current = root;
            BitReader bitR = Factory.getBitReader();
            fIS.skip(n);
            bitR.using(fIS);
            int contador = 0;
            int currentBit;
            while (contador < longOriginal) {
                currentBit = bitR.readBit();

                if (currentBit == 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }

                if (current.getLeft() == null && current.getRight() == null) {
                    fOS.write(current.getC());
                    contador++;
                    current = root;
                }

            }
            fOS.close();
            fIS.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
