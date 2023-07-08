package telran.util;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	Node<T> head;
	Node<T> tail;
	int size;

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;
	}

	private void addNode(int index, Node<T> node) {

		if (index == size) {
			addTail(node);
		} else if (index == 0) {
			addHead(node);
		} else {
			addMiddle(index, node);
		}
		size++;

	}

	private void addMiddle(int index, Node<T> node) {

		Node<T> nextNode = getNode(index);
		Node<T> prevNode = nextNode.prev;
		node.next = nextNode;
		nextNode.prev = node;
		prevNode.next = node;
		node.prev = prevNode;

	}

	private void addHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;

	}

	private void addTail(Node<T> node) {
		if (tail == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new LinkedListIterator();
	}

	private class LinkedListIterator implements Iterator<T> {
		Node<T> current = head;
		int currentIndex = 0;
		boolean isNext = false;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			isNext = true;
			T currentObj = current.obj;
			current = current.next;
			currentIndex++;
			return currentObj;
		}

		@Override
		public void remove() {
			if (!isNext) {
				throw new IllegalStateException();
			}
			LinkedList.this.remove(--currentIndex);
			isNext = false;
		}

	}

	@Override
	public void add(int index, T obj) {
		indexValidation(index, true);
		Node<T> node = new Node<>(obj);
		addNode(index, node);

	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		return node.obj;
	}

	private Node<T> getNode(int index) {

		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public T set(int index, T obj) {
		T res = get(index);
		add(index, obj);
		return res;
	}

	@Override
	public T remove(int index) {
		if (index == size) {
			removeTail();
		} else if (index == 0) {
			removeHead();
		} else {
			removeMiddle(index);
		}
		size--;

		return null;
	}

	private void removeMiddle(int index) {
		Node<T> newCurrentNode = getNode(index).next;
		Node<T> prevNode = getNode(index).prev;
		newCurrentNode.prev = prevNode;
		prevNode.next = newCurrentNode;

	}

	private void removeHead() {
		head = head.next;
		head.prev = null;

	}

	private void removeTail() {
		tail = tail.prev;
		tail.next = null;

	}

	@Override
	public int indexOf(Object pattern) {
		Node<T> current = head;
		int index = 0;
		while (current != null) {
			if (current.obj.equals(pattern)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		Node<T> current = tail;
		int index = size-1;
		while (current != null) {
			if (current.obj.equals(pattern)) {
				return index;
			}
			current = current.prev;
			index--;
		}
		return -1;			}

	@Override
	public int indexOf(Predicate<T> predicate) {
		Node<T> current = head;
		int index = 0;
		while (current != null) {
			if (predicate.test(current.obj)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;		
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		Node<T> current = tail;
		int index = size-1;
		while (current != null) {
			if (predicate.test(current.obj)){
				return index;
			}
			current = current.prev;
			index--;
		}
		return -1;
	}

}
