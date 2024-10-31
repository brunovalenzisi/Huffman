package huffman.def;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
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

	}


	
    
}
