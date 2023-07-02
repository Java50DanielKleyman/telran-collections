package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;

class ArrayListTests {
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
	@Test
	void toArrayTest() {
		Integer [] expected = ar;
		Integer [] testArray = new Integer[25];
		assertEquals(25, forTest.toArray(testArray).length);
		Integer [] testArray1 = new Integer[5];
		assertEquals(expected.length, forTest.toArray(testArray1).length);
		
	}
	@Test
	void iteratorTest() {
		int elements = 0;
		Iterator<Integer> it = forTest.iterator();
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
		while(it.hasNext()) {
			it.next();
			elements++;
		}
		assertEquals(ar.length, elements);
		assertThrowsExactly(NoSuchElementException.class, ()->it.next());
		it.remove();
		assertThrowsExactly(IllegalStateException.class, ()->it.remove());
}
	@Test
	void removeIfTest() {
		Integer [] expected = { 2, 4, 6, 8, 10, 12, 14, 16, 18 };
		Integer [] testArray = new Integer[0];
		forTest.removeIf(element -> element%2!=0);		
		assertArrayEquals(expected, forTest.toArray(testArray));
		
		Integer [] expected1 = new Integer[0];
		Integer [] testArray1 = new Integer[0];
		forTest.removeIf(element -> element%2==0);
		assertArrayEquals(expected1, forTest.toArray(testArray1));
		
	}
}