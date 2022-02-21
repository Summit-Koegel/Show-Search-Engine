import java.util.LinkedList;
import java.util.List;

public class HashTableSortedSets<KeyType, ValueType extends Comparable<ValueType>>
		extends HashtableMap<KeyType, List<ValueType>> {

	public HashTableSortedSets(int capacity) {
		super(capacity);
	}

	public HashTableSortedSets() {
		super();
	}

	public void add(KeyType key, ValueType value) {
		// create new list for key if needed, otherwise add value
		if (!super.containsKey(key)) {
			super.put(key, new LinkedList<ValueType>());
			super.get(key).add(value);
		} else {
			super.get(key).add(value);
			super.size++;
		}
		// sort the list via selection sort
		ValueType smallest = null;
		int indexSmallest = 0;
		// loop through array, moving smallest value to correct position each time
		for (int j = 0; j < super.get(key).size(); j++) {
			// reset smallest value
			smallest = null;
			// get smallest value in unsorted list
			for (int i = j; i < super.get(key).size(); i++) {
				if (smallest == null) {
					// set smallest value to current if it is the first value looked at
					smallest = super.get(key).get(i);
					indexSmallest = i;
				} else if (smallest.compareTo(super.get(key).get(i)) > 0) {
					// set the smallest value to the current value being compared if it is less than
					// the current smallest
					smallest = super.get(key).get(i);
					indexSmallest = i;
				}
			}
			// swap smallest value with the value at desired position
			ValueType current = super.get(key).get(j);
			super.get(key).set(j, smallest);
			super.get(key).set(indexSmallest, current);
		}

	}

}
