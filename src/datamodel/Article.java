package datamodel;

public class Article {

	private final String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;
	
	protected Article(String id, String desc, long price, int units) {
		this.id = id;
		this.description = desc;
		this.unitPrice = price;
		this.unitsInStore = units;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String desc) {
		this.description = desc;
	}
	
	public long getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setUnitPrice(long price) {
		this.unitPrice = price;
	}
	
	public long getUnitsInStore() {
		return this.unitsInStore;
	}
	
	public void setUnitsInStore(int number) {
		this.unitsInStore = number;
	}
}
