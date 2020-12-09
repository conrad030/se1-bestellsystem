package datamodel;

public class OrderItem {

	private String description;
	private final Article article;
	private int unitsOrdered;
	
	protected OrderItem(String desc, Article article, int units) {
		if(desc != null) {
			this.description = desc;
		} else {
			this.description = "";
		}
		this.article = article;
		if(units >= 0) {
			this.unitsOrdered = units;
		} else {
			this.unitsOrdered = 0;
		}
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String desc) {
		if(desc != null) {
			this.description = desc;
		} else {
			this.description = "";
		}
	}
	
	public Article getArticle() {
		return this.article;
	}	
	
	public int getUnitsOrdered() {
		return this.unitsOrdered;
	}
	
	public void setUnitsOrdered(int number) {
		if(number >= 0) {
			this.unitsOrdered = number;
		} else {
			this.unitsOrdered = 0;
		}
	}
}
