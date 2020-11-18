package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import datamodel.Customer;
import datamodel.Order;

final class OutputProcessor implements Components.OutputProcessor {

	private InventoryManager inventoryManager;
	private OrderProcessor orderProcessor;

	/**
	 * Constructor.
	 * 
	 * @param inventoryManager the only instance of InventoryManager
	 * @param orderProcessor   the only instance of OrderProcessor
	 */
	OutputProcessor(InventoryManager inventoryManager, OrderProcessor orderProcessor) {
		this.inventoryManager = inventoryManager;
		this.orderProcessor = orderProcessor;
	}

	@Override
	public void printOrders(List<Order> orders, boolean printVAT) {
		StringBuffer sbAllOrders = new StringBuffer("-------------");
		StringBuffer sbLineItem = new StringBuffer();

		/*
		 * Insert code to print orders with all order items:
		 */
		long priceTotal = 0;
		long vatTotal = 0;

		for (int orderIndex = 0; orderIndex < orders.size(); orderIndex++) {

			Customer customer = orders.get(orderIndex).getCustomer();
			String customerName = splitName( customer, customer.getFirstName() + " " + customer.getLastName() );
			
			String orderString = "#" + orders.get(orderIndex).getId() + ", "
					+ customerName + "'s Bestellung: ";
			long fullPrice = 0;

			for (int itemIndex = 0; itemIndex < orders.get(orderIndex).getItems().size(); itemIndex++) {
				orderString += orders.get(orderIndex).getItems().get(itemIndex).getUnitsOrdered() + "x "
						+ orders.get(orderIndex).getItems().get(itemIndex).getDesciption();
				if (itemIndex != orders.get(orderIndex).getItems().size() - 1) {
					orderString += ", ";
				}

				fullPrice += orders.get(orderIndex).getItems().get(itemIndex).getArticle().getUnitPrice()
						* orders.get(orderIndex).getItems().get(itemIndex).getUnitsOrdered();
				vatTotal += orderProcessor.vat(orders.get(orderIndex).getItems().get(itemIndex).getArticle().getUnitPrice()
						* orders.get(orderIndex).getItems().get(itemIndex).getUnitsOrdered());
			}
			priceTotal += fullPrice;

			String fmtFullPrice = fmtPrice(fullPrice, "EUR");
			sbLineItem = fmtLine(orderString, fmtFullPrice, 95);

			sbAllOrders.append("\n");
			sbAllOrders.append(sbLineItem);
		}

		String fmtPriceTotal = pad(fmtPrice(priceTotal, "", " EUR"), 14, true);

		// append final line with totals
		sbAllOrders.append("\n").append(fmtLine("-------------", "------------- -------------", 95))
				.append("\n").append(fmtLine("Gesamtwert aller Bestellungen:", fmtPriceTotal, 95));
		if(printVAT) {
			String fmtVat = pad(fmtPrice(vatTotal, "", " EUR"), 14, true);
			sbAllOrders.append("\n").append(fmtLine("Im Gesamtwert enthaltene Mehrwertsteuer (19%):", fmtVat, 95));
		}

		// print sbAllOrders StringBuffer with all output to System.out
		System.out.println(sbAllOrders.toString());
	}

	@Override
	public void printInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public String fmtPrice(long price, String currency) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	private String fmtPrice(long price, String prefix, String postfix) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if (prefix != null) {
			fmtPriceSB.append(prefix);
		}

		fmtPriceSB = fmtPrice(fmtPriceSB, price);

		if (postfix != null) {
			fmtPriceSB.append(postfix);
		}
		return fmtPriceSB.toString();
	}

	private StringBuffer fmtPrice(StringBuffer sb, long price) {
		if (sb == null) {
			sb = new StringBuffer();
		}
		double dblPrice = ((double) price) / 100.0; // convert cent to Euro
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("de"))); // rounds double
																										// to 2 digits

		String fmtPrice = df.format(dblPrice); // convert result to String in DecimalFormat
		sb.append(fmtPrice);
		return sb;
	}

	private String pad(String str, int width, boolean rightAligned) {
		String fmtter = (rightAligned ? "%" : "%-") + width + "s";
		String padded = String.format(fmtter, str);
		return padded;
	}

	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer(leftStr);
		int shiftable = 0; // leading spaces before first digit
		for (int i = 1; rightStr.charAt(i) == ' ' && i < rightStr.length(); i++) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1; // - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max(0, excess - shiftable);
		if (shift2 > 0) {
			rightStr = rightStr.substring(shift2, rightStr.length());
			excess -= shift2;
		}
		if (excess > 0) {
			switch (excess) {
			case 1:
				sb.delete(sbLen - excess, sbLen);
				break;
			case 2:
				sb.delete(sbLen - excess - 2, sbLen);
				sb.append("..");
				break;
			default:
				sb.delete(sbLen - excess - 3, sbLen);
				sb.append("...");
				break;
			}
		}
		String strLineItem = String.format("%-" + (tab1 - 1) + "s%s", sb.toString(), rightStr);
		sb.setLength(0);
		sb.append(strLineItem);
		return sb;
	}

	@Override
	public String splitName(Customer customer, String name) {

		String[] nameParts;

		if (name.contains(",")) {
			nameParts = name.split(", ");
			customer.setFirstName(nameParts[1]);
			customer.setLastName(nameParts[0]);
		} else {
			nameParts = name.split(" ");
			String firstName = "";

			for (int index = 0; index < nameParts.length - 1; index++) {
				firstName += nameParts[index];
				if (index != nameParts.length - 2) {
					firstName += " ";
				}
			}
			customer.setFirstName(firstName);
			customer.setLastName(nameParts[nameParts.length - 1]);
		}
		return this.singleName(customer);
	}

	@Override
	public String singleName(Customer customer) {
		return customer.getLastName() + ", " + customer.getFirstName();
	}
}
