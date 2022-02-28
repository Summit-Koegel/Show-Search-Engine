import java.util.List;

public class FrontendDeveloperTests {
    
    /**
     * Tests runCommandLoop to see if program follows the correct procedures and 
     * returns the correct output
     * If certain phrases from the GUI are not printed the tester will check
     * and return false
     * Return true if GUI matches the exact output of the ShowSearcherFrontend
     * method
     * @return T/F
     */
    public static boolean test1(){
        //These 3 lines apply to all the following tester methods
        ShowSearcherBackendPH backend = new ShowSearcherBackendPH(); // Constructs backend for implementation
        // Constructs frontend with "q" as default for quitting the while loop
        ShowSearcherFrontend frontend = new ShowSearcherFrontend("q\n", backend);
        //Constructs UI tester with no input
        TextUITester testerUI = new TextUITester("");

        frontend.runCommandLoop(); // Run method
        String OP = testerUI.checkOutput(); // Store output

        //Check output with that that should have printed
        if(!OP.startsWith("Welcome to the Show Searcher App!")
            || !OP.contains("================================")
            || !OP.contains("1) Search by [T]itle Word")
            || !OP.contains("2) Search by [Y]ear First Produced")
            || !OP.contains("3) [F]ilter by Streaming Provider")
            || !OP.contains("4) [Q]uit")
            || !OP.contains("Choose a command from the menu above: "))
            {
                return false;
            }

        return true;

        
    }

    
    /**
     * Tests toggleFilter to see if program follows the correct procedures and 
     * returns the correct output
     * If certain phrases from the GUI are not printed the tester will check
     * and return false
     * Return true if GUI matches the exact output of the ShowSearcherFrontend
     * method
     * @return T/F
     */
    public static boolean test2(){
        ShowSearcherBackendPH backend = new ShowSearcherBackendPH();
        ShowSearcherFrontend frontend = new ShowSearcherFrontend("q\n", backend);
        TextUITester testerUI = new TextUITester("");

        frontend.toggleFilter(); // Run method
        String OP = testerUI.checkOutput(); // Store output

        //Check output with that that should have printed
        if(!OP.startsWith("Providers that shows are being searched for:")
            || !OP.contains("1) _x_ [N]eflix")
            || !OP.contains("2) _x_ [H]ulu")
            || !OP.contains("3) _x_ [P]rime Video")
            || !OP.contains("4) _x_ [D]isney+")
            || !OP.contains("5) [Q]uit toggling provider filters")
            || !OP.contains("Choose the provider that you'd like to toggle the filter for: "))
            {
                return false;
            }

        return true;

    }
    
    /**
     * Tests displayShows to see if program follows the correct procedures and 
     * returns the correct output
     * If certain phrases from the GUI are not printed the tester will check
     * and return false
     * Return true if GUI matches the exact output of the ShowSearcherFrontend
     * method
     * @return T/F
     */
    public static boolean test3(){
        ShowSearcherBackendPH backend = new ShowSearcherBackendPH();
        ShowSearcherFrontend frontend = new ShowSearcherFrontend("q\n", backend);
        TextUITester testerUI = new TextUITester("");

        List<IShow> list = List.of(
            new ShowPH2("Breaking Bad", 2008, 100, "Netflix"), 
            new ShowPH2("Hang Ups", 2018, 59, "Hulu"),
            new ShowPH2("Titanic", 2012, 55, "Prime Video"),
            new ShowPH2("Dino Ranch", 2021, 42, "Disney+"));

        frontend.displayShows(list); // Run method
        String OP = testerUI.checkOutput(); // Store output

        //Check output with that that should have printed
        if(!OP.startsWith("Found") 
            || !OP.contains("100/100 (2008) on: Netflix")
            || !OP.contains("2. Hang Ups")
            || !OP.contains("59/100 (2018) on: Hulu")
            || !OP.contains("4. Dino Ranch")
            || !OP.contains("42/100 (2021) on: Disney+")){
                return false;
            }

        return true;

    }
    

    /**
     * Tests titleSearch to see if program follows the correct procedures and 
     * returns the correct output
     * If certain phrases from the GUI are not printed the tester will check
     * and return false
     * Return true if GUI matches the exact output of the ShowSearcherFrontend
     * method
     * @return T/F
     */
    public static boolean test4(){
        ShowSearcherBackendPH backend = new ShowSearcherBackendPH();
        ShowSearcherFrontend frontend = new ShowSearcherFrontend("q\n", backend);
        TextUITester testerUI = new TextUITester("");

        frontend.titleSearch(); // Run method
        String OP = testerUI.checkOutput(); // Store output

        //Check output with that that should have printed
        if(!OP.startsWith("Found") 
            || !OP.contains("100/100 (2008) on: Netflix")
            || !OP.contains("2. Hang Ups")
            || !OP.contains("59/100 (2018) on: Hulu")
            || !OP.contains("4. Dino Ranch")
            || !OP.contains("42/100 (2021) on: Disney+")){
                return false;
            }
            
        return true;
    }

    /**
     * Tests yearSearch to see if program follows the correct procedures and 
     * returns the correct output
     * If certain phrases from the GUI are not printed the tester will check
     * and return false
     * Return true if GUI matches the exact output of the ShowSearcherFrontend
     * method
     * @return T/F
     */
    public static boolean test5(){
        ShowSearcherBackendPH backend = new ShowSearcherBackendPH();
        ShowSearcherFrontend frontend = new ShowSearcherFrontend("2018\n", backend); //Different input: year
        TextUITester testerUI = new TextUITester("");

        frontend.yearSearch(); // Run method
        String OP = testerUI.checkOutput();// Store output

        //Check output with that that should have printed
        if(!OP.startsWith("Found") 
            || !OP.contains("100/100 (2008) on: Netflix")
            || !OP.contains("2. Hang Ups")
            || !OP.contains("59/100 (2018) on: Hulu")
            || !OP.contains("4. Dino Ranch")
            || !OP.contains("42/100 (2021) on: Disney+")){
                return false;
            }
            
        return true;
    }
    

    public static void main(String args[]){
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());
    }
}
