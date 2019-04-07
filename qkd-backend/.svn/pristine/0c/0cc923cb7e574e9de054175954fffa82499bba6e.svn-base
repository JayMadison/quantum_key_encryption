package exceptions;

import net.it691team1.qkdbackend.exceptions.QKDatabaseException;
import junit.framework.TestCase;

public class QKDatabaseExceptionTest extends TestCase {
	private QKDatabaseException e;

	public void testQKDatabaseException() {
		e = new QKDatabaseException();
		assertNotNull(e);
		assertEquals("A Database Error has Occured", e.getError());
	}

	public void testQKDatabaseExceptionString() {
		 e = new QKDatabaseException("Test Error");
		assertNotNull(e);
		assertEquals("Test Error",e.getError());	
	}

	public void testGetError() {
		e = new QKDatabaseException();
		assertEquals("A Database Error has Occured", e.getError());
	}

	public void testSetError() {
		e = new QKDatabaseException();
		e.setError("Test Error 2");
		assertEquals("Test Error 2",e.getError());
	}

}
