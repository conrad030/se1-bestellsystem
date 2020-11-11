package datamodel;

public class OrderItem {

	private String description;
	private final Article article;
	private int unitsOrdered;
	
	protected OrderItem(String desc, Article article, int units) {
		this.description = desc;
		this.article = article;
		this.unitsOrdered = units;
	}
	
	public String getDesciption() {
		return this.description;
	}
	
	public void setDesciption(String desc) {
		this.description = desc;
	}
	
	public Article getArticle() {
		return this.article;
	}	
	
	public int getUnitsOrdered() {
		return this.unitsOrdered;
	}
	
	public void setUnitsOrdered(int number) {
		this.unitsOrdered = number;
	}
}
