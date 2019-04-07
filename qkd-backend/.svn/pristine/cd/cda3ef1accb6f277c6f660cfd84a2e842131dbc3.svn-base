package dto;

import net.it691team1.qkdbackend.dto.EqualBits;
import junit.framework.TestCase;

public class EqualBitsTest extends TestCase {
	EqualBits e;
	
	public void setUp(){
		this.e = new EqualBits();
	}
	public void testEqualBits() {
		assertNotNull(e);
	}
	public void testSetK() {
		e.setK(5);
		assertEquals(e.getK(),5);
	}
	public void testGetK() {
		e.setK(7);
		assertEquals(e.getK(),7);
	}
	
	public void testGetTotal() {
		e.setTotal(10);
		assertEquals(e.getTotal(),10);
	}

	public void testSetTotal() {
		e.setTotal(100);
		assertEquals(e.getTotal(),100);
	}

	public void testGetBitsEqual() {
		char [] a = {'Y','N','N','N'};
		e.setBitsEqual(a);
		assertEquals(e.getBitsEqual().length,4);
		assertEquals(e.getBitsEqual()[0],'Y');
		assertEquals(e.getBitsEqual()[1],'N');
	}

	public void testSetBitsEqual() {
		char [] b = {'N','Y'};
		e.setBitsEqual(b);
		assertEquals(e.getBitsEqual(), b);
	}

}
