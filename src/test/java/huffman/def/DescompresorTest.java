package huffman.def;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import imple.Factory;

public class DescompresorTest {
    private static final String FILENAME = "test.x";

    @AfterEach
    public void afterEach() {
        File f = new File(FILENAME);
        f.delete();
        File fComp = new File(FILENAME + ".huf");
        fComp.delete();
    }

    @Test
    public void test1() throws Exception {
        // 1. Crear archivo de prueba
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
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
            fos.close();

            // 2. Obtener instancia de Compresor y contar ocurrencias
            Compresor comp = Factory.getCompresor();
            HuffmanTable[] hT = comp.contarOcurrencias(FILENAME);

            // 3. Crear lista enlazada de Huffman a partir de las ocurrencias
            List<HuffmanInfo> lista = comp.crearListaEnlazada(hT);

            // 4. Construir árbol de Huffman desde la lista enlazada
            HuffmanInfo root = comp.convertirListaEnArbol(lista);

            // 5. Generar códigos de Huffman en la tabla
            comp.generarCodigosHuffman(root, hT);

            comp.escribirEncabezado(FILENAME, hT);
            comp.escribirContenido(FILENAME, hT);

            Descompresor descompresor = Factory.getDescompresor();
            root = new HuffmanInfo();
            HuffmanInfo current = new HuffmanInfo();

            //////////////////////////////// comprobar longitud de
            //////////////////////////////// encabezado//////////////////////////////////////
            long longitudEncabezado = descompresor.recomponerArbol(FILENAME, root);

            assertEquals(20, longitudEncabezado);

            //////////////////////////////// comprobar
            //////////////////////////////// arbol//////////////////////////////////////

            current = root;
            current = current.getRight();
            current = current.getRight();
            assertEquals(67, current.getC());

            current = root;
            current = current.getRight();
            current = current.getLeft();
            current = current.getRight();
            assertEquals(68, current.getC());

            current = root;
            current = current.getRight();
            current = current.getLeft();
            current = current.getLeft();
            assertEquals(69, current.getC());

            current = root;
            current = current.getLeft();
            current = current.getRight();
            assertEquals(66, current.getC());

            current = root;
            current = current.getLeft();
            current = current.getLeft();
            assertEquals(65, current.getC());

            //////////////////////////////// comprobar archivo
            //////////////////////////////// comprimido//////////////////////////////////////

            FileInputStream fisComp = new FileInputStream(FILENAME + ".huf");
            fisComp.skip(longitudEncabezado);
            assertEquals(0, fisComp.read());
            assertEquals(87, fisComp.read());
            assertEquals(236, fisComp.read());
            assertEquals(-1, fisComp.read());

            fisComp.close();

            descompresor.descomprimirArchivo(root, longitudEncabezado, FILENAME);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        // 1. Crear archivo de prueba
        try {
            FileOutputStream fos = new FileOutputStream(FILENAME);
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
            fos.write('E'); // 1 'E'
            fos.close();

            // 2. Obtener instancia de Compresor y contar ocurrencias
            Compresor comp = Factory.getCompresor();
            HuffmanTable[] hT = comp.contarOcurrencias(FILENAME);
            assertEquals(4, hT[65].getN());
            assertEquals(3, hT[66].getN());
            assertEquals(2, hT[67].getN());
            assertEquals(1, hT[68].getN());
            assertEquals(2, hT[69].getN());

            // 3. Crear lista enlazada de Huffman a partir de las ocurrencias
            List<HuffmanInfo> lista = comp.crearListaEnlazada(hT);

            // 4. Construir árbol de Huffman desde la lista enlazada
            HuffmanInfo root = comp.convertirListaEnArbol(lista);

            // 5. Generar códigos de Huffman en la tabla
            comp.generarCodigosHuffman(root, hT);

            comp.escribirEncabezado(FILENAME, hT);
            comp.escribirContenido(FILENAME, hT);

            Descompresor descompresor = Factory.getDescompresor();

            long longitudEncabezado = descompresor.recomponerArbol(FILENAME, root);

            assertEquals(20, longitudEncabezado);

            FileInputStream fisComp = new FileInputStream(FILENAME + ".huf");
            fisComp.skip(longitudEncabezado);
            assertEquals(0, fisComp.read());
            assertEquals(169, fisComp.read());
            assertEquals(39, fisComp.read());
            assertEquals(224, fisComp.read());
            assertEquals(-1, fisComp.read());
            fisComp.close();

            descompresor.descomprimirArchivo(root, longitudEncabezado, FILENAME);
            FileInputStream fisDesc = new FileInputStream(FILENAME);
            assertEquals('A', fisDesc.read());
            assertEquals('A', fisDesc.read());
            assertEquals('A', fisDesc.read());
            assertEquals('A', fisDesc.read());
            assertEquals('B', fisDesc.read());
            assertEquals('B', fisDesc.read());
            assertEquals('B', fisDesc.read());
            assertEquals('C', fisDesc.read());
            assertEquals('C', fisDesc.read());
            assertEquals('D', fisDesc.read());
            assertEquals('E', fisDesc.read());
            assertEquals('E', fisDesc.read());
            assertEquals(-1, fisDesc.read());
            fisDesc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
