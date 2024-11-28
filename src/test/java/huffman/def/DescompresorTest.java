package huffman.def;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.junit.jupiter.api.Test;
import java.util.List;
import imple.Factory;

public class DescompresorTest {
    private static final String FILENAME = "test";
    

    @Test
    public void testRecomponerArbol() throws Exception {
        // 1. Crear archivo de prueba
        try (FileOutputStream fos = new FileOutputStream(FILENAME)) {
            fos.write('A'); // 4 'A's
            fos.write('A');
            fos.write('A');
            fos.write('A');
            fos.write('B'); // 3 'B's
            fos.write('B');
            fos.write('B');
            fos.write('C'); // 2 'C's
            fos.write('C');
            fos.write('D'); // 1 'D'
            fos.write('E'); // 1 'E'
        }

        // 2. Obtener instancia de Compresor y contar ocurrencias
        Compresor comp = Factory.getCompresor();
        HuffmanTable[] hT = comp.contarOcurrencias(FILENAME);

        // 3. Crear lista enlazada de Huffman a partir de las ocurrencias
        List<HuffmanInfo> lista = comp.crearListaEnlazada(hT);

        // 4. Construir árbol de Huffman desde la lista enlazada
        HuffmanInfo root = comp.convertirListaEnArbol(lista);

        // 5. Generar códigos de Huffman en la tabla
        comp.generarCodigosHuffman(root, hT);
       assertEquals("00",hT[65].getCod());//A
		assertEquals("01",hT[66].getCod());//B
		assertEquals("11",hT[67].getCod());//C
		assertEquals("101",hT[68].getCod());//D
		assertEquals("100",hT[69].getCod());//E

        // 6. Escribir encabezado y contenido en el archivo comprimido
        comp.escribirEncabezado(FILENAME, hT);
        comp.escribirContenido(FILENAME, hT);

             
        try (FileInputStream fis = new FileInputStream(FILENAME)) {
            Descompresor descompresor = Factory.getDescompresor();
            root=new HuffmanInfo();
            long longitudEncabezado=descompresor.recomponerArbol(FILENAME, root);
            assertEquals(16,longitudEncabezado);
           
      


        }
            

    }
}