package exceptions;

import net.it691team1.qkdbackend.exceptions.QKDInputException;
import junit.framework.TestCase;

public class QKDInputExecptionTest extends TestCase {
	private QKDInputException e;
	private QKDInputException e2;

	public void setUp(){
		e = new QKDInputException("New Error");
		e2 = new QKDInputException();

	}
	
	public void testGetError() {
		assertEquals(e.getError(),"New Error");
		assertEquals(e2.getError(), "A input error occured");
	}
	
	public void testSetError() {
		e.setError("New Error");
		assertEquals(e.getError(),"New Error");
	}
	




}
