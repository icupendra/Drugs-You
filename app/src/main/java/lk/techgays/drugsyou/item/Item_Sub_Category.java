package lk.techgays.drugsyou.item;

public class Item_Sub_Category {

	private String CatId;
	private String SubCatId;
	private String SubCatName;
	private String SubCatStatus;


	public String getCatId() {
		return CatId;
	}
	public void setCatId(String catId) {
		this.CatId = catId;
	}
	public String getSubCatId() {
		return SubCatId;
	}
	public void setSubCatId(String subCatid) {
		this.SubCatId = subCatid;
	}


	public String getSubCatName() {
		return SubCatName;
	}
	public void setSubCatName(String subCatName) {
		this.SubCatName = subCatName;
	}

	public String getSubCatStatus() {
		return SubCatStatus;
	}
	public void setSubCatStatus(String subCatStatus) {
		this.SubCatStatus = subCatStatus;
	}

}
