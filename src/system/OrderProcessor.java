package system;

import java.util.function.Consumer;

import datamodel.Order;
import datamodel.OrderItem;

final class OrderProcessor implements Components.OrderProcessor {

	private InventoryManager inventoryManager;
	
	/**
	 * Constructor.
	 * 
	 * @param inventoryManager the only instance of the InventoryManager
	 */
	OrderProcessor(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager;
	}
	
	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(Order order, Consumer<Order> acceptCode, Consumer<Order> rejectCode,
			Consumer<OrderItem> rejectedOrderItemCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue) {
		return grossValue / 100 * 16;
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		if(rateIndex == 1) {
			return this.vat(grossValue);
		} else if(rateIndex == 2) {
			return grossValue / 100 * 7;
		} else {
			return 0;
		}
	}
}
