package exceptions;

import net.it691team1.qkdbackend.exceptions.QKDFilterException;
import junit.framework.TestCase;

public class QKDFilterExceptionTest extends TestCase {
	private QKDFilterException e;
	private QKDFilterException e2;

	public void setUp(){
		e = new QKDFilterException("Test Error");
		e2 = new QKDFilterException();

	}

	public void testSetError() {
		e2.setError("New Error");
		assertEquals(e2.getError(),"New Error");
		
	}

	public void testGetError() {
		assertEquals(e.getError(),"Test Error");
	}

}
