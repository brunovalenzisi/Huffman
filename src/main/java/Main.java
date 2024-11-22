import huffman.def.Compresor;
import huffman.def.Descompresor;
import huffman.def.HuffmanInfo;
import huffman.def.HuffmanTable;
import imple.Factory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Verificación de argumentos de entrada
        if (args.length != 1) {
            System.out.println("Uso: java Main <nombre_del_archivo_sin_extension>");
            return;
        }

        String filename = args[0];

        try {
            // Crear instancia de DescompresorImple si el archivo tiene extensión .huf
            if (filename.endsWith(".huf")) {
                System.out.println("Descomprimiendo");
                String baseName = filename.substring(0, filename.length() - 4);
                Descompresor descompresor = Factory.getDescompresor();
                HuffmanInfo root=new HuffmanInfo();
                long n = descompresor.recomponerArbol(baseName,root);
                descompresor.descomprimirArchivo(root,n, baseName);
                System.out.println("Archivo descomprimido exitosamente como " + baseName);
            } else {

                // Compresión si el archivo no tiene la extensión .huf
                System.out.println("Comprimiendo");
                Compresor compresor = Factory.getCompresor();
                HuffmanTable[] hT = compresor.contarOcurrencias(filename);
                List<HuffmanInfo> lst = compresor.crearListaEnlazada(hT);
                HuffmanInfo hI = compresor.convertirListaEnArbol(lst);
                compresor.generarCodigosHuffman(hI, hT);
                compresor.escribirEncabezado(filename, hT);
                compresor.escribirContenido(filename, hT);
                System.out.println("Archivo comprimido exitosamente como " + filename + ".huf");
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error durante la compresión o descompresión: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

