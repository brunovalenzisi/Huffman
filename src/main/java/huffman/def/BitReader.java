package huffman.def;
import java.io.IOException;
import java.io.InputStream;

public interface BitReader
{
	// establece el inputStream desde donde leerá los bits
	public void using(InputStream is) ;

	// retorna 1 o 0, o -1 si es EOF
	public int readBit() throws IOException;
	
	// vacia el buffer
	public void flush() throws IOException;
}
