package datamodel;

import java.util.*;

public class Order {

	private final long id;
	private final Date date;
	private final Customer customer;
	private final List<OrderItem> items;
	
	protected Order(long id, Date date, Customer customer) {
		this.id = id;
		if(date != null) {
			this.date = date;
		} else {
			this.date = new Date();
		}
		this.customer = customer;
		this.items = new ArrayList<>();
	}
	
	public long getId() {
		return this.id;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public List<OrderItem> getItems() {
		return this.items;
	}
	
	public int count() {
		return this.items.size();
	}
	
	public Order addItem(OrderItem item) {
		if(item != null && !this.items.contains(item)) {
			this.items.add(item);
		}
		return this;
	}
	
	public Order removeItem(OrderItem item) {
		this.items.remove(item);
		return this;
	}
	
	public Order clearItems() {
		this.items.clear();
		return this;
	}
}
