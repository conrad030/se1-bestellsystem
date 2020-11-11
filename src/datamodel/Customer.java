package datamodel;

public class Customer {

	private String id;
	private String firstName;
	private String lastName;
	private String contact;
	
	protected Customer(String id, String name, String contact) {
		
		this.id = id;
		this.firstName = "";
		this.lastName = name;
		this.contact = contact;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String newName) {
		this.firstName = newName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String newName) {
		this.lastName = newName;
	}
	
//	public void setName(String name) {
//		
//		String[] nameParts;
//		
//		if (name.contains(",")) {
//			nameParts = name.split(", ");
//			this.firstName = nameParts[1];
//			this.lastName = nameParts[0];
//		} else {
//			nameParts = name.split(" ");
//			String firstName = "";
//			
//			for (int index = 0; index < nameParts.length - 1; index++) {
//				firstName += nameParts[index];
//				if (index != nameParts.length - 2) {
//					firstName += " ";
//				}
//			}
//			this.firstName = firstName;
//			this.lastName = nameParts[nameParts.length - 1];
//		}
//	}
//	
//	public String getContact() {
//		return this.contact;
//	}
//	
//	public void setContact(String newContact) {
//		this.contact = newContact;
//	}
}
