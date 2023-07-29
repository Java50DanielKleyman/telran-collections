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
			int size = list.size();
			list.addNode(size, newNode);
			map.put(obj, newNode);
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object pattern) {
		if (map.containsKey(pattern)) {
			LinkedList.Node<T> removedNode = map.get(pattern);
			list.removeNode(removedNode);
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<T> iterator() {

		return list.iterator();
	}

	@Override
	public T get(Object pattern) {
		// TODO Auto-generated method stub
		return null;
	}

}