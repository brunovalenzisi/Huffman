package huffman.def;

import java.util.List;


public interface Compresor
{
	// ** en todos los casos filename es el nombre del archivo original **
	
	// Recorre filename y retorna un HuffmanTable[256] contando cuántas veces aparece cada byte
	public HuffmanTable[] contarOcurrencias(String filename);
	
	// Retorna una lista ordenada donde cada nodo representa a cada byte del archivo
	public List<HuffmanInfo> crearListaEnlazada(HuffmanTable arr[]);
	
	// Convierte la lista en el árbol Huffman
	public HuffmanInfo convertirListaEnArbol(List<HuffmanInfo> lista);
	
	// Recorre el árbol Huffman y completa los códigos en el array
	public void generarCodigosHuffman(HuffmanInfo root,HuffmanTable arr[]);
	
	// Escribe el encabezado en el archivo filename+".huf", y retorna cuántos bytes ocupa el encabezado
	public long escribirEncabezado(String filename,HuffmanTable arr[]);

	// Recorre el archivo filename por cada byte escribe su código en filename+".huf"
	public void escribirContenido(String filename,HuffmanTable arr[]);	
}
