package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.ArrayList;
import telran.util.Collection;

class ArrayListTests {
	Integer[] ar = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
	Integer[] ar1 = { 19, 20, 21 };
	Integer[] ar2 = { 1, 2, 23 };
	ArrayList<Integer> forTest;
	ArrayList<Integer> forTest1;
	ArrayList<Integer> forTest2;

	@BeforeEach
	void setUp() {
		forTest = new ArrayList<Integer>(ar.length);
		addToCollection(ar, forTest);
		forTest1 = new ArrayList<Integer>(ar1.length);
		addToCollection(ar1, forTest1);
		forTest2 = new ArrayList<Integer>(ar2.length);
		addToCollection(ar2, forTest2);
	}

	void addToCollection(Integer[] ar, Collection<Integer> collection) {

		for (int i = 0; i < ar.length; i++) {
			collection.add(ar[i]);
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
		Integer[] expected = ar;
		Integer[] testArray = new Integer[25];
		assertEquals(25, forTest.toArray(testArray).length);
		Integer[] testArray1 = new Integer[5];
		assertEquals(expected.length, forTest.toArray(testArray1).length);

	}

	@Test
	void iteratorTest() {
		int elements = 0;
		Iterator<Integer> it = forTest.iterator();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
		while (it.hasNext()) {
			it.next();
			elements++;
		}
		assertEquals(ar.length, elements);
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
		it.remove();
		assertThrowsExactly(IllegalStateException.class, () -> it.remove());
	}

	@Test
	void removeIfTest() {
		Integer[] expected = { 2, 4, 6, 8, 10, 12, 14, 16, 18 };
		Integer[] testArray = new Integer[0];
		forTest.removeIf(element -> element % 2 != 0);
		assertArrayEquals(expected, forTest.toArray(testArray));

		Integer[] expected1 = new Integer[0];
		Integer[] testArray1 = new Integer[0];
		forTest.removeIf(element -> element % 2 == 0);
		assertArrayEquals(expected1, forTest.toArray(testArray1));
	}

	@Test
	void sizeTest() {
		assertEquals(ar.length, forTest.size());

	}

	@Test
	void addAllTest() {
		assertTrue(forTest.addAll(forTest1));
		assertEquals(ar.length + ar1.length, forTest.size());

	}

	@Test
	void removeAllTest() {
		assertTrue(forTest.removeAll(forTest2));
		assertFalse(forTest.removeAll(forTest1));
	}

	@Test
	void addTest() {
		int index = ar.length;
		int value = 111;
		forTest.add(index, value);
		assertEquals(index, forTest.indexOf(value));
		assertEquals(ar.length + 1, forTest.size());
		final int indexNew = -2;
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> forTest.add(indexNew, value));
	}

	@Test
	void getTest() {
		int index = -3;
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> forTest.get(index));
		int newIndex = 0;
		assertEquals(ar[newIndex], forTest.get(newIndex));
	}

	@Test
	void setTest() {
		int index = 113;
		int obj = 115;
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> forTest.set(index, obj));
		int newIndex = 0;
		assertEquals(ar[newIndex], forTest.set(newIndex, obj));
	}

	@Test
	void lastIndexOfTest() {
		int obj = 115;
		assertEquals(-1, forTest.lastIndexOf(obj));
		obj = 18;
		assertEquals(17, forTest.lastIndexOf(obj));
	}

	@Test
	void indexOfPredicateTest() {
		Predicate<Integer> predicate = num -> num % 2 == 0;
		assertEquals(1, forTest.indexOf(predicate));
		predicate = num -> num % 20 == 0;
		assertEquals(-1, forTest.indexOf(predicate));
	}

	@Test
	void lastIndexOfPredicate() {
		Predicate<Integer> predicate = num -> num % 2 == 0;
		assertEquals(17, forTest.lastIndexOf(predicate));
		predicate = num -> num % 20 == 0;
		assertEquals(-1, forTest.lastIndexOf(predicate));
	}

}