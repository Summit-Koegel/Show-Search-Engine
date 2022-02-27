
runFrontendDeveloperTests: FrontendDeveloperTests.class
	java FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java ShowSearcherFrontend.class
	javac FrontendDeveloperTests.java

ShowSearcherFrontend.class: ShowSearcherFrontend.java IShowSearcherFrontend.class ShowSearcherBackend.class Show.class
	javac ShowSearcherFrontend.java

ShowSearcherBackend.class: ShowSearcherBackend.java IShowSearcherBackend.class ShowSearcherFrontend.class Show.class
	javac ShowSearcherBackend.java

Show.class: Show.java IShow.class
	javac Show.java

IShowSearcherFrontend.class: IShowSearcherFrontend.java
	javac IShowSearcherFrontend.java

IShowSearcherBackend.class: IShowSearcherBackend.java
	javac IShowSearcherBackend.java

IShow.class: IShow.java
	javac IShow.java

run: ShowSearcherApp.class
	java ShowSearcherApp.class

runTests: runAlgorithmEngineerTests runFrontendDeveloperTests runBackendDeveloperTests

runAlgorithmEngineerTests: AlgorithmEngineerTests.class
	java AlgorithmEngineerTests

AlgorithmEngineerTests.class: AlgorithmEngineerTests.java HashTableSortedSets.class
	javac AlgorithmEngineerTests.java

HashTableSortedSets.class: HashTableSortedSets.java IHashTableSortedSets.class HashtableMap.class
	javac HashTableSortedSets.java

runBackendDeveloperTests: BackendDeveloperTests.class
	java BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java HashTableSortedSetsPlacehold.class IHashTableSortedSets.class IShow.class IShowSearcherBackend.class MapADT.class ShowPH.class ShowSearcherApp.class ShowSearcherBackend.class
	javac BackendDeveloperTests.java

ShowSearcherBackend.class: ShowSearcherBackend.java HashTableSortedSetsPlacehold.class IHashTableSortedSets.class IShow.class IShowSearcherBackend.class MapADT.class ShowPH.class ShowSearcherApp.class ShowSearcherBackend.class
	javac ShowSearcherBackend.java

ShowPH.class: ShowPH.java IShow.class
	javac ShowPH.java
HashTableSortedSetsPlacehold.class: HashTableSortedSetsPlacehold.java IHashTableSortedSets.class
	javac HashTableSortedSetsPlacehold.java

IHashTableSortedSets.class: IHashTableSortedSets.java
	javac IHashTableSortedSets.java

HashtableMap.class: HashtableMap.java
	javac HashtableMap.java

IShowSearcherBackend.class: IShowSearcherBackend.java
	javac IShowSearcherBackend.java

MapADT.class: MapADT.java
	javac MapADT.java
ShowSearcherApp.class: ShowSearcherApp.java IShowSearcherFrontend.class IShowLoader.class IShow.class IShowSearcherBackend.class 
	javac ShowSearcherApp.java
IShowLoader.class: IShowLoader.java IShow.class 
	javac IShowLoader.java
IShowSearcherFrontend.class: IShowSearcherFrontend.java
	javac IShowSearcherFrontend.java


clean:
	rm *.class

