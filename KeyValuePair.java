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
