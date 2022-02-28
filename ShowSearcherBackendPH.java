import java.util.ArrayList;
import java.util.List;


/**
 * Hardcoded backend implementation to provide the front end with
 * something to produce
 */
public class ShowSearcherBackendPH implements IShowSearcherBackend{

    //Availability
    private boolean isNetflix;
    private boolean isHulu;
    private boolean isPrime;
    private boolean isDisney;

    //List of shows from each provider
    List<IShow> list = List.of(
            new ShowPH2("Breaking Bad", 2008, 100, "Netflix"), 
            new ShowPH2("Hang Ups", 2018, 59, "Hulu"),
            new ShowPH2("Titanic", 2012, 55, "Prime Video"),
            new ShowPH2("Dino Ranch", 2021, 42, "Disney+"));

    /**
     * Add a show to the list
     */
    @Override
    public void addShow(IShow show) {
        list.add(show);
    }

    /**
     * Returns number of shows in list
     */
    @Override
    public int getNumberOfShows() {
        return list.size();
    }

    /**
     * Sets the boolean value of each provider for use when
     * determining if they support certain shows
     */
    @Override
    public void setProviderFilter(String provider, boolean filter) {

        if(provider == "Netflix")
            isNetflix = filter;

        if(provider == "Hulu")
            isHulu = filter;

        if(provider == "Prime Video")
            isPrime = filter;
        
        if(provider == "Disney+")
            isDisney = filter;
    }

    /**
     * Retrieves provider availability
     */
    @Override
    public boolean getProviderFilter(String provider) {
        if(provider == "Netflix")
            return isNetflix;
        
        if(provider == "Hulu")
            return isHulu;

        if(provider == "Prime Video")
            return isPrime;
        
        if(provider == "Disney+")
            return isDisney;

        return false;

    }

    /**
     * Changes which provider the user wants to see
     */
    @Override
    public void toggleProviderFilter(String provider) {
        if(provider == "Netflix")
            isNetflix = !isNetflix;

        if(provider == "Hulu")
            isHulu = !isHulu;

        if(provider == "Prime Video")
            isPrime = !isPrime;

        if(provider == "Disney+")
            isDisney = !isDisney;

    }

    /** 
     * Hardcoded implementation of list that would
     * be produced when searching by title
     */
    @Override
    public List<IShow> searchByTitleWord(String word) {
        return list;
    }

    /** 
     * Hardcoded implementation of list that would
     * be produced when searching by title
     */
    @Override
    public List<IShow> searchByYear(int year) {
        return list;
    }
    
}
