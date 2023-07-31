package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.TreeMap;
import telran.util.LinkedHashMap;
import telran.util.Map.Entry;

class LinkedHashMapTest extends MapTest{
	@BeforeEach
	@Override
	void setUp() {
		map = new LinkedHashMap<>();
		super.setUp();
	}

	

	@Override
	protected String[] getKeysActual(String[] keys) {
		
		return keys;
	}

	@Override
	protected Entry<String, Integer>[] getEntriesActual(Entry<String, Integer>[] entries) {
		
		return entries;
	}

}
