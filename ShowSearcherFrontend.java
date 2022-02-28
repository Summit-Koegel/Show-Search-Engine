import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

/**
 * Class that constructs the front end GUI interface for a the Show Searcher App
 */
public class ShowSearcherFrontend implements IShowSearcherFrontend{

    private Scanner scan; // Scanner that takes in input
    private IShowSearcherBackend backend; // Instance of backend
    String input;

    /**
     * Constructor that uses backend instance and makes scanner
     * @param backend - Instance of backend
     */
    public ShowSearcherFrontend(IShowSearcherBackend backend){
        this.backend = backend;
        this.scan = new Scanner(System.in);
    }

    /**
     * Second constructor for displaying information from TextUITester
     * @param input - string input of user
     * @param backend
     */
    public ShowSearcherFrontend(String input, IShowSearcherBackend backend){
        this.input = input;
        this.backend = backend;
        this.scan = new Scanner(new ByteArrayInputStream(input.getBytes())); // Scanner for TextUITester
    }

    /**
     * Starting message of searcher
     */
    public void message(){
        System.out.println("Welcome to the Show Searcher App!");
        System.out.println("================================");
    }
    

    /**
     * While loop that controls the flow of the program
     */
    @Override
    public void runCommandLoop() {

        int count = 0; // Controls while loop

        message();

        displayCommandMenu();

        String choice = scan.nextLine();

        //Checks command from command menu and sends you to appropriate method
        while(count == 0){
            if(choice.equals("1") || choice.toLowerCase().equals("t")){
                System.out.print("Choose a word that you would like to search for: ");
                titleSearch();
            }
    
            if(choice.equals("2") || choice.toLowerCase().equals("y")){
                System.out.print("Choose a year that you would like to search for: ");
                yearSearch();
            }
    
            if(choice.equals("3") || choice.toLowerCase().equals("f")){
                toggleFilter();
            }
    
            if(choice.equals("4") || choice.toLowerCase().equals("q")){
                count++;
                System.out.println();
            }
    
            else{
                System.out.println("That is not a valid input.");
                displayCommandMenu();
            }
        }
        
        
        
        
    }

    /**
     * Displays the command menu and its options
     */
    @Override
    public void displayCommandMenu() {

        System.out.println("Command Menu:");
        System.out.println("\t1) Search by [T]itle Word");
        System.out.println("\t2) Search by [Y]ear First Produced");
        System.out.println("\t3) [F]ilter by Streaming Provider");
        System.out.println("\t4) [Q]uit");
        System.out.print("Choose a command from the menu above: ");

    }

    /**
     * Toggles the "x" on and off for which provider you want to see
     */
    public void toggleFilter(){

        int count = 0;

        String space1 = null;
        String space2 = null;
        String space3 = null;
        String space4 = null;

        space1 = "x";
        space2 = "x";
        space3 = "x";
        space4 = "x";
        
        

        System.out.println("Providers that shows are being searched for:");

        System.out.println("1) _" + space1 + "_ [N]eflix");
        System.out.println("2) _" + space2 + "_ [H]ulu");
        System.out.println("3) _" + space3 + "_ [P]rime Video");
        System.out.println("4) _" + space4 + "_ [D]isney+");
        System.out.println("5) [Q]uit toggling provider filters");

        System.out.println("Choose the provider that you'd like to toggle the filter for: ");

	String choice = scan.nextLine();

        //Sets provider on or off depending on user input
        if(choice == "1" || choice.toLowerCase() == "n"){
            space1 = "_";
            //count++;
            backend.setProviderFilter("Netflix", false);
            toggleFilter();
        }

        if(choice == "2" || choice.toLowerCase() == "h"){
            space2 = "_";
            count++;
            backend.setProviderFilter("Hulu", false);
            toggleFilter();
        }

        if(choice == "3" || choice.toLowerCase() == "p"){
            space3 = "_";
            count++;
            backend.setProviderFilter("Rrime Video", false);
            toggleFilter();
        }

        if(choice == "4" || choice.toLowerCase() == "d"){
            space4 = "_";
            count++;
            backend.setProviderFilter("Disney+", false);
            toggleFilter();
        }

        if(choice == "5" || choice.toLowerCase() == "q"){
            displayCommandMenu();
        }
        
    }

    /**
     * Displays the formatted shows based on user input and each shows
     * various specifications
     */
    @Override
    public void displayShows(List<IShow> shows) {

        System.out.println("Found " + backend.getNumberOfShows() + "/5368 matches.");

        for(int i = 0; i < shows.size(); i++){
            System.out.print((i+1) + ". " + shows.get(i).getTitle() + "\n\t" + shows.get(i).getRating()
             + "/100 (" + shows.get(i).getYear() + ") on: ");

             if((shows.get(i).isAvailableOn("Netflix"))){
                System.out.println("Netflix ");
            }
    
            if(shows.get(i).isAvailableOn("Hulu")){
                System.out.println("Hulu ");
            }
    
            if(shows.get(i).isAvailableOn("Prime Video")){
                System.out.println("Prime Video ");
            }
    
            if(shows.get(i).isAvailableOn("Disney+")){
                System.out.println("Disney+ ");
            }
        }
        
    }

    /**
     * Produces shows based on user input title search
     */
    @Override
    public void titleSearch() {

        String choice = scan.nextLine();

        List<IShow> list = backend.searchByTitleWord(choice);
        displayShows(list);
    }

    /**
     * Produces shows based on user input year search
     */
    @Override
    public void yearSearch() {
        int choice = scan.nextInt();

        List<IShow> list = backend.searchByYear(choice);

        displayShows(list);
        
    }
    
}
