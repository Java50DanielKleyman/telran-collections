package telran.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

abstract class ListTest extends CollectionTest {

	List<Integer> list;

	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		list = (List<Integer>) collection;
	}

	@Override
	@Test
	void addTest() {
		Integer[] expected = { 10, -20, 8, 14, 30, 12, 100, 10 };
		assertTrue(list.add(10));
		assertArrayEquals(expected, list.toArray(new Integer[0]));

	}

	@Test
	void addIndexTest() {
		Integer[] expected = { 10, 10, -20, 8, 14, 30, 12, 100 };
		list.add(0, 10);
		assertArrayEquals(expected, list.toArray(new Integer[0]));
	}

	// TODO all tests for specific list methods
	@Test
	void lastIndexOfTest() {
		assertEquals(2, list.lastIndexOf(8));
		assertEquals(-1, list.lastIndexOf(1000));
	}

	@Test
	void indexOfTest() {
		assertEquals(0, list.indexOf(10));
		assertEquals(6, list.indexOf(100));
	}

	@Test
	void lastIndexOfPredicateTest() {
		assertEquals(1, list.lastIndexOf(num -> num == -20));
		assertEquals(-1, list.lastIndexOf(num -> num > 1000));
		assertEquals(5, list.lastIndexOf(num -> num < 30));
	}
	@Test
	void indexOfPredicateTest() {
		assertEquals(6, list.indexOf(num -> num > 50));
		assertEquals(-1, list.indexOf(num -> num <- 1000));
		assertEquals(3, list.indexOf(num -> num == 14));
	}
	@Test
	void removeIndTest() {
		assertEquals(10, list.remove(0));
		assertEquals(100, list.remove(5));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(5));
	}
	
	@Test
	void setTest() {
		assertEquals(10, list.set(0, 500));
		assertEquals(500, list.set(0, 10));
	}
	@Test
	void getTest() {
		assertEquals(10, list.get(0));
		assertEquals(100, list.get(6));
	}
	@Test
	void addIndObjTest() {
		Integer [] expected1 = {500, 10, -20, 8, 14, 30, 12, 100};
		list.add(0, 500);
		assertArrayEquals(expected1, list.toArray(new Integer[0]));
		Integer [] expected2 = {500, 10, -20, 8, 14, 30, 12, 100, 1000};
		list.add(8, 1000);
		assertArrayEquals(expected2, list.toArray(new Integer[0]));
		Integer [] expected3 = {500, 10, -20, 8, 700, 14, 30, 12, 100, 1000};
		list.add(4, 700);
		assertArrayEquals(expected3, list.toArray(new Integer[0]));
		
	}
	
}