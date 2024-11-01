package imple;

import huffman.def.*;
import java.io.FileInputStream;
import java.io.IOException;

public class DescompresorImple implements Descompresor {
    


	public HuffmanInfo recomponerArbol(String filename){
       // HuffmanTable arrAux []; /*Esto debe ser de otro tipo de dato, al no poder agregarse elementos como tal a un array*/

        BitReader bitR= Factory.getBitReader();
        
        HuffmanInfo root=new HuffmanInfo(); // acepto que es el primer puntero al arbol

        try (FileInputStream fis = new FileInputStream(filename+".huf")) {
            bitR.using(fis);
            int cantHojas=fis.read();

            for (int i=0;i<cantHojas;i++) {
                int code=fis.read();
                int longCod=fis.read();
                bitR.flush();
                HuffmanInfo current=root;
                for(int bitPos=0;bitPos<longCod;bitPos++){
                    int bit=bitR.readBit();
                    if(bit==1){
                        if(current.getRight()==null){
                            current.setRight(new HuffmanInfo());
                            current=current.getRight();
                        }else{
                            current=current.getRight();
                        }
                    }
                    else{
                        if(current.getLeft()==null){
                            current.setLeft(new HuffmanInfo());
                            current=current.getLeft();
                        }
                        else{
                            current=current.getLeft();
                        }
                    }
                }
                current.setC(code);
            }
            
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return root;
    };
	
	public void descomprimirArchivo(HuffmanInfo root,String filename){



    };


}
