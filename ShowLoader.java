import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
/**
 * Instances of classes that implement this interface can be used to load a
 * list of shows from a specified csv source file.
 * The following csv columns are used to load these show attributes:
 *   - Title: the complete title for a show
 *   - Year: the year that the show was first produced
 *   - Rotten Tomatoes: the review score (out of 100) for this show
 *   - Netflix: 1 = available on this service, other wise 0
 *   - Hulu: 1 = available on this service, other wise 0
 *   - Prime Video: 1 = available on this service, other wise 0
 *   - Disney+: 1 = available on this service, other wise 0
 */
public class ShowLoader implements IShowLoader {
  /**
   * This method loads the list of songs described within a CSV file.
   *
   * @param filepath is relative to executable's working directory
   * @return a list of show objects that were read from specified file
   */
  public List<IShow> loadShows(String filepath) throws FileNotFoundException {
    ArrayList<IShow> shows = new ArrayList<IShow>();
    File csv = new File(filepath);
    if (!csv.exists())
      throw new FileNotFoundException("No such file exists");
    Scanner sc = new Scanner(csv);
    //Skips headers
    sc.nextLine();
    while (sc.hasNext()) {
      try{
        String[] showData = sc.nextLine().split(",");
        String providers = "";
        if (showData[7].equals("1"))
          providers += "netflix ";
        if (showData[8].equals("1"))
          providers += "hulu ";
        if (showData[9].equals("1"))
          providers += "prime ";
        if(showData[10].equals("1"))
          providers += "disney+";
	if (showData[6].equals("100/100"))
          showData[6] = showData[6].substring(0, 3);
        else if (showData[6].charAt(1) == '/')
          showData[6] = showData[6].substring(0, 1);
        else
          showData[6] = showData[6].substring(0, 2); 
        Show temp = new Show(showData[2], Integer.parseInt(showData[3]),
            Integer.parseInt(showData[6]), providers);
        shows.add(temp);
      } catch (NumberFormatException | StringIndexOutOfBoundsException ignored){
      }


    }
    return shows;
  }
}

