package huffman.def;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import imple.Factory;

public class BitReaderTest
{
	private static final String FILENAME = "test.x";
	
	@AfterEach
	public void afterEach()
	{
		File f = new File(FILENAME);
		f.delete();
	}
	
	@Test
	public void test1() throws Exception
	{
		FileOutputStream fos = new FileOutputStream(FILENAME);
		fos.write('A');
		fos.write('B');
		fos.write('C');
		fos.close();
		
		FileInputStream fis = new FileInputStream(FILENAME);

		BitReader br = Factory.getBitReader();
		br.using(fis);

		// A
		assertEquals(0,br.readBit());
		assertEquals(1,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(1,br.readBit());
		
		// B
		int c = fis.read();
		assertEquals('B',c);
		
		// C
		assertEquals(0,br.readBit());
		assertEquals(1,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(0,br.readBit());
		assertEquals(1,br.readBit());
		assertEquals(1,br.readBit());
		
		// EOF
		assertEquals(-1,br.readBit());
		
		fis.close();
	}
	
	@Test
	public void test2() throws Exception
	{
		FileOutputStream fos = new FileOutputStream(FILENAME);
		fos.write('A');
		fos.close();
		
		BitReader br = Factory.getBitReader();
		FileInputStream fis = new FileInputStream(FILENAME);
		br.using(fis);

		int i;
		for(i=0; br.readBit()>=0; i++);
		
		assertEquals(8,i);
		
		fis.close();
		
	}
	
	@Test
	public void test3() throws Exception
	{
		FileOutputStream fos = new FileOutputStream(FILENAME);
		fos.write('A');
		fos.write('B');
		fos.write('C');
		fos.close();
		
		BitReader br = Factory.getBitReader();
		FileInputStream fis = new FileInputStream(FILENAME);
		br.using(fis);

		int i;
		for(i=0; br.readBit()>=0; i++);
		
		assertEquals(24,i);
		
		fis.close();
		
	}

	@Test
	public void test4() throws Exception
	{
		FileInputStream fis = new FileInputStream("./src/test/java/huffman/def/prueba.txt");
		BitReader br = Factory.getBitReader();
		br.using(fis);
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());

		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());

		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());

		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());

		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(1, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(0, br.readBit());
		assertEquals(1, br.readBit());
		
		assertEquals(-1, br.readBit());
		

		
		
		
		fis.close();
		
	}

	@Test
	public void test5() throws Exception
	{

		Compresor comp=Factory.getCompresor();
		HuffmanTable[] arr=comp.contarOcurrencias("./src/test/java/huffman/def/prueba.txt");
		assertEquals(5, arr[104].getN());
		assertEquals(5, arr[111].getN());
		assertEquals(5, arr[108].getN());
		assertEquals(5, arr[97].getN());
		assertEquals(4, arr[32].getN());
		List<HuffmanInfo> lst= comp.crearListaEnlazada(arr);

		assertEquals(32,lst.get(0).getC());
		assertEquals(97,lst.get(1).getC());
		assertEquals(104,lst.get(2).getC());
		assertEquals(108,lst.get(3).getC());
		assertEquals(111,lst.get(4).getC());

		HuffmanInfo arbol=comp.convertirListaEnArbol(lst);

		HuffmanInfo aux=new HuffmanInfo();
		
		aux=arbol;
		aux=aux.getRight();
		aux=aux.getRight();
		assertEquals(104, aux.getC());

		aux=arbol;
		aux=aux.getRight();
		aux=aux.getLeft();
		assertEquals(108, aux.getC());

		aux=arbol;
		aux=aux.getLeft();
		aux=aux.getRight();
		assertEquals(111, aux.getC());
		
		aux=arbol;
		aux=aux.getLeft();
		aux=aux.getLeft();
		aux=aux.getRight();
		assertEquals(32, aux.getC());
		comp.generarCodigosHuffman(arbol, arr);

		assertEquals("001",arr[32].getCod());
		assertEquals("000",arr[97].getCod());
		assertEquals("10",arr[108].getCod());
		assertEquals("11",arr[104].getCod());
		assertEquals("01",arr[111].getCod());



		comp.escribirEncabezado("./src/test/java/huffman/def/prueba.txt", arr);
		comp.escribirContenido("./src/test/java/huffman/def/prueba.txt", arr);
		FileInputStream fComp=new FileInputStream("./src/test/java/huffman/def/prueba.txt.huf");
		BitReader btR=Factory.getBitReader();
		btR.using(fComp);
		
		assertEquals(5,fComp.read());

		assertEquals(32,fComp.read());
		assertEquals(3,fComp.read());
		
		assertEquals(0,btR.readBit());//001
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());


		assertEquals(97,fComp.read());
		assertEquals(3,fComp.read());
		assertEquals(0,btR.readBit());//000
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());

		assertEquals(104,fComp.read());
		assertEquals(2,fComp.read());
		assertEquals(1,btR.readBit()); //11
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());

		assertEquals(108,fComp.read());
		assertEquals(2,fComp.read());
		assertEquals(1,btR.readBit()); //10
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());

		assertEquals(111,fComp.read());
		assertEquals(2,fComp.read());
		assertEquals(0,btR.readBit()); //01
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
//contenido
		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());

		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());

		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());

		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());

		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(1,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		assertEquals(0,btR.readBit());
		
		assertEquals(-1,fComp.read());
		
		Descompresor desc=Factory.getDescompresor();
		HuffmanInfo root=new HuffmanInfo();
		long n = desc.recomponerArbol("./src/test/java/huffman/def/prueba.txt", root);
		assertEquals(16, n);
		HuffmanInfo auxiliar =new HuffmanInfo();
		
		auxiliar=root;
		auxiliar =auxiliar.getRight();
		auxiliar =auxiliar.getRight();
		assertEquals(104, auxiliar.getC());
			boolean pass=false;
		if(auxiliar.getLeft()==null && auxiliar.getRight()==null){
			pass=true;
		}
		assertEquals(true, pass);
		

		auxiliar=root;
		auxiliar =auxiliar.getRight();
		auxiliar =auxiliar.getLeft();
		assertEquals(108, auxiliar.getC());
		pass=false;
			if(auxiliar.getLeft()==null && auxiliar.getRight()==null){
			pass=true;
		}
		assertEquals(true, pass);

		auxiliar=root;
		auxiliar =auxiliar.getLeft();
		auxiliar =auxiliar.getRight();
		assertEquals(111, auxiliar.getC());
		pass=false;
			if(auxiliar.getLeft()==null && auxiliar.getRight()==null){
			pass=true;
		}
		assertEquals(true, pass);

		auxiliar=root;
		auxiliar =auxiliar.getLeft();
		auxiliar =auxiliar.getLeft();
		auxiliar =auxiliar.getRight();
		assertEquals(32, auxiliar.getC());
		pass=false;
			if(auxiliar.getLeft()==null && auxiliar.getRight()==null){
			pass=true;
		}
		assertEquals(true, pass);

		auxiliar=root;
		auxiliar =auxiliar.getLeft();
		auxiliar =auxiliar.getLeft();
		auxiliar =auxiliar.getLeft();
		assertEquals(97, auxiliar.getC());
		pass=false;
			if(auxiliar.getLeft()==null && auxiliar.getRight()==null){
			pass=true;
		}
		assertEquals(true, pass);


		desc.descomprimirArchivo(root, n, "./src/test/java/huffman/def/prueba.txt");
		FileInputStream descomprimido=new FileInputStream("./src/test/java/huffman/def/prueba.txt");
		assertEquals(104, descomprimido.read());
		


		

		

		
		
	}

}

