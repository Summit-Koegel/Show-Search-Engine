import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Tests ShowSearcherBackend
 * and 2 ShowSearcherFrontend tests
 * @author charlie jungwirth
 *
 */
public class BackendDeveloperTests {
	public static boolean test1() {//tests addShow and getNumberOfShows
		try {
			ShowSearcherBackend test = new ShowSearcherBackend();
			if(test.getNumberOfShows()!=0) return false;
			test.addShow(new ShowPH("good movie",1984,17));
			if(test.getNumberOfShows()!=1) return false;
			test.addShow(new ShowPH("sample",1492,43));
			if(test.getNumberOfShows()!=2) return false;
		}catch(Exception e) {
			return false;//unexpected exception
		}
		return true; 
	}
	public static boolean test2() {//test get show by year doesn't test if remove unstreamables, does test if order is correct
		try {
		ShowSearcherBackend test = new ShowSearcherBackend();
		test.setProviderFilter("Netflix",true);
		test.setProviderFilter("Hulu",true);
		test.setProviderFilter("Disney Plus",true);
		test.setProviderFilter("Prime Video",true);
		test.addShow(new ShowPH("Best Movie ever",1932,0));
		for(int i = 0; i< 100; i++) {
			test.addShow(new ShowPH(""+i,1932,(int)(Math.random()*101)));
		}
		test.addShow(new ShowPH("Best Movie ever",1932,100));
		
		for(int i = 0; i< 100; i++) {
			test.addShow(new ShowPH(""+i,1933+(int)(Math.random()*100),(int)(Math.random()*101)));
		}
		List<IShow> inTheY = test.searchByYear(1932);
		if(inTheY.size() !=102)return false;
		int min = 100;
		for(int i =0; i< inTheY.size(); i++) {
			if(inTheY.get(i).getRating()>min)return false;
			min = inTheY.get(i).getRating();//is sorted by year
			if(inTheY.get(i).getYear()!=1932)return false;
		}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean test3() {//test get show by name doesn't test if remove unstreamables, does test if order is correct
	
		try {
			ShowSearcherBackend test = new ShowSearcherBackend();
			test.setProviderFilter("Netflix",true);
			test.setProviderFilter("Hulu",true);
			test.setProviderFilter("Disney Plus",true);
			test.setProviderFilter("Prime Video",true);
			test.addShow(new ShowPH("Action",2002,0));
			for(int i = 0; i< 100; i++) {
				test.addShow(new ShowPH("Action",1900+i,(int)(Math.random()*101)));
			}
			test.addShow(new ShowPH("Action",2032,100));
			
			for(int i = 0; i< 100; i++) {
				test.addShow(new ShowPH(""+i,1933+(int)(Math.random()*100),(int)(Math.random()*101)));
			}
			List<IShow> inTheY = test.searchByTitleWord("Action");
			if(inTheY.size() !=102)return false;
			int min = 100;
			for(int i =0; i< inTheY.size(); i++) {
				if(inTheY.get(i).getRating()>min)return false;
				min = inTheY.get(i).getRating();//is sorted by year
				if(inTheY.get(i).getTitle()!="Action")return false;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	public static boolean test4() {//testing if services are toggled
		try {
			ShowSearcherBackend test = new ShowSearcherBackend();
			test.setProviderFilter("Netflix",true);
			test.setProviderFilter("Hulu",false);
			test.setProviderFilter("Disney Plus",false);
			test.setProviderFilter("Prime Video",false);
			//for the placeholder ShowPH when the title starts with "skip" the service won't
			//be provided, or else it just returns true, or if the year is 1337
			test.addShow(new ShowPH("skip this",2077,100));
			test.addShow(new ShowPH("now this",2077,3));
			test.addShow(new ShowPH("now this",1337,10));
			test.addShow(new ShowPH("skipping stones may break",2077,22));
			List<IShow> byWord =test.searchByTitleWord("this");
			List<IShow> byYr =test.searchByYear(2077);
			if(byWord.size()!=1) return false;
			
			if(byWord.get(0).getRating()!=3)return false;
				
				
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	public static boolean test5() { //tests to see if provider filter works
		try {
			ShowSearcherBackend test = new ShowSearcherBackend();
			test.setProviderFilter("Netflix",true);
			test.setProviderFilter("Hulu",false);
			test.setProviderFilter("Disney Plus",false);
			test.setProviderFilter("Prime Video",false);
			//btw for my place holder years under 0 only are on netflix
			//assuming they don't have the same qualities as test4
			test.addShow(new Show("only on netflix",-1984,35,"netflix"));
			test.addShow(new Show("netflix isn't chill",-2,44,"netflix"));
			if(test.searchByTitleWord("netflix").size()!=2)return false;
			test.setProviderFilter("Netflix",false);
			test.setProviderFilter("Hulu",true);
			test.setProviderFilter("Disney Plus",true);
			test.setProviderFilter("Prime Video",true);
			if(test.searchByTitleWord("netflix").size()!=0)return false;
		} catch(Exception e) {
			return false;
		}
		return true; 
		
	}
	
	public static boolean testFE1() {//Test if front end gets correct shows from backend 
		ShowSearcherBackend back = new ShowSearcherBackend();
		ShowSearcherFrontend front = new ShowSearcherFrontend("q\n",back);
		TextUITester uiTest = new TextUITester("");
		for(int i =0; i< 10; i++) {
			back.addShow(new Show("show"+i,2012,90+i,"disney+"));
		}
		
		front.displayShows(back.searchByYear(2012));
		String outP = uiTest.checkOutput();
		
		for(int i = 0; i< 10; i++) {
			if(outP.indexOf("show"+i)==-1)return false;
			if(outP.indexOf(""+(90+i))==-1)return false;//has all shows added to back
		}
		if(outP.indexOf("show9")>outP.indexOf("show8"))return false;//in right order
		
		return true;
		
	}
	public static boolean testFE2() {//displaying a large number of shows
		ShowSearcherBackend back = new ShowSearcherBackend();
		ShowSearcherFrontend front = new ShowSearcherFrontend("q\n",back);
		TextUITester uiTest =new TextUITester("");
		for(int i =0; i<700; i++) {
			back.addShow(new Show("Name "+i,i*10,15,"netflix"));
		}
		front.displayShows(back.searchByTitleWord("name"));
		String outP = uiTest.checkOutput();
		for(int i = 0; i< 700; i++) {
			if(outP.indexOf("Name "+i)==-1)return false;
		}
		
		
		return true;
	}
	
	public static boolean testBE1() {//is this case sensitive
		try {
	
			ShowSearcherBackend back = new ShowSearcherBackend();
			back.addShow(new Show("sOmeThi ###Oo",1984,36,"disney+"));
			back.addShow(new Show("SOMethi other",2001,99,"disney+"));
			List<IShow> shows = back.searchByTitleWord("SOMETHI");
			if(shows.size()!=2)return false;
			if(!shows.get(0).getTitle().equals("SOMethi other"))return false;
			if(!shows.get(1).getTitle().equals("sOmeThi ###Oo"))return false;
			shows = back.searchByTitleWord("somethi");
			if(shows.size()!=2)return false;
			if(!shows.get(0).getTitle().equals("SOMethi other"))return false;
			if(!shows.get(1).getTitle().equals("sOmeThi ###Oo"))return false;
			shows = back.searchByTitleWord("SoMetHI");
			if(shows.size()!=2)return false;
			if(!shows.get(0).getTitle().equals("SOMethi other"))return false;
			if(!shows.get(1).getTitle().equals("sOmeThi ###Oo"))return false;
			shows = back.searchByTitleWord("OTHER");
			if(shows.size()!=1)return false;
			if(!shows.get(0).getTitle().equals("SOMethi other"))return false;
			shows = back.searchByTitleWord("###oO");
			if(shows.size()!=1)return false;
			if(!shows.get(0).getTitle().equals("sOmeThi ###Oo"))return false;
			
			
			
		} catch(Exception e) {//unexpected
			return false;
		}
		
		return true;
		
	}
	public static boolean testBE2() {//tests searching for all words within
		try {
			String toBuild ="";
			ShowSearcherBackend back = new ShowSearcherBackend();
			for(int i =0; i<5;i++) {
				toBuild+="word"+i+" ";
			}
			toBuild = toBuild.strip();
			
			back.addShow(new Show(toBuild,1984,20,"netflix"));
			
			
			for(int i =0; i<5; i++) {
				List<IShow> searched = back.searchByTitleWord("word"+i);
				if(!searched.get(0).getTitle().equals(toBuild))return false;
			}
			
			toBuild = "repeat this repeat";// repeat repeat";
			back.addShow(new Show(toBuild,1986,25,"netflix"));
			List<IShow> searched = back.searchByTitleWord("repeat");
			if(searched.size()!= 1) {
				return false;
			}
			back.addShow(new Show("Go Dog Go",2021,44,"netflix"));
			searched = back.searchByTitleWord("go");
			if(searched.size()!= 1) return false;
			
		}catch(Exception e) {//unexpected
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static boolean runAllTests() {
		boolean success = true;
		//unideal to run each test twice but i am too tired, and doesn't matter enough
		System.out.println("running initial Backend Tests: ");
		System.out.println("1: add show and getNumberOfShows: "+test1());
		System.out.println("2: test search by year+sorted: "+test2());
		System.out.println("3: test search by word + sorted: "+test3());
		System.out.println("4: test if filters are toggled: "+test4());
		System.out.println("5: test provider filter: "+test5());
		success = test1()&&success;
		success = test2()&&success;
		success = test3()&&success;
		success = test4()&&success;
		success = test5()&&success;
		return success;
	}
	public static boolean runNewTests() {
		
		boolean successwr = true;
		
		System.out.println("Running front end tests (written by backend)");
		 
		successwr = testFE1();
		boolean temp =  testFE2();
		System.out.println("Testing for displaying added names in backend: "+successwr);
		System.out.println("Testing large number of names: "+temp);
		System.out.println("All additional Front end tests have passed: "+(successwr&&temp));
		successwr = testBE1();
		temp = testBE2();
		System.out.println("Searches for uppercase + lowercase: "+successwr);
		System.out.println("Searches for multiple word words+ duplicates:"+temp);
		
		return successwr;
	}
	public static void main(String[] args) {
		//only run NewTests(run All tests only works with placeholders)
		runAllTests();
		runNewTests();
		
	}
	
	
}
