package huffman.def;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import imple.Factory;

public class CompresorTest {
    private static final String FILENAME = "test.x";
	
	@AfterEach
	public void afterEach()
	{
		File f = new File(FILENAME);
		f.delete();
		File fhuf = new File(FILENAME+".huf");
		fhuf.delete();

	}

    @Test
	public void contarOcurrenciasTest() throws Exception
	{
		FileOutputStream fos = new FileOutputStream(FILENAME);
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('B');
		fos.write('B');
		fos.write('B');
		fos.write('C');
		fos.write('C');
		fos.write('D');
		fos.close();

		Compresor comp = Factory.getCompresor();
        HuffmanTable[] hT=comp.contarOcurrencias(FILENAME);

		
		assertEquals(4,hT[65].getN());//ocurrencias de A
		assertEquals(3,hT[66].getN());//ocurrencias de B
		assertEquals(2,hT[67].getN());//ocurrencias de C
		assertEquals(1,hT[68].getN());//ocurrencias de D
		
	}
	@Test
	public void crearListaEnlazadaTest() throws Exception
	{
		FileOutputStream fos = new FileOutputStream(FILENAME);
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('B');
		fos.write('B');
		fos.write('B');
		fos.write('C');
		fos.write('C');
		fos.write('D');
		fos.write('E');

		fos.close();
		
		Compresor comp = Factory.getCompresor();
        HuffmanTable[] hT=comp.contarOcurrencias(FILENAME);
		
		List<HuffmanInfo> lst= comp.crearListaEnlazada(hT);
		assertEquals(5,lst.size());			//tamanio de la lista
		assertEquals(68,lst.get(0).getC());	//codigo de D
		assertEquals(1,lst.get(0).getN());	//ocurrencias de D
		assertEquals(69,lst.get(1).getC());	//codigo de E
		assertEquals(1,lst.get(1).getN());	//ocurrencias de E
		assertEquals(67,lst.get(2).getC()); //codigo de C
		assertEquals(2,lst.get(2).getN()); //ocurrencias  de C
		assertEquals(66,lst.get(3).getC()); //codigo de B
		assertEquals(3,lst.get(3).getN());//ocurrencias de B
		assertEquals(65,lst.get(4).getC());//codigo de A
		assertEquals(4,lst.get(4).getN());//ocurrencias de A
		fos.close();

	}


	@Test
	public void convertirListaEnArbolTest() throws Exception
	{
		FileOutputStream fos = new FileOutputStream(FILENAME);
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('B');
		fos.write('B');
		fos.write('B');
		fos.write('C');
		fos.write('C');
		fos.write('D');
		fos.write('E');

		fos.close();
		
		Compresor comp = Factory.getCompresor();
        HuffmanTable[] hT=comp.contarOcurrencias(FILENAME);
		
		List<HuffmanInfo> lst= comp.crearListaEnlazada(hT);
		HuffmanInfo root=comp.convertirListaEnArbol(lst);

		HuffmanInfo current=root;
		assertEquals(11,current.getN());//cantidad de hojas
		assertEquals(255+4,current.getC()); //code
		
		current=current.getLeft();
		assertEquals(7,current.getN());//cantidad de hojas
		assertEquals(255+3,current.getC());//code
		
		current=current.getLeft();
		assertEquals(4,current.getN());//cantidad de hojas
		assertEquals(65,current.getC());//code

		assertEquals(null,current.getLeft());//Null(hoja)
		assertEquals(null,current.getRight());//Null(hoja)
		
		current=root.getLeft();
		current=current.getRight();
		assertEquals(3,current.getN());//cantidad de hojas
		assertEquals(66,current.getC());//code

		assertEquals(null,current.getLeft());//Null(hoja)
		assertEquals(null,current.getRight());//Null(hoja)
		
		
		current=root.getRight();
		assertEquals(4,current.getN());//cantidad de hojas
		assertEquals(255+2,current.getC());//code
		
		current=current.getLeft();
		assertEquals(2,current.getN());//cantidad de hojas
		assertEquals(255+1,current.getC());//code
		
		current=current.getLeft();
		assertEquals(1,current.getN());//cantidad de hojas
		assertEquals(69,current.getC());//code

		assertEquals(null,current.getLeft());//Null(hoja)
		assertEquals(null,current.getRight());//Null(hoja)

		current=root.getRight();
		current=current.getLeft();
		current=current.getRight();
		assertEquals(1,current.getN());//cantidad de hojas
		assertEquals(68,current.getC());//code
		
		assertEquals(null,current.getLeft());//Null(hoja)
		assertEquals(null,current.getRight());//Null(hoja)
		
		current=root.getRight();
		current=current.getRight();
		assertEquals(2,current.getN());//cantidad de hojas
		assertEquals(67,current.getC());//code
		
		assertEquals(null,current.getLeft());//Null(hoja)
		assertEquals(null,current.getRight());//Null(hoja)

		comp.generarCodigosHuffman(root, hT);

		assertEquals("00",hT[65].getCod());//A
		assertEquals("01",hT[66].getCod());//B
		assertEquals("11",hT[67].getCod());//C
		assertEquals("101",hT[68].getCod());//D
		assertEquals("100",hT[69].getCod());//E

	}


	
	
	@Test
	public void escribirEncabezadoTest() throws Exception
	{	
		FileOutputStream fos = new FileOutputStream(FILENAME);
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('A');
		fos.write('B');
		fos.write('B');
		fos.write('B');
		fos.write('C');
		fos.write('C');
		fos.write('D');
		fos.write('E');
		
		fos.close();
		
		Compresor comp = Factory.getCompresor();
        HuffmanTable[] hT=comp.contarOcurrencias(FILENAME);
		
		List<HuffmanInfo> lst= comp.crearListaEnlazada(hT);
		HuffmanInfo root=comp.convertirListaEnArbol(lst);
		comp.generarCodigosHuffman(root, hT);
		long longEncab=comp.escribirEncabezado(FILENAME, hT);
		comp.escribirContenido(FILENAME, hT);
		
		
		
		FileInputStream fis=new FileInputStream(FILENAME+".huf");
		BitReader bitR = Factory.getBitReader();
		bitR.using(fis);
		
		assertEquals(5, fis.read()); 
		
		assertEquals(65, fis.read());
		assertEquals(2, fis.read());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
	   
		assertEquals(66, fis.read());
		assertEquals(2, fis.read());
		assertEquals(0, bitR.readBit());
		assertEquals(1, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());

		assertEquals(67, fis.read());
		assertEquals(2, fis.read());
		assertEquals(1, bitR.readBit());
		assertEquals(1, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());

		assertEquals(68, fis.read());
		assertEquals(3, fis.read());
		assertEquals(1, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(1, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());

		assertEquals(69, fis.read());
		assertEquals(3, fis.read());
		assertEquals(1, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());
		assertEquals(0, bitR.readBit());

		assertEquals(20, longEncab);

		byte[] bytes = new byte[4];
		fis.read(bytes);
		int numero = ByteBuffer.wrap(bytes).getInt();

		assertEquals(11, numero);

		assertEquals(0, fis.read());
		assertEquals(87, fis.read());
		assertEquals(236, fis.read());
		assertEquals(-1, fis.read());

		fis.close();
		
	}
	
	

}
