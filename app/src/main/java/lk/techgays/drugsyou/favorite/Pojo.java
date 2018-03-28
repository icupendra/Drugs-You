package lk.techgays.drugsyou.favorite;

public class Pojo {

	private String SId;
	private String STitle;
	private String SDes;
	 
 	 
	public Pojo()
	{
		
	}
	
	public Pojo(String SId)
	{
		this.SId=SId;
	}
	
	public Pojo(String sid, String stitle, String sdes)
	{
		this.SId=sid;
		this.STitle=stitle;
		this.SDes=sdes;
 		 
	}
	
// --Commented out by Inspection START (2018-03-06 12:27 PM):
//	public int getId() {
//		return id;
//	}
// --Commented out by Inspection STOP (2018-03-06 12:27 PM)

	public void setId(int id) {
	}
	
	public String getSId() {
		return SId;
	}

	public void setSId(String sid) {
		this.SId = sid;
	}
	
	public String getSTitle() {
		return STitle;
	}

	public void setSTitle(String stitle) {
		this.STitle = stitle;
	}
	
	public String getSDes() {
		return SDes;
	}

	public void setSDes(String sdes) {
		this.SDes = sdes;
	}
	 

}
