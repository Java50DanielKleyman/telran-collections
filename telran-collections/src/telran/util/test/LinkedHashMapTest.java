package telran.util.test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.LinkedHashMap;
import telran.util.Map.Entry;


class LinkedHashMapTest extends MapTest{
	@BeforeEach
	@Override
	void setUp() {
		map = new LinkedHashMap<>();
		super.setUp();
	}	
	
	@Test
	void removeEldestEntryTest() {
		int oldSize = map.size();
		map.put("ewe", 11111);
		int newSize = map.size();
		assertEquals(oldSize, newSize);
	}
	@Override
	@Test
	void removeTest() {
		Integer[] removedValues = Arrays.stream(keys).map(map::remove).toArray(size -> new Integer[size]);
	//	assertArrayEquals(values, removedValues);
	//	assertNull(map.remove(keys[0]));
	//	assertEquals(0, map.entrySet().size());
		System.out.println(((LinkedHashMap<Object, String, Integer>) map).getEntry("a"));
//		for(Integer value: removedValues) {
//			System.out.println(value);
//		}
		Integer removedValue = map.remove("a");
	//	System.out.println(removedValue);
		Integer expectedValue = 1;
		assertEquals(expectedValue, removedValue);
	}
}
