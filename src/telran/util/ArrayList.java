package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class ArrayList<T> implements List<T> {
	private static final int DEFAULT_CAPACITY = 16;
	private T[] array;
	private int size = 0;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		array = (T[]) new Object[capacity];
	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T obj) {
		if (size == array.length) {
			reallocate();
		}
		array[size++] = obj;
		return true;
	}

	private void reallocate() {
		array = Arrays.copyOf(array, array.length * 2);

	}

	@Override
	public boolean remove(Object pattern) {
		int oldsize = size;
		@SuppressWarnings("unchecked")
		int index = indexOf((T) pattern);
		if (index != -1) {
			remove(index);
		}
		return size < oldsize;
	}

	@Override
	public T[] toArray(T[] ar) {
		T[] res = ar.length < size ? Arrays.copyOf(ar, size) : ar;
		int index = 0;
		for (T obj : this) {
			res[index++] = obj;
		}
		if (res.length > size) {
			res[size] = null;
		}
		return res;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		int oldLength = array.length;
		Iterator<T> it = iterator();
		while (it.hasNext()) {
			T element = it.next();
			if (predicate.test(element)) {
				it.remove();
			}
		}
		return oldLength > array.length;
	}

	@Override
	public int size() {
		if (size > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return size;
	}

	@Override
	public boolean addAll(Collection<T> collection) {
		while ((collection.size() + size) > array.length) {
			reallocate();
		}
		int oldSize = size;
		@SuppressWarnings("unchecked")
		T[] collectionArray = (T[]) new Object[collection.size()];
		collectionArray = collection.toArray(collectionArray);
		int index = 0;
		while (index < collectionArray.length) {
			array[size++] = collectionArray[index++];
		}
		return oldSize < size;
	}

	@Override
	public boolean removeAll(Collection<T> collection) {
		int oldsize = size;
		@SuppressWarnings("unchecked")
		T[] collectionArray = (T[]) new Object[collection.size()];
		collectionArray = collection.toArray(collectionArray);
		for (T object : collectionArray) {
			int index = indexOf(object);
			if (index != -1) {
				remove(index);
				size--;
			}
		}
		return size < oldsize;
	}

	@Override
	public Iterator<T> iterator() {

		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator<T> {
		int currentIndex = 0;
		boolean flNext = false;

		@Override
		public boolean hasNext() {

			return currentIndex < size;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			flNext = true;
			return array[currentIndex++];
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			flNext = false;
			ArrayList.this.remove(--currentIndex);
		}
	}

	@Override
	public void add(int index, T obj) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(index);
		}
		if (size + 1 > array.length) {
			reallocate();
		}
		System.arraycopy(array, 0, array, 0, index);
		System.arraycopy(array, index, array, index + 1, size - index);
		array[index] = obj;
		size++;
	}

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		return array[index];
	}

	@Override
	public T set(int index, T obj) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(index);
		}
		T res = array[index];
		array[index] = obj;
		return res;
	}

	@Override
	public T remove(int index) {
		indexValidation(index, false);
		T res = array[index];
		size--;
		System.arraycopy(array, 0, array, 0, index);
		System.arraycopy(array, index + 1, array, index, size - index);
		array[size] = null;
		return res;
	}

	private void indexValidation(int index, boolean sizeInclusive) {
		int bounder = sizeInclusive ? size : size - 1;
		if (index < 0 || index > bounder) {
			throw new IndexOutOfBoundsException(index);
		}

	}

	@Override
	public int indexOf(T pattern) {
		int index = 0;
		for (T obj : this) {
			if (pattern.equals(obj)) {
				return index;
			}
			index++;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T pattern) {
		int index = size - 1;
		while (index >= 0) {
			if (pattern.equals(array[index])) {
				return index;
			}
			index--;
		}
		return -1;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
		int index = 0;
		for (T obj : this) {
			if (predicate.test(obj)) {
				return index;
			}
			index++;
		}
		return -1;

	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
		int index = size - 1;
		while (index >= 0) {
			if (predicate.test(array[index])) {
				return index;
			}
			index--;
		}
		return -1;
	}

}