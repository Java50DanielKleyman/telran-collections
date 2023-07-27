package telran.util.test;

import telran.util.TreeSet;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

public class TreeSetTest extends SortedSetTest {
	TreeSet<Integer> treeSet;

	@BeforeEach
	@Override
	void setUp() {
		collection = new TreeSet<>();
		super.setUp();
		treeSet = (TreeSet<Integer>) collection;
	}

	@Override
	protected Collection<Integer> getCollection(Integer[] ar) {
		TreeSet<Integer> res = new TreeSet<>();
		for (Integer num : ar) {
			res.add(num);
		}
		return res;
	}

	@Test
	void displayRotatedTest() {
		treeSet.setSpacesPerLevel(4);
		treeSet.displayRotated();
	}

	@Test
	void widthTest() {
		assertEquals(3, treeSet.width());
	}

	@Test
	void heightTest() {
		assertEquals(4, treeSet.height());
	}

	@Test
	void balanceTest() {
		treeSet.balance();
		assertEquals(4, treeSet.width());
		assertEquals(3, treeSet.height());
	}

	@Test
	void balanceTestArray() {
		Integer[] array = new Integer[1023];
		for (int i = 0; i < array.length; i++) {
			array[i] = i;
		}
		reorderArray(array);
		TreeSet<Integer> tree = new TreeSet<>();
		for (Integer num : array) {
			tree.add(num);
		}
		assertEquals(512, tree.width());
	}

	private void reorderArray(Integer[] array) {
		Integer[] helperArray = new Integer[array.length];
		reorderArrayMethod(helperArray, array, 0, array.length - 1, 0);
		System.arraycopy(helperArray, 0, array, 0, array.length);
	}

	private void reorderArrayMethod(Integer[] helperArray, Integer[] array, int left, int right, int index) {

		if (left <= right) {
			int rootIndex = (left + right) / 2;
			helperArray[index] = array[rootIndex]; 
			reorderArrayMethod(helperArray, array, left, rootIndex - 1, 2* index +1);
			reorderArrayMethod(helperArray, array, rootIndex + 1, right, 2* index + 2);			
		}
	}
	@Test
	void inverseTest() {
		Integer[] expected = { 100, 30, 14, 10, 12, 8, -20 };
		treeSet.inverse();
		assertArrayEquals(expected, treeSet.toArray(new Integer[0]));
		assertTrue(treeSet.contains(100));		
	}

}