runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java HashTableSortedSets.class
	javac AlgorithmEngineerTests.java

HashTableSortedSets.class: HashTableSortedSets.java IHashTableSortedSets.class HashtableMap.class
	javac HashTableSortedSets.java

IHashTableSortedSets.class: IHashTableSortedSets.java
	javac IHashTableSortedSets.java

HashtableMap.class: HashtableMap.java
	javac HashtableMap.java

clean:
	rm *.class
