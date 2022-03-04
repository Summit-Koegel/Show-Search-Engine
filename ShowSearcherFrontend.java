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

    private char space1 = 'x';
    private char space2 = 'x';
    private char space3 = 'x';
    private char space4 = 'x';


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

        //Checks command from command menu and sends you to appropriate method
        while(count == 0){

		displayCommandMenu();

       		String choice1 = scan.nextLine();

		if(choice1.equals("")){
			choice1 = scan.nextLine();
		}

            if(choice1.equals("1") || choice1.toLowerCase().equals("t")){
                System.out.print("Choose a word that you would like to search for: ");
                titleSearch();
            }
    
	    else if(choice1.equals("2") || choice1.toLowerCase().equals("y")){
                System.out.print("Choose a year that you would like to search for: ");
                yearSearch();
            }
    
	    else if(choice1.equals("3") || choice1.toLowerCase().equals("f")){
                toggleFilter();
            }
   
	    else if(choice1.equals("4") || choice1.toLowerCase().equals("q")){
                count++;
                System.out.println();
            }
    
            else{
                System.out.println("That is not a valid input.");	
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

    public void toggleHelper(){

        System.out.println("Providers that shows are being searched for:");

        System.out.println("1) _" + space1 + "_ [N]eflix");
        System.out.println("2) _" + space2 + "_ [H]ulu");
        System.out.println("3) _" + space3 + "_ [P]rime Video");
        System.out.println("4) _" + space4 + "_ [D]isney+");
        System.out.println("5) [Q]uit toggling provider filters");

        System.out.print("Choose the provider that you'd like to toggle the filter for: ");
    }

    /**
     * Toggles the "x" on and off for which provider you want to see
     */
    public void toggleFilter(){

	toggleHelper();

	String choice = scan.nextLine();
	
		
   	//Sets provider on or off depending on user input
        while(!(choice.equals("5")) && !(choice.toLowerCase().equals("q"))){
            if(choice.equals("1") || choice.toLowerCase().equals("n")){
                if(space1 == '_')
			space1 = 'x';
		else
			space1 = '_';
                backend.setProviderFilter("Netflix", false);
                toggleHelper();
		choice = scan.nextLine();
            }
    
            else if(choice.equals("2") || choice.toLowerCase().equals("h")){
                if(space2 == '_')
                        space2 = 'x';
                else
                        space2 = '_';
                backend.setProviderFilter("Hulu", false);
                toggleHelper();
		choice = scan.nextLine();
            }
    
            else if(choice.equals("3") || choice.toLowerCase().equals("p")){
                if(space3 == '_')
                        space3 = 'x';
                else
                        space3 = '_';
                backend.setProviderFilter("Prime Video", false);
                toggleHelper();
		choice = scan.nextLine();
            }
    
            else if(choice.equals("4") || choice.toLowerCase().equals("d")){
                if(space4 == '_')
                        space4 = 'x';
                else
                        space4 = '_';
                backend.setProviderFilter("Disney+", false);
                toggleHelper();
		choice = scan.nextLine();
            }
    
            else if(choice.equals("5") || choice.toLowerCase().equals("q")){
                break;
            }

            else{
                System.out.println("That is not a valid input.");
                toggleHelper();
		choice = scan.nextLine();
            }
        }
    }
    /**
	}	
     * Displays the formatted shows based on user input and each shows
     * various specifications
     */
    @Override
    public void displayShows(List<IShow> shows) {

        System.out.println("Found " + shows.size() + "/" + backend.getNumberOfShows() +  " matches.");

        for(int i = 0; i < shows.size(); i++){
            System.out.print((i+1) + ". " + shows.get(i).getTitle() + "\n\t" + shows.get(i).getRating()
             + "/100 (" + shows.get(i).getYear() + ") on: ");

             if((shows.get(i).isAvailableOn("Netflix"))){
                System.out.print("Netflix ");
            }
    
            if(shows.get(i).isAvailableOn("Hulu")){
                System.out.print("Hulu ");
            }
    
            if(shows.get(i).isAvailableOn("Prime Video")){
                System.out.print("Prime Video ");
            }
    
            if(shows.get(i).isAvailableOn("Disney+")){
                System.out.print("Disney+ ");
            }

	    System.out.println();
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
