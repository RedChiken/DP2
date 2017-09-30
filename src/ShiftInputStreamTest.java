import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShiftInputStreamTest {
	@Test
	void setOffsetByDefaultValue() {
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"));
			assertEquals(sis.getOffset(), 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void setOffsetByConstructor(){
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), 3);
			assertEquals(sis.getOffset(), 3);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void setOffsetByFunction(){
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"));
			sis.setOffset(-2);
			assertEquals(sis.getOffset(), -2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readNonParameterShiftLeft() {
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), -5);
			int result = sis.read();
			assertEquals(result, 'Y');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readNonParameterRightShift() {
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), 29);
			int result = sis.read();
			assertEquals(result, 'G');
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readBytesLength() {
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), 29);
			int length = sis.read(buffer);
			assertEquals(length, 19);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readBytesRightShift(){
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), 29);
			sis.read(buffer);
			assertEquals(new String(buffer), "Ghvljq Sdwwhuq#3852");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readBytesLeftShift(){
		byte[] buffer = new byte[19];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), -23);
			sis.read(buffer);
			assertEquals(new String(buffer), "Ghvljq Sdwwhuq#3852");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readBytesWithOffsetPosition() {
		byte[] buffer = new byte[25];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), 29);
			sis.read(buffer, 4, 18);
			assertEquals(buffer[4], 'G');
			assertEquals(buffer[21], '5');
			assertEquals(buffer[22], 0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void readBytesWithOffset() {
		byte[] buffer = new byte[25];
		try {
			ShiftInputStream sis = new ShiftInputStream(new FileInputStream("test.txt"), 29);
			sis.read(buffer, 4, 18);
			byte[] ret = Arrays.copyOfRange(buffer, 4, 18);
			assertEquals(new String(ret), "Ghvljq Sdwwhuq");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}