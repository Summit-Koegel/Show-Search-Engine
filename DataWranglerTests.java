import java.io.FileNotFoundException;
import java.util.List;

// --== CS400 Project NUMBER File Header ==--
// Name: <your full name>
// CSL Username: <your csl username>
// Email: <your @wisc.edu email address>
// Lecture #: 004
// Notes to Grader: <any optional extra notes to your grader>
public class DataWranglerTests {
  public static void main(String[] args) {
    System.out.println("Show constructor: " + test1());
    System.out.println("Show compareTo: " + test2());
    System.out.println("Show availableOn: " + test3());
    System.out.println("Showloader error handling: " + test4());
    System.out.println("Showloader shows: " + test5());
    System.out.println("ShowLoader into Backend: " + test6());
    System.out.println("Backend searches with showLoader: " + test7());
  }

  /**
   * Tests show constructor
   * @return true if constructor works as expected, false otherwise;
   */
  public static boolean test1(){
    Show a = new Show("Hello", 2008, 92, "prime video");
    if (a.getRating() != 92)
      return false;
    if (!a.getTitle().equals("Hello"))
      return false;
    if (a.getYear() != 2008)
      return false;
    return true;
  }

  /**
   * Checks show compareTo
   * @return true if correct, false otherwise
   */
  public static boolean test2(){
    Show a = new Show("Hello", 2008, 92, "prime video");
    Show b = new Show("World", 2010, 95, "netflix");
    if (a.compareTo(b) != -3)
      return false;
    return true;
  }

  /**
   * Tests show availableOn
   * @return
   */
  public static boolean test3(){
    Show a = new Show("Hello", 2008, 90, "prime video");
    if (!a.isAvailableOn("Prime"))
      return false;
    if (a.isAvailableOn("netflix"))
      return false;
    return true;
  }

  /**
   * Tests incorrect usage of showloader
   * @return true if showloader throws error when it should, false otherwise
   */
  public static boolean test4(){
    ShowLoader shows = new ShowLoader();
    try{
      shows.loadShows("/data/tester");
    } catch (FileNotFoundException correct){
    } catch (Exception e){
      return false;
    }
    try{
      List<IShow> test = shows.loadShows("./tv_shows.csv");
    } catch (FileNotFoundException correct) {
      return false;
    }

    return true;
  }

  /**
   * Tests if showloader correctly loads in show objects
   * @return true if correct implementations, false otherwise
   */
  public static boolean test5() {
    ShowLoader a = new ShowLoader();
    try{
      List<IShow> shows = a.loadShows("./tv_shows.csv");
      if (!shows.get(1).getTitle().equals("Stranger Things"))
        return false;
      if (shows.get(1).getYear() != 2016)
        return false;
      if (shows.get(1).getRating() != 96)
        return false;
      if (!shows.get(1).isAvailableOn("NeTFlIx"))
        return false;
      if (shows.get(1).isAvailableOn("prime"))
        return false;
      for (IShow show : shows) {
        if (show.getRating() < 0 || show.getRating() > 100)
          return false;
        if (show.isAvailableOn("peacock"))
          return false;
     }
    } catch (Exception e){
      return false;
    }
    return true;
  }

  /**
   * Tests edge cases for rating in showLoader and checks if backend adds it
   * @return True if edge cases handled, false otherwise
   */
  public static boolean test6() {
    ShowLoader a = new ShowLoader();
    try {
      List<IShow> shows = a.loadShows("./tv_shows.csv");
      //Breaking bad
      if (shows.get(0).getRating() != 100)
        return false;
      if (shows.get(226).getRating() != 73)
        return false;
      ShowSearcherBackend test = new ShowSearcherBackend();
      test.addShow(shows.get(0));
      if (test.getNumberOfShows() != 1){
	      System.out.println("Incorrect number of shows in backend");
	      return false;
      }
      if (!test.searchByTitleWord("Breaking").get(0).getTitle().equals("Breaking Bad")){
	      System.out.println("Incorrect return by searchByTitle");
	      return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * Tests if backend correctly implements searchByYear and searchByTitleWord
   * @return True if return as expected, false otherwise
   */
  public static boolean test7() {
    ShowLoader a = new ShowLoader();
    try {
      List<IShow> shows = a.loadShows("./tv_shows.csv");
      ShowSearcherBackend test = new ShowSearcherBackend();
      test.addShow(shows.get(0));
      if (!test.searchByTitleWord("Breaking").get(0).getTitle().equals("Breaking Bad")){
              System.out.println("Incorrect return by searchByTitle");
              return false;
      }
      if (!test.searchByYear(2008).get(0).getTitle().equals("Breaking Bad")){
	      System.out.println("Incorrect return by searchByYear");
	      return false;
      }
    } catch (Exception e) {
      return false;
    }
    return true;
  }
}

