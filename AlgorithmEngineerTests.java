import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class AlgorithmEngineerTests {

	public static void main(String[] args) {
		// display all test results
		System.out.println("Constructor and size passed: " + test1());
		System.out.println("Put and remove passed: " + test2());
		System.out.println("Get and add passed: " + test3());
		System.out.println("Contains key passed: " + test4());
		System.out.println("Clear passed: " + test5());
	}
	
	// test constructor and size
	public static boolean test1() {
		try {
			// test default constructor
			HashTableSortedSets<Integer, Integer> table = new HashTableSortedSets<Integer, Integer>();
			if(table.size() != 0)
				return false;
			// test valid constructor
			table = new HashTableSortedSets<Integer, Integer>(15);
			if(table.size() != 0)
				return false;
			// test invalid constructor
			table = new HashTableSortedSets<Integer, Integer>(-5);
			if(table.size() != 0)
				return false;
			// test changes in size
			table.add(5, 10);
			if(table.size() != 1)
				return false;
			table.add(5, 15);
			if(table.size() != 2)
				return false;
		} catch (Exception e) {
			// invalid exception
			return false;
		}
		// all tests passed
		return true;
	}
	
	// test put and remove
	public static boolean test2() {
		try {
			HashTableSortedSets<Integer, Integer> table = new HashTableSortedSets<Integer, Integer>();
			// test putting in range of array
			if(!table.put(5, new LinkedList<Integer>())) {
				return false;
			}
			// test putting outside range
			if(!table.put(5000, new LinkedList<Integer>())) {
				return false;
			}
			// try putting with duplicate key
			if (table.put(5, new LinkedList<Integer>()))
				return false;
			// try putting in same position but different key
			if (!table.put(15, new LinkedList<Integer>()))
				return false;
			// add 3 more keys to overcome load factor, check capacity
			table.put(7, new LinkedList<Integer>());
			table.put(4, new LinkedList<Integer>());
			table.put(3, new LinkedList<Integer>());
			table.put(19, new LinkedList<Integer>());
			if (table.size() != 7)
				return false;
			// test removing
			if(!table.remove(5).equals(new LinkedList<Integer>())) {
				return false;
			}
			if(!table.remove(5000).equals(new LinkedList<Integer>())) {
				return false;
			}
			// test removing key not in table
			if(table.remove(42) != null) {
				return false;
			}
		} catch (Exception e) {
			// invalid exception
			return false;
		}
		return true;
	}
	
	// test get and add
	public static boolean test3() {
		try {
			HashTableSortedSets<Integer, Integer> table = new HashTableSortedSets<Integer, Integer>();
			// test adding first value
			table.add(1, 100);
			if(table.size() != 1)
				return false;
			// test adding duplicate key
			table.add(1, 400);
			if(table.size() != 2)
				return false;
			// test adding different key
			table.add(3, 400);
			if(table.size() != 3)
				return false;
			// add more values for testing
			table.add(1, 200);
			table.add(1, 150);
			// test getting key with one value
			LinkedList<Integer> expected = new LinkedList<Integer>();
			expected.add(400);
			if(!table.get(3).equals(expected))
				return false;
			// test getting key with multiple values
			expected.clear();
			expected.add(100);
			expected.add(150);
			expected.add(200);
			expected.add(400);
			if(!table.get(1).equals(expected))
				return false;
		} catch (Exception e) {
			// invalid exception
			return false;
		}
		// test getting for a key not in table
		try {
			HashTableSortedSets<Integer, Integer> table = new HashTableSortedSets<Integer, Integer>();
			table.get(0);
			return false;
		} catch (NoSuchElementException e) {
			// expected, do nothing
		} catch (Exception e) {
			// unexpected exception
			return false;
		}
		return true;
	}
	
	// test containsKey
	public static boolean test4() {
		try {
			// make table for testing
			HashTableSortedSets<Integer, Integer> table = new HashTableSortedSets<Integer, Integer>();
			table.add(1, 10);
			table.add(4, 400);
			table.add(400, 90);
			// test contains on keys in table
			if(!table.containsKey(1) || !table.containsKey(4) || !table.containsKey(400))
				return false;
			// test contains on key not in table
			if(table.containsKey(2))
				return false;
		} catch (Exception e) {
			// unexpected exception
			return false;
		}
		return true;
	}
	
	// test clear
	public static boolean test5() {
		try {
			// make table for testing
			HashTableSortedSets<Integer, Integer> table = new HashTableSortedSets<Integer, Integer>();
			table.add(1, 10);
			table.add(4, 400);
			table.add(400, 90);
			// test clear on table with values
			table.clear();
			if(table.size() != 0)
				return false;
			// test clear on empty table
			table.clear();
			if(table.size() != 0)
				return false;
		} catch (Exception e) {
			// unexpected exception
			return false;
		}
		return true;
	}

}
