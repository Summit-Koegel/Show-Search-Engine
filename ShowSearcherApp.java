import java.util.List;

public class ShowSearcherApp {
    public static void main(String[] args) throws Exception {
        IShowLoader loader = new ShowLoader();
        List<IShow> shows = loader.loadShows("tv_shows.csv");
        IShowSearcherBackend backend = new ShowSearcherBackend();
        for(IShow show : shows) backend.addShow(show);
        IShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
        frontend.runCommandLoop();
    }
}
