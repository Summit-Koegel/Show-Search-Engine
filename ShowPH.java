public class ShowPH implements IShow{

		private int rate;
		private int yr;
		private String name;
		@Override
		public int compareTo(IShow arg0) {
			
			return rate-arg0.getRating();
		}

		@Override
		public String getTitle() {
			
			return name;
		}

		@Override
		public int getYear() {
			// TODO Auto-generated method stub
			return yr;
		}

		@Override
		public int getRating() {
			
			return rate;
		}

		@Override
		public boolean isAvailableOn(String provider) {
			if(name.length()>3) {
				if(name.substring(0,4).equals("skip")) {
					return false;
				}
			}
			if(yr  == 1337) {
				return false;
			}
			if(yr<0) {
				return provider.equals("Netflix");
			}
			return true;
		}
		public ShowPH(String title, int year, int rating) {
			name = title;
			yr = year;
			rate = rating;
		}
		
	}
