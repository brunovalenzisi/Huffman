package imple;

import huffman.def.Descompresor;
import huffman.def.HuffmanInfo;

import java.io.FileInputStream;
import java.io.IOException;

public class DescompresorImple implements Descompresor {
    


	public HuffmanInfo recomponerArbol(String filename){
        HuffmanTable arrAux []; /*Esto debe ser de otro tipo de dato, al no poder agregarse elementos como tal a un array*/

        HuffmanInfo p=new HuffmanInfo(); // acepto que es el primer puntero al arbol

        try (FileInputStream fis = new FileInputStream(filename)) {
            int data;
            while ((data = fis.read()) != -1) {


            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra el error en la consola
        }




        return info;
    };
	
	public void descomprimirArchivo(HuffmanInfo root,String filename){



    };


}
