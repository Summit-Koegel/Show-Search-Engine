runTests: BackendDeveloperTests.class
	java BackendDeveloperTests

BackendDeveloperTests.class: BackendDeveloperTests.java HashTableSortedSetsPlacehold.class IHashTableSortedSets.class IShow.class IShowSearcherBackend.class MapADT.class ShowPH.class ShowSearcherApp.class ShowSearcherBackend.class
	javac BackendDeveloperTests.java

ShowSearcherBackend.class: ShowSearcherBackend.java HashTableSortedSetsPlacehold.class IHashTableSortedSets.class IShow.class IShowSearcherBackend.class MapADT.class ShowPH.class ShowSearcherApp.class ShowSearcherBackend.class
	javac ShowSearcherBackend.java

ShowPH.class: ShowPH.java IShow.class
	javac ShowPH.java
IShow.class: IShow.java
	javac IShow.java
HashTableSortedSetsPlacehold.class: HashTableSortedSetsPlacehold.java IHashTableSortedSets.class
	javac HashTableSortedSetsPlacehold.java

IHashTableSortedSets.class: IHashTableSortedSets.java
	javac IHashTableSortedSets.java

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


	
