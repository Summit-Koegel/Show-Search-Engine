// --== CS400 Project One File Header ==--
// Name: Corey Johnsen
// CSL Username: johnsen
// Email: cjjohnsen@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: N/A

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

	// capacity of table
	protected int capacity;
	// number of elements in the table
	protected int size;
	// array to store all key, value pairs
	protected ArrayList[] arr;

	public HashtableMap(int capacity) {
		// sets the capacity of the array and initializes each linked list in it
		if(capacity > 0)
			this.capacity = capacity;
		else
			this.capacity = 20;
		arr = new ArrayList[this.capacity];
		for (int i = 0; i < this.capacity; i++) {
			arr[i] = new ArrayList();
		}
	}

	public HashtableMap() {
		// sets the capacity of the array and initializes each linked list in it
		this.capacity = 20;
		arr = new ArrayList[this.capacity];
		for (int i = 0; i < this.capacity; i++) {
			arr[i] = new ArrayList();
		}
	}

	@Override
	public boolean put(KeyType key, ValueType value) {
		// assign new key and value to a new pair
		KeyValuePair newPair = new KeyValuePair(key, value);
		// check if key is already in the table
		boolean keyInTable = false;
		for (int i = 0; i < (arr[Math.abs(key.hashCode()) % capacity].size()); i++) {
			if (((KeyValuePair<KeyType, ValueType>) arr[Math.abs(key.hashCode()) % capacity].get(i)).key().equals(key))
				keyInTable = true;
		}
		// return false if the key is null or already in table
		if (key == null || keyInTable) {
			return false;
		}
		// else add new pair, increment size, resize if needed, and return true
		// load factor >= .75 causes resize
		arr[Math.abs(key.hashCode()) % capacity].add(newPair);
		this.size++;
		if ((float) this.size / this.capacity >= .75)
			this.resize();
		return true;
	}

	@Override
	public ValueType get(KeyType key) throws NoSuchElementException {
		// finds the linked list containing the key, and searches the list to find the
		// expected key
		for (int i = 0; i < arr[Math.abs(key.hashCode()) % capacity].size(); i++) {
			if (((KeyValuePair<KeyType, ValueType>) arr[Math.abs(key.hashCode()) % capacity].get(i)).key().equals(key))
				return ((KeyValuePair<KeyType, ValueType>) arr[Math.abs(key.hashCode()) % capacity].get(i)).value();
		}
		// throws exception when the key is not found in the table
		throw new NoSuchElementException("Key not in table!");
	}

	@Override
	public int size() {
		// returns the number of elements in stored in the table
		return this.size;
	}

	@Override
	public boolean containsKey(KeyType key) {
		// finds the linked list containing the key and searches it to see if the key is
		// in it
		for (int i = 0; i < arr[Math.abs(key.hashCode()) % capacity].size(); i++) {
			if (((KeyValuePair<KeyType, ValueType>) arr[Math.abs(key.hashCode()) % capacity].get(i)).key()
					.equals(key)) {
				return true; // found key, return true
			}
		}
		return false; // key not found, return false
	}

	@Override
	public ValueType remove(KeyType key) {
		ValueType returned;
		// finds the linked list containing the key and searches it until key is found
		// then removes the pair, decrements size, and returns the value that was
		// removed
		for (int i = 0; i < arr[Math.abs(key.hashCode()) % capacity].size(); i++) {
			if (((KeyValuePair<KeyType, ValueType>) arr[Math.abs(key.hashCode()) % capacity].get(i)).key()
					.equals(key)) {
				returned = ((KeyValuePair<KeyType, ValueType>) arr[Math.abs(key.hashCode()) % capacity].get(i)).value();
				arr[Math.abs(key.hashCode()) % capacity].remove(i);
				this.size--;
				return returned;
			}
		}
		// returns null if the key is not found in the table
		return null;
	}

	@Override
	public void clear() {
		// sets each linked list in the array to a new array
		for (int i = 0; i < this.capacity; i++) {
			this.arr[i] = new ArrayList();
		}
		// sets size to 0
		this.size = 0;

	}

	private void resize() {
		// stores old array and old capacity
		ArrayList[] oldArr = this.arr;
		int oldCap = this.capacity;
		// sets new capacity and makes + initializes the new array
		this.capacity *= 2;
		ArrayList[] newArr = new ArrayList[this.capacity];
		for (int i = 0; i < this.capacity; i++) {
			newArr[i] = new ArrayList();
		}
		// sets the table to the new array and new size
		this.size = 0;
		this.arr = newArr;
		// adds each element in the old array to the new array and rehashes each
		for (int i = 0; i < oldCap; i++) {
			for (int j = 0; j < oldArr[i].size(); j++) {
				if (put(((KeyValuePair<KeyType, ValueType>) oldArr[i].get(j)).key(),
						((KeyValuePair<KeyType, ValueType>) oldArr[i].get(j)).value()) == false)
					System.out.println("error resizing at list " + i + " and index " + j);
			}
		}
	}
	
	public int capacity() {
		// returns the capacity of the array (testing purposes only)
		return this.capacity;
	}

}
