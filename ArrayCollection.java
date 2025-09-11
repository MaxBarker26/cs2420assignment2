package assign03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Daniel Kopta and Josi Gac and Max Barker
 * Implements the Collection interface using an array as storage.
 * The array must grow as needed.
 * An ArrayCollection can not contain duplicates.
 * All methods should be implemented as defined by the Java API, unless otherwise specified.
 * 
 * It is your job to fill in the missing implementations and to comment this class. 
 * Every method should have comments (Javadoc-style prefered). 
 * The comments should at least indicate what the method does, 
 * what the arguments mean, and what the returned value is. 
 * It should specify any special cases that the method
 * handles. Any code that is not self-explanatory should be commented.
 *
 * @param <T> - generic type placeholder
 */
public class ArrayCollection<T> implements Collection<T> {

	private T data[]; // Storage for the items in the collection
	private int size; // Keep track of how many items this collection holds

	// There is no clean way to convert between T and Object, so we suppress the warning.
	@SuppressWarnings("unchecked")  
	public ArrayCollection()
	{
		size = 0;
		// We can't instantiate an array of unknown type T, so we must create an Object array, and typecast
		data = (T[]) new Object[10]; // Start with an initial capacity of 10
	}

	/**
	 * This is a helper method specific to ArrayCollection
	 * Doubles the size of the data storage array, retaining its current contents.
	 */
	@SuppressWarnings("unchecked")
	private void grow()
	{
		T newData[] = (T[]) new Object[size*2];
		for (int i = 0; i < size; i++) {
			newData[i] = data[i];
		}
		data = newData;
	}


	public boolean add(T arg0) {
		if (!this.contains(arg0)) {
			data[size] = arg0;
			size++;
			if (size == data.length) {
				this.grow();
			}
			return true;
		}
		return false;
	}

	public boolean addAll(Collection<? extends T> arg0) {
		int previousSize = size;
		for (T element : arg0) {
			if (!this.contains(element)) {
				this.add(element);
			}
		}
		if (size > previousSize) {
			return true;
		}
		return false;
	}

	public void clear() {
		
		 size = 0;
	}

	public boolean contains(Object arg0) {
		for (int i = 0; i < size; i++) {
			if (data[i].equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> arg0) {
		for (Object element : arg0) {
			if (!this.contains(element)) {
				return false;
			}
		}
		return true;
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public Iterator<T> iterator() {
		return new ArrayCollectionIterator();
	}

	public boolean remove(Object arg0) {
		if (!this.contains(arg0)) {
			return false;
		}
		Iterator<T> iterator = this.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().equals(arg0)) {
				iterator.remove();
			}
		}
		return true;
	}

	public boolean removeAll(Collection<?> arg0) {
		int previousSize = size;
		Iterator<T> iterator = this.iterator();
		while (iterator.hasNext()) {
			if (arg0.contains(iterator.next())) {
				iterator.remove();
			}
		}
		if (size < previousSize) {
			return true;
		}
		return false;
	}

	public boolean retainAll(Collection<?> arg0) {
		int previousSize = size;
		Iterator<T> iterator = this.iterator();		
		while (iterator.hasNext()) {
			if (!arg0.contains(iterator.next())) {
				iterator.remove();
			}
		}
		if (this.size < previousSize) {
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}

	public Object[] toArray() {
		Object[] objectArray = new Object[size];
		for (int i = 0; i < size; i++) {
			objectArray[i] = data[i];
		}
		return objectArray;
	}

	/* 
	 * Don't implement this method (unless you want to).
	 * It must be here to complete the Collection interface.
	 * We will not test this method.
	 */
	public <T> T[] toArray(T[] arg0) {
		return null;
	}



	/*
     
	*/
	/**
	 * Sorting method specific to ArrayCollection - not part of the Collection interface.
     	Must implement a selection sort (see Assignment 2 for code).
     	Must not modify this ArrayCollection.
	 * @param cmp - the comparator that defines item ordering
	 * @return - the sorted list
	 */
	public ArrayList<T> toSortedList(Comparator<? super T> cmp)
	{
		ArrayList<T> list = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			list.add(data[i]);
		}
		for(int i = 0; i < this.size() - 1; i++) {
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < this.size(); j++)
				if(cmp.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			T temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
		return list;
	}


	/**
	 * 
	 * @author Josi Gac and Max Barker
	 * Describe your ArrayCollectionIterator class here.
	 *
	 */
	private class ArrayCollectionIterator implements Iterator<T>
	{
		int cursor;
		int removeCallsConsecutive;
		public ArrayCollectionIterator()
		{
			cursor = 0;
			removeCallsConsecutive = 0;
		}

		public boolean hasNext() {
			if (cursor + 1 > size) {
				return false;
			}
			return true;
		}

		public T next() {
				if (cursor == size) {
					throw new NoSuchElementException();
				}
				T returnValue = data[cursor];
				cursor++;
				if (removeCallsConsecutive == 1) {
					removeCallsConsecutive--;
				}
				return returnValue;
		}

		public void remove() {
			if (cursor == 0) {
				throw new IllegalStateException();
			}
			if (removeCallsConsecutive >= 1) {
				throw new IllegalStateException();
			}
			for (int i = cursor - 1; i < size; i++) {
				data[i] = data[i + 1];
			}
			removeCallsConsecutive++;
			size--;
			cursor--;
		}

	}

}