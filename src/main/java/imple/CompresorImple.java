package imple;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import huffman.def.Compresor;
import huffman.def.HuffmanInfo;
import huffman.def.HuffmanTable;

public class CompresorImple implements Compresor {
    
public HuffmanTable[] contarOcurrencias(String filename) {
    HuffmanTable[] arr = new HuffmanTable[256];
    File file = new File(filename);

    try (FileInputStream fis = new FileInputStream(file)) {
        int newByte;
        
        while ((newByte = fis.read()) != -1) {
            if (newByte >= 0 && newByte < 256) {
                if (arr[newByte] != null) {
                    arr[newByte].increment();
                } else {
                    String binaryString = String.format("%8s", Integer.toBinaryString(newByte)).replace(' ', '0');
                    HuffmanTable hT = new HuffmanTable();
                    hT.setCod(binaryString); 
                    hT.setN(1); 
                    arr[newByte] = hT;
                }
            }
        }

    } catch (IOException e) {
        e.printStackTrace();
    }

    return arr;
}
	// Retorna una lista ordenada donde cada nodo representa a cada byte del archivo
	public List<HuffmanInfo> crearListaEnlazada(HuffmanTable arr[]){
        List<HuffmanInfo> lst= new ArrayList<HuffmanInfo>();
        return lst;


    };
	
	// Convierte la lista en el árbol Huffman
	public HuffmanInfo convertirListaEnArbol(List<HuffmanInfo> lista){
        HuffmanInfo hF=new HuffmanInfo();
        return hF;
    };
	
	// Recorre el árbol Huffman y completa los códigos en el array
	public void generarCodigosHuffman(HuffmanInfo root,HuffmanTable arr[]){

    };
	
	// Escribe el encabezado en el archivo filename+".huf", y retorna cuántos bytes ocupa el encabezado
	public long escribirEncabezado(String filename,HuffmanTable arr[]){
        long l=0;
        return l;

    };

	// Recorre el archivo filename por cada byte escribe su código en filename+".huf"
	public void escribirContenido(String filename,HuffmanTable arr[]){


        
    };	


    
}
