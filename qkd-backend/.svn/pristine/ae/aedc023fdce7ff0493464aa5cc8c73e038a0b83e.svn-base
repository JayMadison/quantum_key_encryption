	package dto;

import net.it691team1.qkdbackend.dto.MatchBits;
import junit.framework.TestCase;

public class MatchBitsTest extends TestCase {
	private MatchBits m;
	
	protected void setUp() throws Exception {
		super.setUp();
		this.m = new MatchBits();
	}

	public void testMatchBits() {
		assertNotNull(m);
	}

	public void testGetBitsAfterMatch() {
		m.setBitsAfterMatch(new int[]{1,2,3});
		assertEquals(m.getBitsAfterMatch().length,3);
		assertEquals(m.getBitsAfterMatch()[1],2);
	}

	public void testSetBitsAfterMatch() {
		m.setBitsAfterMatch(new int[]{4,5,6,7});
		assertEquals(m.getBitsAfterMatch().length,4);
		assertEquals(m.getBitsAfterMatch()[1],5);
	}

	public void testGetFinalBits() {
		m.setBitsAfterMatch(new int[]{3,4});
		assertEquals(m.getBitsAfterMatch()[1],4);
	}

	public void testSetFinalBits() {
		m.setBitsAfterMatch(new int[]{7,8});
		assertEquals(m.getBitsAfterMatch()[0], 7);
	}

	public void testGetFinalBitsSlots() {
		m.setFinalBitsSlots(new int[]{5,3});
		assertEquals(m.getFinalBitsSlots()[0],5);
		assertEquals(m.getFinalBitsSlots().length,2);
	}

	public void testSetFinalBitsSlots() {
		m.setFinalBitsSlots(new int[]{2,1});
		assertEquals(m.getFinalBitsSlots()[0],2);
	}

	public void testSetK() {
		m.setK(7);
		assertEquals(m.getK(),7);
	}

	public void testGetK() {
		m.setK(4);
		assertEquals(m.getK(),4);
	}

}
