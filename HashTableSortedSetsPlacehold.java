import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class HashTableSortedSetsPlacehold<KeyType> implements IHashTableSortedSets<KeyType,IShow> {
		private int length;
		@Override
		public boolean put(KeyType key, List<IShow> value) {
			length++;
			return true;
		}

		@Override
		public List<IShow> get(KeyType key) throws NoSuchElementException {
			// TODO Auto-generated method stub
			if(key.equals(1932)) {
				ArrayList<IShow> test = new ArrayList<IShow>();
				test.add(new ShowPH("Best Movie ever",1932,0));
				for(int i = 0; i< 100; i++) {
					test.add(new ShowPH(""+i,1932,(int)(Math.random()*101)));
				}
				test.add(new ShowPH("Best Movie ever",1932,100));
				return test;
			}
			if(key.equals("Action")) {
				ArrayList<IShow> test = new ArrayList<IShow>();
				test.add(new ShowPH("Action",2032,0));
				for(int i = 0; i< 100; i++) {
					test.add(new ShowPH("Action",1900+i,(int)(Math.random()*101)));
				}
				test.add(new ShowPH("Action",2002,100));
				return test;
			}
			if(key.equals(2077)) {
				ArrayList<IShow> test = new ArrayList<IShow>();
				test.add(new ShowPH("skip this",2077,100));
				test.add(new ShowPH("now this",2077,3));
				test.add(new ShowPH("skipping stones may break",2077,22));
				return test;
			}
			if(key.equals("netflix")) {
				ArrayList<IShow> test = new ArrayList<IShow>();
				test.add(new ShowPH("only on netflix",-1984,35));
				test.add(new ShowPH("netflix isn't chill",-2,44));
				return test;
			}
			try {//I know this is gross, but is placeholder
				if(((String)key).indexOf("this")!=-1) {
					ArrayList<IShow> test = new ArrayList<IShow>();
					test.add(new ShowPH("skip this",2077,100));
					test.add(new ShowPH("now this",2077,3));
					test.add(new ShowPH("now this",1337,10));
					return test;
					
				}
			}catch(Exception e) {
				
			}
			return new ArrayList<IShow>();
		}

		@Override
		public int size() {
			// TODO Auto-generated method stub
			return length;
		}

		@Override
		public boolean containsKey(KeyType key) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public List<IShow> remove(KeyType key) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void clear() {
			// I don't use this
			
		}

		@Override
		public void add(KeyType key, IShow value) {
			length++;
			
		}



	}
