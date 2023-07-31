package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;


public class LinkedHashMap<T, K, V> extends HashMap<K, V> {
	HashMap<K, V> map = new HashMap<>();
	LinkedList<Entry<K, V>> list = new LinkedList<>();
	private static final int MAX_ENTRIES = 100;
	boolean accessOrder;

	public LinkedHashMap() {
		super();
	}

	public LinkedHashMap(boolean accessOrder) {
		this.accessOrder = accessOrder;
	}

	public boolean add(K key, V value) {
		Entry<K, V> newEntry = new Entry<>(key, value);
		LinkedList.Node<Entry<K, V>> newNode = new Node<>(newEntry);
		if (!map.containsKey(key)) {
			list.addTail(newNode);
			map.put(key, value);
			list.size++;
			if (list.size > MAX_ENTRIES) {
				removeEldestEntry(list.head);
			}
			return true;
		}
		return false;
	}

	protected boolean removeEldestEntry(Node<Entry<K, V>> eldestEntry) {
		remove(eldestEntry.obj.getKey());
		return size() <= MAX_ENTRIES;
	}

	@Override
	public V remove(Object pattern) {
		Entry<K, V> removedEntry = map.getEntry(pattern);
		LinkedList.Node<Entry<K, V>> removedNode = list.getNodeByPattern(removedEntry);
		if (removedNode != null) {
			list.removeNode(removedNode);
			map.remove(pattern);
			return removedEntry.getValue();
		}
		return null;
	}

	public boolean contains(Object pattern) {
		boolean res = map.containsKey(pattern);
		if (res && accessOrder) {
			reoder(pattern);
		}
		return res;
	}

	@Override
	public int size() {

		return map.size();
	}

	public Iterator<T> iterator() {

		return (Iterator<T>) list.iterator();
	}

	@Override
	public V get(Object key) {
		Entry<K, V> pattern = new Entry<>((K) key, null);

		Entry<K, V> entry = set.get(pattern);
		if (entry != null && accessOrder) {
			reoder(entry.getKey());
		}
		return entry == null ? null : entry.getValue();
	}

	private void reoder(Object pattern) {
		Entry<K, V> accessedEntry = map.getEntry(pattern);
		LinkedList.Node<Entry<K, V>> accessedNode = list.getNodeByPattern(accessedEntry);
		if (!accessedNode.equals(list.tail)) {
			remove(accessedNode.obj.getKey());
			put(accessedNode.obj.getKey(), accessedNode.obj.getValue());
		}
	}

	@Override
	public boolean containsValue(Object value) {
		Iterator<Entry<K, V>> it = set.iterator();
		boolean res = false;
		while (it.hasNext() && !res) {
			Entry<K, V> entry = it.next();
			if (entry.getValue().equals(value)) {
				res = true;
				if (res && accessOrder) {
					reoder(entry.getKey());
				}
			}
		}

		return res;
	}
}