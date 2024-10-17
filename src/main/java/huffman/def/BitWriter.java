package huffman.def;

public interface BitWriter
{
	public void open(String fname);
	public void writeBit(int bit);
	public void flush();
	public void close();
}
