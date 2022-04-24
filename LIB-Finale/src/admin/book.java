package admin;

public class book {
	private String isbn="N/A",totq="N/A",tak="N/A";
	private String nam="N/A",cat="N/A",auth="N/A";
	book(String isbn,String nam,String cat,String auth,String totq,String tak){
		this.isbn=isbn;
		this.nam=nam;
		this.cat=cat;
		this.auth=auth;
		this.totq=totq;
		this.tak=tak;
		
	}
	public String getIsbn(){return isbn;}
	public String getNam(){return nam;}
	public String getCat(){return cat;}
	public String getAuth(){return auth;}
	public String getTotq(){return totq;}
	public String getTak(){return tak;}
	
}
