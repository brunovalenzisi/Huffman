package imple;

import huffman.def.Descompresor;
import huffman.def.HuffmanInfo;
import huffman.def.HuffmanTable;

import java.io.FileInputStream;
import java.io.IOException;

public class DescompresorImple implements Descompresor {
    


	public HuffmanInfo recomponerArbol(String filename){
        HuffmanTable arrAux []; /*Esto debe ser de otro tipo de dato, al no poder agregarse elementos como tal a un array*/

        HuffmanInfo p=new HuffmanInfo(); // acepto que es el primer puntero al arbol

        try (FileInputStream fis = new FileInputStream(filename+".huf")) {
            int data;
            while ((data = fis.read()) != -1) {


            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error en la consola
        }




        return p;
    };
	
	public void descomprimirArchivo(HuffmanInfo root,String filename){



    };


}
