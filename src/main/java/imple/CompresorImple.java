package imple;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import huffman.def.*;
import huffman.util.HuffmanTree;

public class CompresorImple implements Compresor {
    FileOutputStream fOS;
    @Override   
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
                    HuffmanTable hT = new HuffmanTable();
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
    @Override
	public List<HuffmanInfo> crearListaEnlazada(HuffmanTable arr[]){
        List<HuffmanInfo> lst= new ArrayList<HuffmanInfo>();
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=null){
                HuffmanInfo hI=new HuffmanInfo(i,arr[i].getN());
                lst.add(hI);
            }
        }
        lst.sort((h1, h2) -> {
            int compareN = Integer.compare(h1.getN(), h2.getN());
            if (compareN != 0) {
                return compareN; 
            }
            return Integer.compare(h1.getC(), h2.getC());
        });
        return lst;
    };
	
	
    // Convierte la lista en el árbol Huffman
    @Override
    public HuffmanInfo convertirListaEnArbol(List<HuffmanInfo> lista) {
        int count = 1;
        while (lista.size() > 1) {
            HuffmanInfo hIAux = new HuffmanInfo();
            hIAux.setC(255 + count);
    
            HuffmanInfo hID = lista.remove(0); 
            HuffmanInfo hII = lista.remove(0); 
            
           
            hIAux.setRight(hID);
            hIAux.setLeft(hII);
            hIAux.setN(hID.getN() + hII.getN());
            lista.add(hIAux);
            lista.sort((h1, h2) -> {
                int compareN = Integer.compare(h1.getN(), h2.getN());
                if (compareN != 0) {
                    return compareN; 
                }
                return Integer.compare(h1.getC(), h2.getC());
            });
            
            count++;
        }
        return lista.get(0);
    }
	
	// Recorre el árbol Huffman y completa los códigos en el array
    @Override
	public void generarCodigosHuffman(HuffmanInfo root,HuffmanTable arr[]){
        HuffmanTree hT= new HuffmanTree(root);
        StringBuffer sB=new StringBuffer();
        HuffmanInfo hoja= hT.next(sB);
        while(hoja!=null){
            int c=hoja.getC();
            arr[c].setCod(sB.toString());
            hoja=hT.next(sB);
        }
    };
	
	// Escribe el encabezado en el archivo filename+".huf", y retorna cuántos bytes ocupa el encabezado
    @Override
	public long escribirEncabezado(String filename,HuffmanTable arr[]){
        File f=new File(filename+".huf");
        try  {
            if(fOS==null){
                fOS= new FileOutputStream(f);
            }
            
            int arrLength = 0;
            for(int i=0;i<arr.length;i++){if(arr[i]!=null){
                arrLength++;}}
            BitWriter ppbWriter = Factory.getBitWriter();
            ppbWriter.using(fOS);
             fOS.write(arrLength);
             for(int i=0;i<arr.length;i++){
                if(arr[i]!=null){
                    int longC=arr[i].getCod().length();
                    fOS.write(i);
                    fOS.write(longC);
                    
                    for(int j=0;j<longC;j++){
                        int newBit=arr[i].getCod().charAt(j)=='0'?0:1;
                        ppbWriter.writeBit(newBit);   
                    }
                    ppbWriter.flush();                
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return f.length();
        
    };
    
	// Recorre el archivo filename por cada byte escribe su código en filename+".huf"
    @Override
	public void escribirContenido(String filename,HuffmanTable arr[]){
        File f =new File(filename+".huf");
        try{
            FileInputStream fIS=new FileInputStream(filename);
            if(fOS==null){
                fOS= new FileOutputStream(f);
            }
            BitWriter ppbWriter = Factory.getBitWriter();
            ppbWriter.using(fOS);
            int newByte=fIS.read();
            while(newByte!=-1){
                HuffmanTable hT = arr[newByte];
                    String newCode = hT.getCod();
                    for (int i = 0; i < newCode.length(); i++) {
                        int bit = newCode.charAt(i) == '0' ? 0 : 1;
                        ppbWriter.writeBit(bit);
                    }
                    newByte=fIS.read();
        }
            ppbWriter.flush();
            fIS.close();
            fOS.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    };	

    


    
}
