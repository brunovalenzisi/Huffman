package imple;

import huffman.def.BitReader;
import huffman.def.BitWriter;
import huffman.def.Compresor;
import huffman.def.Descompresor;
import huffman.def.HuffmanTable;

public class Factory
{
	public static BitWriter getBitWriter()
	{
		return new BitWriterImple();
	}

	public static BitReader getBitReader()
	{
		return new BitReaderImple();
	}
	public static HuffmanTable getHuffmanTable()
	{
		return new HuffmanTable();
	}

	public static Compresor getCompresor()
	{
		return new CompresorImple();
	}
	
	public static Descompresor getDescompresor()
	{
		return new DescompresorImple();
	}


}
