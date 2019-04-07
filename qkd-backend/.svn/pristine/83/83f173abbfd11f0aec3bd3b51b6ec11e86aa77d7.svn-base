package core;

import net.it691team1.qkdbackend.core.QKUtils;
import junit.framework.TestCase;

public class QKUtilsTest extends TestCase {

	public void testCheckFilter() {
		char[] goodSet = {'/','|','-','\\'};
		char[] badSet = {'/','|','a'};
		assertTrue(QKUtils.checkFilter(goodSet));
		assertFalse(QKUtils.checkFilter(badSet));
	}

	public void testCheckFilterSet() {
		char[] goodSet = {'X','+'};
		char[] badSet = {'a','+'};
		assertTrue(QKUtils.checkFilterSet(goodSet));
		assertFalse(QKUtils.checkFilterSet(badSet));
	}

	public void testCheckMatch() {
		char[] goodSet = {'Y','N','~'};
		char[] badSet = {'a','N','Y'};
		assertTrue(QKUtils.checkMatch(goodSet));
		assertFalse(QKUtils.checkMatch(badSet));

	}

	public void testCheckBits() {
		int[] goodSet = {1,0,0};
		int[] badSet = {1,0,7};
		assertTrue(QKUtils.checkBits(goodSet));
		assertFalse(QKUtils.checkBits(badSet));
	}

}
