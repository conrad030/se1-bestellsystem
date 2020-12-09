package datamodel;

public class Article {

	private final String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;
	
	protected Article(String id, String desc, long price, int units) {
		this.id = id;
		if(desc != null) {
			this.description = desc;
		} else {
			this.description = "";
		}
		if(price >= 0) {
			this.unitPrice = price;
		} else {
			this.unitPrice = 0;
		}
		if(units >= 0) {
			this.unitsInStore = units;
		} else {
			this.unitsInStore = 0;
		}
	}
	
	public String getId() {
		return this.id;
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
	
	public long getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setUnitPrice(long price) {
		if(price != Long.MAX_VALUE && price != Long.MIN_VALUE) {
			this.unitPrice = price;
		} else if(price == Long.MAX_VALUE) {
			this.unitPrice = 0;
		} else {
			this.unitPrice = -1;
		}
	}
	
	public long getUnitsInStore() {
		return this.unitsInStore;
	}
	
	public void setUnitsInStore(int number) {
		if(number != Integer.MAX_VALUE && number != Integer.MIN_VALUE) {
			this.unitsInStore = number;
		} else if(number == Integer.MAX_VALUE) {
			this.unitsInStore = 0;
		} else {
			this.unitsInStore = -1;
		}
	}
}
