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
		return this.vat(grossValue, 1);
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		if(rateIndex == 1) {
			return Math.round(grossValue / 1.19 * 0.19);
		} else if(rateIndex == 2) {
			return Math.round(grossValue / 1.07 * 0.07);
		} else {
			return 0;
		}
	}
}
