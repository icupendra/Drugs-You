package lk.techgays.drugsyou.item;

public class Item_Category {

 	private String CatId;
	private String CatName;
	private String SubCatStatus;
 
	public String getCatId() {
		return CatId;
	}
	public void setCatId(String catid) {
		this.CatId = catid;
	}
	public String getCatName() {
		return CatName;
	}
	public void setCatName(String catname) {
		this.CatName = catname;
	}

	public String getSubCatStatus() {
		return SubCatStatus;
	}
	public void setSubCatStatus(String subCatStatus) {
		this.SubCatStatus = subCatStatus;
	}
}