package fixtures;

public class Person {
	private String name;
	private Address address;

	public Person(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Person copy() {
		return new Person(name, address.copy());
	}

	public boolean equals(Person p) {
		return name.equals(p.getName()) && address.equals(p.getAddress());
	}
}
