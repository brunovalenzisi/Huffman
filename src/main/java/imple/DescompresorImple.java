package imple;

import huffman.def.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

public class DescompresorImple implements Descompresor {
    
    HuffmanInfo root=new HuffmanInfo();

    @Override
    public long recomponerArbol(String filename, HuffmanInfo arbol) {
        BitReader bitR= Factory.getBitReader();
        HuffmanInfo current;
        long longitud=0;

        try  {
            FileInputStream fIS = new FileInputStream(filename+".huf");
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
            fIS.close();
            
        } catch (IOException e) {
            e.printStackTrace(); 
        }
        return longitud;
    }

    @Override
    public void descomprimirArchivo(HuffmanInfo root, long n, String filename) {
        File fOut=new File(filename);
        try {
            HuffmanInfo current=root;
            FileOutputStream fOS=new FileOutputStream(fOut);
            FileInputStream fIS = new FileInputStream(filename+".huf");
            BitReader bitR=Factory.getBitReader();
            bitR.using(fIS);
            fIS.skip(n);
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

    }


}
