package huffman.def;
import java.io.IOException;

import java.io.OutputStream;

public interface BitWriter {
    // Establece el OutputStream donde escribirá los bits
    void using(OutputStream os);

    // Escribe un bit
    void writeBit(int bit) throws IOException; // Declara IOException aquí

    // Completa el buffer con ceros y lo escribe
    void flush() throws IOException; // También aquí
}
