import java.util.List;

import huffman.def.Compresor;
import huffman.def.Descompresor;
import huffman.def.HuffmanInfo;
import huffman.def.HuffmanTable;
import huffman.util.Console;
import imple.Factory;

public class Main {

    public static String mostrarMenu(Console c) {
        String rta = "";
        while (true) { // Repetir hasta obtener una entrada válida
            c.println("Desea comprimir o descomprimir un archivo? (Y/N)");
            rta = c.readString().trim();

            if (rta.equalsIgnoreCase("Y")) {
                return c.fileExplorer();
            } else if (rta.equalsIgnoreCase("N")) {
                c.println("Gracias por utilizar el compresor Huffman.");
                c.closeAndExit();
            } else {
                c.println("Opción no válida. Por favor, ingrese Y o N.");
            }
        }
    }

    public static void main(String[] args) {
        Console c = Console.get();
        c.println("Bienvenido al compresor de archivos Huffman");
        String fileName = mostrarMenu(c);

        while (!fileName.isEmpty()) {
            try {
                // Descompresión si el archivo tiene extensión .huf
                if (fileName.endsWith(".huf")) {
                    c.println("Descomprimiendo...");
                    String baseName = fileName.substring(0, fileName.length() - 4);
                    Descompresor descompresor = Factory.getDescompresor();
                    HuffmanInfo root = new HuffmanInfo();
                    long n = descompresor.recomponerArbol(baseName, root);
                    descompresor.descomprimirArchivo(root, n, baseName);
                    c.println("Archivo descomprimido exitosamente como " + baseName);
                } else {
                    // Compresión si el archivo no tiene la extensión .huf
                    c.println("Comprimiendo...");
                    Compresor compresor = Factory.getCompresor();
                    HuffmanTable[] hT = compresor.contarOcurrencias(fileName);
                    List<HuffmanInfo> lst = compresor.crearListaEnlazada(hT);
                    HuffmanInfo hI = compresor.convertirListaEnArbol(lst);
                    compresor.generarCodigosHuffman(hI, hT);
                    compresor.escribirEncabezado(fileName, hT);
                    compresor.escribirContenido(fileName, hT);
                    c.println("Archivo comprimido exitosamente como " + fileName + ".huf");
                }
            } catch (Exception e) {
                c.println("Se ha producido un error: " + e.getMessage());
            }
            fileName = mostrarMenu(c);
        }

        c.closeAndExit();
        
    }
}


       


