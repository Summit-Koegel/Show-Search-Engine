runTests: Show ShowLoader DataWranglerTests.java
	javac DataWranglerTests.java

Show: Show.java
	javac Show.java

ShowLoader: ShowLoader.java
	javac ShowLoader.java

clean:
	rm *.class
