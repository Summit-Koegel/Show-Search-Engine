public class Show implements IShow{
        protected String title;
        protected int year;
        protected int rating;
        protected String providers;

        public Show(String title, int year, int rating, String providers){
                this.title = title;
                this.year = year;
                this.rating = rating;
                this.providers = providers;
        }

        public String getTitle(){
                return title;
        }
        public int getYear(){
                return year;
        }
        public int getRating(){
                return rating;
        }
        public boolean isAvailableOn(String provider){
		provider = provider.toLowerCase();
                return providers.contains(provider);
        }

        @Override public int compareTo(IShow show){
                return rating - show.getRating();
	}
}
