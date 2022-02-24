import java.util.List;
import java.util.NoSuchElementException;

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
			test.addShow(new ShowPH("only on netflix",-1984,35));
			test.addShow(new ShowPH("netflix isn't chill",-2,44));
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
	public static boolean runAllTests() {
		boolean success = true;
		//unideal to run each test twice but i am too tired, and doesn't matter enough
		System.out.println("1:"+test1());
		System.out.println("2:"+test2());
		System.out.println("3:"+test3());
		System.out.println("4:"+test4());
		System.out.println("5:"+test5());
		success = test1()&&success;
		success = test2()&&success;
		success = test3()&&success;
		success = test4()&&success;
		success = test5()&&success;
		return success;
	}
	public static void main(String[] args) {
		System.out.println("All test passed? "+runAllTests());
	}
	
	
}
