package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ListTests {
	Integer[] ar = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
	ArrayList<Integer> forTest;

	@BeforeEach
	void setUp() {
		forTest = new ArrayList<Integer>(18);
		for (int i = 0; i < ar.length; i++) {
			forTest.add(ar[i]);
		}
	}

	@Test
	void addObjTest() {
		Integer[] testArray = new Integer[5];
		assertArrayEquals(ar, forTest.toArray(testArray));
	}

	@Test
	void removeObjTest() {
		Integer three = 3;
		Integer thirty = 30;
		assertTrue(forTest.remove(three));
		assertFalse(forTest.remove(thirty));
	}

	@Test
	void indexOfTest() {
		Integer three = 3;
		Integer thirty = 30;
		assertEquals(2, forTest.indexOf(three));
		assertEquals(-1, forTest.indexOf(thirty));
	}

	@Test
	void removeIndexTest() {
		assertEquals(10, forTest.remove(9));
		assertEquals(18, forTest.remove(16));
		assertEquals(1, forTest.remove(0));

	}
}
