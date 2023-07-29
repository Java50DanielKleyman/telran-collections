package telran.util;

import java.util.Iterator;

import telran.util.LinkedList.Node;

public class LinkedHashSet<T> implements Set<T> {
	HashMap<T, LinkedList.Node<T>> map = new HashMap<>();
	LinkedList<T> list = new LinkedList<>();

	@Override
	public boolean add(T obj) {
		if (!map.containsKey(obj)) {
			LinkedList.Node<T> newNode = new Node<>(obj);			
			list.addTail(newNode);
			list.size++;
			map.put(obj, newNode);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object pattern) {
		LinkedList.Node<T> removedNode = map.get(pattern);
		if (removedNode != null) {			
			list.removeNode(removedNode);
			map.remove(pattern);
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(Object pattern) {
		
		return map.containsKey(pattern);
	}

	@Override
	public int size() {
		
		return map.size();
	}

	@Override
	public Iterator<T> iterator() {

		return list.iterator();
	}

	@Override
	public T get(Object pattern) {
		
		return map.get(pattern).obj;
	}

}