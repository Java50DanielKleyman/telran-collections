package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;
import telran.util.Map.Entry;

public class LinkedHashMap<T, K, V> extends AbstractMap<K, V> {
	HashMap<K, LinkedList.Node<Entry <K, V>>> map = new HashMap<>();	
	LinkedList<Entry <K, V>> list = new LinkedList<>();
	
	
	public boolean add(K key, V value) {
		Entry <K, V> newEntry = new Entry<>(key, value);
		LinkedList.Node<Entry <K, V>> newNode = new Node<>(newEntry);
		if (!map.containsKey(key)) {			
			list.addTail(newNode);
			list.size++;
			map.put(key, newNode);
			return true;
		}
		return false;
	}


	@Override
	public V remove(Object pattern) {
		LinkedList.Node<Entry <K, V>> removedNode = map.get(pattern);
		if (removedNode != null) {			
			list.removeNode(removedNode);
			map.remove(pattern);
			return removedNode.obj.getValue();
		}
		return null;
	}

	public boolean contains(Object pattern) {
		
		return map.containsKey(pattern);
	}

	@Override
	public int size() {
		
		return map.size();
	}

	public Iterator<T> iterator() {

		return (Iterator<T>) list.iterator();
	}

	@Override
	public V get(Object pattern) {
		
		return map.get(pattern).obj.getValue();
	}


	@Override
	protected Set<K> getEmptyKeySet() {
		Set<K> res = new HashSet<>();
		return null;
	}

}