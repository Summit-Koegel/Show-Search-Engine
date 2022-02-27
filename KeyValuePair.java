// --== CS400 Project One File Header ==--
// Name: Corey Johnsen
// CSL Username: johnsen
// Email: cjjohnsen@wisc.edu
// Lecture #: 004 @4:00pm
// Notes to Grader: N/A

public class KeyValuePair <KeyType, ValueType> {

	private KeyType key;
	private ValueType value;
	
	public KeyValuePair(KeyType key, ValueType value) {
		// sets the key and value in this pair
		this.key = key;
		this.value = value;
	}
	
	public KeyType key() {
		// returns the key
		return key;
	}
	
	public ValueType value() {
		// returns the value
		return value;
	}
	
}