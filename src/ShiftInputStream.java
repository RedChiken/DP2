import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ShiftInputStream extends FilterInputStream{
	private int offset;
	
	public ShiftInputStream(InputStream in, int offset){
		super(in);
		setOffset(offset);
	}
	public ShiftInputStream(InputStream in){
		this(in, 0);
	}
	private int circularShift(int c, int offset){
		while(offset < 0){
			offset += 26;
		}
		return (c + (offset % 26));
	}
	
	public void setOffset(int offset){
		this.offset = offset;
	}
	
	public int getOffset(){
		return this.offset;
	}
	
	public int read() throws IOException {
		int inputChar = super.read();
		if(((inputChar >= 'A') && (inputChar <= 'Z'))
				|| ((inputChar >= 'a') && (inputChar <= 'z'))){
			inputChar = circularShift(inputChar, offset);
		}
		return inputChar;
	}
	
	public int read(byte[] b) throws IOException, NullPointerException {
		return read(b, 0, b.length);
	}
	
	public int read(byte[] b, int off, int len) throws IOException, NullPointerException, IndexOutOfBoundsException {
		int length;
		if(b == null){
			throw new NullPointerException();
		}
		for(length = 0; length < len; length++ ){
			if((off + length) >= b.length){
				throw new IndexOutOfBoundsException();
			}
			byte result;
			if((result = (byte)this.read()) < 0){
				break;
			}
			else{
				b[off + length] = result;
			}
		}
		return length;
	}
}
