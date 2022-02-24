import java.util.List;

public class ShowSearcherBackend implements IShowSearcherBackend{
	
	private IHashTableSortedSets<String,IShow> showsByTitleWord;
	private IHashTableSortedSets<Integer,IShow> showsByYear;
	private int length = 0;
	private boolean[] provTog;//provider toggle
	private String[] prov;
	@Override
	public void addShow(IShow show) {
		
		showsByYear.add(show.getYear(), show);
		String full = show.getTitle();
		while(full.length()>0) {
			int space = full.indexOf(" ");
			if(space == -1) {
				showsByTitleWord.add(full, show);
				full = "";
			}else {
				showsByTitleWord.add(full.substring(0,space), show);
				if(full.length()>space+1)
					full = full.substring(space+1);//go again with next word
			}
		}
		showsByTitleWord.add(show.getTitle(), show);
		length++;
		
	}
	public ShowSearcherBackend() {//create show searcher
		showsByTitleWord = new HashTableSortedSetsPlacehold<String>();
		showsByYear = new HashTableSortedSetsPlacehold<Integer>();
		prov = new String[] {"Netflix", "Hulu", "Prime Video","Disney Plus"};
		provTog = new boolean[4];
	}

	@Override
	public int getNumberOfShows() {
		
		return length;
	}

	/**
	 * sets provider to true(include) or false(exclude)
	 */
	@Override
	public void setProviderFilter(String provider, boolean filter) {
		
		int ind = 0;
		boolean escape = true;
		for(ind = 0; ind< prov.length&&escape; ind++) {
			if(prov[ind].equals(provider)) {
				escape = false;
			}
		}
		if(escape) {//streaming service not in list
			return;
		}
		provTog[ind-1] = filter;
		
	}

	/**
	 * tells whether you include(true) or exclude(false) provider
	 */
	@Override
	public boolean getProviderFilter(String provider) {
		int ind = 0;
		boolean escape = true;
		for(ind = 0; ind< prov.length&&escape; ind++) {
			if(prov[ind].equals(provider)) {
				escape = false;
			}
		}
		if(escape) {//streaming service not in list
			return false;
		}
		return provTog[ind];
	}

	/**
	 * changes the filter value of provider
	 */
	@Override
	public void toggleProviderFilter(String provider) {
		int ind = 0;
		boolean escape = true;
		for(ind = 0; ind< prov.length&&escape; ind++) {
			if(prov[ind].equals(provider)) {
				escape = false;
			}
		}
		if(escape) {//streaming service not in list
			return;
		}
		provTog[ind] = !provTog[ind];
	}
	
	/**
	 * search for word with key word, ex "well written code"
	 * would be found with well, written, and code, but not ell
	 */
	@Override
	public List<IShow> searchByTitleWord(String word) {
		
		List<IShow> withWord= showsByTitleWord.get(word);
		filterOut(withWord);//good service
		sortByRating(withWord);//sort
		return withWord;
		
	}
	
	private void filterOut(List<IShow> toF) {//remove unowned streaming services
		
		for(int i = toF.size()-1; i>-1;i--) {//look through all make sure see if has service
			boolean avail = false;
			for(int j = 0; j < provTog.length&&!avail; j++) {
				avail = provTog[j]&&toF.get(i).isAvailableOn(prov[j]);
			}
			if(!avail) {
				toF.remove(i);
			}
			
		}
	}
	private void sortByRating(List<IShow> toS){//insertion sort
		//could make this radix sort
		for(int i = 1; i<toS.size();i++) {
			IShow cur = toS.get(i);
			int c = i-1;
			while(c>-1) {
				if(cur.getRating()>toS.get(c).getRating()) {
					toS.set(c+1,toS.get(c));
					toS.set(c, cur);
					c--;
					
				}else {
					
					c=-1;
				}
			}
		}
	}
	

	/**
	 * return all shows made in year
	 */
	@Override
	public List<IShow> searchByYear(int year) {
		List<IShow> withYear= showsByYear.get(year);
		filterOut(withYear);//good service
		sortByRating(withYear);//sort
		return withYear;
	}

}

