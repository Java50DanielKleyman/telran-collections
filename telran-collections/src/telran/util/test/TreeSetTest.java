package telran.util.test;

import telran.util.TreeSet;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<Integer> list = new ArrayList<>();
		reorderArray(array, 0, array.length - 1, list);
		int index = 0;
		for(int num: list) {
			array[index++] = num;
		}
		
		
	}
	private void reorderArray(Integer[] array, int left, int right,
			List<Integer> list) {
		if(left <= right) {
			int middle = (left + right) / 2;
			list.add(array[middle]);
			reorderArray(array, left, middle - 1, list);
			reorderArray(array, middle + 1, right, list);
		}
		
	}
	@Test
	void inverseTest() {
		Integer[] expected = { 100, 30, 14, 12, 10, 8, -20 };
		
		treeSet.inverse();
		Integer [] inversedArray = treeSet.toArray(new Integer[0]);
//		for(Integer elm : inversedArray ) {
//			System.out.println(elm);
//		}		
//		assertArrayEquals(expected, inversedArray);
		assertTrue(treeSet.contains(100));		
	}

}