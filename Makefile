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

clean:
	rm *.class
