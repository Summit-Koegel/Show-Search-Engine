import java.util.ArrayList;
import java.util.List;

public class Show implements IShow{

    private String title;
    private int year;
    private int rating;
    private String providers;
    
    /**
     * Constructs a show with all of its attributes
     * @param title
     * @param year
     * @param rating
     * @param providers
     */
    public Show(String title, int year, int rating, String providers){
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.providers = providers;
    }

    /**
     * Compares ratings of two shows
     */
    @Override
    public int compareTo(IShow o) {
        return getRating() - o.getRating();
    }

    /**
     * Retrieves title of show
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves year of show
     */
    @Override
    public int getYear() {
        return year;
    }

    /**
     * Retrieves rating of show
     */
    @Override
    public int getRating() {
        return rating;
    }

    /**
     * Checks if show is available on certain providers
     */
    @Override
    public boolean isAvailableOn(String provider) {
        if(providers.contains(provider))
            return true;

        return false;

    }
    
}
