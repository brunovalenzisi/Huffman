package huffman.def;

public interface BitReader
{
	public void open(String fname);
	public int readBit();
	public void close();
}
