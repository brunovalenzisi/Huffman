package imple;

import huffman.def.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class DescompresorImple implements Descompresor {
    FileInputStream fIS;
    HuffmanInfo root=new HuffmanInfo();

    @Override
    public long recomponerArbol(String filename, HuffmanInfo arbol) {
        BitReader bitR= Factory.getBitReader();
        HuffmanInfo current;
        long longitud=0;

        try  {
            fIS = new FileInputStream(filename+".huf");
            bitR.using(fIS);
            int cantHojas=fIS.read();
            longitud++;
            for (int i=0;i<cantHojas;i++) {
                int code=fIS.read();
                int longCod=fIS.read();
                longitud+=2;
                longitud+=longCod/3;
                if(longCod%3!=0){
                    longitud++;
                }
                bitR.flush();
                current=arbol;
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
        return longitud;
    }

    @Override
    public void descomprimirArchivo(HuffmanInfo root, long n, String filename) {

    }
    

   /* @Override
	public HuffmanInfo recomponerArbol(String filename){
       // HuffmanTable arrAux []; /*Esto debe ser de otro tipo de dato, al no poder agregarse elementos como tal a un array

        BitReader bitR= Factory.getBitReader();
        
        HuffmanInfo root=new HuffmanInfo(); // acepto que es el primer puntero al arbol

        try  {
            fIS = new FileInputStream(filename+".huf");
            bitR.using(fIS);
            int cantHojas=fIS.read();

            for (int i=0;i<cantHojas;i++) {
                int code=fIS.read();
                int longCod=fIS.read();
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
    }; */


	/*@Override
	public void descomprimirArchivo(HuffmanInfo root,String filename){
        File fOut=new File(filename);
        try {
            HuffmanInfo current=root;
            FileOutputStream fOS=new FileOutputStream(fOut);
            BitReader bitR=Factory.getBitReader();
            bitR.using(fIS);
            int currentBit=bitR.readBit();
            while(currentBit!=-1){
                if(currentBit==0){
                    current=current.getLeft();
                }else{
                    current=current.getRight();                    
                }
                if(current.getLeft()==null&&current.getRight()==null){
                    fOS.write(current.getC());
                    current=root;
                }
                currentBit=bitR.readBit();
                
            }
            fOS.close();
            fIS.close();
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }

    };
   */




}
