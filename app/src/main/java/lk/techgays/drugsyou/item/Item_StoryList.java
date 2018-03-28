package lk.techgays.drugsyou.item;

public class Item_StoryList {

	private String StoryId;
	private String StoryTitle;
 	private String StoryDes;
	private String CatId;
	private String SubCatId;


	public String getStoryId() {
		return StoryId;
	}
	public void setStoryId(String storyid) {
		this.StoryId = storyid;
	}
	public String getStoryTitle() {
		return StoryTitle;
	}
	public void setStoryTitle(String storytitle) {
		this.StoryTitle = storytitle;
	}
	 
	public String getStoryDes() {
		return StoryDes;
	}
	public void setStoryDes(String storydes) {
		this.StoryDes = storydes;
	}

	public String getCatId() {
		return CatId;
	}
	public void setCatId(String catId) {
		this.CatId = catId;
	}

	public String getSubCatId() {
		return SubCatId;
	}
	public void setSubCatId(String subCatId) {
		this.SubCatId = subCatId;
	}
}
